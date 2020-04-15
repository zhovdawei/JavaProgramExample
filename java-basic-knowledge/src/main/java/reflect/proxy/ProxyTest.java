package reflect.proxy;


import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static CallService createStaticProxy(CallService callService) {
        CallService proxyCall = new ProxyCall(callService);
        return proxyCall;
    }

    private static CallService createDynamicProxy(CallService callService) {
        CallService service = (CallService) Proxy.newProxyInstance(CallService.class.getClassLoader(),
                new Class[]{CallService.class},
                (Object proxy, Method method, Object[] args) -> {
                    System.out.println("动态代理前...");
                    String param = (String) args[0] + "[DynamicProxy]";
                    args[0] = param;
                    Object result = method.invoke(callService, args);
                    System.out.println("动态代理后...");
                    return result;
                }
        );
        return service;
    }

    public static void main(String[] args) {
        CallService appleCall = new AppleCall();
        CallService proxy_1 = createStaticProxy(appleCall);
        CallService proxy_2 = createDynamicProxy(appleCall);
        System.out.println(proxy_1.call("Jack"));
        System.out.println(proxy_2.call("Rose"));
    }
}
