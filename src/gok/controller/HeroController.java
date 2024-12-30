package gok.controller;

import gok.Test;
import gok.model.Hero;
/*
* 角色控制器，动态图
* */
public class HeroController implements Runnable{
    private Hero hero;

    public HeroController(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void run() {
        while (true) {
            try {
                switch (hero.getStatus()) {
                    case 1: // 站立
                        if (hero.getMoving() > 3) {
                            hero.setMoving(0);
                        }
                        hero.setShowImage(hero.getRightStandImages().get((int) hero.getMoving() % hero.getRightStandImages().size()));
                        hero.setMoving(hero.getMoving() + 0.2);
                        break;

                    case 2: // 跑
                        if (hero.getMoving() > 9) {
                            hero.setMoving(0);
                        }
                        hero.setShowImage(hero.getRightRunImages().get((int) hero.getMoving() % hero.getRightRunImages().size()));
                        hero.setMoving(hero.getMoving() + 0.5);
                        break;

                    case 3: // 跳
                        hero.setMoving(0);
                        hero.setShowImage(hero.getRightJumpImages().get((int) hero.getMoving() % hero.getRightJumpImages().size()));
                        break;

                    case 4: // 攻击
                        if (hero.getMoving() > 12) {
                            hero.setMoving(0);
                        }
                        hero.setShowImage(hero.getRightAttackImages().get((int) hero.getMoving() % hero.getRightAttackImages().size()));
                        hero.setMoving(hero.getMoving() + 0.5);
                        if (hero.getMoving() == 12) {
                            hero.setStatus(hero.getStatus() > 0 ? 1 : -1);
                        }
                        break;

                    case -1: // 左站立
                        if (hero.getMoving() > 3) {
                            hero.setMoving(0);
                        }
                        hero.setShowImage(hero.getLeftStandImages().get((int) hero.getMoving() % hero.getLeftStandImages().size()));
                        hero.setMoving(hero.getMoving() + 0.2);
                        break;

                    case -2: // 左跑
                        if (hero.getMoving() > 9) {
                            hero.setMoving(0);
                        }
                        hero.setShowImage(hero.getLeftRunImages().get((int) hero.getMoving() % hero.getLeftRunImages().size()));
                        hero.setMoving(hero.getMoving() + 0.5);
                        break;

                    case -3: // 左跳
                        hero.setMoving(0);
                        hero.setShowImage(hero.getLeftJumpImages().get((int) hero.getMoving() % hero.getLeftJumpImages().size()));
                        break;

                    case -4: // 左攻击
                        if (hero.getMoving() > 12) {
                            hero.setMoving(0);
                        }
                        hero.setShowImage(hero.getLeftAttackImages().get((int) hero.getMoving() % hero.getLeftAttackImages().size()));
                        hero.setMoving(hero.getMoving() + 0.5);
                        if (hero.getMoving() == 12) {
                            hero.setStatus(hero.getStatus() > 0 ? 1 : -1);
                        }
                        break;

                    default:
                        break;
                }
                hero.moveXY();
                if (hero.getX()< -100) {
                    hero.setX(0);
                    Test.isOk = true;
                }
                if (hero.getX() > 750) {
                    hero.setX(0);
                    Test.isOk = true;
                }

                Thread.sleep(40);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
