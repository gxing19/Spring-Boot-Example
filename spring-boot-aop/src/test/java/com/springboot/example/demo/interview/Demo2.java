package com.springboot.example.demo.interview;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * 栈：一种线型数据结构，先进后出，后进先出。可以看做只有一个开口的圆桶容器。
 * 问：定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
 * 思路二：原理同方法一，只是数据操作的栈是自定义栈，通过数组扩容完成
 */
public class Demo2 {

    private Integer[] elements = new Integer[10];
    private int size = 0;
    private int min = 0;

    private Stack<Integer> minStack = new Stack<>();

    /**
     * 入栈
     *
     * @param node
     */
    public void push(int node) {
        //确保可以再添加一个元素,如果不够就扩容
        ensureCapacity(size + 1);
        //添加元数,数组元素个数为size
        elements[size++] = node;
        if (size == 1) {
            min = node;
        } else {
            if (node < min) {
                min = node;
            }
        }
        minStack.push(min);
    }

    /**
     * 数组扩容
     *
     * @param size
     */
    public void ensureCapacity(int size) {
        int len = elements.length;
        if (size > len) {
            int newLen = len * 2;
            elements = Arrays.copyOf(elements, newLen);
        }
    }

    /**
     * 出栈
     */
    public void pop() {
        if (size >= 1) {
            elements[size - 1] = null;
            //此处需要注意，int[]数组元素不能设置为null,即使是（Integer）null，也会报空指针异常
            //因此使用 Integer[] 数组
        }
        size--;
        minStack.pop();
        min = minStack.peek();
    }

    /**
     * 栈顶
     *
     * @return
     */
    public int top() {
        if (size >= 1) {
            return elements[size - 1];
        }
        return Integer.parseInt(null);
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
        Demo2 demo1 = new Demo2();
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
