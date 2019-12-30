package com.zdw.tool;

public class JhsdbTest {

    static class Test{
        static ObjectHolder staticObje = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo(){
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");
        }
    }

    private static class ObjectHolder{}

    public static void main(String[] args) {
        Test test = new JhsdbTest.Test();
        test.foo();
    }

}
