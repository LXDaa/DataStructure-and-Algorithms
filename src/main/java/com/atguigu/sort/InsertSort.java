package com.atguigu.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        insertSort(arr);


    }

    //插入排序
    public static void insertSort(int[] arr) {
        //使用for循环进行代码简化
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];
            //等待被插入数的索引,即arr[1]前面这个数的索引
            int insertIndex = i - 1;

            //给insertValue找的一个插入的位置
            //insertIndex >= 0 保证在给insertValue找插入位置时，不越界
            //insertValue < arr[insertIndex]  还没有找到插入位置
            //将arr[insertIndex] 后移
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];   //相当于=> {101,101,119,1}
                insertIndex--;//和101的前一个进行比较  ，这里就体现了越界条件的设置
            }
            //退出while循环时，插入位置已经找到，位置:insetIndex+1,因为他不满足条件才插入,他是和前一个在比较,那就是在等待被插入数的索引的下一个
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }

//            System.out.println("第" + i + "轮插入后：");
//            System.out.println(Arrays.toString(arr));

        }




       /* //逐步推导的过程
        //第一轮{101, 34, 119, 1}  将{101}看作一个有序数组，{34, 119, 1}为无序数组，第一轮给34找位置

        //定义待插入的数
        int insertValue = arr[1];
        //等待被插入数的索引,即arr[1]前面这个数的索引
        int insertIndex = 1 - 1;

        //给insertValue找的一个插入的位置
        //insertIndex >= 0 保证在给insertValue找插入位置时，不越界
        //insertValue < arr[insertIndex]  还没有找到插入位置
        //将arr[insertIndex] 后移
        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];   //相当于=> {101,101,119,1}
            insertIndex--;//和101的前一个进行比较  ，这里就体现了越界条件的设置
        }
        //退出while循环时，插入位置已经找到，位置:insetIndex+1,因为他不满足条件才插入,他是和前一个在比较,那就是在等待被插入数的索引的下一个
        arr[insertIndex + 1] = insertValue;
        System.out.println("第一轮插入后：");
        System.out.println(Arrays.toString(arr));





        //第二轮
        //定义待插入的数
         insertValue = arr[2];
        //等待被插入数的索引,即arr[1]前面这个数的索引
        insertIndex = 2 - 1;

        //给insertValue找的一个插入的位置
        //insertIndex >= 0 保证在给insertValue找插入位置时，不越界
        //insertValue < arr[insertIndex]  还没有找到插入位置
        //将arr[insertIndex] 后移
        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];   //相当于=> {101,101,119,1}
            insertIndex--;//和101的前一个进行比较  ，这里就体现了越界条件的设置
        }
        //退出while循环时，插入位置已经找到，位置:insetIndex+1,因为他不满足条件才插入,他是和前一个在比较,那就是在等待被插入数的索引的下一个
        arr[insertIndex + 1] = insertValue;
        System.out.println("第2轮插入后：");
        System.out.println(Arrays.toString(arr));



        //第三轮
        //定义待插入的数
        insertValue = arr[3];
        //等待被插入数的索引,即arr[1]前面这个数的索引
        insertIndex = 3 - 1;

        //给insertValue找的一个插入的位置
        //insertIndex >= 0 保证在给insertValue找插入位置时，不越界
        //insertValue < arr[insertIndex]  还没有找到插入位置
        //将arr[insertIndex] 后移
        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];   //相当于=> {101,101,119,1}
            insertIndex--;//和101的前一个进行比较  ，这里就体现了越界条件的设置
        }
        //退出while循环时，插入位置已经找到，位置:insetIndex+1,因为他不满足条件才插入,他是和前一个在比较,那就是在等待被插入数的索引的下一个
        arr[insertIndex + 1] = insertValue;
        System.out.println("第3轮插入后：");
        System.out.println(Arrays.toString(arr));*/
    }
}
