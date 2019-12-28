package com.zdw.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args : -Xms5m -Xmx5m -XX:+HeapDumpOnOutOfMemoryError
 * */
public class TestHeapOutofMemory {

    static class HeapOOM{
        private int[] ints = new int[100];
    }


    public static void main(String[] args) {
        List<HeapOOM> list =new ArrayList<>();
        while (true){
            list.add(new HeapOOM());
        }
    }

}
// -XX:+PrintGCDetails