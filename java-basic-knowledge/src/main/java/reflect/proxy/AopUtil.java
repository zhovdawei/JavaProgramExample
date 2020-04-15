package reflect.proxy;

public class AopUtil {

    public void beforeRealMethod(){
        System.out.println("在被代理方法执行前处理...");
    }

    public void afterRealMethod(){
        System.out.println("在被代理方法执行后处理...");
    }



}
