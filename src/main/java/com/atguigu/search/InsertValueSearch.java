package com.atguigu.search;

import java.util.Arrays;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        int index = insertValueSearch(arr, 0, arr.length - 1, 1);
        System.out.println("index = " + index);
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param arr     查找的数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 需要查找的值
     * @return 如果找到就返回下标，没有找到就返回-1
     */
    //编写插值查找算法(数组是有序的)
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        //注意：findVal < arr[0]  和 findVal > arr[arr.length - 1]  必须要
        //否则，我们得到的mid可能越界       比如我们要找的值巨大  1000000000000
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        //求出mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal < midVal) {//向左递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else if (findVal > midVal) {//向右递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else {
            return mid;
        }

    }
}
