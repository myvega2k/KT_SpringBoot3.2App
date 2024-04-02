package com.kt.myspringboot.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MyRunner implements ApplicationRunner {
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("VM Argument : " + args.containsOption("foo"));
        System.out.println("Program Argument : " + args.containsOption("bar"));

        //람다식 구문
        args.getOptionNames()//java.util.Set
                .forEach(name -> System.out.println(name));  //Iterable
        /*
         forEach(Consumer)
         Consumer의 추상메서드 void accept(T t);
         */

        //Anonymous Inner Class
        args.getOptionNames().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

    }
}