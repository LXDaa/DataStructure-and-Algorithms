package com.atguigu.Algorithm;

//背包问题    KnapsackProblem
public class Dynamic {
    public static void main(String[] args) {
        int[] weight = {1, 4, 3};  //物品的重量
        int[] value = {1500, 3000, 2000};//物品的价值
        int capacity = 4;//背包的容量
        int n = value.length;//物品的个数


        //创建二维数组
        // maxValue[i][j]   表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] maxValue = new int[n + 1][capacity + 1];    //行是物品信息，列是背包重量
        //为了记录放入商品的情况，我们定义一个二维数组
        int[][] path = new int[n + 1][capacity + 1];


        //初始化第一行和第一列
        for (int i = 0; i < maxValue.length; i++) {     //maxValue.length二维数组的行数
            maxValue[i][0] = 0;   //将第一列设置为0
        }
        for (int i = 0; i < maxValue[0].length; i++) {    //maxValue[0].length二维数组的列数
            maxValue[0][i] = 0;  //将第一行设置为0
        }

        //动态规划处理
        for (int i = 1; i < maxValue.length; i++) {    //不处理第一行
            for (int j = 1; j < maxValue[0].length; j++) {         //不处理第一列
                if (weight[i - 1] > j) {     //因为程序是从i=1开始的，所以修改为weight[i-1]
                    maxValue[i][j] = maxValue[i - 1][j];
                } else {
                    //因为程序是从i=1开始的，所以修改为value[i-1]       weight[i-1]      在剩余重量为[j - weight[i-1]]的情况下，找放其他物品的最优解
                    //maxValue[i][j] = Math.max(maxValue[i - 1][j], value[i-1] + maxValue[i - 1][j - weight[i-1]]);
                    if (maxValue[i - 1][j] < value[i - 1] + maxValue[i - 1][j - weight[i - 1]]) {
                        maxValue[i][j] = value[i - 1] + maxValue[i - 1][j - weight[i - 1]];
                        //只有这里是当前情况下最优的解  ，其他的只是用了上一种情况最优解的值
                        path[i][j] = 1;
                    } else {
                        maxValue[i][j] = maxValue[i - 1][j];
                        //这里的最优前面已经记录过了
                    }
                }
            }
        }

        //输出二维数组
        for (int i = 0; i < maxValue.length; i++) {
            for (int j = 0; j < maxValue[i].length; j++) {
                System.out.print(maxValue[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
        //输出最后我们放入的是那些商品
        //这样输出会把所有的情况都得到
        //比如  能放入的商品为1时，背包重量为 j  怎么放最优的情况
        //     能放入的商品为1、2时，背包重量为 j  怎么放最优的情况
        //              。。。。。。。。。。。。。。。
        //而我们只需要最后的放入情况!!!!!!!
/*        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                if (path[i][j] == 1) {
                    System.out.println("第" + i + "个商品放入到背包");
                    System.out.print(path[i][j] + " ");
                }
            }
            System.out.println();
        }*/

        //
        int i = path.length - 1;     //行的最大下标
        int j = path[0].length - 1;       //列的最大下标
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("第" + i + "个商品放入到背包");
                //还剩余的容量
                j -= weight[i - 1];
            }
            //在它的上一行找容量为j的最优解
            i--;
        }


    }


}
