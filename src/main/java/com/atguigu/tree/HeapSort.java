package com.atguigu.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排列
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    //编写一个堆排序的方法
    public static void heapSort(int[] arr) {
        int temp = 0;
        System.out.println("堆排序");
//        adjustHeap(arr, 1, arr.length);
//        System.out.println("第1次：" + Arrays.toString(arr));
//        adjustHeap(arr,0, arr.length);
//        System.out.println("第2次：" + Arrays.toString(arr));
        //完成最终代码
        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
        //重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
        //5个数,只需要调整4个数，最后一个数一定是它的位置
        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //交换完成后，除了顶部外其他部分仍是大顶堆结构，只需要调整顶部
            adjustHeap(arr,0,j);
        }
        System.out.println("arr=" + Arrays.toString(arr));
    }

    /**
     * 将非叶子节点i对应的树调整为大顶堆
     *
     * @param arr    待调整的数组
     * @param i      非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整,length在逐渐减少
     */
    //将一个数组（二叉树）调整为大顶堆
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //开始调整
        //k是i节点的左子节点
        for (int k = 2 * i + 1; k <length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) { //说明当前左子节点<右子节点
                k++; //k指向右子节点
            }
            if (arr[k] > temp) {   //如果子节点>父节点
                arr[i] = arr[k];   //将较大的值赋给当前节点
                i = k;  //i指向k，继续循环比较
            } else {
                break;   //因为是从左往右，由下至上，下面的已经排好    不用交换已经是大顶堆结构
            }
        }
        //当for循环结束时，已经将以i为父节点的树的最大值，放在了最顶端的位置（局部）
        arr[i] = temp;//将temp放到调整后的位置
    }
}
