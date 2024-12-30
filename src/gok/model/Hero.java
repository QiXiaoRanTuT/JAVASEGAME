package gok.model;

import gok.Test;
import gok.controller.HeroController;
import gok.util.StaticValue;

import java.awt.image.BufferedImage;
import java.util.List;

public class Hero extends Person {
    private Thread controllerThread;
    public static long systemTime=System.currentTimeMillis();   //系统时间
    private int score=0;    //得分

    public Hero(int y, int x, BackGround backGround) {
        super(y, x, backGround);
        this.setStatus(-1);
        this.score=0;

        //打开线程
        controllerThread = new Thread(new HeroController(this));
        controllerThread.start();
        setImageList();
    }

    @Override
    public void setImageList() {
        setLeftStandImages(StaticValue.leftPersonImgs.subList(0, 4));
        setLeftRunImages(StaticValue.leftPersonImgs.subList(5, 9));
        setLeftJumpImages(StaticValue.leftPersonImgs.subList(6, 7));
        setLeftAttackImages(StaticValue.leftPersonImgs.subList(9, 12));

        setRightStandImages(StaticValue.rightPersonImgs.subList(0, 4));
        setRightRunImages(StaticValue.rightPersonImgs.subList(5, 9));
        setRightJumpImages(StaticValue.rightPersonImgs.subList(6, 7));
        setRightAttackImages(StaticValue.rightPersonImgs.subList(9, 12));
    }

    @Override
    public BufferedImage getShowImage() {
        if (super.getShowImage() == null) {
            super.setShowImage(StaticValue.leftPersonImgs.get(0));
        }
        return super.getShowImage();
    }

    @Override
    public void moveXY() {
        switch (getStatus()) {
            case 1:
            case -1:
                break;
            case 2:
                setX(getX() + 10);
                break;
            case -2:
                setX(getX() - 10);
                break;
            case 3:
            case -3:
                if (getJumpMoveDirection() > 0) {
                    setX(getX() + 10);
                } else if (getJumpMoveDirection() < 0) {
                    setX(getX() - 10);
                }
                setY(getY() - getJumpForce());
                if (getY() > 350) {
                    setY(305);//最低高度
                    if (getJumpMoveDirection() > 0) {
                        setStatus(2);
                    } else if (getJumpMoveDirection() < 0) {
                        setStatus(-2);
                    } else {
                        if (getStatus() > 0) {
                            setStatus(1);
                        } else {
                            setStatus(-1);
                        }
                    }
                }
                setJumpForce(getJumpForce() - 1);
                break;
            //攻击
            case 4:
            case -4:
                if (getBackGround() != null) {
                    List<Enemy> allEnemyList = getBackGround().getAllEnemyList();
                    for (int i = 0; i < allEnemyList.size(); i++) {
//                    for (Enemy enemy:allEnemyList){   //无法在线程内部使用迭代器
                        Enemy enemy = allEnemyList.get(i);
                        //判断  英雄右边的坐标大于敌人左边的坐标  并且  英雄左边的坐标小于敌人右边的坐标
                        if (((this.getX() + 150) > enemy.getX()) && (this.getX() < (enemy.getX() + 250))) {
//                            this.getBackGround().getAllEnemyList().remove(enemy);
                            this.score+=10;
                            enemy.dead();

                        }
                    }
                }
                break;
        }
    }

    public void leftRun() {
        if (getStatus() == 3 || getStatus() == -3) {
            return;
        }
        setStatus(-2);
    }

    public void rightRun() {
        if (getStatus() == 3 || getStatus() == -3) {
            return;
        }
        setStatus(2);
    }

    public void stopLeftRun() {
        if (getStatus() == 3 || getStatus() == -3) {
            setJumpMoveDirection(0);
            return;
        }
        setStatus(-1);
    }

    public void stopRightRun() {
        if (getStatus() == 3 || getStatus() == -3) {
            setJumpMoveDirection(0);
            return;
        }
        setStatus(1);
    }

    public void jump() {
        if (getStatus() != 3 && getStatus() != -3) {
            if (getStatus() == 2 || getStatus() == -2) {
                setJumpMoveDirection(getStatus() > 0 ? 1 : -1);
            }
            setStatus(getStatus() > 0 ? 3 : -3);
            setJumpForce(15);
        }
    }

    //攻击方法
    public void attack() {
        if (getStatus() != -3 && getStatus() != 3) {
            if (getStatus() > 0) {
                setStatus(4);
            } else {
                setStatus(-4);
            }
        }
    }

    public static long getSystemTime() {
        return systemTime;
    }

    public static void setSystemTime(long systemTime) {
        Hero.systemTime = systemTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
