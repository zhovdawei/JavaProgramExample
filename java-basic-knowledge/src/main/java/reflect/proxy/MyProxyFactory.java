package reflect.proxy;

import java.lang.reflect.Proxy;

public class MyProxyFactory {

    public static Object createProxy(Object target){
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.setTarget(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),handler);
    }

    public static void main(String[] args) {
        CommonCall call = new HuaweiCall();
        CommonCall proxy = (CommonCall) createProxy(call);
        System.out.println(proxy.call("Jack"));
        System.out.println(proxy.msg("Rose"));

    }
}
