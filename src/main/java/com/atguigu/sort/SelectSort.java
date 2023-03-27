package com.atguigu.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        selectSort(arr);

    }

    //选择排序
    public static void selectSort(int[] arr) {
        //根据推导，发现了规律
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];//假定的最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {  //假定的最小值不是最小值
                    minIndex = j;
                    min = arr[j];
                }
            }
            //将最小值和我们的arr[0]进行交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];  //将真正最小值所在位置的值改为当前第一个位置的值
                arr[i] = min;   // 将原来第一个位置的值放入找到的真正的最小值
            }
            System.out.println("第" + (i + 1) + "轮后:");
            System.out.println(Arrays.toString(arr));

        }


       /* //推导
        //第一轮
        int minIndex = 0;
        int min = arr[0];//假定的最小值
        for (int j = 0 + 1; j < arr.length; j++) {
            if (min > arr[j]) {  //假定的最小值不是最小值   从小到大排序
                minIndex = j;
                min = arr[j];
            }
        }
        //将最小值和我们的arr[0]进行交换
        if (minIndex != 0) {
            arr[minIndex] = arr[0];  //将真正最小值所在位置的值改为当前第一个位置的值
            arr[0] = min;   // 将原来第一个位置的值放入找到的真正的最小值
        }
        System.out.println("第一轮后:");
        System.out.println(Arrays.toString(arr));


        //第二轮
        minIndex = 1;
        min = arr[1];//假定的最小值
        for (int j = 1 + 1; j < arr.length; j++) {
            if (min > arr[j]) {  //假定的最小值不是最小值
                minIndex = j;
                min = arr[j];
            }
        }
        //将最小值和我们的arr[0]进行交换
        if (minIndex != 1) {
            arr[minIndex] = arr[1];  //将真正最小值所在位置的值改为当前第一个位置的值
            arr[1] = min;   // 将原来第一个位置的值放入找到的真正的最小值
        }
        System.out.println("第二轮后:");
        System.out.println(Arrays.toString(arr));


        //第三轮
        minIndex = 2;
        min = arr[2];//假定的最小值
        for (int j = 2 + 1; j < arr.length; j++) {
            if (min > arr[j]) {  //假定的最小值不是最小值
                minIndex = j;
                min = arr[j];
            }
        }
        //将最小值和我们的arr[0]进行交换
        if (minIndex != 2) {
            arr[minIndex] = arr[2];  //将真正最小值所在位置的值改为当前第一个位置的值
            arr[2] = min;   // 将原来第一个位置的值放入找到的真正的最小值
        }
        System.out.println("第三轮后:");
        System.out.println(Arrays.toString(arr));*/
    }
}
