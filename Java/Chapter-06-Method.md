# **메소드와 변수의 스코프**

## 목차
1. [메소드에 대한 이해와 메소드의 정의](#1-메소드에-대한-이해와-메소드의-정의)  
   1.1 [메소드](#11-메소드)  
   1.2 [main 메소드에 대해서 우리가 아는 것과 모르는 것](#12-main-메소드에-대해서-우리가-아는-것과-모르는-것)  
   1.3 [값을 반환하는 메소드](#13-값을-반환하는-메소드)  
   1.4 [return의 두 가지 의미](#14-return의-두-가지-의미)
<br>


# 1. 메소드에 대한 이해와 메소드의 정의

## 1.1 메소드
*메소드는 기능상자이다.
기능상자에 +1을 해주는 기능(code)을 담아놓는다고 가정하자.*

- 기능상자에 숫자 5가 들어왔을 때 이 숫자는 6이 되어 나간다.

- 이런 기능상자를 제공해주는 경우도 있지만 대개는 우리가 이런 기능상자를 만들어야 한다.

- Class에 여러 개의 기능상자들을 담아야 하고, 필요할 때마다 기능상자를 활용 한다.

- main이라는 기능상자는 특별한 기능상자이다.  
자바 가상머신은 메인 기능상자를 찾고 그 안에 있는 문장들을 하나하나 실행해 나간다.
메인 상자는 다른 기능상자에 만들어놓은 기능들을 쓸 수 있다.

절차 지향적인 성격의 설명이지만 우선은 이렇게 이해하자.
<br>


## 1.2 main 메소드에 대해서 우리가 아는 것과 모르는 것
```java
public static void main(String[] args) {
    int num1 = 5;
    int num2 = 7;
    System.out.println("5 + 7 = " + (num1 + num2));
}
```
### 1.2.1 아는 것
- 메소드의 이름은 main이다.
- 자바에서 정한 규칙:   
프로그램의 시작은 main에서부터.
- 시작도 main이지만 종료도 main이다.
- 메소드의 중괄호 내에 존재하는 문장들이 위에서 아래로 순차적으로 실행된다.

### 1.2.2 모르는 것
- public, static 그리고 void가 왜 붙어있고 의미하는 바가 무엇인가?
- 이름은 왜 항상 main이어야 하는가?
- 메소드의 이름 오른편에 있는 소괄호와 그 안에 위치한 String[] args는 무엇인가?
<br>


## 1.3 값을 반환하는 메소드
```java
public static void main(String[] args) {
    int result;
    result = adder(4, 5);  // adder가 반환하는 값을 result에 저장
    System.out.println("4 + 5 = " + result);
    System.out.println("3.5 * 3.5 = " + square(3.5));
}

public static int adder(int num1, int num2) {
    int addResult = num1 + num2;
    return addResult;  // addResult의 값을 반환
}

public static double square(double num) {
    return num * num;  // num * num의 결과를 반환
}
```
- return: 값의 반환을 명령.  
= 내보내라. 결과물로 던져라.  
반환이라고 하는 이유는 값을 호출한 영역으로 전달해주기 떄문이다.

- result = adder(4, 5);  
adder(4, 5)를 실행하면 9가 return 되는데, 이 메소드 호출이 끝나면 adder(4, 5) 문장을 대체하고 9라는 숫자가 등장하게 된다.   
이것이 반환이고 이것을 명령하는 것이 return이다.

- 값을 반환한다는 것은 그 메소드 호출문을 그 결과로 대체한다는 의미이다.
  
- 내가 반환하는 값의 자료형(int, double)을 메소드 앞에 꼭, 굳이 표시하는 이유가 있을까?  
값이 반환이 됐다는 사실, 그 메소드 호출문장을 대신했다는 의미는 사실은 어딘가의 메모리 공간에 저장을 해야 된다는 의미니까 자바 가상 머신에게 힌트를 준다고 생각하면 된다.  
'결과물로 더블형 실수가 반환이 되겠구나, 미리 메모리 공간 만들어놔야지' 라고 생각하자.(지금은 이정도로 이해)

## 1.4 return의 두 가지 의미
```java
public static void main(String[] args) {
    divide(4, 2);
    divide(6, 2);
    divide(9, 0);
}

public static void divide(int num1, int num2) {
    if (num2 == 0) {
        System.out.println("0으로 나눌 수 없습니다.");
        return;  // 값의 반환 없이 메소드만 종료
    }
    System.out.println("나눗셈 결과: " +(num1 / num2));
}
```
1. 메소드를 호출한 영역으로 값을 반환한다.
2. 메소드의 종료


# 2. 변수의 스코프
# 3. 메소드의 재귀 호출