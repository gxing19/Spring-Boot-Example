package com.springboot.activemq.hashmap;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest<K, V> {

    @Test
    public void capacity() {
        Map<Object, String> map = new HashMap<>(4);
        map.put(1, "aa");
        map.put(2, "bb");
        map.put(1.0, "cc");
        map.put(2.0, "dd");
        map.put(3, "ee");

        int MAXIMUM_CAPACITY = 1 << 30;
        int cap = 16;
//        int n = cap;
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        int count = (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
        System.out.println(count);
    }

    // static hash 方法
    @Test
    public void hashValue() {
//        Integer key = 1;
        String key = "kitty";
        int h = key.hashCode();
        System.out.println("key.hashCode：" + h);
        String str = Integer.toBinaryString(h);
        System.out.println("二进制:" + str);

        int val = h >>> 16;
        System.out.println("h >>> 16：" + val);

        int result = h ^ val;
        System.out.println("h ^ val：" + result);

        int hashValue = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Test
    public void arrayIndex() {
        int n = 16;

        for (int i = 0; i < 20; i++) {
            int key = i;

            int keyHash = new Integer(key).hashCode();

            int val = keyHash >>> 16;
            int hash = keyHash ^ val;

//            int index = ((n - 1) & hash);
            int index = hash % n;
            System.out.print(index + ", ");
        }
        System.out.println();

    }

}

