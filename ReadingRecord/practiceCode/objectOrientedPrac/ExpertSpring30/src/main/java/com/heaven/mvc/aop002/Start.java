package com.heaven.mvc.aop002;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop002/aop002.xml", Start.class);

        Person romeo = context.getBean("boy", Person.class);
        Person juliet = context.getBean("girl", Person.class);

        romeo.runSomething();
        System.out.println();
        juliet.runSomething();
    }
}
