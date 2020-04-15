package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClazzTest {

    public static void getPublicField() throws ClassNotFoundException {
        String str = "reflect.myobj.Person";
        Class clazz = Class.forName(str);
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

    public static void getPrivateField() throws ClassNotFoundException {
        String str = "reflect.myobj.Person";
        Class clazz = Class.forName(str);
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

    public static void getPublicMethod() throws ClassNotFoundException {
        String str = "reflect.myobj.Person";
        Class clazz = Class.forName(str);
        Method[] methods = clazz.getMethods();
        System.out.println("公共方法："+methods.length+"个");
        for (Method method : methods) {
            System.out.print(method.getName()+" , ");
        }
    }

    public static void getPrivateMethod() throws ClassNotFoundException {
        String str = "reflect.myobj.Person";
        Class clazz = Class.forName(str);
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("公共方法+私有方法："+methods.length+"个");
        for (Method method : methods) {
            System.out.print(method.getName()+" , ");
        }
    }

    public static void setField() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        String str = "reflect.myobj.Person";
        Class clazz = Class.forName(str);
        Object obj = clazz.newInstance();
        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        field.set(obj, "David");
        Object name = field.get(obj);
        System.out.println(name);
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        getPrivateMethod();
        System.out.println("===========================");
        getPublicMethod();
    }

}
