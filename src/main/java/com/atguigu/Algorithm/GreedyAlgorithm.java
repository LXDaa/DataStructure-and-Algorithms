package com.atguigu.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台，放到Map中
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        HashSet<String> regions1 = new HashSet<>();
        regions1.add("北京");
        regions1.add("上海");
        regions1.add("天津");
        HashSet<String> regions2 = new HashSet<>();
        regions2.add("广州");
        regions2.add("北京");
        regions2.add("深圳");
        HashSet<String> regions3 = new HashSet<>();
        regions3.add("成都");
        regions3.add("上海");
        regions3.add("杭州");
        HashSet<String> regions4 = new HashSet<>();
        regions4.add("上海");
        regions4.add("天津");
        HashSet<String> regions5 = new HashSet<>();
        regions5.add("杭州");
        regions5.add("大连");

        //加入到map
        broadcasts.put("k1", regions1);
        broadcasts.put("k2", regions2);
        broadcasts.put("k3", regions3);
        broadcasts.put("k4", regions4);
        broadcasts.put("k5", regions5);
        //存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建ArrayList,存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();
        //定义一个临时的集合，存放交集
        HashSet<String> tempSet = new HashSet<>();
        //定义maxKey保存再一次遍历中，能够覆盖最大未覆盖的地区对应的电台key
        String maxKey = null;
        //如果maxKey不为空，则会加入到selects中
        while (allAreas.size() != 0) {
            //每进行一次while，需要
            maxKey = null;
            //遍历broadcasts，取出对应的key
            for (String key : broadcasts.keySet()) {
                //每进行一次for
                tempSet.clear();
                //当前key所能覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出 tempSet  和 allAreas的交集,交集会赋给tempSet
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多
                //就需要重置key
                if (tempSet.size() > 0 && (maxKey != null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区，从allareas中去掉
                allAreas.removeAll(broadcasts.get(maxKey));

            }
        }
        System.out.println(selects);
    }

}
