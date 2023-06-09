package com.atguigu.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //需要创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
//        HeroNode node5 = new HeroNode(5, "关胜");
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
//        node3.setLeft(node5);
//        //测试
//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//        System.out.println("后序遍历");
//        binaryTree.postOrder();
//
//        //前序遍历查找
//        //前序遍历查找的次数:4
//        System.out.println("前序遍历查找");
//        HeroNode resNode = binaryTree.preOrderSearch(5);
//        if (resNode != null) {
//            System.out.println("找到了，信息为no=" + resNode.getNo() + "  name=" + resNode.getName());
//        } else {
//            System.out.println("没有找到no=" + 5 + "的英雄");
//        }
//
//        //中序遍历查找
//        //中序遍历查找的次数:3
//        System.out.println("中序遍历查找");
//        resNode = binaryTree.infixOrderSearch(5);
//        if (resNode != null) {
//            System.out.println("找到了，信息为no=" + resNode.getNo() + "  name=" + resNode.getName());
//        } else {
//            System.out.println("没有找到英雄");
//        }
//
//        //后序遍历查找
//        //后序遍历查找的次数:3
//        System.out.println("后序遍历查找");
//        resNode = binaryTree.postOrderSearch(5);
//        if (resNode != null) {
//            System.out.println("找到了，信息为no=" + resNode.getNo() + "  name=" + resNode.getName());
//        } else {
//            System.out.println("没有找到no=" + 5 + "的英雄");
//        }
        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();
        binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();

    }
}


//定义BinaryTree  二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //删除节点
    public void delNode(int no) {
        if (root != null) {
            //如果这里只有一个root节点，立即判断root是不是要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                //递归进行删除
                root.delNode(no);
            }

        } else {
            System.out.println("这是一颗空树");
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

//先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
    //递归删除节点
    //如果删除的是叶子节点，则删除该节点
    //如果删除的节点是非叶子节点，则删除该树

    public void delNode(int no) {
        //当前节点的左子节点不为空并且当前节点的左子节点为待删除的节点
        if (this.left != null && this.left.no == no) {
/*            if (this.left.left != null || this.right.right != null) {
                //说明要删除的节点是非叶子节点
                if ((this.left.left != null && this.right.right == null) || (this.left.left == null && this.right.right != null)) {
                    //只有一个子结点
                    if (this.left.left != null) {
                        this.left = this.left.left;
                    } else {
                        this.right = this.right.right;
                    }
                }
            }*/
            this.left = null;
            return;
        }
        //当前节点的右子节点不为空并且当前节点的右子节点为待删除的节点
        if (this.right != null && this.right.no == no) {
/*            if (this.left.left != null || this.right.right != null) {
                //说明要删除的节点是非叶子节点
                if ((this.left.left != null && this.right.right == null) || (this.left.left == null && this.right.right != null)) {
                    //只有一个子结点
                    if (this.left.left != null) {
                        this.left = this.left.left;
                    } else {
                        this.right = this.right.right;
                    }
                }
            }*/

            this.right = null;
            return;
        }
        //向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        //向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }


    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this);        //先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        //递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历查找");
        //先比较当前节点是不是要查找的节点
        if (this.no == no) {
            return this;
        }
        //不是，判断当前节点的左子节点是否为空,如果不为空，则递归进行前序查找
        //如果，左递归前序查找找到节点，就返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {  //说明找到
            return resNode;
        }
        //左递归前序查找，找到节点，则返回，找不到则继续判断
        //当前节点的右子节点是否为空，如果不为空，则继续向右进行前序查找
        //如果，右递归前序查找找到节点，就返回
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;

    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {

        HeroNode resNode = null;
        //先判断当前节点的左子节点是否为空
        if (this.left != null) {
            //递归进行中序遍历
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序遍历查找");
        //判断当前节点是不是要查询的节点
        if (this.no == no) {
            return this;
        }
        //判断当前节点的右子节点是否为空
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        //先判断当前节点的左子节点是否为空
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序遍历查找");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}
