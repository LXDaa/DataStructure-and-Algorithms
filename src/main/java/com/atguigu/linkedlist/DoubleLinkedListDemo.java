package com.atguigu.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //双链表的测试
        //进行测试
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "1", "1");
        HeroNode2 hero2 = new HeroNode2(4, "2", "2");
        HeroNode2 hero3 = new HeroNode2(5, "3", "3");
        HeroNode2 hero4 = new HeroNode2(3, "4", "4");
        //创建一个双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
//        doubleLinkedList.list();
        //修改
        HeroNode2 newHeroNode = new HeroNode2(2, "44", "44");
//        doubleLinkedList.update(newHeroNode);
//        System.out.println();
//        doubleLinkedList.list();
//        doubleLinkedList.del(4);
//        System.out.println();
//        doubleLinkedList.list();
        doubleLinkedList.addByOrder(newHeroNode);
        doubleLinkedList.list();

    }
}

//创建一个双向链表的类
class DoubleLinkedList {
    //初始化一个头节点，头节点不要动,不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表
    public void list() {
        if (head.next == null) {
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //按编号添加节点
    public void addByOrder(HeroNode2 heroNode2) {
        HeroNode2 temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode2.no) {
                break;
            } else if (temp.next.no == heroNode2.no) { //希望添加的heroNode2的编号已经存在
                flag = true;   //编号存在
                break;
            }
            temp = temp.next;
        }
        if(flag){
            System.out.println(heroNode2.no + "编号存在，无法添加");
        }else {
            heroNode2.next=temp.next;
            temp.next.pre=heroNode2;
            heroNode2.pre=temp;
            temp.next=heroNode2;
        }
    }

    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //因为头节点不能动,因此设计一个辅助节点（temp）
        HeroNode2 temp = head;
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
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改节点的信息
    public void update(HeroNode2 heroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空!");
            return;
        }
        //因为头节点不能动,因此设计一个辅助节点（temp）
        HeroNode2 temp = head.next;
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

    //从双向链表中删除一个节点
    public void del(int no) {
        if (head.next == null) {
            System.out.println("空链表无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;//表示是否找到要删除的节点
        while (true) {
            if (temp == null) { //已经到链表的最后
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("没有找到编号为" + no + "的节点");
        }
    }
}


//修改一个结点的内容
//定义一个HeroNode2类,每一个HeroNode对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;//指向前一个节点

    //构造器
    public HeroNode2(int no, String name, String nickname) {
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
