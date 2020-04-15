package reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public void setTarget(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        AopUtil aop = new AopUtil();
        aop.beforeRealMethod();
        args[0] = args[0]+"[AOP]";
        Object obj = method.invoke(target,args);
        aop.afterRealMethod();
        return obj;
    }
}
