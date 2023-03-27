package com.atguigu.search;

import java.util.ArrayList;

//使用二分查找的条件是数组必须是有序的
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000,1000,1000, 1234};
//        int resIndex = binarySearch(arr, 0, arr.length - 1, 20);
//        System.out.println("resIndex = " + resIndex);
        ArrayList<Integer> list = binarySearch2(arr, 0, arr.length - 1, 100);
        System.out.println("list = " + list);
    }

    //二分查找

    /**
     * @param arr     查找的数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 需要查找的值
     * @return 如果找到就返回下标，没有找到就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //如果left>right说明递归了整个数组，但是没有找到
        if (left > right) {
            return -1;
        }
        //确定中间的下标
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            //查找的值在中间值的右边，向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }


    //如果寻找的值有多个，怎么返回所有
    //思路分析:
    //1、在我找到findVal == midVal的时候,先不要返回，此时这个中间的下标mid指向了findVal
    //2、在mid下标（索引值）的左边进行扫描，将所有等于findVal的下标，加入到arryList中
    //3、在mid下标（索引值）的右边进行扫描，将所有等于findVal的下标，加入到arryList中
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        //如果left>right说明递归了整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        //确定中间的下标
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            //查找的值在中间值的右边，向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            //1、在我找到findVal == midVal的时候,先不要返回，此时这个中间的下标mid指向了findVal
            //2、在mid下标（索引值）的左边进行扫描，将所有等于findVal的下标，加入到arryList中
            //3、在mid下标（索引值）的右边进行扫描，将所有等于findVal的下标，加入到arryList中
            ArrayList<Integer> resIndexList = new ArrayList<>();
            //在mid下标（索引值）的左边进行扫描，将所有等于findVal的下标，加入到arryList中
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    //arr[temp] != findVal就可以退出了，不需要再往前走了，因为这是个有序数组，前一个一定比当前的值小
                    break;
                }
                //否则，将这个temp放入到resIndexList中
                resIndexList.add(temp);
                temp--; //temp左移
            }
            resIndexList.add(mid);
            //在mid下标（索引值）的右边进行扫描，将所有等于findVal的下标，加入到arryList中
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    //arr[temp] != findVal就可以退出了，不需要再往前走了，因为这是个有序数组，前一个一定比当前的值小
                    break;
                }
                //否则，将这个temp放入到resIndexList中
                resIndexList.add(temp);
                temp++; //temp右移
            }
            return resIndexList;
        }

    }
}
