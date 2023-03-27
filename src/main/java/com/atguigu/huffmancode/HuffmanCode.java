package com.atguigu.huffmancode;

import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        byte[] huffmanZip = huffmanZip(contentBytes);
        System.out.println(Arrays.toString(huffmanZip));
       /* List<Node> nodes = getNodes(contentBytes);

        System.out.println("赫夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历 ");
        preOrder(huffmanTreeRoot);
        //
        System.out.println("对应的赫夫曼编码");
        getCodes(huffmanTreeRoot);
        System.out.println(huffmanCodes);
        System.out.println("赫夫曼编码后对应的byte");
        byte[] huffmanCodesBytes = zip(contentBytes, huffmanCodes);
        System.out.println("huffmanCodesBytes=" + Arrays.toString(huffmanCodesBytes));*/

    }
    //完成数据的解压
    //1、将得到的赫夫曼压缩后的字节数组转为赫夫曼编码对应的二进制字符串
    //2、将此二进制对照赫夫曼编码表解码

    /**
     * 将一个byte转为二进制字符串
     *
     * @param b
     * @return
     */
    public static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b;
        //如果是正数，我们需要补高位
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    //将前面的方法封装起来，便于调用
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //生成对应的赫夫曼编码表
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码表，压缩
        byte[] huffmanCodesBytes = zip(bytes, huffmanCodes);
        return huffmanCodesBytes;
    }

    //编写一个方法，将字符串对应的byte[]数组，通过赫夫曼树编码表返回赫夫曼编码压缩后的byte[]

    /**
     * @param bytes        原始字符串对应的字节数组
     * @param huffmanCodes 赫夫曼编码表
     * @return 返回赫夫曼编码压缩后的byte[]
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1、利用 huffmanCodes   将 bytes   转为  赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //将得到的字符串转为byte[]数组
        //统计返回的  赫夫曼编码对应的字符串长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建压缩后的byte数组
        byte[] huffmanCodesBytes = new byte[len];
        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {   //因为每8位对应一个byte
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                //不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转为一个byte，存放到huffmanCodesBytes中
            huffmanCodesBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;


        }
        return huffmanCodesBytes;
    }

    //生成的赫夫曼树对应的赫夫曼编码
    //1、将赫夫曼编码表存放在Map<Byte,String>
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //2、在生成赫夫曼编码表示需要不断地拼接路径，定义一个StringBuilder存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }//处理root的左子数
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子数
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;

    }

    /**
     * 将传入的node结点的叶子节点的赫夫曼编码得到，并存放到huffmanCodes中
     *
     * @param node          传入的结点
     * @param code          路径值   左子节点：0     右子节点：1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {
            //判断当前node是叶子结点还是非叶子节点
            if (node.data == null) {
                //非叶子节点
                //递归处理
                //向左
                getCodes(node.left, "0", stringBuilder2);
                //向右
                getCodes(node.right, "1", stringBuilder2);
            } else {
                //叶子节点，就表示找到了某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树");
        }

    }

    /**
     * @param bytes 接受字节数组
     * @return
     */
    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        //遍历bytes,统计每一个byte出现的次数  ——>map
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            if (counts.get(b) == null) {  //map中还没有当前字符
                counts.put(b, 1);
            } else {
                counts.put(b, counts.get(b) + 1);
            }
        }
        //将每一个键值对转为Node对象
        //遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过list创建对应的赫夫曼树
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        //nodes中的最后结点，也就是赫夫曼树的根节点
        return nodes.get(0);
    }
}

class Node implements Comparable<Node> {
    Byte data;//存放数据本身，比如'a'=>97
    int weight;//权值 ，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
