package com.atguigu.Algorithm;

//分治算法
public class Divide_and_Conquer {
    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');

    }

    //汉诺塔的移动方案
    //使用分治算法
    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            //如果num>2，我们总是看为两个盘，最下面的盘和它上面的所有盘
            //1、先把最上面的所有盘移动到b   移动过程使用到c
            hanoiTower(num - 1, a, c, b);
            //2、将最下面的盘移动到c
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            //3、将b上的所有盘移动到c盘     借助a
            hanoiTower(num - 1, b, a, c);
        }
    }
}
