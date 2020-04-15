package reflect.test;

class A {

    static {
        m = 300;
    }

    static int m = 100;

}

class B {

    static int m = 100;

    static {
        m = 300;
    }

}

public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println(A.m);
        System.out.println(B.m);
    }
}
