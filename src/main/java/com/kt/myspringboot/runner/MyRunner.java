package com.kt.myspringboot.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MyRunner implements ApplicationRunner {
    @Autowired
    private Environment environment;

    @Value("${myboot.name}")
    private String name;

    @Value("${myboot.age}")
    private int age;

    public void run(ApplicationArguments args) throws Exception {
        System.out.println("myboot.name 환경변수 = " + name);
        System.out.println("myboot.age 환경변수 = " + age);
        System.out.println("myboot.fullName 환경변수 " + environment.getProperty("myboot.fullName"));

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