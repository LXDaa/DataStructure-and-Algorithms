package com.atguigu.linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        //测试构建环形链表和遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.list();
        //测试小孩出圈是否正确
        circleSingleLinkedList.countBoy(1, 2, 5);

    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个First节点
    private Boy first = null;

    //添加小孩节点，构成一个环形链表
    public void addBoy(int nums) {
        //对nums进行数据校验
        if (nums < 1) {
            System.out.println("nums值不正确");
            return;
        }
        //创建辅助节点,帮助创建环形链表
        Boy curBoy = null;
        //使用for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环形链表
    public void list() {
        if (first == null) {
            System.out.println("没有任何小孩");
            return;
        }
        //first不能动，设置一个辅助节点
        Boy curBoy = first;
        while (true) {
            System.out.println("小孩的编号:" + curBoy.getNo());
            if (curBoy.getNext() == first) { //遍历完毕
                break;
            }
            curBoy = curBoy.getNext();
        }
    }
    //根据用户输入，计算小孩出圈的顺序

    /**
     * @param startNo  开始的小孩
     * @param countNum 数多少下
     * @param nums     总的小孩数量
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 0 || startNo > nums) {
            System.out.println("参数输入有误,请重新输入");
            return;
        }
        //创建辅助指针
        Boy heaper = first;
        while (true) {
            //事先让heaper指向环形链表的最后一个节点
            if (heaper.getNext() == first) {  //heaper指向最后的节点
                break;
            }
            heaper = heaper.getNext();
        }
        //小孩报数前，先让first和heaper移动k-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            heaper = heaper.getNext();
        }
        //当小孩报数时，让first和heaper移动m-1次，然后出圈
        //这里是循环,直到圈中只有一个人
        while (true) {
            if (heaper == first) {
                break;
            }
            //让first和heaper移动m-1次
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                heaper = heaper.getNext();
            }
            //此时first指向的节点，就是要出圈的小孩
            System.out.println("出圈的小孩是：" + first.getNo());
            //让first指向下一个节点
            first = first.getNext();
            //出圈
            heaper.setNext(first);
        }
        System.out.println("最后的小孩编号" + first.getNo());
    }

}


//创建一个Boy类，代表一个节点
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
