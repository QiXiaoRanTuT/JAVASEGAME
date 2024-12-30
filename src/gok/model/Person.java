package gok.model;

import gok.Test;
import gok.util.StaticValue;

import java.awt.image.BufferedImage;
import java.util.List;

public class Person {
    private int x;
    private int y;
    private BufferedImage showImage;
    private Thread t;
    private double moving = 0;
    private int status = 1;
    private int jumpForce = 0;
    private int jumpMoveDirection = 0;

    private List<BufferedImage> leftStandImages;
    private List<BufferedImage> leftRunImages;
    private List<BufferedImage> leftJumpImages;
    private List<BufferedImage> leftAttackImages;
    private List<BufferedImage> rightStandImages;
    private List<BufferedImage> rightRunImages;
    private List<BufferedImage> rightJumpImages;
    private List<BufferedImage> rightAttackImages;

    private static BackGround backGround;//重要：不同线程想要共享同一个变量，需要加上static，否则会独立出两个场景空间

    public Person( int y, int x,BackGround backGround) {
        this.y = y;
        this.x = x;
        this.backGround=backGround;
//        setImageList();
//        Thread t = new Thread(this);
//        t.start();
    }
    /*
    * 死亡
    * */
    public void dead(){

    }
    /*
     * 图集的初始化
     * */
    public void setImageList() {
//        leftStandImages = StaticValue.leftPersonImgs.subList(0, 4);
//        leftRunImages = StaticValue.leftPersonImgs.subList(5, 9);
//        leftJumpImages = StaticValue.leftPersonImgs.subList(6, 7);
//        leftAttackImages = StaticValue.leftPersonImgs.subList(9, 12);
//
//        rightStandImages = StaticValue.rightPersonImgs.subList(0, 4);
//        rightRunImages = StaticValue.rightPersonImgs.subList(5, 9);
//        rightJumpImages = StaticValue.rightPersonImgs.subList(6, 7);
//        rightAttackImages = StaticValue.rightPersonImgs.subList(9, 12);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getShowImage() {
        return showImage;
    }

