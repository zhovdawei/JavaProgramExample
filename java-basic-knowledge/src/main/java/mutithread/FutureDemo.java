package mutithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class Animal{

    private final String name;
    private final int age;

    public Animal(String name,int age){
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString(){
        return "Animal[name="+name+",age="+age+"]";
    }
}

class AnimalCall implements Callable<Animal> {

    private final String name;

    public AnimalCall(String animal){
        this.name = animal;
    }

    @Override
    public Animal call() throws Exception {
        int age = (int)(Math.random()*9)+1;
        Animal animal = new Animal(name+"-"+Thread.currentThread().getName(),age);
//        System.out.println(animal);
        return animal;
    }
}

public class FutureDemo {

    private final FutureTask<Animal> future ;

    public FutureDemo(String name){
        future = new FutureTask<>(new AnimalCall(name));
    }

    public Animal get() {
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        FutureDemo demo1 = new FutureDemo("牧羊犬");
        FutureDemo demo2 = new FutureDemo("田园犬");
        FutureDemo demo3 = new FutureDemo("哈士奇");
        FutureDemo demo4 = new FutureDemo("阿拉斯加");
        FutureDemo demo5= new FutureDemo("萨摩耶");
        for (int i = 0; i < 5; i++) {
            switch (i){
                case 0:
                    new Thread(demo1.future).start();
                    break;
                case 1:
                    new Thread(demo2.future).start();
                    break;
                case 2:
                    new Thread(demo3.future).start();
                    break;
                case 3:
                    new Thread(demo4.future).start();
                    break;
                case 4:
                    new Thread(demo5.future).start();
                    break;
                default:
                    break;
            }
        }
        Animal animal = demo1.get();
        System.out.println(animal);

        Animal anima2 = demo2.get();
        System.out.println(anima2);

        Animal anima3 = demo3.get();
        System.out.println(anima3);

        Animal anima4 = demo4.get();
        System.out.println(anima4);

        Animal anima5 = demo5.get();
        System.out.println(anima5);
    }


}
