package com.mytests.spring.webflux.functionaljavatest0.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/1/2019.
 * Project: functional-java-test0
 * *******************************
 */
@Repository
public class PersonsRepo {
    private List<Person> persons = new ArrayList<>();

    public PersonsRepo() {
         persons.add(new Person("1", "irina", 49));
         persons.add(new Person("2", "andrey", 49));
         persons.add(new Person("3", "vera", 23));
         persons.add(new Person("4", "lena", 49));
         persons.add(new Person("5", "katya", 26));
    }
    public Mono<Person> getPersonById(String id){
        Person person = new Person("null", "null", 0);
        for (Person person1 : persons) {
            if(person1.getId().equals(id)){person = person1;};
        }
        return Mono.just(person);
    }
    public Flux<Person> getPersonsByName(String name){
        List<Person> rez = new ArrayList<>();
        for (Person person : persons) {
            if (person.getName().equals(name)){rez.add(person);}
        }
        return Flux.fromIterable(rez);
    }

    public Flux<Person> getAllpersons() {
        return Flux.fromIterable(persons);
    }


}
