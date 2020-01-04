package com.zdw.clazz;

class Parent{
    static {
        System.out.println("parent init!");
    }

    public static int value = 123;

}

class Son extends Parent{
    static {
        System.out.println("son init!");
    }

}

class Const{
    static {
        System.out.println("cont init!");
    }

    public static final String HELLO = "Hello 你好！";

    public static String wolrd = "Hello world!";

}


public class PassivRef {

    public static void main(String[] args) {
        /* example 1 */
//        System.out.println(Son.value);

        /* example 2 */
//        Parent[] parents = new Parent[10];

        /* example 3 */
        System.out.println(Const.HELLO);
    }

}
