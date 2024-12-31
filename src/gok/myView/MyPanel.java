package gok.myView;

import gok.Test;
import gok.dao.GameDao;
import gok.dao.impl.GameDaoImpl;
import gok.frame.LoginFrame;
import gok.model.BackGround;
import gok.model.Enemy;
import gok.model.Game;
import gok.model.Hero;
import gok.util.NumberUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class MyPanel extends JPanel implements Runnable {
    private BackGround backGround;  //定义当前场景



    public MyPanel(BackGround backGround) {
        this.backGround = backGround;

        //启动线程
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void paint(Graphics g) {
        /*
         * 设置画布对象
         * */
        //1.找到参数对象
        BufferedImage image = new BufferedImage(900, 600, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.drawImage(getBackGround().getShowBgImage(), 0, 0, this);

        //画英雄
//        graphics.drawImage(MyGameFrame.person.getShowImage(),
//                MyGameFrame.person.getX(),
//                MyGameFrame.person.getY(), this);
        graphics.drawImage(MyGameFrame.hero.getShowImage(),
                MyGameFrame.hero.getX(),
                MyGameFrame.hero.getY(), this);

        //画敌人
        List<Enemy> allEnemyList = getBackGround().getAllEnemyList();
        for (Enemy enemy : allEnemyList) {
            graphics.drawImage(enemy.getShowImage(),
                    enemy.getX(),
                    enemy.getY(), this);
        }

        //画分数
        if (MyGameFrame.hero.getScore() >= 0) {
            graphics.drawImage(NumberUtils.getNumberImage(MyGameFrame.hero.getScore() + ""
                    , this), 400, 60, this);
        }

        //画时间
        long nowTime = System.currentTimeMillis();
        long time = nowTime - Hero.systemTime;
        graphics.drawString("游戏时间："
                        + (time / 1000 / 60 / 24) + " 时 "
                        + (time / 1000 / 60) + " 分 "
                        + (time / 1000 % 60) + " 秒"
                , 600, 60);

        //开始画图
        g.drawImage(image, 0, 0, this);
        //判断是否要切换场景
        if (Test.isOk) {
            backGround = backGround.next();
            Test.isOk = false;
        }

        //当用户数据大于旧数据时更新数据,避免数据库访问量过大
        if (LoginFrame. zsScore < MyGameFrame.hero.getScore() ){
            Game game = new Game();
//            game.setId(LoginFrame.userid);
            game.setScore(MyGameFrame.hero.getScore());
            game.setGametime((time / 1000 / 60 / 24) + " 时 "
                    + (time / 1000 / 60) + " 分 "
                    + (time / 1000 % 60) + " 秒");

            game.setGameid(LoginFrame.gameid);
            GameDaoImpl gameDaoImpl = new GameDaoImpl();
            LoginFrame.zsScore=MyGameFrame.hero.getScore();
            try {
                gameDaoImpl.updateGame(game);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public BackGround getBackGround() {
        return backGround;
    }

    public void setBackGround(BackGround backGround) {
        this.backGround = backGround;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.repaint();//重新画场景
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
