package com.atguigu.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);

    }

    //希尔排序
    public static void shellSort(int[] arr) {
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //遍历各组中的所有元素（共gap组）,步长为gap
            for (int j = gap - 5; j >= 0; j -= gap) {
                //如果当前元素大于加上步长后的那个元素，就交换
                if (arr[j] > arr[j + gap]) {
                    temp = arr[j];
                    arr[j] = arr[j + gap];
                    arr[j + gap] = temp;
                }

            }
        }




        /*int temp = 0;
        //10个数据走3轮
        //第1轮，将10个数据分成了5组
        for (int i = 5; i < arr.length; i++) {    //分为5组，5组有序&无序，比较5次
            //遍历各组中的所有元素（共5组，每组有2个元素）,步长为5
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素大于加上步长后的那个元素，就交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }

            }

        }
        System.out.println("希尔排序1轮后=" + Arrays.toString(arr));


        //第2轮，将10个数据分成了5/2=2组
        for (int i = 2; i < arr.length; i++) {     //分为两组，2个有序，8个无序，比较8次
        已i为分界线，i左边是有序组，从i右边拿到一个数插入合适的位置然后i+1
        i+1既可以改变分组，也可以从无序列表中拿元素插入本组
            //遍历各组中的所有元素（共5组，每组有2个元素）,步长为5
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果当前元素大于加上步长后的那个元素，就交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }

            }

        }
        System.out.println("希尔排序2轮后=" + Arrays.toString(arr));


        //第3轮，将10个数据分成了5/2=2/2=1组
        for (int i = 1; i < arr.length; i++) {    分为1组，1个有序，9个无序，比较9次
            //遍历各组中的所有元素（共5组，每组有2个元素）,步长为5
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果当前元素大于加上步长后的那个元素，就交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

            }

        }
        System.out.println("希尔排序3轮后=" + Arrays.toString(arr));*/
    }

    //对交换式的希尔排序进行优化-》移位法
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从gap个元素，逐个对其所在组进行直接插入
            for (int i = gap; i < arr.length; i++) {
                int j = i;//先将待（寻找）插入位置的数对应的下标的值保存起来
                int temp = arr[j]; //待插入的数，放到temp中给他找位置
                if (arr[j] < arr[j - gap]) {
                    //比它被插入位置的值小，并不是它的前一个，是根据gap计算的
                    //while循环还没有退出，就还没找到位置,j-gap>=0,说明它前面还有位置可以继续找位置
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];//将j-gap的值移动到j的位置
                        j -= gap;//继续和arr[j-gap]之前的数比较，最终找到位置
                    }
                    //退出while后，说明给temp找到了插入的位置，直接放进去
                    arr[j] = temp;
                }
            }
        }

    }
}
