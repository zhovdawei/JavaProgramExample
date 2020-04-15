package reflect;

import reflect.myobj.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    public static Person getObj(String name,int age,boolean sex) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String className = "reflect.myobj.Person";
        Class clazz = Class.forName(className);
        Constructor con = clazz.getConstructor(String.class,Integer.class,Boolean.class);
        Person person = (Person)con.newInstance(name,age,sex);
        return person;
    }

    public static boolean getMethod(int age,boolean sex) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String className = "reflect.myobj.Person";
        Class clazz = Class.forName(className);
        Method method = clazz.getDeclaredMethod("methodFour", Integer.class, Boolean.class);
        Person person = (Person) clazz.newInstance();
        method.setAccessible(true);
        boolean flag = (Boolean) method.invoke(person,age,sex);
        System.out.println(person);
        return flag;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        Person person = getObj("周大伟",25,true);
//        System.out.println(person);
        System.out.println(getMethod(18,true));
    }

}
