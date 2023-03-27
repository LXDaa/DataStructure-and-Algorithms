package com.atguigu.Algorithm;

public class ViolenceMatch {
    public static void main(String[] args) {
        // 测试暴力匹配算法
        String str1 = "123";
        String str2 = "123";
        System.out.println(violenceMatch(str1, str2));

    }

    //暴力匹配算法
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Length = s1.length;
        int s2Length = s2.length;

        int i = 0;  //i索引指向s1
        int j = 0;   //j索引指向s2          可以当作步长

        while (i < s1Length && j < s2Length) {    //保证匹配时不越界
            if (s1[i] == s2[j]) {   //匹配成功
                i++;
                j++;
            } else {    //没有匹配成功    j=0,记录了i移动的次数，一旦不符合，就要从上次的起点往后一位开始，就是i-j+1
                i = i - (j - 1);
                j = 0;
            }
        }
        //判断是否匹配成功
        if (j == s2Length) {    //因为最后一次移动后j++了
            return i - j;      //i-   移动的步长j
        } else {
            return -1;
        }

    }
}
