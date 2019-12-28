package com.zdw.allocate;

/**
 * -XX:PretenureSizeThreshold=3145728
 * 设置大对象的阈值(byte),超过会被分配到老年代
 * */
public class BigObjAllocateTest {

    private static final int _1MB = 1024 * 1024;

    public static void test() {
        byte[] allocation1;
        allocation1 = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
        test();
    }

}
