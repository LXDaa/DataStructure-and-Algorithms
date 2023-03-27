package com.atguigu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList;      // 存储顶点的集合
    private int[][] edges;   //存储图对应的邻接矩阵    每一行代表每个顶点，每一行中=1的值代表相关联的值
    private int numOfEdges;  //表示边的条数
    //定义数组boolean[],记录某个结点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 5;
        String[] Vertexs = {"A", "B", "C", "D", "E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for (String vertex : Vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        //显示邻接矩阵
        graph.showGraph();
        //测试dfs遍历
        graph.dfs();
        System.out.println();
        //测试bfs遍历
        graph.bfs();
    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;

    }

    /**
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    //得到第一个邻接结点的下标w
    //例：当前index为0 也就是A这个顶点，在二位数组  A这一行找 1 ，就代表关联的值
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 方法是找当前节点的下一个邻接节点
     *
     * @param v1 当前结点的下标        正在被访问的结点的下标
     * @param v2 邻接结点的下标       找到的被访问的结点的邻接结点    已经找过的结点的下标
     * @return
     */
    //根据前一个邻接结点的下标获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2) {
        //这里的情况是  找到的这个w (纵轴的A：0;B:1........)被访问过，找它的下一个
        //当前结点i   的w邻接点的    下一个邻接点
        for (int j = v2 + 1; j < vertexList.size(); j++) {        //始终要有二维数组那张图,方便理解
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    private void dfs(boolean[] isVisited, int i) {
        //首先访问初始结点i
        System.out.print(getValueByIndex(i) + "-->");
        //将访问过的结点置为已访问
        isVisited[i] = true;
        //查找当前访问结点i的第一个邻接结点w
        int w = getFirstNeighbor(i);
        while (w != -1) {    //有邻接结点
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w结点已经被访问过了
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行一个重载，遍历所有的结点，并进行dfs
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        //遍历所有的结点进行dfs【回溯】
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //广度优先遍历算法
    private void bfs(boolean[] isVisited, int i) {
        int u;//表示队列的头结点对应的下标
        int w;  //邻接结点的下标
        //队列   记录结点访问顺序
        LinkedList queue = new LinkedList();
        //访问结点
        System.out.print(getValueByIndex(i) + "-->");
        //标记为已访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接结点的下标w
            w = getFirstNeighbor(u);
            while (w != -1) {   //找到邻接结点
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "-->");
                    //标记已访问
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //访问过，还是要找以u为起始点(u这一行)，已访问过的结点的下一个结点
                w = getNextNeighbor(u, w);  //这里就体现出广度优先！！！！！
            }
        }
    }

    //遍历所有的结点，都进行广度优先搜索
    //上面的只是进行了一个结点
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    //图中常用的方法
    //返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回结点i（下标）对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * @param v1     表示点的下标
     * @param v2     第二个顶点的下标
     * @param weight
     */
    //添加边
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
