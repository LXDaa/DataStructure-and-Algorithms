package com.atguigu.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    //基数排序
    public static void radixSort(int[] arr) {
        //最终的代码
        //1、得到数组中最大的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数的位数
        int maxLength = (max + "").length();


        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1、二维数组包含10个一维数组
        //2、为了防止在放入数的时候，数据溢出，则每个一维数组(桶)的大小定为arr.length
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中实际存放了多少个数，我们定义一个一维数组来记录各个桶的每次放入的数据的个数
        int[] bucketElementCounts = new int[10];

        //使用循环对代码进行处理,进行的轮数就是当前数组中的最大位数
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //每轮（针对每个元素的位进行排序）
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位数
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照桶的顺序（一维数组的下标依次取出数据，放入到原来的数组中）
            int index = 0;
            //遍历每一个桶，并将桶中的每一个数据放入到原数组中
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，才放入到原数组中
                if (bucketElementCounts[k] != 0) {
                    //循环该桶，即第k个一维数组
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr中
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将每个bucketElementCounts[k] = 0
                bucketElementCounts[k] = 0;

            }
            System.out.println("第" + (i + 1) + "轮,排序处理后   arr=" + Arrays.toString(arr));
        }

/*
        //第1轮（针对每个元素的个位进行排序）
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位数，个位数是几就放到那个桶里
            int digitOfElement = arr[j] / 1 % 10;
            //放入到对应的桶中,bucketElementCounts[digitOfElement]是第digitOfElement个桶中的存放的数据个数
            //举例：当前桶中存放了0个数据，那么就放在当前桶的第0个位置
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++; //将当前桶中存放的数据个数+1

        }
        //按照桶的顺序（一维数组的下标依次取出数据，放入到原来的数组中）
        int index = 0;
        //遍历每一个桶，并将桶中的每一个数据放入到原数组中
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，才放入到原数组中
            if (bucketElementCounts[k] != 0) {
                //循环该桶，即第k个一维数组
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr中
                    arr[index++] = bucket[k][l];
                }
            }
            //第1轮处理后，需要将每个bucketElementCounts[k] = 0
            bucketElementCounts[k] = 0;

        }
        System.out.println("第1轮，对个位的排序处理   arr=" + Arrays.toString(arr));
//*********************************************************************************************************************
        //第2轮（针对每个元素的十位进行排序）
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位数
            int digitOfElement = arr[j] / 10 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照桶的顺序（一维数组的下标依次取出数据，放入到原来的数组中）
        index = 0;
        //遍历每一个桶，并将桶中的每一个数据放入到原数组中
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，才放入到原数组中
            if (bucketElementCounts[k] != 0) {
                //循环该桶，即第k个一维数组
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr中
                    arr[index++] = bucket[k][l];
                }
            }
            //第2轮处理后，需要将每个bucketElementCounts[k] = 0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第2轮，对十位的排序处理   arr=" + Arrays.toString(arr));
//*********************************************************************************************************************
        //第3轮（针对每个元素的百位进行排序）
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位数
            int digitOfElement = arr[j] / 10 / 10 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照桶的顺序（一维数组的下标依次取出数据，放入到原来的数组中）
        index = 0;
        //遍历每一个桶，并将桶中的每一个数据放入到原数组中
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，才放入到原数组中
            if (bucketElementCounts[k] != 0) {
                //循环该桶，即第k个一维数组
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr中
                    arr[index++] = bucket[k][l];
                }
            }
            //第3轮处理后，需要将每个bucketElementCounts[k] = 0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第3轮，对百位的排序处理   arr=" + Arrays.toString(arr));
*/

    }
}
