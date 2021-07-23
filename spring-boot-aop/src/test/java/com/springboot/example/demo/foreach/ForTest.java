package com.springboot.example.demo.foreach;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ForTest {

    public static void main(String[] args) {
        forArray();
        forArrayList();
        forLinkList();
    }

    public static void forArray() {
        Integer[] numArray = new Integer[5000000];
        for (int i = 0; i < 5000000; i++) {
            numArray[i] = i;
        }

        Long start = System.currentTimeMillis();
        for (int i = 0; i < numArray.length; i++) {
            Integer integer = numArray[i];
        }
        System.out.print("Array for:");
        System.out.println(System.currentTimeMillis() - start);

        Long start2 = System.currentTimeMillis();
        for (Integer num : numArray) {

        }
        System.out.print("Array foreach:");
        System.out.println(System.currentTimeMillis() - start2);

    }

    public static void forArrayList() {
        List<Integer> list = new ArrayList<>(5000000);
        for (int i = 0; i < 5000000; i++) {
            list.add(i);
        }

        Long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        System.out.print("ArrayList for:");
        System.out.println(System.currentTimeMillis() - start);

        Long start2 = System.currentTimeMillis();
        for (Integer num : list) {

        }
        System.out.print("ArrayList foreach:");
        System.out.println(System.currentTimeMillis() - start2);

        Long start3 = System.currentTimeMillis();
        list.forEach(e -> {});
        System.out.print("ArrayList lambada foreach:");
        System.out.println(System.currentTimeMillis() - start3);

        Long start4 = System.currentTimeMillis();
        list.stream().forEach(p ->{});
        System.out.print("ArrayList stream foreach:");
        System.out.println(System.currentTimeMillis() - start4);
    }

    public static void forLinkList() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 200000; i++) {
            list.add(i);
        }

        Long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        System.out.print("LinkList for:");
        System.out.println(System.currentTimeMillis() - start);

        Long start2 = System.currentTimeMillis();
        for (Integer num : list) {

        }
        System.out.print("LinkList foreach:");
        System.out.println(System.currentTimeMillis() - start2);

        Long start3 = System.currentTimeMillis();
        list.forEach(e ->{});
        System.out.print("LinkList lambda foreach:");
        System.out.println(System.currentTimeMillis() - start3);

        Long start4 = System.currentTimeMillis();
        list.stream().forEach(p -> {});
        System.out.print("LinkList stream foreach:");
        System.out.println(System.currentTimeMillis() - start4);
    }
}
/*
Array for:2
Array foreach:2
ArrayList for:3
ArrayList foreach:13
ArrayList lambada foreach:43
ArrayList stream foreach:13
LinkList for:14931
LinkList foreach:3
LinkList lambda foreach:4
LinkList stream foreach:2

Array for:2
Array foreach:2
ArrayList for:3
ArrayList foreach:15
ArrayList lambada foreach:43
ArrayList stream foreach:13
LinkList for:14335
LinkList foreach:4
LinkList lambda foreach:7
LinkList stream foreach:2

Array for:2
Array foreach:2
ArrayList for:4
ArrayList foreach:13
ArrayList lambada foreach:41
ArrayList stream foreach:12
LinkList for:14206
LinkList foreach:5
LinkList lambda foreach:4
LinkList stream foreach:2
**/