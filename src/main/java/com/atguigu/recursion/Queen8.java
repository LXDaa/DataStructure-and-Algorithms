package com.atguigu.recursion;

public class Queen8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组，存入结果{0,4,75,2,6,1,3}
    //表示皇后所在的位置,数组下标+1，代表第几个皇后,同时表示皇后第几行
    //                数组下标对应的值+1，表示皇后所在的列数
    //array[i]=j;  表示第i+1个皇后,在第i+1行第j+1列
    int[] array = new int[max];


    public static void main(String[] args) {
        //测试
        Queen8 queen8 = new Queen8();
        queen8.check(0);



    }
    //编写一个方法放置皇后
    private void check(int n){
        if (n==max){
            //8个皇后已经放好了
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前皇后放到当前行的第一列
            array[n]=i;
            //放置后判断是否冲突
            if (judge(n)) {
                //不冲突，接着放下一个皇后，开始递归
                check(n+1);
            }
            //如果冲突继续执行array[n]=i;放到当前列的下一列
        }
    }

    //检测每当放置第n个皇后时判断与之前的皇后是否冲突
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //array[i] == array[n] 表示当前皇后与之前的皇后在同一列
            //Math.abs(n - i) == Math.abs(array[n] - array[i]  表示当前皇后与之前的皇后在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return false;
            }

        }
        return true;
    }

    //写一个方法，将皇后摆放的位置输出
    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
