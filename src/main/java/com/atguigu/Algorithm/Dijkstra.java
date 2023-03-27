package com.atguigu.Algorithm;

import java.util.Arrays;

public class Dijkstra {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;//表示不可连接
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.djs(6);

    }
}

// 已访问顶点集合
class VisitedVertex {
    // 记录各个顶点是否访问过 1 表示访问过,0 未访问,会动态更新
    public int[] already_arr;
    // 每个下标对应的值为前一个顶点下标, 会动态更新
    public int[] pre_visited;
    // 记录出发顶点到其他所有顶点的距离,比如 G 为出发顶点，就会记录 G 到其它顶点的距离，会动态更新，求的最短距离就会存放到 dis
    public int[] dis;

    /**
     * @param length 表示顶点的个数
     * @param index  出发顶点对应的下标
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        already_arr[index] = 1;
        //初始化dis数组
        for (int i = 0; i < length; i++) {
            if (i != index) {
                dis[i] = 65535;
            } else {
                dis[i] = 0;  //设置出发顶点的访问距离为0
            }
        }

    }

    /**
     * @param index 判断index顶点是否被访问
     * @return
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * @param index 更新出发顶点到index顶点的距离
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新前驱
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     *
     * @param index
     */
    public int getDis(int index) {
        return dis[index];
    }

    //继续选择并返回新的访问顶点
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        //更新index顶点被访问过
        already_arr[index] = 1;
        return index;
    }
}

class Graph {
    private char[] vertex;//顶点数组
    private int[][] matrix; //邻接矩阵
    private VisitedVertex visitedVertex;//已经访问过的顶点的集合

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }
    //迪杰斯特拉算法实现

    /**
     * @param index 出发顶点对应的下标
     */
    public void djs(int index) {
        visitedVertex = new VisitedVertex(vertex.length, index);
        update(index);  //更新index下标顶点到周围顶点的距离和周围顶点的前驱结点
        for (int j = 1; j < vertex.length; j++) {
            index = visitedVertex.updateArr();//选择并返回新的访问顶点
            update(index); //更新index下标顶点到周围顶点的距离和周围顶点的前驱结点
        }

    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱结点
    private void update(int index) {
        //遍历邻接矩阵第index行
        for (int j = 0; j < matrix[index].length; j++) {
            //出发顶点到index的距离+从index顶点到j顶点的距离和
            int len = visitedVertex.getDis(index) + matrix[index][j];
            //如果j顶点没有被访问过，并且len小于出发顶点到j的距离，就需要更新
            if (!visitedVertex.in(j) && len < visitedVertex.getDis(j)) {
                visitedVertex.updatePre(j, index);   //更新j的前驱为index顶点
                visitedVertex.updateDis(j, len);   //更新出发顶点到j的距离
            }
        }
    }
}
