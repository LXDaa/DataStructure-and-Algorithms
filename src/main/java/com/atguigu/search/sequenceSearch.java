package com.atguigu.search;

public class sequenceSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int index = seqenceSearch(arr, 11);
        if(index==-1){
            System.out.println("为查找到指定值");
        }
    }

    //线性查找,这里找的一个满足条件的就返回
    public static int seqenceSearch(int[] arr, int value) {
        //线性查找是逐一比对，如果有相同值就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
