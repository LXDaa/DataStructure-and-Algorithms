package com.atguigu.Algorithm;

import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext("ABCDABD");
        System.out.println("next = " + Arrays.toString(next));
        int index = kmpSearch(str1, str2, next);
        System.out.println("index = " + index);
    }

    /**
     * @param str1 主串
     * @param str2 模式串
     * @param next 模式串对应的部分匹配表
     * @return -1  没有匹配到   ，否则返回第一个匹配的位置
     */
    //写出kmp搜索算法
    public static int kmpSearch(String str1, String str2, int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j != 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {    //找到了
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到一个字符串（子串）的部分匹配值(表)      dest是需要匹配的字符串
    public static int[] kmpNext(String dest) {
        //创建一个和传入字符串长度一致的数组
        int[] next = new int[dest.length()];
        //如果传入字符串长度为1  ，它的部分匹配值为 0   例：A
        next[0] = 0;
        //i数组的下标：以匹配前缀的下一个位置      j=最长可匹配子串的数量
        for (int i = 1, j = 0; i < dest.length(); i++) {         //for循环就是计算next[0],next[1],.....的值。
            //当dest.charAt(i) ！= dest.charAt(j)，我们需要从next[j-1]获取新的j
            //直到dest.charAt(i) == dest.charAt(j)成立才退出
            //kmp算法的核心点
            //j=0时，需要移动主串i++;
            while (j != 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];              //j回溯到前面前面字符串长度的最长可匹配子串的数量
            }
            //当dest.charAt(i) == dest.charAt(j)满足时，匹配值就+1
            //B串自己与自己匹配，B【1】~B【i】的前缀与它的后缀匹配   后缀为主串   前缀为模式串
            //每次匹配上一个就+1，j 也就代表最长可匹配子串的数量
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;

    }
}
