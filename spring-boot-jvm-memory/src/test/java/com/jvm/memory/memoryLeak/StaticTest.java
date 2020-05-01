package com.jvm.memory.memoryLeak;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.nio.ByteBuffer;
import java.util.HashMap;

public class StaticTest {

    private HashMap<String, String> hashMap = new HashMap<>();
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void populateList() throws InterruptedException {
        for (int i = 0; i < 100000; i++) {
            hashMap.put("key" + i, "value" + i);
            Thread.sleep(100);
        }
        /*try {
            threadLocal.set("value");
            //...some processing......
        } finally {
            threadLocal.remove();
        }*/

    }

    public static void main(String[] args) throws InterruptedException {
        /*StaticTest staticTest = new StaticTest();
        new StaticTest().populateList();*/

        for (int i = 0; i < 1024; i++) {
            ByteBuffer.allocateDirect(1024 * 1024);
            System.out.println(i);
//            System.gc();
        }
    }
}
