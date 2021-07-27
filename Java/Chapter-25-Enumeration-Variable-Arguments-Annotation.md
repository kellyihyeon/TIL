# 열거형, 가변인자 그리고 어노테이션

## 목차
1. [열거형](#1-열거형)  
   1.1 [인터페이스 기반 상수의 정의: 자바 5 이전의 방식](#11-인터페이스-기반-상수의-정의-자바-5-이전의-방식)  
   1.2 [이전 방식의 문제점](#12-이전-방식의-문제점)  
   1.3 [자료형의 부여를 돕는 열거형](#13-자료형의-부여를-돕는-열거형)  
   1.4 [열거형 기반으로 수정한 결과와 개선된 부분](#14-열거형-기반으로-수정한-결과와-개선된-부분)  
   1.5 [클래스 내에 열거형 정의 가능](#15-클래스-내에-열거형-정의-가능)  
   1.6 [열거형 값의 정체: 이런 문장 삽입 가능](#16-열거형-값의-정체-이런-문장-삽입-가능)  
   1.7 [열거형 값의 정체: 열거형 값이 인스턴스라는 증거 1](#17-열거형-값의-정체-열거형-값이-인스턴스라는-증거-1)  
   1.8 [열거형 값의 정체: 열거형 값이 인스턴스라는 증거 2](#18-열거형-값의-정체-열거형-값이-인스턴스라는-증거-2)  
   1.9 [열거형 값의 정체: 결론](#19-열거형-값의-정체-결론)  
   1.10 [열거형 생성자에 인자 전달하기](#110-열거형-생성자에-인자-전달하기)     

2. [매개변수의 가변 인자 선언](#2-매개변수의-가변-인자-선언)  
   2.1 [매개변수의 가변 인자 선언과 호출](#21-매개변수의-가변-인자-선언과-호출)  
   2.2 [가변 인자 선언에 대한 컴파일러 처리](#22-가변-인자-선언에-대한-컴파일러-처리)  

3. [어노테이션](#3-어노테이션)  
   3.1 [어노테이션의 설명 범위](#31-어노테이션의-설명-범위)  
   3.2 [\@Override](#32-override)  
   3.3 [\@Deprecated](#33-deprecated)  
   
<br>

# 1. 열거형
## 1.1 인터페이스 기반 상수의 정의: 자바 5 이전의 방식
```java
interface Scale {
    int DO = 0;
    int RE = 1;
    int MI = 2;
    int FA = 3;
    int SO = 4;
    int RA = 5;
    int TI = 6;
}
```
- 인터페이스 내에 선언된 변수는 public, static, final이 선언된 것으로 간주한다.
<br>
<br>

## 1.2 이전 방식의 문제점
```java
interface Animal {
    int DOG = 1;
    int CAT = 2;
}

interface Person {
    int Man = 1;
    int WOMAN = 2;
}
```
- 여기서는 상수의 이름이 중요하다. 값은 의미가 없다.  
<br>

```java
class NonSafeConst {
    public static void main(String[] args) {
        who(Person.Man);    // 정상적인 메소드 호출
        who(Animal.DOG);    // 비정상적 메소드 호출
    }

    public static void who(int man) {
        switch (man) {
            case Person.Man:
                System.out.println("남성 손님입니다.");
                break;
            case Person.WOMAN:
                System.out.println("여성 손님입니다.");
                break;
        }
    }
}
```
- who(Animal.DOG);  
호출하면 "남성 손님입니다." 결과가 나온다.  
컴파일 및 실행 과정에서 발견되지 않는 오류이다.
<br>
<br>

## 1.3 자료형의 부여를 돕는 열거형
```java
enum Scale {
    DO, RE, MI, FA, SO, RA, TI
}
```
- 열거 자료형 Scale의 정의이다. 

- 열거형 값(Enumerated Values)을 선언한다.
<br>

```java
public static void main(String[] args) {
    Scale sc = Scale.DO;
    System.out.println(sc);

    switch (sc) {
        case DO:
            System.out.println("도~");
            break;
        case RE:
            System.out.println("레~");
            break;
        case MI:
            System.out.println("미~");
            break;
        case FA:
            System.out.println("파~");
            break;
        default:
            System.out.println("솔~ 라~ 시~");
}
```
- case 문에서는 표현의 간결함을 위해 DO와 같이 '열거형 값'의 이름만 명시하기로 약속되어 있다.
(Scale.DO -> DO)
<br>
<br>

## 1.4 열거형 기반으로 수정한 결과와 개선된 부분
```java
enum Animal {
    DOG, CAT
}

enum Person {
    MAN, WOMAN
}
```

```java
class SafeEnum {
    public static void main(String[] args) {
        who(Person.MAN);    
        who(Person.WOMAN);  // 정상적인 메소드 호출
        who(Animal.DOG);    // 비정상적 메소드 호출
    }

    public static void who(Person man) {
        switch (man) {
            case MAN:
                System.out.println("남성 손님입니다.");
                break;
            case WOMAN:
                System.out.println("여성 손님입니다.");
                break;
        }
    }
}
```
- who(Animal.DOG);  
컴파일 과정에서 자료형 불일치로 인한 오류가 발생한다.  
<br>
<br>


## 1.5 클래스 내에 열거형 정의 가능
```java
class Customer {
    enum Gender {   // 클래스 내에 정의된 열거형 Gender
        MALE, FEMALE
    }

    private String name;
    private Gender gen;

    Customer(String n, String g) {
        name = n;

        if (g.equals("man")) {
            gen = Gender.MALE;
        } else {
            gen = Gender.FEMALE;
        }
    }
}
```
- 클래스 내에 열거형이 정의되면 해당 클래스 내에서만 사용 가능한 열거형이 된다.
<br>
<br>

## 1.6 열거형 값의 정체: 이런 문장 삽입 가능
```java
class Person {
    public static final Person MAN = new Person();
    public static final Person WOMAN = new Person();

    @Override
    public String toString() {
        return "I love dogs";
    }
}

class InClassInst {
    public static void main(String[] args) {
        System.out.println(Person.MAN);
        System.out.println(Person.WOMAN);
    }
}
```
- static이 붙었기 때문에 Person 클래스 내에서 Person 인스턴스를 생성하는 것이 가능하다.
<br>
<br>


## 1.7 열거형 값의 정체: 열거형 값이 인스턴스라는 증거 1
```java
enum Person {
    MAN, WOMAN;


    @Override
    public String toString() {
        return "I love dogs.";
    }
}

class EnumConst {
    public static void main(String[] args) {
        System.out.println(Person.MAN);     // toString 메소드의 반환 값 출력
        System.out.println(Person.WOMAN);   // toString 메소드의 반환 값 출력
    }
}
```
```bash
I love dogs.
I love dogs.
```
- 모든 열거형은 java.lang.Enum\<E> 클래스를 상속한다.  
그리고 Enum\<E>는 Object 클래스를 상속한다. 이런 측면에서 볼 때 열거형은 클래스이다.  
<br>
<br>

## 1.8 열거형 값의 정체: 열거형 값이 인스턴스라는 증거 2
```java
enum Person {
    MAN, WOMAN;

    
    private Person() {
        System.out.println("Person constructor called.");
    }
    

    @Override
    public String toString() {
        return "I love dogs.";
    }
}

class EnumConst {
    public static void main(String[] args) {
        System.out.println(Person.MAN);  
        System.out.println(Person.WOMAN);
    }
}
```
```bash
Person constructor called.
Person constructor called.
I love dogs.
I love dogs.
```
- 열거형의 정의에도 생성자가 없으면 디폴트 생성자가 삽입된다.  
다만 이 생성자는 private 으로 선언이 되어 직접 인스턴스를 생성하는 것이 불가능하다.  
<br>
<br>

## 1.9 열거형 값의 정체: 결론
```java
enum Person {
    MAN, WOMAN;
    ...
}
```
- public static final Person Man = new Person();  
  public static final Person WOMAN = new Person();  
  열거형 값의 실체를 설명하는 문장이고, 실제로 이렇게 컴파일이 되지는 않는다. (이해를 돕기 위한 문장)
<br>
<br>

## 1.10 열거형 생성자에 인자 전달하기
```java
enum Person {
    MAN(29), WOMAN(25);

    int age;

    private Person(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "I am " + age + " years old.";
    }
}

class EnumParamConstructor {
    public static void main(String[] args) {
        System.out.println(Person.MAN);
        System.out.println(Person.WOMAN);
    }
}
```
```bash
I am 29 years old.
I am 25 years old.
```
<br>
<br>


# 2. 매개변수의 가변 인자 선언
## 2.1 매개변수의 가변 인자 선언과 호출
```java
class Vargs {
    public static void showAll(String... vargs) {
        System.out.println("LENGTH: " + vargs.length);

        for (String s : vargs) {
            System.out.print(s + '\t');
        }
        System.out.println();
    }

    public static void main(String[] args) {
        showAll("Box");
        showAll("Box", "Toy");
        showAll("Box", "Toy", "Apple");
    }
}
```
```bash
LENGTH: 1
Box	
LENGTH: 2
Box	Toy	
LENGTH: 3
Box	Toy	Apple	
```
- showAll(String... vargs)  
가변 인자 선언.  
하나, 하나 이상이 되어도 몇개든지 참조변수 vargs 다 받아주겠다는 의미이다. 

- showAll("Box", "Toy", "Apple");  
길이가 3인 배열이 만들어지고, 이 배열을 참조하는 참조값이 showAll 의 인자로 전달이 되는 것이다.  
<br>
<br>

## 2.2 가변 인자 선언에 대한 컴파일러 처리
```java
public static void showAll(String... vargs) {...}

public static void main(String[] args) {
    showAll("Box");
    showAll("Box", "Toy");
    showAll("Box", "Toy", "Apple");
}
```
- 컴파일러는 이를 아래와 같이 배열 기반 코드로 수정을 한다.

```java
public static void showAll(String[] vargs) {...}

public static void main(String[] args) {
    showAll(new String[]{"Box"});
    showAll(new String[]{"Box", "Toy"});
    showAll(new String[]{"Box", "Toy", "Apple"});
}
```
- vargs를 배열의 참조변수로 간주하고 코드로 작성하면 된다.
<br>
<br>


# 3. 어노테이션
*@annotation  
컴파일러에게 메세지를 전달 하는 것이다.*
## 3.1 어노테이션의 설명 범위
```text
@Override

@Deprecated

@SuppressWarnings
```
- 어노테이션 관련 문서
  ``` text
  JSR 175 "A Metadata Facility for the Java Programming Language."
  JSR 250 "Common Annotations for the Java Platform."
  ```
<br>
<br>

## 3.2 \@Override
```java 
interface Viewable {
    public void showIt(String str);
}

class Viewer implements Viewable {

    @Override
    public void showIt(String str) {
        System.out.println(str);
    }
}
```
- public void showIt(int n)  
프로그래머가 오버라이딩할 의도로 Viewer 클래스에 이 메소드를 정의했다.  
이것은 오버라이딩이 아니라 오버로딩이다. 프로그래머의 실수로 오버로딩이 되버렸는데 실수를 찾지 못하고 넘어갈 수도 있다.

- @Override 어노테이션을 사용하면 컴파일 단계에서 위와 같은 실수를 발견할 수 있다.
<br>
<br>

## 3.3 \@Deprecated
```java
interface Viewable {

    @Deprecated
    public void showIt(String str);     // Deprecated 된 메소드

    public void brShowIt(String str);
}

class Viewer implements Viewable {

    @Override
    public void showIt(String str) {
        System.out.println(str);    // 컴파일러 경고 -> 나는 경고 안뜨는데..?
    }

    @Override
    public void brShowIt(String str) {
        System.out.println('[' + str + ']');
    }

    public static void main(String[] args) {
        Viewable view = new Viewer();
        view.showIt("Hello Annotation.");   // 컴파일러 경고
    }
}
```
- 결과 잘 나오는데...?
-  Deprecated  
문제의 발생 소지가 있거나 개선된 기능의 다른 것으로 대체되어서 더 이상 필요 없게 되었음을 뜻한다.

- 기존 코드, 하위 코드의 호환성을 위해 없애지 않고 그냥 두지만 표시는 해두기 위해 사용한다.  
<br>
<br>

## 3.4 @SuppressWarnings
```java
interface Viewable {

    @Deprecated
    public void showIt(String str);     // Deprecated 된 메소드

    public void brShowIt(String str);
}

class Viewer implements Viewable {

    @Override
    @SuppressWarnings("deprecataion")
    public void showIt(String str) {
        System.out.println(str);    
    }

    @Override
    public void brShowIt(String str) {
        System.out.println('[' + str + ']');
    }
}

class AtSuppressWarnings {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Viewable view = new Viewer();
        view.showIt("Hello Annotation."); 
        view.brShowIt("Hello Annotation.");
    }   
}
```
- @SuppressWarnings("deprecation")  
deprecation 관련 경고 메세지를 생략하라는 의미이다. 

- 컴파일러가 주는 경고를 무조건 무시하면 안된다.  
경고 메세지를 무조건 끄는 건 바람직한 것은 아니므로 제한적인 상황에서 사용하자.