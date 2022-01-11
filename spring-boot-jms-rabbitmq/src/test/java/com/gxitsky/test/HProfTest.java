package com.gxitsky.test;

public class HProfTest {

    public static void main(String[] args) {
        HProfTest hProfTest = new HProfTest();
        hProfTest.slowMethod();
        hProfTest.slowerMethod();
        hProfTest.fastMethod();
    }

    public void slowMethod(){
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void slowerMethod(){
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fastMethod(){
        try {
            Thread.yield();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
