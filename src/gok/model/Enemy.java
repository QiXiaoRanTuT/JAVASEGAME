package gok.model;

import gok.controller.EnemyController;
import gok.util.StaticValue;

import java.awt.image.BufferedImage;

public class Enemy extends Person {
    private Thread controllerThread;

    public Enemy(int y, int x, BackGround backGround) {

        super(y, x, backGround);
        this.setStatus(-1);

        //打开线程
        controllerThread = new Thread(new EnemyController(this));
        controllerThread.start();
        setImageList();
    }

    @Override
    public BufferedImage getShowImage() {
        if (super.getShowImage() == null) {
            super.setShowImage(StaticValue.leftEnemyImgs.get(0));
        }
        return super.getShowImage();
    }

    /*
     * 重写敌人的图集
     * */
//    @Override
    public void setImageList() {
        setLeftStandImages(StaticValue.leftEnemyImgs.subList(14, 20));
        setLeftRunImages(StaticValue.leftEnemyImgs.subList(0, 6));
        setLeftAttackImages(StaticValue.leftEnemyImgs.subList(6, 14));

        setRightStandImages(StaticValue.rightEnemyImgs.subList(14, 20));
        setRightRunImages(StaticValue.rightEnemyImgs.subList(0, 6));
        setRightAttackImages(StaticValue.rightEnemyImgs.subList(6, 14));
    }

    /*
     * 敌人移动
     * */
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
        }
    }

    /*
     * 敌人死亡方法
     * */
    @Override
    public void dead() {
        this.getBackGround().getAllEnemyList().remove(this);
        System.out.println("敌人死亡");
    }
}
