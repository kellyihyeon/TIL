package com.heaven.mvc.expert006;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

public class Car {

//    @Autowired
//    @Qualifier("tire1")
    @Resource(name = "tire")
    Tire tire;

    public String getTireBand() {
        return "장착된 타이어: " + tire.getBrand();
    }
}
