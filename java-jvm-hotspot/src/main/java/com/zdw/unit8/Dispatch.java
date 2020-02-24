package com.zdw.unit8;

public class Dispatch {

    static class QQ{}
    static class _360{}

    public static class Father{

        public void hardChoice(QQ arg){
            System.out.println("father choose qq");
        }

        public void hardChoice(_360 arg){
            System.out.println("father choose 360");
        }
    }

    public static class Son extends Father{

        @Override
        public void hardChoice(QQ arg){
            System.out.println("Son choose qq");
        }

        @Override
        public void hardChoice(_360 arg){
            System.out.println("Son choose 360");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());
        son.hardChoice(new QQ());
        Father father1 = new Father();
        father1.hardChoice(new QQ());
    }


}
