package reflect.proxy;

public class ProxyCall implements CallService {

    private CallService callService;

    public ProxyCall(CallService callService) {
        this.callService = callService;
    }

    @Override
    public String call(String msg) {
        System.out.println("静态代理前...");
        msg = msg +"[StaticProxy]";
        String result = callService.call(msg);
        System.out.println("静态代理后...");
        return result;
    }

}
