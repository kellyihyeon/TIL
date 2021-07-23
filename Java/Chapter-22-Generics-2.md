# 제네릭(Generics) 2 

## 목차
1. [제네릭의 심화 문법](#1-제네릭의-심화-문법)  
   1.1 [제네릭 클래스와 상속](#11-제네릭-클래스와-상속)  
   1.2 [타겟 타입](#12-타겟-타입)  
   1.3 [와일드 카드의 설명에 앞서 (제네릭 메소드 vs 일반 메소드)](#13-와일드-카드의-설명에-앞서-제네릭-메소드-vs-일반-메소드)  
   1.4 [와일드카드](#14-와일드카드)  
   1.5 [기능적으로는 두 메소드 완전 동일](#15-기능적으로는-두-메소드-완전-동일)  
   1.6 [와일드 카드의 상한과 하한의 제한: Bounded Wildcards](#16-와일드-카드의-상한과-하한의-제한-bounded-wildcards)  
   1.7 [상한이 제한된 와일드카드](#17-상한이-제한된-와일드카드upper-bounded-wildcards)  
   1.8 [하한이 제한된 와일드카드(Lower-Bounded Wildcards)](#18-하한이-제한된-와일드카드lower-bounded-wildcards)  
   1.9 [와일드카드 제한 이유를 설명하기 위한 도입](#19-와일드카드-제한-이유를-설명하기-위한-도입)  

<br>

# 1. 제네릭의 심화 문법
## 1.1 제네릭 클래스와 상속
```java
class Box<T> {
    protected T ob;

    public void set(T o) {
        ob = o;
    }

    public T get() {
        return ob;
    }
}
```

```java
class SteelBox<T> extends Box<T> {

    public SteelBox(T o) {
        ob = o;
    }
}
```
- Box\<Integer> iBox = new SteelBox<>(7959);  
  Box\<String> iBox = new SteelBox<>("Simple);  
  상속의 특성이 그대로 반영된다.  
  T형이 맞지 않을 때는 상속 관계가 성립되지 않는다.
<br>
<br>

## 1.2 타겟 타입
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
class EmptyBoxFactory {
    
    public static <T> Box<T> makeBox() {
        Box<T> box = new Box<>();
        return box;
    } 
}
```

```java
public static void main(String[] args) {
    Box<Integer> iBox = EmptyBoxFactory.<Integer>makeBox();
  //Box<Integer> iBox = EmptyBoxFactory.makeBox();
    iBox.set(25);
    System.out.println(iBox.get());
}
```
- Box\<Integer> iBox = EmptyBoxFactory.makeBox();  
참조변수의 형 Box\<Integer>를 기반으로 makeBox 메소드의 T를 결정하게 된다.  
따라서 이를 가리켜 `타겟 타입`이라 한다.

- 메소드 호출 이후에 반환된 값을 가지고 대입을 진행한다.   
대입보다 먼저 진행되는 것이 메소드 호출이다.
컴파일러가 메소드를 호출할 때 T의 자료형을 결정짓기 위한 정보를 타겟 타입이라 부른다.
<br>
<br>

## 1.3 와일드 카드의 설명에 앞서 (제네릭 메소드 vs 일반 메소드)
```java
public static <T> void peekBox(Box<T> box) {
    System.out.println(box);
}
```
- Box\<Integer>의 인스턴스, Box\<String>의 인스턴스를 인자로 전달 가능하다.

```java
public static void peekBox(Box<Object> box) {
    System.out.println(box);
}
```
- Box\<Integer>의 인스턴스, Box\<String>의 인스턴스를 인자로 전달 가능할 것 같지만 불가능하다.

- Box\<Object>와 Box\<String>은 상속 관계를 형성하지 않는다.  
Box\<Object>와 Box\<Integer>는 상속 관계를 형성하지 않는다.  
그러나 와일드카드를 사용하면 일반 메소드도 이 두 인스턴스를 인자로 받을 수 있다.

- public static void peekBox(Box\<Object> box) {...}    
너무 헷갈리는데, `Box<String> iBox = new Box<Integer>("Simple);` 이 코드를 보면 바로 말도 안되는 코드라고 할 수 있는 것처럼, Box\<Object>, Box\<Integer> 자료형이 다르면 어떤 상관 관계도 없다. T가 다르면 서로 아무 관계도 없다.
  - [ ] 쉽게 바꿔보자.  
    peekBox(Box\<Apple> box) Apple형을 담을 수 있는 peekBox 메소드가 있다고 가정하자.  
    Box\<Apple> aBox = new Box<>();  
    Box\<Orange> oBox = new Box<>();  
    fruit.peekBox(aBox);  
    fruit.peekBox(oBox);  
    peekBox에 oBox를 담을 수 없는 것과 같다고 보면 되는 건가?
<br>
<br>


## 1.4 와일드카드
*제네릭 메소드와 합쳐졌을 때 막강한 파워를 가지는 와일드카드*

```java
public static void peekBox(Box<?> box) {
    System.out.println(box);
}
```
- `<?>`  
무엇을 넣어도 받아 주겠다는 선언

- Box\<Integer>의 인스턴스, Box\<String>의 인스턴스를 인자로 전달 가능하다.

- 제네릭 메소드와 기능적으로는 차이가 없다.  
하지만 둘은 결과적으로는 같지만 와일드카드는 제네릭메소드가 아니다. 성격적으로 다르다.  
내부적으로 동작하는 방식에도 차이가 있다. 
<br>
<br>

## 1.5 기능적으로는 두 메소드 완전 동일
```java
// 제네릭 메소드의 정의
public static <T> void peekBox(Box<T> box) {
   System.out.println(box); 
}

// 와일드카드 기반 메소드 정의
public static void peekBox(Box<?> box) {
   System.out.println(box); 
}
```
- 그러나 와일드카드 기반 메소드 정의가 더 간결하므로 우선시 해야 한다고 `권고하고 있다`.  
메소드의 정의가 복잡해질수록 와일드카드 기반 메소드 정의가 더 간결해 보인다.
<br>
<br>

## 1.6 와일드 카드의 상한과 하한의 제한: Bounded Wildcards
- 제네릭에서는 제한하는 방법이 extends 하나였다.
와일드카드는 extends와 super 두 개가 있다.
<br>
<br>

## 1.7 상한이 제한된 와일드카드(Upper-Bounded Wildcards)
```java
public static void peekBox(Box<?> box) {
   System.out.println(box); 
}

public static void peekBox(Box<? extends Number> box) {
   System.out.println(box); 
}
```
- box는 Box\<T> 인스턴스의 참조 값을 전달 받는 매개변수이다.  
단 전달되는 인스턴스의 T는 Number 또는 이를 상속하는 하위 클래스이어야 한다.

- 최소한 Number 아래쪽에 있는 클래스여야한다. 상한을 Number로 정했기 때문에 상한 제한된 와일드카드 라고 한다.
<br>
<br>

## 1.8 하한이 제한된 와일드카드(Lower-Bounded Wildcards)
```java
public static void peekBox(Box<? super Integer> box) {
   System.out.println(box); 
}
```

```text
Object
  ↑
Number
  ↑
Integer
```
- box는 Box\<T> 인스턴스의 참조 값을 전달 받는 매개변수이다.  
단 전달되는 인스턴스의 T는 Integer 또는 Integer가 상속하는 클래스 이어야 한다.  
즉 위 메소드의 인자로 전달 가능한 인스턴스는 Box\<Integer>, Box\<Number>, Box\<Object>로 제한된다.

- Integer로 하한 제한을 했다. 
최소 Integer를 기준으로 Integer 위로, Integer가 직접 또는 간접적으로 상속하는 클래스만 허용한다.

- 예를들어 Number를 상속하는 Double, Boolean 등은 오지 못한다.
<br>
<br>

## 1.9 와일드카드 제한 이유를 설명하기 위한 도입
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
class Toy {
    public string toString() {
        return "I am a Toy";
    }
}
```

```java
class BoxHandler {

    // 상자에서 꺼내는 메소드
    public static void outBox(Box<Toy> box) {
        Toy t = box.get();
        System.out.println(t);
    }

    // 상자에 넣는 메소드
    public static void inBox(Box<Toy> box, Toy n) {
        box.set(n);
    }
}
```
- 프로그래머가 두 가지 메소드를 정의했다.  
각 메소드의 기능에 맞는 동작이 일어나야 하는데, outBox에서 상자에 넣는 동작이 일어난다면, 그것은 프로그래머의 실수이고 이 실수가 컴파일 오류로 이어지는 게 가장 바람직하다.

```java
// 아래의 오류 상황에서 컴파일 오류가 발생하지 않는다.
   public static void outBox(Box<Toy> box) {
        box.get();  // 꺼내는 것 OK
        box.set(new Toy()); // 넣는 것도 OK
    }

    public static void inBox(Box<Toy> box, Toy n) {
        box.set(n);    // 넣는 것 OK
        Toy myToy = box.get();  // 꺼내는 것도 OK
    }
```
- 각 메소드의 기능에 맞지 않는 동작이 일어났지만 컴파일 오류가 발생하지 않는다.  
무엇인가를 박스에서 꺼내려고 하는데 set 메소드를 호출하는 것 자체가 오류이다.  
오류임에도 불구하고 컴파일과정에서 오류가 드러나지 않는다.  

- 좋은 프로그래밍 언어는 프로그래머의 의도에서 벗어난 코드에 대해 컴파일 오류를 일으켜 주는 언어이다.   
따라서 컴파일 오류를 일으켜주는 장치가 있다고 부각시키는 것이다.

- 이런 실수를 누가하느냐?  
처음부터 끝까지 전부 타이핑을 하는 경우가 아니라 일부 검증된 코드를 copy & paste 할 때, copy는 잘했는데 paste 할 위치를 잘못 찍었을 때 이런 실수가 일어난다.
<br>
<br>

## 1.10 상한 제한의 목적
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
class Car extends Toy {...}
class Robot extends Toy {...}
```

```java
...
public static void outBox(Box<? extends Toy> box) {
    box.get();  // 꺼내는 것 OK
    box.set(new Toy()); // 넣는 것 ERROR
}
```
- Box<? extends Toy> box 대상으로 넣는 것은 불가능하다.
<br>
<br>

## 1.11 상한 제한의 결과
```java
class BoxHandler {
    public static void outBox(Box<? extends Toy> box) {
        Toy t = box.get();  // 상자에서 꺼내기
        System.out.println(t);
    }

    public static void inBox(Box<Toy> box, Toy n) {
        box.set(n);    // 상자에 넣기
    }
}
```
<br>
<br>

## 1.12 하한 제한의 목적
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
class Plastic {...}
class Toy extends Plastic {...}
```

```java
...
public static void inBox(Box<? super Toy> box, Toy n) {
    box.set(n);    // 넣는 것 OK
    Toy myToy = box.get();  // 꺼내는 것 ERROR
}
```
- Box\<Toy> 또는 Box\<Plastic> 인스턴스가 인자로 전달될 수 있다.

- Box<? super Toy> box 대상으로 꺼내는 것은 불가능하다.

<br>
<br>

## 1.13 하한 제한의 결과
```java
class BoxHandler {
    public static void outBox(Box<? extends Toy> box) {
        Toy t = box.get();  // 상자에서 꺼내기
        System.out.println(t);
    }

    public static void inBox(Box<? super Toy> box, Toy n) {
        box.set(n);    // 상자에 넣기
    }
}
```

<br>
<br>

## 1.14 상한 제한과 하한 제한의 좋은 예
```java
class BoxContentsMover {
    // from 에 저장된 내용물을 to 로 이동
    public static void moveBox(Box<? super Toy> to, Box<? extends Toy> from) {
        to.set(from.get());
    }
}
```
<br>
<br>

## 1.15 제한된 와일드카드 선언을 갖는 제네릭 메소드: 도입
```java
class BoxHandler {
    public static void outBox(Box<? extends Toy> box) {
        Toy t = box.get();  // 상자에서 꺼내기
        System.out.println(t);
    }

    public static void inBox(Box<? super Toy> box, Toy n) {
        box.set(n);    // 상자에 넣기
    }
}
```
- 위 클래스의 두 메소드는 사실상 Box\<Toy> 인스턴스를 대상으로 정의된 메소드이다.  
따라서 Toy와 전혀 관계 없는 Robot 클래스가 존재하는 상황에서 Box\<Robot>을 대상으로는 동작하지 않는다.  
그렇다면 이 상황에서 메소드 오버로딩이 가능할까?

<br>
<br>

## 1.16 다음 형태로 메소드 오버로딩이 불가능하다
```java
class BoxHandler {
    // 다음 두 메소드는 오버로딩 인정 안됨
    public static void outBox(Box<? extends Toy> box) {...}
    public static void outBox(Box<? extends Robot> box) {...}

    // 다음 두 메소드는 두 번째 매개변수로 인해 오버로딩 인정 됨
    public static void inBox(Box<? super Toy> box, Toy n) {...}
    public static void inBox(Box<? super Robot> box, Robot n) {...}
}
```
- 왜 이러한 형태의 오버로딩을 허용하지 않을까?    
이유는 Type Erasure 때문이다.  
컴파일 과정에서 <...> 내용이 모두 지워진다. 따라서 컴파일러가 이러한 형태의 메소드 오버로딩을 허용하지 않는다.

<br>
<br>

## 1.17 그래서 와일드 카드 선언을 갖는 메소드를 제네릭으로
```java
public static void outBox(Box<? extends Toy> box) {...}
public static void outBox(Box<? extends Robot> box) {...}

                        ↓

public static <T> void outBox(Box<? extends T> box) {...}
```

<br>
<br>

## 1.18 제네릭 인터페이스의 정의와 구현
```java
interface Getable<T> {
    public T get();
}
```

```java
class Box<T> implements Getable<T> {
    
    private T ob;

    public void set(T o) {
        ob = o;
    }
    
    @Override
    public T get() {
        return ob;
    }
}
```

```java
class Toy {
    @Override
    public String toString() {
        return "I am a Toy";
    }
}
```

```java
public static void main(String[] args) {
    Box<Toy> box = new Box<>();
    box.set(new Toy());
    
    // Box<T>가 Getable<T>를 구현하므로 참조 가능
    Getable<Toy> gt = box;
    System.out.println(gt.get());
}
```

