package gok.controller;

import gok.model.Enemy;

public class EnemyController implements Runnable {
    private Enemy enemy;

    public EnemyController(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //站立姿态
                switch (enemy.getStatus()) {
                    case 1: // 站立
                        enemy.setStatus(-2);
                        if (enemy.getMoving() > 3) {
                            enemy.setMoving(0);
                        }
                        enemy.setShowImage(enemy.getRightStandImages().get((int) enemy.getMoving() % enemy.getRightStandImages().size()));
                        enemy.setMoving(enemy.getMoving() + 0.2);
                        break;

                    case 2: // 跑
                        if (enemy.getMoving() > 9) {
                            enemy.setMoving(0);
                        }
                        enemy.setShowImage(enemy.getRightRunImages().get((int) enemy.getMoving() % enemy.getRightRunImages().size()));
                        enemy.setMoving(enemy.getMoving() + 0.5);
                        break;

//                        case 4: // 攻击
//                            if (enemy.getMoving() > 12) {
//                                enemy.setMoving(0);
//                            }
//                            enemy.setShowImage(enemy.getRightAttackImages().get((int) enemy.getMoving() % enemy.getRightAttackImages().size()));
//                            enemy.setMoving(enemy.getMoving() + 0.5);
//                            if (enemy.getMoving() == 12) {
//                                enemy.setStatus(enemy.getStatus() > 0 ? 1 : -1);
//                            }
//                            break;

                    case -1: // 左站立
                        enemy.setStatus(-2);
                        if (enemy.getMoving() > 3) {
                            enemy.setMoving(0);
                        }
                        enemy.setShowImage(enemy.getLeftStandImages().get((int) enemy.getMoving() % enemy.getLeftStandImages().size()));
                        enemy.setMoving(enemy.getMoving() + 0.2);
                        break;

                    case -2: // 左跑
                        if (enemy.getMoving() > 9) {
                            enemy.setMoving(0);
                        }
                        enemy.setShowImage(enemy.getLeftRunImages().get((int) enemy.getMoving() % enemy.getLeftRunImages().size()));
                        enemy.setMoving(enemy.getMoving() + 0.5);
                        break;

//                        case -4: // 左攻击
//                            if (enemy.getMoving() > 12) {
//                                enemy.setMoving(0);
//                            }
//                            enemy.setShowImage(enemy.getLeftAttackImages().get((int) enemy.getMoving() % enemy.getLeftAttackImages().size()));
//                            enemy.setMoving(enemy.getMoving() + 0.5);
//                            if (enemy.getMoving() == 12) {
//                                enemy.setStatus(enemy.getStatus() > 0 ? 1 : -1);
//                            }
//                            break;

                    default:
                        break;
                }
                enemy.moveXY();
                //敌人到边界
                if (enemy.getX() < -200) {
//                    enemy.setX(-200);
                    enemy.setStatus(2);//向右
                }
                if (enemy.getX() > 670) {
                    enemy.setStatus(-2);//向左
                }
                Thread.sleep(50);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
