package reflect.myobj;

public class Person extends Species{

    private String name;
    private int age;
    private boolean sex;
    public double weight;
    public double height;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Integer age, Boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    private void methodOne(){
        System.out.println("私有无参无返回方法");
    }

    private void methodTwo(int age,boolean sex){
        System.out.println("私有有参无返回方法");
        this.age = age;
        this.sex = sex;
    }

    private boolean methodThree(){
        System.out.println("私有无参有返回方法");
        return true;
    }

    private boolean methodFour(Integer age,Boolean sex){
        System.out.println("私有有参有返回方法");
        this.age = age;
        this.sex = sex;
        return true;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isSex() {
        return sex;
    }
}
