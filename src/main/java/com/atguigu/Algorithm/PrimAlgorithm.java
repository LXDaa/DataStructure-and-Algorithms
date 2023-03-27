package com.atguigu.Algorithm;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertex = data.length;
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
        //创建MGraph对象
        MGraph graph = new MGraph(vertex);
        //创建MinTree
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, vertex, data, weight);
        minTree.showGraph(graph);
        //测试普利姆算法
        minTree.prim(graph, 0);
    }
}

//创建最小生成树
class MinTree {
    //创建图的邻接矩阵
    public void createGraph(MGraph graph, int vertex, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < vertex; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的方法
    public void showGraph(MGraph mGraph) {
        for (int[] link : mGraph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }


    //编写prim算法，得到最小生成树

    /**
     * @param graph 图
     * @param v     表示从图的第几个顶点开始生成
     */
    public void prim(MGraph graph, int v) {
        //标记结点（顶点）是否被访问过
        int[] visited = new int[graph.vertex];
        //把当前结点标记为已访问
        visited[v] = 1;
        //用h1和h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//将minWeight初始化为一个大数，后面在遍历过程中会被替换
        for (int k = 1; k < graph.vertex; k++) {//因为有graph.vertex顶点，普利姆算法结束后，有graph.vertex-1条边
            //确定每一次生成的子图，和那个结点的距离最近
            for (int i = 0; i < graph.vertex; i++) {  //i结点表示被访问过的结点
                for (int j = 0; j < graph.vertex; j++) {   //j结点表示没有被访问过的结点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到最小的一条边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + ">权值：" + minWeight);
            //将当前结点标记为已经访问
            visited[h2] = 1;
            //重置minWeight
            minWeight = 10000;


        }
    }

}

class MGraph {
    int vertex;//表示图的节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边，邻接矩阵

    public MGraph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];

    }
}
