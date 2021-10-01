package com.heaven.mvc.aop002;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//MyAspect 를 이제 AOP 에서 사용하겠다는 의미
@Aspect
public class MyAspect {

    // 대상 메서드(runSomething()) 실행 전에 이 메서드(before())를 실행하겠다는 의미
    @Before("execution(* runSomething())")  // "컴퓨터로 게임을 한다.
    public void before(JoinPoint joinPoint) {
        System.out.println("얼굴 인식 확인: 문을 개방하라");
//        System.out.println("열쇠로 문을 열고 집에 들어간다.");
    }

}
