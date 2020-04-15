package reflect.test;
class Father {
    static int b = 2;
    static {
        System.out.println("父类被加载");
    }
}
class C extends Father {
    static {
        System.out.println("子类被加载");
        m = 300;
    }
    static int m = 100;
    static final int M = 1;
}
public class ClassLoadingTest {
    public static void main(String[] args) throws ClassNotFoundException {


    }
}
