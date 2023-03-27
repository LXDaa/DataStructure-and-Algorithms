package com.atguigu.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        //创建AVL树
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //中序遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("树的高度=" + avlTree.getRoot().height());
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight());
        System.out.println("根节点=" + avlTree.getRoot());

    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回以当前结点为根结点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
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

    /**
     * 左旋转的方法
     */
    public void leftRotate() {
        //1、创建新的结点,以当前根结点的值
        Node newNode = new Node(value);
        //2、将新的结点的左子树设置为当前结点的左子树
        newNode.left = this.left;
        //把新的结点的右子树设置为当前结点的右子树的左子树
        newNode.right = this.right.left;
        //将当前结点的值替换为当前结点的右子结点的值
        this.value = this.right.value;
        //把当前结点的右子树设置为右子树的右子树
        this.right = this.right.right;
        //将当前结点的左子树设置为新的结点
        this.left = newNode;
    }

    /**
     * 右旋转的方法
     */
    public void rightRotate() {
        //1、创建新的结点,以当前根结点的值
        Node newNode = new Node(this.value);
        //2、新结点的右子树指向当前结点的右子树
        newNode.right = this.right;
        //3、新的结点的左子树设置为当前节点的左子节点的右子节点
        newNode.left = this.left.right;
        //4、将当前结点的值改为当前结点的左子节点的值
        this.value = this.left.value;
        //5、当前结点的左子节点指向当前左子节点的左子节点
        this.left = this.left.left;
        //6、当前结点的右子节点指向新的结点
        this.right = newNode;

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
        //当添加完一个结点后，如果(右子树的高度-左子树的高度)>1,进行左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树的高度大于右子树的高度
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                //需要先对当前结点的右子树进行右旋转
                this.right.rightRotate();
            } else {
                //直接进行左旋转
                leftRotate();
            }
            return;  //!!!!!!!!!!!!!!!!!!!!!!

        }
        //当添加完一个结点后，如果(左子树的高度-右子树的高度)>1,进行右旋转
        if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树的高度大于左子树的高度
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                //要先对当前结点的左子树进行左旋转
                this.left.leftRotate();
                //然后针对当前结点进行右旋转
                this.rightRotate();
            } else {
                //直接进行右旋转
                this.rightRotate();
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
