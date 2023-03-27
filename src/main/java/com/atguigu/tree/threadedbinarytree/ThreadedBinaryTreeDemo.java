package com.atguigu.tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "lucy");
        //创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        //前序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();
        //测试
        HeroNode leftNode = node5.getLeft();
        System.out.println("10号节点的前驱节点 " + leftNode);
        System.out.println("10号节点的后继节点 " + node5.getRight());

        //当线索化二叉树时，不能使用原来的遍历方法
        System.out.println("使用线索化的方式遍历 线索化二叉树 ");
        threadedBinaryTree.threadedList();

    }
}

//定义ThreadedBinaryTree  实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root;
    //为了实现线索化，需要创建一个指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre总是保留前一个结点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载threadedNodes方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //遍历中序线索化二叉树
    public void threadedList() {
        //定义一个变量,存储当前遍历的节点,从root开始
        HeroNode node = root;
        while (node != null) {
            //循环的找到leftType==1的节点，第一个找到的就应该是8这个节点
            //后面随着遍历会变化，因为当leftType==1时，说明该节点是按照线索化处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //打印当前这个节点
            System.out.println(node);
            //如果当前节点的右指针指向的时后继节点，就一直输出
            while (node.getRightType() == 1) {
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }

    /**
     * @param node 当前需要线索化的节点
     */
    //编写对二叉树进行中序线索化的方法
    public void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        //(一)先线索化左子树
        threadedNodes(node.getLeft());
        //(二)线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型,指向前驱节点
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;
        //(三)线索化右子树
        threadedNodes(node.getRight());
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

//
//先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    //说明:
    //1、如果leftType==0,说明指向的是左子树;如果leftType==1,说明指向的是前驱节点
    //1、如果rightType==0,说明指向的是右子树;如果rightType==1,说明指向的是后继节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

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
