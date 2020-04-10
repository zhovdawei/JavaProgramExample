package mutithread;

import java.util.HashSet;
import java.util.Set;

class Person{

    private String name;
    private int age;

    public Person(String name,int age){
        this.name =name;
        this.age=age;
    }

    public Person(Person son){
        this.name = son.name;
        this.age = son.age;
    }


    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return "Person=>[name:"+name+" , age:"+age+"]";
    }



}

public class FinalTest {

    private final Set<Person> myset = new HashSet<>();

    private final Person[] myp = new Person[12];

//    public void changeSet(){
//        this.myset = new HashSet<>(23);
//    }

    public void addInt(int i){
        System.out.println(myset);
        myset.add(new Person("zdw",34));
        System.out.println(myset);
    }

    public Person getOne(){
        Person person = myp[0];
        return person==null?null:person;
    }

    public Person getOtherOne(){
        Person person = myp[0];
        return person==null?null:new Person(person);
    }


    public static void main(String[] args) {
        FinalTest test = new FinalTest();
        System.out.println(test.myp);
        test.myp[0] = new Person("zdw",12);
        System.out.println(test.myp);

        System.out.println("====================");
        System.out.println(test.myp[0]);
        Person person = test.getOtherOne();
        person.setName("周大伟");
        System.out.println(test.myp[0]);
    }

}
