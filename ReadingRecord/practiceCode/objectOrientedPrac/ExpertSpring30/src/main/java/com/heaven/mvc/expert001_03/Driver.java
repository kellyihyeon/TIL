package com.heaven.mvc.expert001_03;

/**
 * 스프링 없이 의존성 주입하기 2 - 속성을 통한 의존성 주입
 */
public class Driver {
    public static void main(String[] args) {
        Tire tire = new KoreaTire();
        Car car = new Car();
        car.setTire(tire);

        System.out.println(car.getTireBand());
    }
}
