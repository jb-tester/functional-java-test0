package com.mytests.spring.webflux.functionaljavatest0.data;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/1/2019.
 * Project: functional-java-test0
 * *******************************
 */
public class Person {
    String id;
    String name;
    int age;

    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
