package mutithread;

final class Animals {

    public final String name;
    public final int age;

    public Animals(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Animals{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

// 不可继承
/*class Dog extends Animals{
    public Dog(String name, int age) {
        super(name, age);

    }
}*/

public class FinalTest2 {
    public static void main(String[] args) {

        Animals animals = new Animals("dog",2);


    }


}
