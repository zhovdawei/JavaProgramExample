package com.zdw.oom;

/**
 * -Xss128k
 * */
public class TestStackOverflow1 {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable{
        TestStackOverflow1 sof = new TestStackOverflow1();
        try {
            sof.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length : "+sof.stackLength);
            throw e;
        }
    }
}
