package reflect.proxy;

public class HuaweiCall implements CommonCall {

    @Override
    public String msg(String msg) {
        return "Huawei msg => "+msg;
    }

    @Override
    public String call(String msg) {
        return "Huawei call => "+msg;
    }
}
