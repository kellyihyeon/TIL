# 정보 은닉 그리고 캡슐화

## 목차
1. [정보 은닉(Informaiotn Hiding)](#1-정보-은닉informaiotn-hiding)  
   1.1 [정보 은닉의 주체](#11-정보-은닉의-주체)    
   1.2 [정보 은닉의 의미](#12-정보-은닉의-의미)  
   1.3 [정보를 은닉해야 하는 이유](#13-정보를-은닉해야-하는-이유)  
   1.4 [정보의 은닉을 위한 private 선언](#14-정보의-은닉을-위한-private-선언)
  
2. [접근 수준 지시자(Access-level Modifiers)](#2-접근-수준-지시자access-level-modifiers)
3. [캡슐화(Encapsulation)](#3-캡슐화encapsulation)
<br>

# 1. 정보 은닉(Informaiotn Hiding)
```   
클래스                   인스턴스
┌────┐                   ┌────┐
│    │ ────────────────> │    │
└────┘                   └────┘
```
- 클래스를 기반으로 해서 만들어진 게 인스턴스이므로 클래스에서 되는 것은 당연히 인스턴스에서도 되고 그 역도 마찬가지다.

- 클래스에는 대표적으로 2가지가 들어간다. 바로 데이터와 기능이다.
<br>

## 1.1 정보 은닉의 주체
- 인스턴스 혹은 클래스에 선언된 데이터에 인스턴스 혹은 클래스 외부에서 접근 하는 것을 허용하지 않겠다는 의미이다.
  
- 그럼 누가 접근을 하냐?  
클래스 내부에서만 접근을 허용하겠다.

- 클래스를 기준으로 `외부`에서는 이 데이터가 보이지도 않게하겠다.   
`접근 불가능`한 상태가 되도록 하겠다.

## 1.2 정보 은닉의 의미
*외부에서 접근이 불가능하고 내부에서만 접근을 하면 이게 어떤 의미를 갖는가.*
<br>

- 데이터를 가리고, 이 데이터에 접근을 하도록 하되 기능을 통해서 접근을 하도록 유도하겠다.  
= 클래스 외부에서 데이터에 **`직접`** 접근하는 것을 막겠다.
<br>
<br>


## 1.3 정보를 은닉해야 하는 이유
### 1.3.1 serRad() 메소드를 통한 접근  
```java
class Circle {
    double rad = 0;
    final double PI = 3.14;

    public Circle(double r) {
        setRad(r);
    }

    public void serRad(double r) {
        if( r < 0 ) {
            rad = 0;
            return;
        }
        rad = r;
    }

    public double getArea() {
        return (rad * rad) * PI;
    }
}
``` 
- 안정성이 높아진다. 잘못된 값이 전달 됐을 때 대처 방안을 마련할 수 있다.    
(r < 0 -> rad = 0으로 초기화.)
<br>

### 1.3.2 변수에 직접 접근
```java
...
public static void main(String arg[]) {
    Circle c = new Circle(1.5);
    System.out.println(c.getArea());

    c.setRad(2.5);
    System.out.println(c.getArea());

    c.setRad(-3.3);
    System.out.println(c.getArea());

    c.rad = -4.5;
    System.out.println(c.getArea());
}
```
- c.rad = -4.5;  
컴파일 오류가 발생하지 않는다.

- setRad() 메소드를 통해서만 rad를 초기화, 수정해주길 원하는데, 이렇게 변수에 직접 접근하는 것을 막아낼 수 있는 방법이 없다.   
직접 접근이 허용이 된다. (c.rad)

- 반지름이라는 것은 0보다 작을 수가 없는데, 0보다 작은 값을 갖는 상황이 발생한다.  
논리적 오류가 발생했음에도 불구하고 이런 논리적 오류는 컴파일 오류로 이어지지 않기 때문에 문제가 된다.

- 컴파일 오류로도 발생이 안되고 실행 과정에서도 문제가 생기지 않는다.   
그 결과 엉뚱한 값이 출력이 된다.
<br>

### 1.3.3 정보를 은닉하는 이유와 방법
#### 1.3.3.1 정보 은닉 이유
- 이러한 논리적 오류가 발생했을 때, `논리적 오류를 문법적 오류가 되게끔 해주는 방법`중의 하나가 `정보 은닉`이다.
 
- 직접 접근을 허용하지 않는다. 무조건 강제한다.  
메소드를 통해서 접근하도록 클래스를 정의했는데 직접 접근을 했을 때, 컴파일 오류로 이어지도록 문법적으로 강제하는 것이다.
<br>

#### 1.3.3.2 정보 은닉 방법
- 멤버 앞에 private이라는 선언을 넣어주면 된다.

- private 이름이 의미 하듯이 이 클래스에게 private한 멤버가 된다.   
private이 선언되면 클래스 내부에서는 언제든 접근이 가능하지만 클래스 외부에서 **`.`** 을 통해서 접근할 때, 컴파일 오류로 이어진다. 
<br>
<br>


## 1.4 정보의 은닉을 위한 private 선언
### 1.4.1 getter와 setter
```java
class Circle {
    private double rad = 0;
    final double PI = 3.14;

    public Circle(double r) {
        setRad(r);
    }

    public void serRad(double r) {  //setter
        if( r < 0 ) {
            rad = 0;
            return;
        }
        rad = r;
    }

    public double getRad() {  //getter
        return rad;
    }

    public double getArea() {
        return (rad * rad) * PI;
    }
}
``` 

- 접근하는 형태는 두 가지로 나뉜다.  
  ① 값을 가져가기 위한 접근 (getter)  
  ② 값을 초기화, 수정하기 위한 접근 (setter)  

- 이 두 가지 접근을 허용하기 위해 메소드 이름을 set-, get- 2개로 정의했다.  
그리고 rad라는 변수에 private을 넣어서 rad에 직접 접근을 할 수 없게 정의했다.
<br>

### 1.4.2 컴파일 오류
```java
...
public static void main(String arg[]) {
    Circle c = new Circle(1.5);
    System.out.println(c.getArea());

    c.setRad(2.5);
    System.out.println(c.getArea());

    c.setRad(-3.3);
    System.out.println(c.getArea());

    c.rad = -4.5;
    System.out.println(c.getArea());
}
```

- c.rad = -4.5;  
private을 선언했기 때문에 컴파일 오류로 이어진다.

- 클래스 안에 정의되고 있는 인스턴스 변수들은 어떠한 경우에도 불구하고 직접적으로 접근하는 것을 허용하지 않게 하는것이 좋은 코드이고, 접근할 수 있는 별도의 메소드를 제공하는 것이 좋은 객체지향을 기반으로 한 클래스 설계의 기본 원칙이다.

- private = 접근 수준 지시자  
접근할 수 있는 수준을 지시하는 지시자.  
`클래스 외부의 모든 접근을 원칙적으로 막겠다.`  
이러한 유형의 키워드들이 있다. 알아보자.
<br>
<br>


# 2. 접근 수준 지시자(Access-level Modifiers)

## 2.1. 네 가지 종류의 접근 수준 지시자






## 2.2 클래스 정의 대상의 public과 default 선언이 갖는 의미
## 2.3 클래스의 public, default 선언 관련 예
## 2.4 인스턴스 멤버의 private 선언이 갖는 의미
## 2.5 상속에 대한 약간의 설명: protected 선언의 의미 이해를 위한
## 2.6 인슨턴스 멤버의 protected 선언이 갖는 의미
## 2.7 인스턴스 멤버 대상 접근 수준 지시자 정리


# 3. 캡슐화(Encapsulation)