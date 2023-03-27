package com.atguigu.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "30+2*6-2";
        //创建两个栈，数栈，符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int result = 0;
        char ch = ' ';//将每次扫描的得到的char保存到ch中
        String keepNum = "";//用于拼接多位数
        //开始while循环扫描expression
        while (true) {
            //依次得到expression中的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是数字还是操作符，然后做相应的处理
            if (operStack.isOper(ch)) { //如果是运算符
                //判断operStack是不是空栈
                if (operStack.isEmpty()) {
                    //是空栈，直接push
                    operStack.push(ch);
                } else {
                    //不是空栈,比较优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        //当前要加入的运算符的优先级小于等于当前栈中的运算符的优先级
                        //从numStack中pop出两个值与operStack栈顶的运算符，进行运算
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.cal(num1, num2, oper);
                        //将结果重新push到numStack中
                        numStack.push(result);
                        //将操作符存到operStack
                        operStack.push(ch);
                    } else {
                        //当前要加入的运算符的优先级大于当前栈中的运算符的优先级
                        //直接push到operStack中
                        operStack.push(ch);
                    }
                }
            } else {
                //否则是数字
                //处理多位数
                keepNum += ch;
                //如果ch已经是expression的最后一位，则不需要继续判断，直接push
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个是不是数字，如果是数字继续扫描，否则就直接push
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //是运算符
                        numStack.push(Integer.parseInt(keepNum));
                        //!!!!!!!清空keepNum
                        keepNum = "";
                    } else {
                        numStack.push(ch - 48);  //因为得到的是字符'',转为数字-48
                    }
                }

            }
            //让index+1，判断是否扫描到expression的结尾
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //扫描完毕，顺序的从numStack和operStack中pop出相应的值和运算符进行运算
        while (true) {
            //如果符号栈为空，则计算结束了，numStack中留下的就是结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1, num2, oper);
            //将结果重新push到numStack中
            numStack.push(result);
        }
        System.out.println("表达式" + expression + "的结果是：" + numStack.pop());

    }
}

//先创建一个栈
//定义一个ArrayStack表示栈
class ArrayStack2 {
    private int maxSize;  //栈的大小
    private int[] stack;  //用数组模拟栈，将数据保存到数组中
    private int top = -1;   //栈顶

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //查看栈顶的值
    public int peek() {
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断是否栈满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;

    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈,从栈顶显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);

        }
    }

    //返回运算级的优先顺序
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; //假定表达式中只有+-*/
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;  //注意顺序
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }
}