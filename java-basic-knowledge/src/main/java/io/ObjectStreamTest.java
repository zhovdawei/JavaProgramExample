package io;

import java.io.*;

class Human implements Serializable {

    private static final long serialVersionUID = 2348234824184671L;

    private String name;
    private int age;
    private boolean sex;

    public Human(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}

public class ObjectStreamTest {

    public void test1() throws IOException {
        Human human = new Human("徐皮皮", 16, false);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\test\\code\\obj.txt"));
        oos.writeObject(human);
        oos.flush();
        oos.close();
    }

    public void test2() throws IOException,ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\test\\code\\obj.txt"));
        Human human = (Human) ois.readObject();
        System.out.println(human);
        ois.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectStreamTest test = new ObjectStreamTest();
        test.test1();
        test.test2();
    }


}
