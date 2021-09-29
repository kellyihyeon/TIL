package com.heaven.mvc.expert002;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {

    public static void main(String[] args) {
        // 종합 쇼핑몰에 대한 정보
        ApplicationContext context = new ClassPathXmlApplicationContext("expert002.xml");

        // 종합 쇼핑몰에서 상품에 해당하는 Car 와 Tire 를 구매하는 코드
        Car car = context.getBean("car", Car.class);

        Tire tire = context.getBean("tire", Tire.class);

        car.setTire(tire);

        System.out.println(car.getTireBand());
    }
}
