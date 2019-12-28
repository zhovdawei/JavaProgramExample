package com.zdw.allocate;

/**
 * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -Xms20m 堆最小20m
 * -Xmx20m 堆最大20m
 * -Xms20m -Xmx20m 设置一样时堆不可扩展
 * -Xmn10m 新生代10m
 * -XX:+PrintGCDetails 打印GC日志
 * -XX:SurvivorRatio=8 设置Eden和survivor的比例
 * */
public class EdenAllocateTest {

    private static final int _1MB = 1024 * 1024;

    public static void test() {
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
        test();
    }

}
