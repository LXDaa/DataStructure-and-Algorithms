package com.atguigu.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("归并排序后的数组" + Arrays.toString(arr));
    }

    //分+合的方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        int mid = (left + right) / 2;//中间的索引
        if (left < right) { //直到left==right也就是数组不能再分了
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            //每分解一次就合并一次
            //合并顺序
            //tempLeft:0  right:1
            //tempLeft:2  right:3
            //tempLeft:0  right:3
            //tempLeft:4  right:5
            //tempLeft:6  right:7
            //tempLeft:4  right:7
            //tempLeft:0  right:7
            merge(arr, left, mid, right, temp);

        }
    }

    /**
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 最右边的索引
     * @param temp  中转的临时数组
     */
    //合并的方法
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
        int i = left;  //初始化i，左边有序序列的初始索引
        int j = mid + 1;   //初始化j，右边有序序列的初始索引
        int t = 0;//t是指向temp数组的当前索引
        //（一）
        //先把左右两边（有序）的数据按照规则填充到temp数组中，直到左右两边有一边处理完毕
        while (i <= mid && j <= right) {//继续
            if (arr[i] <= arr[j]) {  //左边有序序列的当前元素  <=  右边有序序列的当前元素
                temp[t] = arr[i];   //将左边有序序列的当前元素加入到temp数组中的
                t++;
                i++;
            } else {
                //左边有序序列的当前元素  > 右边有序序列的当前元素
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //while循环后可能有一边会有剩余
        //（二）
        //哪边有剩余，就把剩余的数据依次填到temp中去
        while (i <= mid) {
            //左边有剩余
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            //右边有剩余
            temp[t] = arr[j];
            t++;
            j++;
        }
        //（三）
        //将temp数组的元素拷贝到arr
        //并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        System.out.println("tempLeft:" + tempLeft + "  right:" + right);
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft += 1;
        }
    }
}
