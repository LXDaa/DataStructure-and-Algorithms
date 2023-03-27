package com.atguigu.recursion;

public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "   ");
            }
            System.out.println();
        }
        //使用递归回溯来找路
        setWay(map,1,1);
        //走过的路
        System.out.println("走过的路");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "   ");
            }
            System.out.println();
        }

    }



    /**
     * @param map
     * @param i   起始位置
     * @param j
     * @return 起始位置（1，1），如果小球能到（6，5），则找到路
     * 地图中的0代表没有走过，1代表墙，2表示可以走通，3表示位置已经走过但是不通
     * 下-右-上-左 ，走不通在回溯
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { //通路已将找到
            return true;
        } else {
            if (map[i][j] == 0) {
                //还没走过，可按策略走一次
                map[i][j] = 2;//假定可以走通
                if (setWay(map, i + 1, j)) {  //下
                    return true;
                } else if (setWay(map, i, j + 1)) {  //右
                    return true;
                } else if (setWay(map, i - 1, j)) {  //上
                    return true;
                } else if (setWay(map, i, j - 1)) {   //左
                    return true;
                } else {
                    //走不通是死路
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }

    }
}
