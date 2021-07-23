package com.springboot.example.demo.interview;

import java.util.Random;
import java.util.Stack;

/**
 * 栈：一种线型数据结构，先进后出，后进先出。可以看做只有一个开口的圆桶容器。
 * 问：定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
 * 思路一：使用两个栈，一个完成数据的出栈入栈操作，另一个辅助栈存储每次操作之后得到的最小值。
 */
public class Demo1 {

    Stack stack1 = new Stack();
    Stack stack2 = new Stack();

    int min = 0;

    /**
     * 入栈
     */
    public void push(int node) {
        stack1.push(node);
        //只有一个元素时
        if (stack1.size() == 1) {
            min = (int) stack1.peek();
        } else {
            //每加入一个元素，都需要将其与最小值比较，判断此次操作后的最小值
            if (min > node) {
                min = node;
            }
        }
        //栈顶一直维持最小,加入
        stack2.push(min);
    }

    /**
     * 出栈
     */
    public void pop() {
        //如果出栈的元素大于min,那么stack2的栈顶元素与倒数第二个是相同的，所以删掉一个后，最小值仍然是栈顶元素
        stack1.pop();
        //无论栈顶元素与min是否相等，都需要向下移动一个，要保证辅助栈stack2中值与操作的一致                                       性
        stack2.pop();
        min = (int) stack2.peek();
    }

    /**
     * 栈顶
     *
     * @return
     */
    public int top() {
        return (int) stack1.peek();
    }

    /**
     * 最小
     *
     * @return
     */
    public int min() {
        return min;
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int anInt = random.nextInt(100);
            demo1.push(anInt);
        }
        System.out.println(demo1.min());

        for (int i = 0; i < 10; i++) {
            demo1.pop();
        }
        System.out.println(demo1.min());
    }
}
