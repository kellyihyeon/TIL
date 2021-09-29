package com.heaven.mvc.expert001_02;

/**
 * 스프링 없이 의존성 주입하기 1 - 생성자를 통한 의존성 주입
 */
public class Driver {

    public static void main(String[] args) {
        Tire tire = new KoreaTire();
//        Tire tire = new AmericaTire();
        Car car = new Car(tire);

        System.out.println(car.getTireBrand());
    }
}
