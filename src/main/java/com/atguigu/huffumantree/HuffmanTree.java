package com.atguigu.huffumantree;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
//        createHuffmanTree(arr);
        preOrder(createHuffmanTree(arr));

    }
    //前序遍历方法
    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else {
            System.out.println("空树");
        }
    }

    //创建赫夫曼数的方法
    public static Node createHuffmanTree(int[] arr) {
        //
        //1、遍历arr数组
        //2、将arr的每个元素构建为一个Node对象
        //3、将Node放入arrayList中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        //处理的过程是一个循环的过程
        while (nodes.size() > 1) {
            //排序，从小到大
            Collections.sort(nodes);
            //取出根节点权值最小的二叉树
            //1、取出权值最小的结点
            Node leftNode = nodes.get(0);
            //2、取出次小的结点
            Node rightNode = nodes.get(1);
            //3、构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //4、从ArrayList中删除已经处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5、将parent加入到ArrayList中
            nodes.add(parent);
        }
        //返回赫夫曼树的root结点
        return nodes.get(0);

    }
}

//创建结点类
//为了让Node对象支持排序 Collections 集合排序
class Node implements Comparable<Node> {
    int value;//结点的权值
    Node left; //指向左子结点
    Node right;  //指向右子结点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大进行排序
        return this.value - o.value;
    }

    //写一个前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }

    }
}