package reflect.proxy;

public class AppleCall implements CallService {
    @Override
    public String call(String msg) {
        return "apple call => "+msg;
    }
}
