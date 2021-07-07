# 메소드 오버로딩과 String 클래스

## 목차
1. [메소드 오버로딩(Method Overloading)](#1-메소드-오버로딩method-overloading)  
   1.1 [메소드 오버로딩?](#11-메소드-오버로딩)  
   1.2 [메소드 오버로딩의 예](#12-메소드-오버로딩의-예)  
   1.3 [오버로딩 관련 피해야할 애매한 상황](#13-오버로딩-관련-피해야할-애매한-상황)  
   1.4 [생성자의 오버로딩](#14-생성자의-오버로딩)  
   1.5 [키워드 this를 이용한 다른 생성자의 호출](#15-키워드-this를-이용한-다른-생성자의-호출)  
   1.6 [키워드 this를 이용한 인스턴스 변수의 접근](#16-키워드-this를-이용한-인스턴스-변수의-접근)  
2. [String 클래스](#2-string-클래스)
3. [String 클래스의 메소드](#3-string-클래스의-메소드)
<br>


# 1. 메소드 오버로딩(Method Overloading)

## 1.1 메소드 오버로딩?
```java
class MyHome {
    void mySimpleRoom(int n){...}
    void mySimpleRoom(int n1, int n2){...}
    void mySimpleRoom(double d1, double d2){...}
}
```
- 호출된 메소드를 찾을 때 참조하게 되는 두 가지 정보
  - 메소드의 이름
  - `메소드의 매개변수 정보`

- 자바 가상머신이 호출할 메소드를 찾을 때 이름만 같으면 될 것 같지만, 매개 변수 정보도 같아야 한다.  

- 매개변수를 전달받지 않는 메소드 m(); 이 따로 없다면, m(int num) 이라는 메소드를 호출하면서 매개변수 num을 전달하지 않으면 컴파일 에러로 이어진다.

- 이름이 같아도 매개 변수가 다르면 호출할 때 서로 다른 메소드로 인지된다.

- 따라서 이 둘 중 하나의 형태가 다른 메소드를 정의하는 것이 가능하다.  
이를 가리켜 **`메소드 오버로딩`** 이라 한다.
<br>

```java
MyHome a = new MyHome();  
a.mySimpleRoom(10)
```
- a 라는 참조 변수가 MyHome을 참조하고 있다. mySimpleRoom 이라는 메소드를 호출하면서 인자로 10을 전달 했다면 우리는 세개의 메소드 중에 어떤 메소드를 호출 했는지 판단할 수 있다.
  - a.mySimpleRoom(10);
  - a.mySimpleRoom(1, 2);
  - a.mySimpleRoom(1.1, 2.2);
<br>
<br>


## 1.2 메소드 오버로딩의 예
### 1.2.1 매개변수의 수
```java
void simpleMethod(int n) {...}
void simpleMethod(int n1, int n2) {...}
```
- 매개변수의 수가 다르므로 성립한다.  
인자를 하나만 전달할 것인지, 인자를 두 개를 전달할 것인지 전달되는 인자의 갯수에 따라서 어떤 메소드를 호출할 것인지 구분이 된다.
<br>

### 1.2.2 자료형
```java
void simpleMethod(int n) {...}
void simpleMethod(double d) {...}
```
- 매개변수의 자료형이 다르므로 성립한다.  
정수를 전달 할 것인지, 실수형을 전달 할 것인지 전달되는 인자의 자료형에 따라서 무엇을 호출할 것인지 구분할 수 있다.
<br>

### 1.2.3 반환형
```java
int simpleMethod() {...}
double simpleMethod() {...}
```
- 반환형은 메소드 오버로딩의 조건이 아니다.  
이 둘은 반환형이 다르다. 왠지 구분이 되는 것 같아 보이지만, 메소드를 호출을 할 때 참조하는 두 가지 정보는 메소드의 이름과 전달하는 인자의 정보이다.   반환형과는 전혀 상관이 없다.  
어떤 메소드를 호출해야 하는지 구분할 수 없기 때문에 컴파일러가 에러를 낸다.
<br>
<br>


## 1.3 오버로딩 관련 피해야할 애매한 상황
```java
class AAA {
    void simple(int p1, int p2) {...}
    void simple(int p1, double p2) {...}
}
```
- 매개변수의 수는 같지만 자료형이 다르므로 메소드 오버로딩 기준에는 부합이 된다.
<br>

```java
AAA inst = new AAA();
inst.simple(7, 'K');
```
- 이 경우에는 어떤 메소드가 호출될 것인가?

- 'K' - char 형은 int 형으로 변환이 된다. (자동형변환)
- 'K' - char 형은 double 형으로 변환이 된다. (자동형변환)

- 어느 메소드를 호출해도 자동 형변환 규칙에는 위배되지 않는다.  
메소드 호출 과정에서도 자동 형변환 규칙이 적용이 된다. 하지만 이와같은 모호한 상황을 연출하지 않는 것이 좋다.

- inst.simple(7,(int)'K');  
이처럼 인자를 전달하기 전에 강제 형변환을 해서 명확하게 표현하자.

- 다시 결론으로 어떤 메소드가 호출이 될까?  
void simple(int p1, int p2) {...} 메소드가 호출이 된다. 
char 관점에서 char와 가까이 있는 것이 int 이기 때문이다.
    ```text
    char    int    double
    ```
<br>
<br>


## 1.4 생성자의 오버로딩
```java
class Person {
    private int regiNum;    // 주민등록 번호
    private int passNum;    // 여권 번호

    Person(int rnum, int pnum) {
        regiNum = rnum;
        passNum = pnum;
    }

    Person(int rnum) {
        regiNum = rnum;
        passNum = 0;
    }
    
    void showPersonalInfo() {...}
}
```

```java
public static void main(String[] args) {
        // 여권 있는 사람의 정보를 담은 인스턴스 생성
        Person jung = new Person(335577, 112233);
        
        // 여권 없는 사람의 정보를 담은 인스턴스 생성
        Person hong = new Person(775544);
        
        jung.showPersonalInfo();
        hong.showPersonalInfo();
    }
```
- 생성자의 오버로딩을 통해 생성되는 인스턴스의 유형을 구분할 수 있다.
  - 여권이 있는 사람과 없는 사람
  - 운전 면허증을 보유한 사람과 보유하지 않은 사람
<br>
<br>


## 1.5 키워드 this를 이용한 다른 생성자의 호출
```java
class Person {
    private int regiNum;    // 주민등록 번호
    private int passNum;    // 여권 번호

    Person(int rnum, int pnum) {
        regiNum = rnum;
        passNum = pnum;
    }

    Person(int rnum) {
        this(rnum, 0);
    }
    
    void showPersonalInfo() {...}
}
```

```java
Person(int rnum) {      ->    Person(int rnum) {
    regiNum = rnum;               this(rnum, 0);
    passNum = 0;              }
}                       
```
- this
**`이 인스턴스`** 를 의미한다.

- this(rnum, 0);  
생긴 모양을 보자. 매개변수로 rnum과 0을 전달하는 메소드를 호출하는 것처럼 보인다.  
이 this는 어디서 쓰인지가 중요한데, 여기서는 생성자 안에서 쓰였다.  
이 인스턴스의 생성자 안에서 쓰였다.   
rnum과 0을 인자로 받는 이 인스턴스의 오버로딩 된 다른 생성자를 호출한다.

- 키워드 this를 이용한 다른 생성자의 호출은 중복된 코드를 줄이는 효과가 있다.

- new Person(991212);   
인스턴스를 생성하면 처음에 Person(int rnum) 생성자가 호출이 되고 this(rnum, 0); 에 의해서 Person(int rnum, int pnum) 생성자가 호출이 된다.

- 생성자를 2개 정의한 이유는 여권 번호가 있을 수도 있고 없을 수도 있기 때문이다.  
여권 번호가 있느냐 없느냐에 따라 모든 초기화 문장을 중복해서 넣어주는 것은 불편해보이고 보기에도 안좋아보인다.
<br>
<br>


## 1.6 키워드 this를 이용한 인스턴스 변수의 접근
```java
class SimpleBox {
    private int data;
    
    SimpleBox(int data) {
        this.data = data;
    }
}
```
- this.data = data;  
`.`은 언제 찍나? 인스턴스 변수나 인스턴스 메소드에 접근할 때 찍는다.
<br>

```java
SimpleBox(int data) {
    this.data = data;
}
```
- SimpleBox 생성자 안에서의 data는 매개변수 data를 의미한다.

- this.data의 data는 SimpleBox 의 인스턴스 변수 data를 뜻하는 것인가?  
SimpleBox(int data) 생성자의 매개변수 data를 뜻하는 것인가?    
this.data는 어느 위치에서 건 인스턴스 변수 data를 의미한다.

- 매개변수로 전달된 값이 인스턴스 변수로 초기화된다.
<br>
<br>


# 2. String 클래스

# 3. String 클래스의 메소드