package gok.model;

import gok.util.StaticValue;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/*
 * 游戏场景类
 * */
public class BackGround {
    //存储当前游戏场景
    private BufferedImage showBgImage = null;
    //存储当前图片的序号
    private int sort;
    //存储下一章图片的序号
    private int nextSort;

    public List<Enemy> getAllEnemyList() {
        return allEnemyList;
    }

    public void setAllEnemyList(List<Enemy> allEnemyList) {
        this.allEnemyList = allEnemyList;
    }

    //当前场景的敌人
    private List<Enemy> allEnemyList = new ArrayList<Enemy>();

    //英雄

    public BackGround(int sort) {
        this.sort = sort;
        create();
    }

    //根据方法自动初始化场景内容
    public void create() {
        if (sort == 1) {
            showBgImage = StaticValue.bg01;
            nextSort = 2;
            //生成敌人
            this.allEnemyList.add(new Enemy(150, 400, this));
            this.allEnemyList.add(new Enemy(150, 200, this));

        } else if (sort == 2) {
            showBgImage = StaticValue.bg02;
            nextSort = 3;

            this.allEnemyList.add(new Enemy(150, 400, this));
            this.allEnemyList.add(new Enemy(150, 200, this));
            this.allEnemyList.add(new Enemy(150, 300, this));
        } else if (sort == 3) {
            showBgImage = StaticValue.bg03;
            nextSort = 4;

            this.allEnemyList.add(new Enemy(150, 400, this));
            this.allEnemyList.add(new Enemy(150, 300, this));
            this.allEnemyList.add(new Enemy(150, 200, this));
            this.allEnemyList.add(new Enemy(150, 300, this));
        } else if (sort == 4) {
            showBgImage = StaticValue.bg04;
            nextSort = 1;

            this.allEnemyList.add(new Enemy(150, 400, this));
            this.allEnemyList.add(new Enemy(150, 450, this));
            this.allEnemyList.add(new Enemy(150, 300, this));
            this.allEnemyList.add(new Enemy(150, 350, this));

        }

    }

    //是否需要切换到下一个场景
    public boolean hasNext() {
        return nextSort != 0;
    }

    //创建下一个场景
    public BackGround next() {
        return new BackGround(nextSort);
    }

    public BufferedImage getShowBgImage() {
        return showBgImage;
    }

    public void setShowBgImage(BufferedImage showBgImage) {
        this.showBgImage = showBgImage;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getNextSort() {
        return nextSort;
    }

    public void setNextSort(int nextSort) {
        this.nextSort = nextSort;
    }
}
