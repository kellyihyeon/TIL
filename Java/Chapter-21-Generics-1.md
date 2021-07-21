# 제네릭(Generics)

## 목차
1. [제네릭의 이해](#1-제네릭의-이해)  
   1.1 [제네릭 이전의 코드](#11-제네릭-이전의-코드)  
   1.2 [제네릭 이전의 코드의 사용의 예](#12-제네릭-이전의-코드의-사용의-예)  
   1.3 [제네릭 이전 코드가 갖는 문제점 1](#13-제네릭-이전-코드가-갖는-문제점-1)  
   1.4 [제네릭 이전 코드가 갖는 문제점 2](#14-제네릭-이전-코드가-갖는-문제점-2)   
   1.5 [제네릭 기반의 클래스 정의하기](#15-제네릭-기반의-클래스-정의하기)  
   1.6 [제네릭 클래스 기반 인스턴스 생성](#16-제네릭-클래스-기반-인스턴스-생성)  
   1.7 [제네릭 이후의 코드: 개선된 결과](#17-제네릭-이후의-코드-개선된-결과)  
   1.8 [실수가 컴파일 오류로 이어진다](#18-실수가-컴파일-오류로-이어진다)   

2. []()

<br>

# 1. 제네릭의 이해
*자료형을 유동적으로 바꿀 수 있어야 할 때 자료형만 제외하고 클래스의 틀을 만들어 놓는 것이 제네릭이다.*

## 1.1 제네릭 이전의 코드
```java
class Apple {
    public String toString() {
        return "I am an apple.";
    } 
}
```
```java
class Orange {
    public String toString() {
        return "I am an orange.";
    }
}
```
```java
// 다음 상자는 사과도 오렌지도 담을 수 있다.
class Box {
    private Object ob;

    public void set(Object o) {
        ob = o;
    }
    
    public Object get() {
        return ob;
    }
} 
```
- 담을 수도 있고 꺼낼 수도 있는 Box 클래스 정의
<br>
<br>


## 1.2 제네릭 이전의 코드의 사용의 예
```java
public static void main(String[] args) {
    Box aBox = new Box();
    Box oBox = new Box();

    aBox.set(new Apple());
    oBox.set(new Orange());

    Apple ap = (Apple)aBox.get();
    Orange og = (Orange) oBox.get();

    System.out.println(ap);
    System.out.println(og);
}
```
```bash
I am an apple.
I am an orange.
```
- 어쩔 수 없이 형 변환의 과정이 수반된다.  
그리고 이는 컴파일러의 오류 발견 가능성을 낮추는 결과로 이어진다.

- 컴파일러가 판단하지 않고 프로그래머가 판단하므로 명시적 형변환을 하는 순간 코드의 안정성이 떨어진다.
<br>
<br>


## 1.3 제네릭 이전 코드가 갖는 문제점 1
```java
public static void main(String[] args) {
    Box aBox = new Box();
    Box oBox = new Box();

    aBox.set("Apple");
    oBox.set("Orange");

    Apple ap = (Apple)aBox.get();
    Orange og = (Orange)oBox.get();

    System.out.println(ap);
    System.out.println(og);
}
```
```bash
Exception in thread "main" java.lang.ClassCastException: java.lang.String cannot be cast to Apple
	at Main.main(Main.java:11)
```
- aBox.set("Apple");  
  oBox.set("Orange");  
set 메소드에서 사과와 오렌지 인스턴스가 아닌 "문자열"을 담았다. (프로그래머의 실수)

- get 메소드에서 상자에 과일이 담기지 않았는데 강제 형변환으로 과일을 꺼내려 한다.
프로그래머가 명시적으로 형변환을 한다고 코드를 작성했기 때문에 문제 없이 컴파일이된다.

- 프로그래머의 실수가 컴파일 과정에서 발견되지 않는다.  
프로그래밍 언어는 컴파일 과정에서 프로그래머의 실수가 발견될 수 있도록 발전해나가고 있다.
  - ①컴파일 -> ②예외처리 -> ③실행  
    최대한 ①컴파일 과정에서 오류가 발견이 되도록 코드를 작성해야 한다.

- 다행히 실행 과정에서 자바 가상머신이 ClassCastException을 알려준다.
<br>
<br>

## 1.4 제네릭 이전 코드가 갖는 문제점 2
```java
public static void main(String[] args) {
    Box aBox = new Box();
    Box oBox = new Box();

    aBox.set("Apple");
    oBox.set("Orange");

    System.out.println(aBox.get());
    System.out.println(oBox.get());
}
```
```bash
Apple
Orange
```
- aBox.set("Apple");   
  oBox.set("Orange");  
  위 코드는 프로그래머의 실수이다.
  프로그래머의 실수가 실행 과정에서조차 발견되지 않을 수 있다.

- 형변환도 하지 않았으므로 실행까지 되고, 실행 결과를 봤을 때 실수가 발생한 건지 아닌지 분간 하기가 어려울 수 있다.
<br>  
<br>  


## 1.5 제네릭 기반의 클래스 정의하기
```java
class Box<T> {
    private T ob;

    public void set(T o) {
        ob = o;
    }

    public T get() {
        return ob;
    }
}
```
- 인스턴스 생성 시 결정이 되는 자료형의 정보를 T로 대체한다.

- 컴파일러에게 이 클래스를 제네릭화 하기 위해서 일부러 비워둔 것이라고 알려줘야하기 때문에 `<T>`라고 적어놓는다.
<br>
<br>

## 1.6 제네릭 클래스 기반 인스턴스 생성
```java
Box<Apple> aBox = new Box<Apple>();
```
- T를 Apple로 결정하여 인스턴스를 생성하겠다는 의미이다.
따라서 Apple 또는 Apple을 상속하는 하위 클래스의 인스턴스 저장 가능

```java
Box<Orange> oBox = new Box<Orange>();
```
- T를 Orange로 결정하여 인스턴스를 생성한다.
따라서 Orange 또는 Orange를 상속하는 하위 클래스의 인스턴스 저장 가능
<br>

### 1.6.1 용어정리
```java
Box<T>에서 T  
타입 매개변수(Type Parameter)  

Box<Apple>에서 Apple  
타입 인자(Type Argument)  

Box<Apple>  
매개변수화 타입(Parameterized Type)
```
<br>
<br>


## 1.7 제네릭 이후의 코드: 개선된 결과
```java
public static void main(String[] args) {
    Box<Apple> aBox = new Box<Apple>();
    Box<Orange> oBox = new Box<Orange>();

    aBox.set(new Apple());
    oBox.set(new Orange());

    Apple ap = aBox.get();
    Orange og = oBox.get();

    System.out.println(ap);
    System.out.println(og);
}
```
- get 메소드는 Object형으로 반환을 하지 않기 때문에 get 메소드에서 꺼낼 때 형변환이 필요가 없어졌다.

- 사과와 오렌지를 꺼낼 때 형변환 하지 않는다.
<br>
<br>

## 1.8 실수가 컴파일 오류로 이어진다
```java
public static void main(String[] args) {
    Box<Apple> aBox = new Box<Apple>();
    Box<Orange> oBox = new Box<Orange>();

    aBox.set("Apple");
    oBox.set("Orange");

    Apple ap = aBox.get();
    Orange og = oBox.get();

    System.out.println(ap);
    System.out.println(og);
}
```
```bash
java: incompatible types: java.lang.String cannot be converted to Apple

java: incompatible types: java.lang.String cannot be converted to Orange
```
- 타입 불일치  
프로그래머의 실수가 1번 그물, 컴파일 과정에서 다 잡힌다.