    public void setShowImage(BufferedImage showImage) {
        this.showImage = showImage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BackGround getBackGround() {
        return backGround;
    }

    public void setBackGround(BackGround backGround) {
        this.backGround = backGround;
    }

    public void moveXY(){}

    /*@Override
    public void run() {
        while (true) {
            try {
                switch (status) {
                    case 1:
                        if (this.moving > 3) {
                            this.moving = 0;
                        }
                        this.showImage = rightStandImages.get((int) moving % rightStandImages.size());
                        moving += 0.2;
                        break;

                    case 2:
                        if (this.moving > 9) {
                            this.moving = 0;
                        }
                        //索引超出界线，判断不大于List的长度即可
                        this.showImage = rightRunImages.get((int) moving % rightRunImages.size());
                        moving += 0.5;
                        break;
                    case 3:
                        this.moving = 0;
                        this.showImage = rightJumpImages.get((int) moving % rightJumpImages.size());
                        break;
                    case 4:
                        if (this.moving > 12) {
                            this.moving = 0;
                        }
                        this.showImage = rightAttackImages.get((int) moving % rightAttackImages.size());
                        moving += 0.5;
                        if (this.moving == 12) {
                            this.status = this.status > 0 ? 1 : -1;
                        }
                        break;
                    case -1:
                        if (this.moving > 3) {
                            this.moving = 0;
                        }
                        this.showImage = leftStandImages.get((int) moving % leftStandImages.size());
                        moving += 0.2;
                        break;
                    case -2:
                        if (this.moving > 9) {
                            this.moving = 0;
                        }
                        this.showImage = leftRunImages.get((int) moving % leftRunImages.size());
                        moving += 0.5;
                        break;
                    case -3:
                        this.moving = 0;
                        this.showImage = leftJumpImages.get((int) moving % leftJumpImages.size());
                        break;
                    case -4:
                        if (this.moving > 12) {
                            this.moving = 0;
                        }
                        this.showImage = leftAttackImages.get((int) moving % leftAttackImages.size());
                        moving += 0.5;
                        if (this.moving == 12) {
                            this.status = this.status > 0 ? 1 : -1;
                        }
                        break;
                    default:
                        break;
                }
                moveXY();
                if (x < -100) {
                    x = 0;
                    Test.isOk = true;
                }
                if (x > 750) {
                    x = 0;
                    Test.isOk = true;
                }

                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void moveXY() {
        switch (status) {
            case 1:
            case -1:
                break;
            case 2:
                x += 10;
                break;
            case -2:
                x -= 10;
                break;
            case 3:
            case -3:
                if (jumpMoveDirection > 0) {
                    x += 10;
                } else if (jumpMoveDirection < 0) {
                    x -= 10;
                }
                y -= jumpForce;
                if (y > 350) {
                    y = 305;
                    if (this.jumpMoveDirection > 0) {
                        this.status = 2;
                    } else if (this.jumpMoveDirection < 0) {
                        this.status = -2;
                    } else {
                        if (this.status > 0) {
                            this.status = 1;
                        } else {
                            this.status = -1;
                        }
                    }
                }
                jumpForce--;
                break;
            //攻击
            case 4:
            case -4:
                break;
        }
    }

    public void leftRun() {
        if (this.status == 3 || this.status == -3) {
            return;
        }
        this.status = -2;
    }

    public void rightRun() {
        if (this.status == 3 || this.status == -3) {
            return;
        }
        this.status = 2;
    }

    public void stopLeftRun() {
        if (this.status == 3 || this.status == -3) {
            this.jumpMoveDirection = 0;
            return;
        }
        this.status = -1;
    }

    public void stopRightRun() {
        if (this.status == 3 || this.status == -3) {
            this.jumpMoveDirection = 0;
            return;
        }
        this.status = 1;
    }

    public void jump() {
        if (this.status != 3 && this.status != -3) {
            if (this.status == 2 || this.status == -2) {
                this.jumpMoveDirection = this.status > 0 ? 1 : -1;
            }
            this.status = this.status > 0 ? 3 : -3;
            this.jumpForce = 15;
        }
    }*/

    public List<BufferedImage> getLeftStandImages() {
        return leftStandImages;
    }

    public void setLeftStandImages(List<BufferedImage> leftStandImages) {
        this.leftStandImages = leftStandImages;
    }

    public List<BufferedImage> getLeftRunImages() {
        return leftRunImages;
    }

    public void setLeftRunImages(List<BufferedImage> leftRunImages) {
        this.leftRunImages = leftRunImages;
    }

    public List<BufferedImage> getLeftJumpImages() {
        return leftJumpImages;
    }

    public void setLeftJumpImages(List<BufferedImage> leftJumpImages) {
        this.leftJumpImages = leftJumpImages;
    }

    public List<BufferedImage> getLeftAttackImages() {
        return leftAttackImages;
    }

    public void setLeftAttackImages(List<BufferedImage> leftAttackImages) {
        this.leftAttackImages = leftAttackImages;
    }

    public List<BufferedImage> getRightStandImages() {
        return rightStandImages;
    }

    public void setRightStandImages(List<BufferedImage> rightStandImages) {
        this.rightStandImages = rightStandImages;
    }

    public List<BufferedImage> getRightRunImages() {
        return rightRunImages;
    }

    public void setRightRunImages(List<BufferedImage> rightRunImages) {
        this.rightRunImages = rightRunImages;
    }

    public List<BufferedImage> getRightJumpImages() {
        return rightJumpImages;
    }

    public void setRightJumpImages(List<BufferedImage> rightJumpImages) {
        this.rightJumpImages = rightJumpImages;
    }

    public List<BufferedImage> getRightAttackImages() {
        return rightAttackImages;
    }

    public void setRightAttackImages(List<BufferedImage> rightAttackImages) {
        this.rightAttackImages = rightAttackImages;
    }

    public double getMoving() {
        return moving;
    }

    public void setMoving(double moving) {
        this.moving = moving;
    }

    public int getJumpForce() {
        return jumpForce;
    }

    public void setJumpForce(int jumpForce) {
        this.jumpForce = jumpForce;
    }

    public int getJumpMoveDirection() {
        return jumpMoveDirection;
    }

    public void setJumpMoveDirection(int jumpMoveDirection) {
        this.jumpMoveDirection = jumpMoveDirection;
    }
}
