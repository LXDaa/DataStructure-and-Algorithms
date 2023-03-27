package com.atguigu.linkedlist;


import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "1", "1");
        HeroNode hero2 = new HeroNode(2, "2", "2");
        HeroNode hero3 = new HeroNode(3, "3", "3");
        HeroNode hero4 = new HeroNode(4, "4", "4");
        //创建单链表
        SingleLinkList singleLinkList = new SingleLinkList();
        //加入
        singleLinkList.add(hero1);
        singleLinkList.add(hero2);
        singleLinkList.add(hero3);
        singleLinkList.add(hero4);
//        System.out.println("反转前");
//        singleLinkList.list();
//        System.out.println("反转后");
//        reverseList(singleLinkList.getHead());
//        singleLinkList.list();
        reversePrint(singleLinkList.getHead());
        /*
        //按照编号加入
        singleLinkList.addByOrder(hero2);
        singleLinkList.addByOrder(hero3);
        singleLinkList.addByOrder(hero1);
        singleLinkList.addByOrder(hero4);
        System.out.println("修改前");
        singleLinkList.list();
        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(4, "44", "44");
        singleLinkList.update(newHeroNode);
        //删除一个节点
        singleLinkList.del(1);
        singleLinkList.del(2);

        //显示
        System.out.println("修改后");
        singleLinkList.list();
        //测试，求单链表中有效节点的个数
        System.out.println("有效节点的个数： " + getLength(singleLinkList.getHead()));
        //测试，找到倒数第k个节点
        HeroNode res = findLastIndexNode(singleLinkList.getHead(), 1);
        System.out.println("res=" + res);
*/
    }

    //实现逆序打印
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        //创建一个空栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }


    //将单链表进行反转
    public static void reverseList(HeroNode head) {
        //如果当前链表为空或者只有一个节点,不进行反转
        if (head.next == null || head.next.next == null) {
            return;
        }
        //设置一个辅助节点,帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;  //当前节点的下一节点
        HeroNode reverseNode = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出放到新节点的最前面的位置
        while (cur != null) {
            next = cur.next; //保存当前节点的下一节点
            cur.next = reverseNode.next;  //将cur链表的下一节点指到reverseNode的最前端
            reverseNode.next = cur;
            cur = next;
        }
        //将head.next指向reverse.next
        head.next = reverseNode.next;
    }

    //查询单链表中倒数第k个节点
    //思路：1、编写一个方法，接收head节点和index
    //2、index表示倒数第几个节点
    //3、第一次遍历获取到链表的有效个数
    //4、倒数第k个节点,将辅助节点指到length-index位置
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int length = getLength(head);
        if (index < 0 || index > length) {
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < length - index; i++) {
            cur = cur.next;
        }
        return cur;
    }


    //方法：获取到单链表的个数（如果带头结点的链表，需求不统计头节点）
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        //定义一个辅助变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;

    }
}

//定义SingleLinkedList管理我们的英雄
class SingleLinkList {
    //初始化一个头节点，头节点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加接节点到单向链表
    //思路：（不考虑编号顺序时）
    //1、找到链表的最后一个节点
    //2、将最后一个结点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为头节点不能动,因此设计一个辅助节点（temp）
        HeroNode temp = head;
        //遍历链表,找到最后一个节点
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //不是最后的链表，将temp后移
            temp = temp.next;
        }
        //但退出while循环时，temp指向链表的最后一个节点
        temp.next = heroNode;
    }

    //第二种方式，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败)
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动,因此设计一个辅助节点（temp）来找到添加的位置
        //因为是单链表，找到的temp是需要添加的位置的前一个节点
        HeroNode temp = head;
        boolean flag = false;//flag 表示添加的编号是否存在
        while (true) {
            if (temp.next == null) {//说明temp已在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { //位置找到
                break;
            } else if (temp.next.no == heroNode.no) { //希望添加的heroNode的编号已经存在
                flag = true;   //编号存在
                break;
            }
            temp = temp.next; //后移，遍历当前链表
        }
        if (flag) { //编号存在，无法添加
            System.out.println(heroNode.no + "编号存在，无法添加");
        } else {
            //可以插入到链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息
    public void update(HeroNode heroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空!");
            return;
        }
        //因为头节点不能动,因此设计一个辅助节点（temp）
        HeroNode temp = head.next;
        boolean flag = false;   //表示是否找到该节点
        while (true) {
            //判断是否到达链表的最后
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag，判断是否找到要修改的节点
        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            System.out.println("没有找到编号为" + heroNode.no + "的节点");
        }
    }

    //删除节点
    //思路：
    //1、head不能动需要设置一个辅助节点temp指向要删除结点的前一个节点
    //2、比较的是temp.next.no和和需要删除的no比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;//表示是否找到要删除的节点
        while (true) {
            if (temp.next == null) { //已经到链表的最后
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("没有找到编号为" + no + "的节点");
        }
    }


    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空!");
            return;
        }
        //因为头节点不能动,因此设计一个辅助节点（temp）
        HeroNode temp = head.next;
        while (true) {
            //判断是否到达链表的最后
            if (temp == null) {
                return;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }

    }
}

//定义一个HeroNode类,每一个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
