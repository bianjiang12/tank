package com.bianjiang.tank;

import java.awt.*;
import java.util.Random;

//封装坦克属性成一个坦克的对象
public class Tank {

    private int x, y;

    private Dir dir;

    private static final int SPEED = 5;

    public static final int WIDTH = ResourcesMgr.goodTankD.getWidth();

    public static final int HEIGHT = ResourcesMgr.goodTankD.getHeight();

    private Random random = new Random();

    private boolean moving = true;

    private boolean living = true;

    private Group group = Group.BAD;

    private TankFrame tf = null;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        //如果坦克死亡的话，移除list里面的坦克
        if (!living) tf.tanks.remove(this);
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourcesMgr.goodTankL : ResourcesMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourcesMgr.goodTankU : ResourcesMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourcesMgr.goodTankR : ResourcesMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourcesMgr.goodTankD : ResourcesMgr.badTankD, x, y, null);
                break;
        }
        move();
    }

    //坦克移动方法
    private void move() {
        if (!moving) return;

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();

            //给敌方坦克添加随机方向移动
            // 如果想让地方坦克慢点改变方向，可以把 && random.nextInt(100) > 95 添加到this.group == Group.BAD后面
            if (this.group == Group.BAD) {
                randomDir();
            }
        }
        //边界检测判断
        boundsCheck();
    }

    //边界检测判断方法
    private void boundsCheck() {
        if (this.x < 2) {
            x = 2;
        }
        if (this.y < 28) {
            y = 28;
        }
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) {
            x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        }
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) {
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
        }
    }

    //敌方坦克随机方向移动方法
    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bX, bY, this.dir, this.group, this.tf));
        new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }

    //坦克死亡
    public void die() {
        this.living = false;
    }
}
