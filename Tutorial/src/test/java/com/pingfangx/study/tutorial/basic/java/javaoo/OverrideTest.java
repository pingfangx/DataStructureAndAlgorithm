package com.pingfangx.study.tutorial.basic.java.javaoo;

/**
 * @author pingfangx
 * @date 2019/9/26
 */
public class OverrideTest {
    public static class Animal {
        public static void testClassMethod() {
            System.out.println("The static method in Animal");
        }

        public void testInstanceMethod() {
            System.out.println("The instance method in Animal");
        }
    }

    public static class Cat extends Animal {
        public static void testClassMethod() {
            System.out.println("The static method in Cat");
        }

        public void testInstanceMethod() {
            System.out.println("The instance method in Cat");
        }
    }

    public static void main(String[] args) {
        Cat myCat = new Cat();
        Animal myAnimal = myCat;
        Animal.testClassMethod();//The static method in Animal
        Cat.testClassMethod();//The static method in Cat
        myAnimal.testClassMethod();//The static method in Animal
        myCat.testClassMethod();//The static method in Cat
        new Animal().testClassMethod();//The static method in Animal
        new Cat().testClassMethod();//The static method in Cat
        myAnimal.testInstanceMethod();//The instance method in Cat
    }
}
