package test;

public class MathTest {

    static int getNum(int x){
        return x>>>1;
    }

    public static void main(String[] args) {


        System.out.println(getNum(100));
        System.out.println(getNum(101));
        System.out.println(getNum(102));
        System.out.println(getNum(103));
        System.out.println(getNum(104));
        System.out.println(getNum(105));
        System.out.println(getNum(106));
        System.out.println(getNum(107));
        System.out.println(getNum(10000));
    }

}
