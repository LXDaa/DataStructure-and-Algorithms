package com.atguigu.binarysortTree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        //测试
        binarySortTree.delNode(12);
        //
        binarySortTree.infixOrder();

    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    /**
     * 返回 以node为根节点的二叉排序树的最小结点的值
     * 删除  以node为根节点的二叉排序树的最小结点的值
     *
     * @param node
     * @return 以node为根节点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左节点，就会查到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这时target就指向了最小结点
        //删除 最小结点
        delNode(target.value);
        return target.value;


    }

    //查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            System.out.println("空树");
            return null;
        }
        return root.search(value);
    }

    //查找父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    //删除结点
    public void delNode(int value) {
        if (root == null) {
            System.out.println("空树");
            return;
        } else {
            //查找要删除的结点
            Node targetNode = search(value);
            //如果没有找到要删除的结点
            if (targetNode == null) {
                System.out.println("删除结点不存在");
                return;
            }
            //如果当前二叉树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //查找targetNode的父结点
            Node parent = searchParent(value);
            //如果要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是parent的左子结点还是右子结点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;

                }
            } else if (targetNode.left != null && targetNode.right != null) {  //要删除的结点有两棵子树
                targetNode.value = delRightTreeMin(targetNode.right);

            } else {  //要删除的结点有一棵子树

                //如果要删除的结点有左子结点
                if (targetNode.left != null) {
                    if (parent != null) {
                        //如果要删除的结点是parent结点的左子结点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {    //要删除的结点是parent结点的右子结点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {   //如果要删除的结点有右子结点
                    if (parent != null) {
                        //如果要删除的结点是parent结点的左子结点
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {    //要删除的结点是parent结点的右子结点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }

                }

            }
        }
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("空树");
        }
    }
}

//创建Node结点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //查找结点
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (this.value > value) { //向左子树递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {  //向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除结点的父结点
    public Node searchParent(int value) {
        //当前结点就是要删除的结点的父结点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //   查找的值<当前结点的值    向左递归查找
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
                //   查找的值>当前结点的值    向右递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;  //没有找到父结点
            }
        }

    }

    //添加结点的方法
    //递归的形式添加结点，需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                //递归的向左子树添加
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
