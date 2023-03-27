package com.atguigu.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            //将数组中的第0个数字作为基准数
            int stard = arr[start];
            //记录需要排序的下标
            int left = start;
            int right = end;
            //接下来需要循环找数字，左边的比右边的小
            while (left < right) {
                while (left < right && stard <= arr[right]) {  //如果下标right对应的数，大于基准数stard,就让right往前走
                    right--;
                }
                //下标right对应的数，小于基准数stard,没进上一个while循环,就用右边的替换左边的
                arr[left] = arr[right];
                //下面轮到了左边,如果左边的数<=基准数，不需要替换，将下标向后移
                while (left < right && arr[left] <= stard) {
                    left++;
                }
                //如果左边的数>基准数，替换
                arr[right] = arr[left];

            }
            //结束的时候left=right,将基准数重新加回去
            arr[left] = stard;

            //到这里就把比基准数小的数放在了基准数的左边，把比基准数小的放在基准数的右边
            //对比基准数小的数进行排序
            quickSort(arr, start, left);
            //对比基准数大的数进行排序
            quickSort(arr, left + 1, end);

        }
    }


/*
    public static void quickSort(int[] arr, int left, int right) {
        int l = left; //左下标
        int r = right;//右下标
        int temp = 0;
        //中间值
        int pivot = arr[(left + right) / 2];
        //下面的while 退出条件是l>=r，不满足条件继续从交换完的位置往下走，是为了把左右两边的数都找完
        while (l < r) {  //while循环的目的是把比pivot小的值放到pivot左边，把比pivot大的值放到pivot右边
            //在pivot的左边一直找，直到找到大于或等于pivot的值时退出
            while (arr[l] < pivot) {
                l += 1;
            }
            while (arr[r] > pivot) {
                //在pivot的右边一直找，直到找到小于或等于pivot的值时退出
                r -= 1;
            }
            //如果l>=r成立说明，<=pivot的值都在pivot左边，>=pivot的值都在 pivot右边
            if (l >= r) {//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后,发现这个arr[l]==pivot    左边找到的要交换的值和右边找到的要交换的值一样，也就是这个数组中有多个重复的值
            //那么我就把r-1,让它去找前一个还满足小于pivot的值，避免了死循环，参考{-9, 78, 0,0,-1,0, 23, -567, 70};
            //上面的例子假定0是中间值，只是考虑一种特殊情况，便于理解
            if (arr[l] == pivot) {
                r -= 1;
            }
            if (arr[r] == pivot) {
                l += 1;
            }
        }


        //上面已经按照中间值左右分开了
        //最后结束的时候，r和l都指向了中间值，此时r==l
        if (l == r) {
            l+=1;
            r-=1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
        */

}
