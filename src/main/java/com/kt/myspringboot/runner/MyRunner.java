package com.kt.myspringboot.runner;

import com.kt.myspringboot.dto.AccountDTO;
import com.kt.myspringboot.property.MybootProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    private MybootProperties properties;

    @Value("${myboot.name}")
    private String name;

    @Value("${myboot.age}")
    private int age;

    @Autowired
    private AccountDTO account;
    private Logger logger = LoggerFactory.getLogger(MyRunner.class);

    public void run(ApplicationArguments args) throws Exception {
        logger.info("Logger 구현 클래스 이름 = {}", logger.getClass().getName());
        logger.info("현재의 Mode  = {} {}" ,account.getMode(), account.getUsername());
        logger.info("MybootProperties fullName = {}", properties.getFullName());

        logger.info("myboot.name 환경변수 = " + name);
        logger.info("myboot.age 환경변수 = " + age);
        logger.info("myboot.fullName 환경변수 " + environment.getProperty("myboot.fullName"));
        logger.info("port 번호 " + environment.getProperty("local.server.port"));

        logger.debug("VM Argument : " + args.containsOption("foo"));
        logger.debug("Program Argument : " + args.containsOption("bar"));

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