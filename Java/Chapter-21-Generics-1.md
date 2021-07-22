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

2. [제네릭의 기본 문법](#2-제네릭의-기본-문법)  
   2.1 [다중 매개변수 기반 제네릭 클래스의 정의](#21-다중-매개변수-기반-제네릭-클래스의-정의)  
   2.2 [타입 매개변수의 이름 규칙](#22-타입-매개변수의-이름-규칙)  
   2.3 [기본 자료형에 대한 제한 그리고 래퍼 클래스](#23-기본-자료형에-대한-제한-그리고-래퍼-클래스)  
   2.4 [다이아몬드 기호](#24-다이아몬드-기호)  
   2.5 ['매개변수화 타입'을 '타입 인자'로 전달](#25-매개변수화-타입을-타입-인자로-전달)  
   2.6 [제네릭 클래스의 타입 인자 제한하기](#26-제네릭-클래스의-타입-인자-제한하기)   
   2.7 [타입 인자 제한의 효과](#27-타입-인자-제한의-효과)   
   2.8 [제네릭 클래스의 타입 인자를 인터페이스로 제한하기](#28-제네릭-클래스의-타입-인자를-인터페이스로-제한하기)   
   2.9 [하나의 클래스와 하나의 인터페이스에 대해 동시 제한](#29-하나의-클래스와-하나의-인터페이스에-대해-동시-제한)   
   2.10 [제네릭 메소드의 정의](#210-제네릭-메소드의-정의)  
   2.11 [제네릭 메소드의 제한된 타입 매개변수 선언](#211-제네릭-메소드의-제한된-타입-매개변수-선언)  

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
<br>
<br>


# 2. 제네릭의 기본 문법
## 2.1 다중 매개변수 기반 제네릭 클래스의 정의
```java
class DBox<L, R> {
    private L left;
    private R right;

    public void set(L o, R r) {
        left = o;
        right = r;
    }

    @Override
    public String toString() {
        return left + " & " + right;
    }
}
```

```java
public static void main(String[] args) {
    DBox<String, Integer> box = new DBox<>();
    box.set("Apple", 25);
    System.out.println(box);
}
```
- box.set("Apple", 25);  
Integer형 인스턴스가 와야 하는데, 값이 왔다.  
여기서 오토 박싱이 진행됐음을 알 수 있다.
<br>
<br>


## 2.2 타입 매개변수의 이름 규칙
### 2.2.1 일반적인 관례
- 한 문자로 이름을 짓는다.
- 대문자로 이름을 짓는다.
<br>

### 2.2.2 보편적인 선택
```text
E       Element
K       Key
N       Number
T       Type
V       Value
```
<br>
<br>

## 2.3 기본 자료형에 대한 제한 그리고 래퍼 클래스
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

```java
class PrimitiveAndGeneric {
    public static void main(String[] args) {
        Box<Integer> iBox = new Box<>();
        iBox.set(125);  // 오토 박싱 진행
        int num = iBox.get();   // 오토 언박싱 진행
        System.out.println(num);
    }
}
```
- Box\<int> box = new Box<>();  
**`타입 인자로 기본 자료형이 올 수 없으므로`** 컴파일 오류가 발생한다.

- 클래스 이름만 올 수 있는데, int 값을 넣고 싶다면?  
int 값을 넣어도 오토 박싱, 오토 언박싱이 진행되기 때문에 넣을 수 있다.
<br>
<br>

## 2.4 다이아몬드 기호
```java
Box<Apple> aBox = new Box<Apple>();    
이 문장을 대신하여  

Box<Apple> aBox = new Box<>();  
이렇게 쓸 수 있다.  
```
- 참조변수 선언을 통해서 컴파일러가 <> 사이에 Apple이 와야 함을 유추한다.
<br>
<br>

## 2.5 '매개변수화 타입'을 '타입 인자'로 전달
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

```java
public static void main(String[] args) {
    Box<String> sBox = new Box<>();
    sBox.set("I am so happy.");

    Box<Box<String>> wBox = new Box<>();
    wBox.set(sBox);

    Box<Box<Box<String>>> zBox = new Box<>();
    zBox.set(wBox);

    System.out.println(zBox.get().get().get());
}
```

```bash
I am so happy.
```
- [x] 그림으로 그리면서 코드를 읽어나가면 이해가 쉽다.
<br>
<br>


## 2.6 제네릭 클래스의 타입 인자 제한하기
```java
Class Box<T extends Number> {...}
```
- 인스턴스 생성 시 타입 인자로 Number 또는 이를 상속하는 클래스만 올 수 있다.

- T에는 누구나 다 올 수 있었는데, 이 T에 올 수 있는 클래스를 제한하겠다.

```java
class Box<T extends Number> {
    private T ob;

    public void set(T o) {
        ob = o;
    }

    public T get() {
        return ob;
    }
}
```
- T에 Number가 와도 되고, Integer, Double 등 Number를 상속한 클래스가 올 수 있다.

```java
public static void main(String[] args) {
    Box<Integer> iBox = new Box<>();
    iBox.set(24);

    Box<Double> dBox = new Box<>();
    dBox.set(5.97);
}
```
- Integer? Double?  
Number는 아니지만 Number를 상속하기 때문에 문제 없다.
<br>
<br>


## 2.7 타입 인자 제한의 **효과**
*왜 타입 인자에 올 수 있는 것들을 제한 하는 것일까?*

- T에 모든 클래스들이 오는 것이 부담스러워서 특정 클래스들로 제한하기 위해서 타입 인자를 제한을 하기도 한다.

- 타입 인자를 제한하면 부수적인 효과가 발생하는데, 이 효과 때문에 제한하는 경우도 많다. 
<br>

```java
class Box<T> {
    private T ob;

    ...
    public int toIntValue() {
        return ob.intValue();   // ERROR
    }
}
```
- 프로그래머가 클래스를 정의할 때, Number를 상속하는 클래스를 인자로 전달해서 사용할 것이기때문에 toIntValue 메소드를 정의했다.  
사용할 때 String이나 다른 클래스는 절대 인자로 전달 안할 거니까.
  - intValue 메소드는 Integer 클래스에 정의되어있다.


- 컴파일이 안된다.  
컴파일러의 관점에서는 프로그래머의 마음까지 읽어주지 않는다.  
intValue 라는 메소드를 썼으니까 인자로 전달되는 것에는 반드시 intValue 메소드가 있다고 가정하기 때문이다.

- ob.intValue();  
이 문장을 언제, 어디서, 어떻게 시행하더라도 허용할 수 있는가를 판단해서, 상황에 상관없이 즉 T가 무엇이든지 무조건 호출 되었을 때 호출이 가능한 경우에만 컴파일 OK 사인을 준다.
<br>

```java
class Box<T extends Number> {
    private T ob;

     ...
    public int toIntValue() {
        return ob.intValue();   // OK
    }
}
``` 
- Box\<T extends Number>  
이렇게 제한하면 T에 올 수 있는 클래스들은 Number를 상속하기 때문에 전부 intValue 라는 메소드를 구현해서 가지고 있다.  
그러므로 컴파일러 입장에서 허용을 해주는 것이다.

- Number 클래스를 상속하는 경우라고 제한을 해줬더니 부가적으로 Number 클래스에 있는 메소드를 호출할 수 있는 효과가 생겼다.
<br>
<br>


## 2.8 제네릭 클래스의 타입 인자를 인터페이스로 제한하기
```java
interface Eatable {
    public String eat();
}
```

```java
class Apple implements Eatable {
    public String eat() {
        return "it tastes so good!";
    }
    ...
}
```
- Apple 클래스는 Eatable 인터페이스를 구현했다.

```java
class Box<T extends Eatable> {
    T ob;

    public void set(T o) {
        ob = o;
    }

    public T get() {
        System.out.println(ob.eat());
        return ob;
    }
}
```
- Eatable 인터페이스를 직접, 간접적으로 구현한 클래스의 이름이 T로 전달될 수 있다.

- Eatable로 제한하였기 때문에 eat 메소드 호출이 가능하다.

- T로 전달되는 클래스들은 모두 Eatable을 구현하기 때문에 eat 메소드를 호출 할 수 있다.
그러므로 컴파일러는 이 문장을 허용해줄 수 있다.
<br>
<br>


## 2.9 하나의 클래스와 하나의 인터페이스에 대해 동시 제한
```java
class Box<T extends Number & Eatable> {...}
```
 - Number는 클래스 이름, Eatable은 인터페이스 이름

 - T에는 Number를 상속할 뿐만 아니라 Eatable을 구현한 클래스가 올 수 있다.
<br>
<br>


## 2.10 제네릭 메소드의 정의
```java
class BoxFactory {
    public static <T> Box<T> makeBox(T o) {
        Box<T> box = new Box<>();
        box.set(o);
        return box;
    }
}
```
- 클래스 전부가 아닌 메소드 하나에 대해 제네릭으로 정의하는 것이다.

- 클래스가 아니라 메소드 하나에 대해 선언하는 것이므로 \<T>를 메소드로 끌고 와야한다.

- \<T> 
이 메소드가 제네릭임을 선언한 것이다.
<br>


```java
Box<String> sBox = BoxFactory.<String>makeBox("Sweet");

Box<Double> dBox = BoxFactory.<Double>makeBox(7.59);    // 7.59에 대해 오토 박싱 진행됨
```
- T가 결정되는 시기  
제네릭 클래스는 제네릭 클래스를 기반으로 인스턴스 생성할 때 T를 결정해줘야했다.  
제네릭 메소드의 T는 메소드가 호출될 때 결정해주면 된다.
<br>


```java
Box<String> sBox = BoxFactory.makeBox("Sweet");

Box<Double> dBox = BoxFactory.makeBox(7.59);    // 7.59에 대해 오토 박싱 진행됨
```
- 타입 인자 생략이 가능하다. 

- 전달되는 인자를 통해서 컴파일러가 판단할 수 있으므로 생략이 가능하다.
<br>
<br>


## 2.11 제네릭 메소드의 제한된 타입 매개변수 선언
```java
public static <T extends Number> Box<T> makeBox(T o) {
    ...
            
    // 타입 인자 제한으로 intValue 호출 가능
    System.out.println("Boxed data = " + o.intValue());
    return box;
}

public static <T extends Number> T openBox(Box<T> box) {
    // 타입 인자 제한으로 intValue 호출 가능
    System.out.println("Unboxed data = " + box.get().intValue());
    return box.get();
} 
```
- \<T extends Number> Box\<T> makeBox(T o)  
메소드 모양 때문에 혼란이 올 수 있지만, 제네릭 클래스를 떠올리며 이해해보자. 클래스 정의했을 때와 똑같다.  
클래스에 있었던 선언이 메소드의 위치로 내려온 것일뿐이다.

- T에 올 수 있는 것은 Number이거나 Number를 상속한 클래스이어야 한다고 제한을 하고 있다.
 
- Number로 제한을 했기 때문에 Number에 정의되어있는 메소드 호출이 가능하다.

- 제네릭 클래스와 제네릭 메소드는 T의 결정 시기에서 차이가 난다.