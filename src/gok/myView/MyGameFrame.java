package gok.myView;

import gok.action.MyListener;
import gok.frame.FirstPageFrame;
import gok.model.BackGround;
import gok.model.Hero;
import gok.model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyGameFrame extends JFrame implements KeyListener {
    //英雄类
//    public static Person person = new Person(300, 0, new BackGround(1));
    public static Hero hero = new Hero(300, 0, new BackGround(1));
    public MyGameFrame(){

        //窗体标题
        this.setTitle("游戏界面");
        //窗体大小
        this.setSize(900, 600);
        //设置窗体居中
        //Toolkit.getDefaultToolkit().getScreenSize()获取显示器屏幕大小
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((width-900)/2, (height-600)/2);//设置窗体位置

//        JButton button = new JButton("你好");
//        button.addActionListener(new MyListener());

        //定义画背景的画板对象
        BackGround backGround = new BackGround(1);  //设置当前场景
        MyPanel myPanel = new MyPanel(backGround);
//        myPanel.add(button);    //将按钮添加到画布里面
        this.add(myPanel);  //将画板添加到窗体

        this.addKeyListener(this);

        //设置关闭按钮
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体关闭方式:直接关闭应用程序
        //设置可见
        this.setVisible(true);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code==68){  //按下d键
            hero.rightRun();
        }
        if (code==65){  //按下a键
            hero.leftRun();
        }
        if (code==75){  //k跳跃
            hero.jump();
        }
        if (code==74){  //j攻击
            hero.attack();

        }
        //暂停与继续功能
        if (code==89){//按y键
            System.out.println("继续");
//            frame.MyFrame.ifwait=true;//线程继续
//            TestApp.mygame.setVisible(true);//打开游戏窗体
            this.setVisible(false);//隐藏当前窗体
        }
        if (code==78){//按n键
            System.out.println("退出");
            new FirstPageFrame();
            this.setVisible(false);//隐藏当前窗体
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code==68){
            hero.stopRightRun();
        }
        if (code==65){
            hero.stopLeftRun();
        }
    }
}
