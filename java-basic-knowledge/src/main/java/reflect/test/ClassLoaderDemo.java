package reflect.test;

import java.io.InputStream;

public class ClassLoaderDemo {

    public static ClassLoader getSystemClassLoader(){
        ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
        return systemLoader;
    }

    public static ClassLoader getParentLoader(ClassLoader classLoader){
        return classLoader.getParent();
    }

    public static ClassLoader getClassByClassName(String name) throws ClassNotFoundException {
        return Class.forName(name).getClassLoader();
    }

    public InputStream getClassLoaderStream(){
        return this.getClass().getClassLoader().getResourceAsStream("test.properties");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader systemLoader = getSystemClassLoader();
        System.out.println("系统类加载器 => "+systemLoader);
        ClassLoader extensionLoader = getParentLoader(systemLoader);
        System.out.println("扩展类加载器 => "+extensionLoader);
        ClassLoader bootstrapLoader = getParentLoader(extensionLoader);
        System.out.println("引导类加载器 => "+bootstrapLoader);
        ClassLoader objectLoader = getClassByClassName("java.lang.Object");
        System.out.println("Object类的类加载器 => "+objectLoader);
        ClassLoaderDemo demo = new ClassLoaderDemo();
        ClassLoader demoLoader = getClassByClassName("reflect.test.ClassLoaderDemo");
        System.out.println("自定义类的类加载器 => "+demoLoader);
        System.out.println("类加载器的流 => "+demo.getClassLoaderStream());


    }

}
