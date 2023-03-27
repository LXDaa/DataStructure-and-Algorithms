package com.atguigu.stack;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式转成后缀表达式的功能
        //说明：
        //1、1+((2+3)×4)-5 => 转成 1 2 3 + 4 * + 5
        //2、因为直接对字符串进行操作不方便，先将这个表达式存到ArrayList中
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpression = toInfixExpression(expression);
        System.out.println(infixExpression);
        System.out.println(parseSuffixExpressionList(infixExpression));
        System.out.println(calculate(parseSuffixExpressionList(infixExpression)));

      /*  //先定义一个逆波兰表达式
        String suffixExpression = "3 4 + 5 * 6 -";
        //1、先将"3 4 + 5 * 6 -"放到ArrayList数组中
        //2、将ArrayList传递给一个方法，遍历ArrayList配合栈 完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println(calculate(list));*/
    }

    //将得到的中缀表达式List  转为   后缀表达式的List
    public static List<String> parseSuffixExpressionList(List<String> list) {
        Stack<String> s1 = new Stack<>();//符号栈
        //s2在整个转为过程中没有pop，而且后面需要逆序输出，我们使用List替代
        List<String> s2 = new ArrayList<>();//存储中间结果
        //Stack<String> s2 = new Stack<>();//中间结果栈

        //遍历list
        for (String item : list) {
            //如果是一个数，入栈s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                //入符号栈s1
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是），则依次弹出s1栈顶的运算符，并加到s2中，直到遇见），然后将这对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//此时s1中还剩（，pop出来，因此就消掉了一对括号
            } else {
                //当item的优先级小于等于s1栈顶运算符，将s1的栈顶运算符加到s2中，再次与s1中的新栈顶运算符比较
                //缺少比较优先级的方法
                while (s1.size() != 0 &&Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        //将s1中剩余的一次加到s2中
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;//因为存放到list中，是按顺序的，因此输出的就是后缀表达式对应的list
    }

    //将中缀表达式转为对应的List
    public static List<String> toInfixExpression(String s) {
        //定义一个List存放中缀表达式对应的内容
        List<String> list = new ArrayList<>();
        int i = 0;//这是一个指针，用于遍历中缀表达式
        String str;//对多位数的拼接
        char c;//每遍历到一个字符，就放到c中
        do {
            //如果c是一个非数字，就加到list中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add(c + "");
                i++; //i需要后移
            } else {
                //是一个数字，考虑多位数
                str = "";//先将str置空“ ”  '0'[48]  ->  '9'[57]
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c; //拼接
                    i++;
                }
                list.add(str);
            }

        } while (i < s.length());
        return list;
    }

    //将一个逆波兰表达式中的数据依次放到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression根据“ ”进行分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> ls) {
        //创建一个栈
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls) {
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")) {
                //入栈
                stack.push(item);
            } else {
                //运算符，从栈中pop两个数进行运算，结果在push到栈中
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num2 + num1;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有问题");
                }
                //将结果入栈
                stack.push(res + "");
            }
        }
        //最后留在栈中的就是运算结果
        return Integer.parseInt(stack.pop());

    }
}

//编写一个类Operation可以返回运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回对应的优先级数字
    public static int getValue(String operation) {
        int res = 0;
        switch (operation) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return res;
    }
}