package com.bianjiang.tank;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            frame.tanks.add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, frame));
        }

        while (true) {
            Thread.sleep(50);
            frame.repaint();
        }

    }


}
