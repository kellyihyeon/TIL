package com.heaven.mvc.expert001_01;

public class Car {
    Tire tire;

    public Car() {
        tire = new KoreaTire();
//        tire = new AmericaTier();
    }

    public String getTireBrand() {
        return "장착된 타이어: " + tire.getBrand();
    }
}
