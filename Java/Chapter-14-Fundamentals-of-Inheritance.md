# 클래스의 상속 - 1. 상속의 기본

## 목차
1. [상속의 기본 문법 이해](#1-상속의-기본-문법-이해)  
   1.1 [상속의 매우 치명적인 오해](#11-상속의-매우-치명적인-오해)  
   1.2 [상속의 가장 기본적인 특성](#12-상속의-가장-기본적인-특성)  
   1.3 [상속 관련 용어의 정리와 상속의 UML 표현](#13-상속-관련-용어의-정리와-상속의-uml-표현)  
   1.4 [상속과 생성자 1](#14-상속과-생성자-1)  
   1.5 [상속과 생성자 2](#15-상속과-생성자-2)  
   1.6 [상속과 생성자 3: 생성자 호출 관계 파악하기](#16-상속과-생성자-3-생성자-호출-관계-파악하기)  
   1.7 [상속과 생성자 4: 상위 클래스의 생성자 호출 지정](#17-상속과-생성자-4-상위-클래스의-생성자-호출-지정)  
   1.8 [적절한 생성자 정의의 예](#18-적절한-생성자-정의의-예)  
   1.9 [단일 상속만 지원하는 자바](#19-단일-상속만-지원하는-자바)  

2. [클래스 변수, 클래스 메소드와 상속](#2-클래스-변수-클래스-메소드와-상속)  
   2.1 [클래스 변수, 메소드는 상속이 되는가?](#21-클래스-변수-메소드는-상속이-되는가)  
<br>

# 1. 상속의 기본 문법 이해
## 1.1 상속의 매우 치명적인 오해
- 코드의 재활용을 위한 문법이다. (X)   
상속은 재활용을 목적으로 디자인된 문법이 아니다.  
그렇다면 무엇을 위한 문법인가? 

- 연관된 일련의 클래스들에 대해 공통적인 규약을 정의할 수 있다. (O)  
똑같은 클래스가 아니라 다른 클래스, 클래스들이 다르면 규약을 적용하기가 어렵다. 기본적으로 A, B, C클래스들은 다르기 때문이다.  
하지만 관련있는 일련의 클래스들을 상속이라는 것을 통해서 관계를 갖게 하면 공통적인 규약을 정의할 수 있다.
<br>

## 1.2 상속의 가장 기본적인 특성
```java
class Man {
    String name;

    public void tellYourName() {
        System.out.println("My name is " + name);
    }
}
```

```java
class BusinessMan extends Man {
    String company;
    String position;
    
    public void tellYourInfo() {
        System.out.println("My company is " + company);
        System.out.println("My position is = " + position);
        tellYourName();
    }
}
```
- 상속은 extends라는 키워드를 사용하고 상속할 클래스의 이름을 써준다.

- tesllYourName() 이라는 메소드를 호출하고 있는데, 이 메소드는 Man 클래스에 있다. 어떻게 이게 가능할까?

```java
BusinessMan man = new BusinessMan();

참조변수
  man ────> String name;   // Man의 멤버
            String company;
            String position;
            void tellYourName(){...}   // Man의 멤버
            void tellYourInfo(){...}
```
- Man은 BusinessMan과 같은 인스턴스 내에 존재하지만(이해를 위해 이렇게 생각해두자), 만일 Man의 멤버를 private으로 선언하면 접근은 할 수 없다는 걸 알아두자.
<br>

## 1.3 상속 관련 용어의 정리와 상속의 UML 표현
```java
class Man {...}

class BusinessMan extends Man {...}
```
- Man 클래스   
상속의 대상이 되는 클래스, **`상위 클래스`**, 기초 클래스, **`부모 클래스`**

- BusinessMan 클래스  
상속을 하는 클래스, **`하위 클래스`**, 유도 클래스, **`자식 클래스`**

- UML 표현
    ```text
    ┌─────────────┐  
    │     Man     │ 
    └─────────────┘ 
            ^
            │
    ┌─────────────┐  
    │ BusinessMan │ 
    └─────────────┘ 
    ```
<br>
<br>

## 1.4 상속과 생성자 1
```java
class Man {
    String name;

    public Man(String name) {
        this.name = name;
    }

    public void tellYourName() {
        System.out.println("My name is " + name);
    }
}
```
- 기본적으로 생성자는 그 클래스 안에 존재하는 인스턴스 변수를 초기화하는 것이 목적이다.  
생성자 정의한 것을 보면 아주 잘 되어있다.

- BusinessMan 인스턴스 생성 시 문제점은?
<br>

```java
class BusinessMan extends Man {
    String company;
    String position;

    public BusinessMan(String company, String position) {
        this.company = company;
        this.position = position;
    }
    
    public void tellYourInfo() {
        System.out.println("My company is " + company);
        System.out.println("My position is = " + position);
        tellYourName();
    }
}
```
- Man을 상속하지 않았다고 가리고 보면, BusinessMan 역시 생성자 정의가 잘 되어있다.

- Man을 상속했을 때, BusinessMan 인스턴스를 생성하면서 생성자가 호출이 되고 company, position 정보가 전달이 된다.  
하지만 name 정보는 초기화가 안되는 문제가 생긴다. 이를 어떻게 수정하면 될까?
<br>
<br>


## 1.5 상속과 생성자 2
```java
class Man {
    String name;

    public Man(String name) {
        this.name = name;
    }
    ...
}
```
<br>

```java
class BusinessMan extends Man {
    String company;
    String position;

    public BusinessMan(String name, String company, String position) {
        // 상위 클래스 Man의 멤버 초기화
        this.name = name;

        // 클래스 BusinessMan의 멤버 초기화
        this.company = company;
        this.position = position;
    }
    
    public void tellYourInfo() {...}
}
```
<br>

```java
BusinessMan man = new Businessman("YOON", "Hybrid ELD", "Staff Eng");
```
- Man을 상속하였고 name 변수에도 접근이 가능하니까 생성자에서 name, company, position 세 개의 정보를 다 받는다.

- 해당 클래스가 정의될 때 그 클래스의 멤버 변수는 그 클래스의 생성자에서 초기화 시켜주는 것이 가장 좋다.   
그것이 비록 상속 관계로 연결 되어 있어도, Man의 멤버 초기화는 Man의 생성자에서, BusinessMan의 멤버 초기화는 BusinessMan의 생성자에서 초기화를 시켜주는 것이 가장 이상적이다.  
그 클래스를 정의할 때 멤버를 어떻게 초기화 할지 결정 짓게 되어있고, 그렇게 결정 된 초기화 방법이 가장 안정적이기 때문이다. 

- Man의 멤버를 BusinessMan에서 초기화를 해주고 있다.  
모든 멤버의 초기화는 이루어진다. 그러나 생성자를 통한 초기화 원칙에는 어긋난다.

- 동작하는 코드라고는 말할 수 있겠지만 좋은 코드라고 말하기는 어렵다. 그렇다면 어떻게 작성해야 좋은 코드가 될까?
<br>
<br>


## 1.6 상속과 생성자 3: 생성자 호출 관계 파악하기
```java
// 상위 클래스
class SuperCLS {

    public SuperCLS() {
        System.out.println("I'm Super Class");
    }
}
```
<br>

```java
// 하위 클래스
class SubCLS extends SuperCLS {

    public SubCLS() {
        System.out.println("I'm Sub Class");
    }
}
```
<br>

```java
class SuperSubCon {

    public static void main(String[] args) {
        new SubCLS();
    }
}
```
<br>

```bash
I'm Super Class
I'm Sub Class
```
- 상위 클래스의 생성자 실행 이후 하위 클래스의 생성자가 실행 된다.

- 하위 클래스의 생성자에서는 반드시 상위 클래스의 생성자를  호출하게 되어있기 때문에 상위 클래스의 호출문을 넣어줘야 한다.  

- 하위 클래스의 생성자  SubCLS() 에는 상위 클래스의 생성자를 호출하는 코드가 없다. 어떻게 된 걸까?
이런 경우에는 컴파일러가 기다렸다는듯이 하위 클래스의 생성자에 넣어준다.  
우리가 직접적으로 호출하지 않아도 하위 클래스의 생성자가 호출되면, 그 안에서는 제일 먼저 상위 클래스의 생성자가 호출이 된다.

- 우리가 직접 호출하려면 어떻게 해야 할까?
<br>
<br>

## 1.7 상속과 생성자 4: 상위 클래스의 생성자 호출 지정
```java
class SuperCLS {

    public SuperCLS() {
        System.out.println("...");
    }

    public SuperCLS(int i) {
        System.out.println("...");
    }

    public SuperCLS(int i, int j) {
        System.out.println("...");
    }
}
```
<br>

```java
class SubCLS extends SuperCLS {

    public SubCLS() {
        System.out.println("...");
    }

    public SubCLS(int i) {
        super(i);
        System.out.println("...");
    }

    public SubCLS(int i, int j) {
        super(i, j);
        System.out.println("...");
    }
}
```
- 키워드 **`super`** 를 통해 상위 클래스의 생성자 호출을 명시할 수 있다.

- super는 상위 클래스의 생성자를 호출하도록 해놓은 예약어이기 때문에 생성자 안에서만 사용해야 한다.

- 기본 생성자 SubCLS() 에서는 상위 클래스의 생성자를 호출하지 않았는데, super() 문장을 넣어서 호출해주면 된다.
<br>
<br>


## 1.8 적절한 생성자 정의의 예
```java
class Man {
    String name;

    public Man(String name) {
        this.name = name;
    }
    
    public void tellYourName() {
        System.out.println("My name is " + name);
    }
}
```
<br>

```java
class BusinessMan extends Man {
    String company;
    String position;

    public BusinessMan(String name, String company, String position) {
        super(name);
        this.company = company;
        this.position = position;
    }
    
    public void tellYourInfo() {
        System.out.println("My company is " + company);
        System.out.println("My position is = " + position);
        tellYourName();
    }
}
```
- **`super(name);`**

- **`tellYourName();`**
<br>
<br>


## 1.9 단일 상속만 지원하는 자바
```java
class AAA {...}
class MMM extends AAA {...}
class ZZZ extends MMM {...}
```
- 자바는 다중 상속을 지원하지 않는다.
한 클래스에서 상속할 수 있는 최대 클래스의 수는 한 개이다.
<br>
<br>



# 2. 클래스 변수, 클래스 메소드와 상속
## 2.1 클래스 변수, 메소드는 상속이 되는가?
```java
class SuperCLS {

    static int count = 0;   // 클래스 변수

    public SuperCLS() {
        count++;    // 클래스 내에서는 직접 접근이 가능
    }
}
```
- 프로그램 전체에서 딱 하나만 존재하는데 상속의 대상이 되겠는가?

- SuperCLS 인스턴스를 생성을 해도 그 안에 count가 없는데, SubCLS 인스턴스를 생성을 했다고 해서 그 안에 count가 있을까?   당연히 없다.

- **`클래스 변수도, 클래스 메소드도 상속의 대상이 아니다.`**

- 하지만 클래스 내에서 직접 접근은 가능하다.   
SuperCLS() 기본 생성자에서 count 변수에 직접 접근을 하고 있는데, 이 권한은 하위 클래스도 갖는다.
<br>

```java
class SubCLS extends SuperCLS {

    public void showCount() {
        System.out.println(count);  // 상위 클래스에 위치하는 클래스 변수에 접근
    }
}
```
- 하위 클래스 SubCLS의 showCount() 메소드에서도 count에 직접 접근할 권한이 있다.   
단, static 변수가 private가 아닐 때여야 한다.