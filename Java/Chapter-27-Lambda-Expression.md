# 람다 표현식

## 목차
1. [람다와 함수형 인터페이스](#1-람다와-함수형-인터페이스)  
   1.1 [인스턴스보다 기능 하나가 필요한 상황을 위한 람다](#11-인스턴스보다-기능-하나가-필요한-상황을-위한-람다)  
   1.2 [매개변수가 있고 반환하지 않는 람다식](#12-매개변수가-있고-반환하지-않는-람다식)  
   1.3 [매개변수가 둘인 람다식](#13-매개변수가-둘인-람다식)  
   1.4 [매개변수가 있고 반환하는 람다식 1](#14-매개변수가-있고-반환하는-람다식-1)  
   1.5 [매개변수가 있고 반환하는 람다식 2](#15-매개변수가-있고-반환하는-람다식-2)  
   1.6 [매개변수가 없는 람다식](#16-매개변수가-없는-람다식)  
   1.7 [함수형 인터페이스(Functional Interfaces)와 어노테이션](#17-함수형-인터페이스functional-interfaces와-어노테이션)  
   1.8 [람다식과 제네릭](#18-람다식과-제네릭)  

2. [정의되어 있는 함수형 인터페이스](#2-정의되어-있는-함수형-인터페이스)  
   2.1 [미리 정의되어 있는 함수형 인터페이스](#21-미리-정의되어-있는-함수형-인터페이스)  
   2.2 [대표 선수들](#22-대표-선수들)  
   2.3 [Predicate\<T>](#23-predicatet)  
   2.4 [Predicate\<T>를 구체화하고 다양화한 인터페이스들](#24-predicatet를-구체화하고-다양화한-인터페이스들)  
   2.5 [Supplier\<T>](#25-suppliert)  
   2.6 [Supplier\<T>를 구체화 한 인터페이스들](#26-suppliert를-구체화-한-인터페이스들)  
   2.7 [Consumer\<T>](#27-consumert)  
   2.8 [Consumer\<T>를 구체화하고 다양화한 인터페이스들](#28-consumert를-구체화하고-다양화한-인터페이스들)  

<br>

# 1. 람다와 함수형 인터페이스
## 1.1 인스턴스보다 기능 하나가 필요한 상황을 위한 람다
*기능 하나가 필요한 상황 = 메소드 하나가 필요한 상황*

```java
class SLenComp implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
}

class SLenComparator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Robot");
        list.add("Lambda");
        list.add("Box");

        Collections.sort(list, new SLenComp());   // 정렬

        for (String s : list) {
            System.out.println(s);
        }
    }
}
```
```bash
Box
Robot
Lambda
```
- sort 메소드를 호출하면서 list와 기능을 매개변수로 전달해야 하는데, 기능을 전달할 때 메소드만 전달해주는 방법은 없기 때문에 인스턴스를 생성해서 그 인스턴스 안에 sort가 요구하는 기능을 하는 메소드를 담아놓고 인스턴스를 매개변수로 전달을 하였다.  

- 이런 경우가 대표적으로 람다식이 인스턴스 생성을 대신할 수 있는 예이다.
<br>
<br>

## 1.2 매개변수가 있고 반환하지 않는 람다식
```java
interface Printable {
    void print(String s);   // 매개변수 하나, 반환형 void
}

class OneParamNoReturn {
    public static void main(String[] args) {
        Printable p;
        p = (String s) -> { System.out.println(s); };    // 줄임 없는 표현
        p.print("Lambda exp one.");

        p = (String s) ->  System.out.println(s);    // 중괄호 생략
        p.print("Lambda exp two.");

        p = (s) ->  System.out.println(s);    // 매개변수 형 생략
        p.print("Lambda exp three.");

        p = s ->  System.out.println(s);    // 매개변수 소괄호 생략
        p.print("Lambda exp four.");
    }
}
```
- 메소드 몸체가 둘 이상의 문장으로 이뤄져 있거나, 매개변수의 수가 둘 이상인 경우에는 각각 중괄호와 소괄호의 생략이 불가능하다.

- p = (String s) ->  System.out.println(s);  
중괄호를 생략한 문장이다. 남아있는 ;은 람다식 문장 {...} **`;`** 의 세미콜론이 남은 것이다. 
<br>
<br>

## 1.3 매개변수가 둘인 람다식
```java
interface Calculate {
    void cal(int a, int b); // 매개변수 둘, 반환형 void
}

class TwoParamNoReturn {
    public static void main(String[] args) {
        Calculate c;
        c = (a, b) -> System.out.println(a + b);
        c.cal(4, 3);    // 덧셈 진행

        c = (a, b) -> System.out.println(a - b);
        c.cal(4, 3);    // 뺄셈 진행

        c = (a, b) -> System.out.println(a * b);
        c.cal(4, 3);    // 곱셈 진행
    }
}
```
```bash
7
1
12
```
<br>
<br>

## 1.4 매개변수가 있고 반환하는 람다식 1
```java
interface Calculate {
    int cal(int a, int b);  // 값을 반환하는 추상 메소드
}

class TwoParamAndReturn {
    public static void main(String[] args) {
        Calculate c;
        c = (a, b) -> { return a + b; };   // return 문의 중괄호는 생략 불가
        System.out.println(c.cal(4, 3));

        c = (a, b) -> a + b;    // 연산 결과가 남으면 별도로 명시하지 않아도 반환 대상이 됨
        System.out.println(c.cal(4, 3));
    }
}
```
```bash
7
7
```
- c = (a, b) -> a + b;   
'->' 의 오른쪽에 문장 실행 결과인 값이 남아 있는 경우 이 값은 자동적으로 return 된다고 간주한다.

- return문을 사용할 때는 { }중괄호는 생략하지 못한다. 
<br>
<br>

## 1.5 매개변수가 있고 반환하는 람다식 2
```java
interface HowLong {
    int len(String s);  // 값을 반환하는 메소드
}

class OneParamAndReturn {
    public static void main(String[] args) {
        HowLong h1 = s -> s.length();
        System.out.println(h1.len("I am so happy."));
    }
}
```
```bash
14
```
- '->' 오른쪽에 값만 남기도록 람다식을 정의하면 굳이 return을 써주지 않아도 된다.
<br>
<br>

## 1.6 매개변수가 없는 람다식
```java
interface Generator {
    int rand();  // 매개변수 없는 메소드
}

class NoParamAndReturn {
    public static void main(String[] args) {
        Generator gen = () -> {
            Random rand = new Random();
            return rand.nextInt(50);
        };
        System.out.println(gen.rand());
    }
}
```
```bash
18
```
- 람다식이 둘 이상의 문장으로 구성된 경우 { } 중괄호는 생략할 수 없다.
<br>
<br>

## 1.7 함수형 인터페이스(Functional Interfaces)와 어노테이션
- 함수형 인터페이스: 추상 메소드가 딱 하나만 존재하는 인터페이스

```java
@FunctionalInterface
interface Calculate {
    int cal(int a, int b);
}
```
- @FunctionalInterface  
함수형 인터페이스의 조건을 갖추었는지에 대한 검사를 컴파일러에게 요청.  
이 인터페이스를 검증해달라는 의미이다.
<br>

```java
@FunctionalInterface
interface Calculate {
    int cal(int a, int b);

    default int add(int a, int b) {
        return a + b;
    }

    static int sub(int a, int b) {
        return a - b;
    }
}
```
- 추상 메소드가 하나이니 함수형 인터페이스 조건에 부합한다. 
  - 구현해야 할 메소드가 딱 하나
<br>
<br>

## 1.8 람다식과 제네릭
```java
@FunctionalInterface
interface Calculate<T> {    // 제네릭 기반의 함수형 인터페이스
    T cal(T a, T b);
}

class LambdaGeneric {
    public static void main(String[] args) {
        Calculate<Integer> ci = (a, b) -> a + b;
        System.out.println(ci.cal(4, 3));

        Calculate<Double> cd = (a, b) -> a + b;
        System.out.println(cd.cal(4.32, 3.45));
    }
}
```
```bash
7
7.7700000000000005
```
- 인터페이스가 제네릭 기반이라 하여 특별히 신경 쓸 부분은 없다.  
타입 인자가 전달이 되면(결정이 되면) 추상 메소드의 T는 결정이 되므로.
<br>
<br>

# 2. 정의되어 있는 함수형 인터페이스
## 2.1 미리 정의되어 있는 함수형 인터페이스
```java
default boolean removeIf(Predicate<? super E> filter)
```
- Collection\<E> 인터페이스에 정의되어 있는 디폴트 메소드

- Predicate 인터페이스의 추상 메소드는 다음과 같이 정의해 두었다.
  ```java
  @FunctionalInterface
  public interface Predicate<T> {
      boolean test(T t);
  }
  ```

- 미리 정의해두었으므로 Predicate라는 이름만으로 통한다. 
<br> 
<br> 

## 2.2 대표 선수들
```text
Predicate<T>        boolean test(T t)
                      전달 인자를 근거로 참 또는 거짓을 반환

Supplier<T>         T get()
                      메소드 호출 시 무엇인가를 제공함

Consumer<T>         void accept(T t)
                      무엇인가를 받아들이기만 함

Function(T, R)      R apply(T t)
                      입력과 출력이 있음(수학적으로는 함수)
```
- java.util.function 패키지로 묶여 있다. 
<br>
<br>

## 2.3 Predicate\<T>
- boolean test(T t);

```java
public static int sum(Predicate<Integer> p, List<Integer> lst) {
    int s = 0;
    for (Integer n : lst) {
        if (p.test(n)) {
            s += n;
        }
    }
    return s;
}

public static void main(String[] args) {
    List<Integer> list = Arrays.asList(1, 5, 7, 9, 11, 12);
    int s;

    s = sum(n -> n % 2 == 0, list);
    System.out.println("짝수 합 = " + s);

    s = sum(n -> n % 2 != 0, list);
    System.out.println("홀수 합 = " + s);
}
```
```bash
짝수 합 = 12
홀수 합 = 33
```
<br>
<br>

## 2.4 Predicate\<T>를 구체화하고 다양화한 인터페이스들
```java
IntPredicate            boolean test(int value)

LongPredicate           boolean test(long value)

DoublePredicate         boolean test(double value)

BiPredicate<T, U>       boolean test(T t, U u)
```

```java
public static int sum(Predicate<Integer> p, List<Integer> lst) { ... }
                            ↓
public static int sum(IntPredicate p, List<Integer> lst) { ... }
```
- sum 메소드의 인자로 기본 자료형 값을 전달 할 수 있으므로 오토 박싱, 오토 언박싱이 일어날 필요가 없게 만들어 놓았다.
<br>
<br>

## 2.5 Supplier\<T>
- T get();

```java
public static List<Integer> makeIntList(Supplier<Integer> s, int n) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        list.add(s.get());  // 난수를 생성해 담는다.
    }
    return list;
}

public static void main(String[] args) {
    Supplier<Integer> spr = () -> {
        Random rand = new Random();
        return rand.nextInt(50);
    };

    List<Integer> list = makeIntList(spr, 5);
    System.out.println(list);

    list = makeIntList(spr, 10);
    System.out.println(list);
}
```
```bash
[10, 2, 44, 36, 40]
[17, 19, 8, 6, 12, 35, 7, 39, 30, 10]
```
<br>
<br>

## 2.6 Supplier\<T>를 구체화 한 인터페이스들
```java
IntSupplier             int getAsInt()

LongSupplier            long getAsLong();

DoubleSupplier          double getAsDouble()

BooleanSupplier         boolean getAsBoolean()
```

```java
public static List<Integer> makeIntList(Supplier<Integer> s, int n) { ... }
                                ↓
public static List<Integer> makeIntList(IntSupplier s, int n) { ... }
```
- 불필요한 박싱과 언박싱을 피할 수 있다.
<br>
<br>

## 2.7 Consumer\<T>
- void accept(T t)

```java
class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> c = s -> System.out.println(s);
        c.accept("Pineapple");  // 출력이라는 결과를 보임
        c.accept("Strawberry");
    }
}
```
```bash
Pineapple
Strawberry
```
<br>
<br>

## 2.8 Consumer\<T>를 구체화하고 다양화한 인터페이스들
```java
IntConsumer                 void accept(int value)
ObjIntConsumer<T>           void accept(T t, int value)

LongConsumer                void accept(long value)
ObjLongConsumer<T>          void accept(T t, long value)

DoubleConsumer              void accept(double value)
ObjDoubleConsumer<T>        void accept(T t, double value)

BiConsumer<T, U>            void accept(T t, U u)
```

```java
Consumer<String> c = s -> System.out.println(s);

ObjIntConsumer<String> c = (s, i) -> System.out.println(i + ". " + s);
```
<br>
<br>

## 2.9 Function\<T, R>
- R apply(T t)

```java
class FunctionDemo {
    public static void main(String[] args) {
        Function<String, Integer> f = s -> s.length();
        System.out.println(f.apply("Robot"));
        System.out.println(f.apply("System"));
    }
}
```
```bash
5
6
```
<br>
<br>

## 2.10 Function\<T, R>을 구체화하고 다양화 한 인터페이스들
```java
IntToDoubleFunction         double applyAsDouble(int value)
DoubleToIntFunction         int applyAsInt(double value)

IntUnaryOperator            int applyAsInt(int operand)
DoubleUnaryOperator         double applyAsDouble(double operand)

BiFunction<T, U, R>         R apply(T t, U u)

IntFunction<R>              R apply(int value)
DoubleFunction<R>           R apply(double value)

ToIntFunction<T>            int applyAsInt(T value)
ToDoubleFunction<T>         double applyAsDouble(T value)
ToIntViFunction<T, U>       int applyAsInt(T t, U u)
ToDoubleBiFunction<T, U>    double applyAsDouble(T t, U u)
```
<br>
<br>

## 2.11 추가
```java
Function<T, R>              R apply(T t)
BiFunction<T, U, R>         R apply(T t, U u)

UnaryOperator<T>            T apply(T t)
BinaryOperator<T>           T apply(T t1, T t2)
```
<br>
<br>

## 2.12 removeIf 메소드를 사용해보자 1
- removeIf 메소드의 기능  
"Removes all of the elements of this collection that satisfy the given predicate."
<br>

```java
default boolean removeIf(Predicate<? super E> filter)
```
- Collection\<E> 인터페이스의 디폴트 메소드
<br>

```java
public boolean removeIf(Predicate<? super Integer> filter)
```
- ArrayList\<Integer> 인스턴스의 removeIf
<br>
<br>

## 2.13 removeIf 메소드를 사용해보자 2
```java
public static void main(String[] args) {
    List<Integer> ls1 = Arrays.asList(1, -2, 3, -4, 5);
    ls1 = new ArrayList<>(ls1);

    List<Double> ls2 = Arrays.asList(-1.1, 2.2, 3.3, -4.4, 5.5);
    ls2 = new ArrayList<>(ls2);

    Predicate<Number> p = n -> n.doubleValue() < 0.0;   // 삭제의 조건
    ls1.removeIf(p);    // List<Integer> 인스턴스에 전달
    ls2.removeIf(p);    // List<Double> 인스턴스에 전달

    System.out.println(ls1);
    System.out.println(ls2);
}
```
```bash
[1, 3, 5]
[2.2, 3.3, 5.5]
```
- 람다식 정의한 게 Predicate\<T> 인터페이스 filter 메소드의 몸체를 채운 거고 removeIf(p)는 ArrayList\<T>에 정의된 메소드.


