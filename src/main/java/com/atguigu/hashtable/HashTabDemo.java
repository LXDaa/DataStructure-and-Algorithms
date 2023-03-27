package com.atguigu.hashtable;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查询雇员");
            System.out.println("del: 删除雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入雇员的id:");
                    int id = scanner.nextInt();
                    System.out.println("请输入雇员的name:");
                    String name = scanner.next();
                    hashTab.add(new Emp(id, name));
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的雇员的id:");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "del":
                    System.out.println("请输入要删除的雇员的id:");
                    id = scanner.nextInt();
                    hashTab.del(id);
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }

    }
}

//创建hashTab用来管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArr;
    private int size;//表示共有多少条链表

    public HashTab(int size) {
        this.size = size;
        //初始化empLinkedListArr
        empLinkedListArr = new EmpLinkedList[size];
        //不要忘记初始化每一条链表
        for (int i = 0; i < empLinkedListArr.length; i++) {
            empLinkedListArr[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id,得到该员工应该添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArr[empLinkedListNo].add(emp);

    }

    //遍历所有的链表,遍历HashTab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArr[i].list(i);
        }
    }

    //根据id查找雇员
    public void findEmpById(int id) {
        Emp emp = empLinkedListArr[hashFun(id)].findEmpById(id);
        if (emp != null) {
            System.out.println("在第" + (hashFun(id) + 1) + "条链表中找到雇员id=" + id);

        } else {
            System.out.println("没有找到该雇员！");
        }
    }

    //根据id删除雇员
    public void del(int id) {
        empLinkedListArr[hashFun(id)].del(id);
    }


    //编写散列函数，使用一个简单的取模法
    public int hashFun(int id) {
        return id % size;
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;  //next默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList,表示链表
class EmpLinkedList {
    //头指针,指向第一个Emp
    private Emp head;//默认为空

    //添加雇员到链表
    //说明
    //假定，当添加雇员时，id是自增长的，即id的分配总是从小到大
    //因此我们直接将雇员直接加到链表的最后即可
    public void add(Emp emp) {
        //如果添加的是第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果添加的不是第一个雇员，则使用一个辅助的指针，帮助我们找到最后的链表
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        //退出时，直接将Emp加到链表的最后
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "条链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + "条链表的雇员信息为：");
        Emp curEmp = head;
        while (true) {
            System.out.print("  => id=" + curEmp.id + ",name=" + curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();

    }

    //根据id查找雇员
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空！");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;//此时curEmp指向要查找的雇员
            }
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    //根据id删除雇员
    public void del(int id) {
        if (head == null) {
            System.out.println("当前链表为空,删除id不存在");
            return;
        }

        if (head.id == id) {
            head = head.next;
            System.out.println("删除成功");
            return;
        }
        //这里指向待删除id的前一个节点
        Emp curEmp = head;
        boolean flag = false;
        while (true) {
            if (curEmp.next.id == id) {
                flag = true;
                break;
            }
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        if (flag) {
            curEmp.next = curEmp.next.next;
            System.out.println("删除成功");
        } else {
            System.out.println("删除id不存在");
        }


    }
}
