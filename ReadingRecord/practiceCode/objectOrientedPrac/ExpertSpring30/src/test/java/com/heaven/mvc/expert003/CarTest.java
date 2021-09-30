package com.heaven.mvc.expert003;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/expert003.xml")
class CarTest {

    @Autowired
    Car car;

    @Test
    public void 자동차_코리아타이어_장착_타이어브랜드_테스트() {
        assertEquals("장착된 타이어: 코리아 타이어", car.getTireBand());
    }


}