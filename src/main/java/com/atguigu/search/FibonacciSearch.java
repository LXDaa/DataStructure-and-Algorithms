package com.atguigu.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("index=" + fibSearch(arr, 1234));
    }

    //因为后面我们的公式mid=low+F(k-1)-1会用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
    //非递归方法得到斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * @param arr
     * @param findVal 需要查找的值
     * @return 返回对应下标，没有返回-1
     */
    //编写斐波那契查找算法
    //使用非递归的方式编写算法
    public static int fibSearch(int[] arr, int findVal) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放mid值
        int[] f = fib();//获取到斐波那契数列
        //获取到斐波那契分割数值的下标
        //只要顺序表的长度为F[k]-1，则可以将该表分成长度为F[k-1]-1和F[k-2]-1的两段
        //刚开始k是0
        //第k个斐波那契数的值是等于或者将将正好大于要查找数组的长度
        //顺序表长度n不一定刚好等于F[k]-1，所以需要将原来的顺序表长度n增加至F[k]-1
        //获取斐波那契数列中等于或第一个大于数组长度的数
        //这里的f[k]  -  1是因为high是表示的下标，斐波那契数列中的每一项-1，就代表原数列的下标，因为原数列的下标是从0开始的
        while (f[k] - 1 < high) {
            k++;
        }
        //因为f[k]可能大于数组的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp
        //也就是让这个数组满足成为斐波那契数列的条件
        //此时不足的部分会使用0填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        //实际上需求使用arr数组最后的数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //使用while循环来处理，找到我们的数findVal
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (findVal < temp[mid]) {//应该向数组的左边继续查找
                high = mid - 1;
                //
                k--;  //而f[k - 1]代表左部分
            } else if (findVal > temp[mid]) {//应该向数组的右边继续查找
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;


    }
}
