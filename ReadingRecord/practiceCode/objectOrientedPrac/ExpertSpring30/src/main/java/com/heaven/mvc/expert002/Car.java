package com.heaven.mvc.expert002;

public class Car {
    Tire tire;


    public Tire getTire() {
        return tire;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
    }

    public String getTireBand() {
        return "장착된 타이어: " + tire.getBrand();
    }
}
