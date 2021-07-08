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
   2.1 [String 인스턴스 생성의 두가지 방법](#21-string-인스턴스-생성의-두가지-방법)  
   2.2 [String 인스턴스와 println 메소드](#22-string-인스턴스와-println-메소드)  
   2.3 [문자열 생성 방법 두 가지의 차이점](#23-문자열-생성-방법-두-가지의-차이점)  
   2.4 [2.4 String 인스턴스는 Immutable 인스턴스](#24-string-인스턴스는-immutable-인스턴스)  
   2.5 [String 인스턴스 기반 switch문 구성](#25-string-인스턴스-기반-switch문-구성)  

3. [String 클래스의 메소드](#3-string-클래스의-메소드)  
   3.1 [문자열 연결시키기](#31-문자열-연결시키기)  
   3.2 [문자열의 일부 추출](#32-문자열의-일부-추출)  
   3.3 [문자열의 내용 비교](#33-문자열의-내용-비교)  
   3.4 [기본 자료형의 값을 문자열로 바꾸기](#34-기본-자료형의-값을-문자열로-바꾸기)  
   3.5 [문자열 대상 + 연산과 += 연산](#35-문자열-대상--연산과--연산)  
   3.6 [문자열과 기본 자료형의 + 연산](#36-문자열과-기본-자료형의--연산)  
   3.7 [concat 메소드는 이어서 호출 가능](#37-concat-메소드는-이어서-호출-가능)  
   3.8 [문자열 결합의 최적화를 하지 않을 경우](#38-문자열-결합의-최적화를-하지-않을-경우)  
   3.9 [문자열 결합의 최적화를 진행 할 경우](#39-문자열-결합의-최적화를-진행-할-경우)  
   3.10 [StringBuilder](#310-stringbuilder)  
   3.11 [StringBuffer](#311-stringbuffer)
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

## 2.1 String 인스턴스 생성의 두가지 방법
```java
String str1 = new String("Simple String");

String str2 = "The Best String";
```
- 둘 다 String `인스턴스의 생성`으로 이어지고 그 결과 인스턴스의 참조 값이 반환된다.
<br>
<br>


## 2.2 String 인스턴스와 println 메소드
```java
public static void main(String[] args) {
    String str1 = new String("Simple String");
    String str2 = "The Best String";

    System.out.println(str1);
    System.out.println(str1.length());
    System.out.println();   // '개행'

    System.out.println(str2);
    System.out.println(str2.length());
    System.out.println();

    showString("Funny String");
}

public static void showString(String str) {
    System.out.println(str);
    System.out.println(str.length());
}
```
- System.out.println("hello, it's me");  
문자열을 전달하고 있다.   
구체적으로 이는 String 인스턴스의 생성으로 이어지고 그 인스턴스 참조값이 println 메소드로 전달이 되는 것이다.
<br>

```java
void println() {...}
void println(int x) {...}
void println(String x) {...}
```
- println 메소드가 다양한 인자를 전달받을 수 있는 이유는 메소드 오버로딩 
<br>
<br>


## 2.3 문자열 생성 방법 두 가지의 차이점
```java
class ImmutableString {
    public static void main(String[] args) {
        String str1 = "Simple String";
        String str2 = "Simple String";

        String str3 = new String("Simple String");
        String str4 = new String("Simple String");

        if (str1 == str2) {
            System.out.println("str1과 str2는 동일 인스턴스 참조");
        } else {
            System.out.println("str1과 str2는 다른 인스턴스 참조");
        }

        if (str3 == str4) {
            System.out.println("str3과 str4는 동일 인스턴스 참조");
        } else {
            System.out.println("str3과 str4는 다른 인스턴스 참조");
        }
    }
}
```
- String 인스턴스 4개가 만들어지고, 각 참조변수가 str1 -> new String(), str2 -> new String() ... 가리키고 있겠구나 라고 예측할 수 있다. 결과를 보자.
<br>

```text
str1과 str2는 동일 인스턴스 참조
str3과 str4는 다른 인스턴스 참조
```
- 당황스럽게도 str1과 str2는 동일 인스턴스를 참조하고 있다.  
하나의 인스턴스를 가리키고 있다는 사실을 여기서 알 수 있다. 

- String str1 = "Simple String";  
  String str2 = "Simple String";  
첫 번째 코드에서는 인스턴스가 생기고, 다음 문장을 실행할 때 새 인스턴스를 생성한 것이 아니라 str1이 가지고 있는 참조값이 단순히 str2에 저장이 되었다는 사실을 알 수 있다. 

    ```java
    String str1 = "Simple String";
    String str2 = str1;
    ```
<br>
<br>


## 2.4 String 인스턴스는 Immutable 인스턴스
*String 인스턴스는 Immutable 인스턴스!
따라서 생성되는 인스턴스의 수를 최소화 한다.*

```java
public static void main(String[] args) {
    String str1 = "Simple String";
    String str2 = str1;
    ...
}
```
- 각 인스턴스가 생성이 되지 않고, 왜 이렇게 처리하는 걸까?  

- String 인스턴스는 Immutable 인스턴스이기 때문이다.  
String 인스턴스 안에 저장된 문자열은 바꿀 수 없다. 클래스가 한 번 생성되면 바꾸지 못하도록 이렇게 디자인 되어있다. 

- 그래서 그게 str1의 참조 값을 str2에 저장하는 것과 무슨 상관일까?    
str1과 완전히 같은 문자열("Simple String")을 만들고 str2가 이를 가리키게 했을 때, 자바 가상머신은 새 인스턴스를 생성해주는 게 아니라 그 문자열을 기반으로 생성된 인스턴스의 참조값을 던져준다.
그래서 str2가 "Simple String"을 참조하게 되는데, 이렇게 해도 문제 되는 일이 없을까?

- 문제 되는 일이 없다.   
String 인스턴스는 Immutabale 인스턴스이다.   
이를 다시 생각해보면 저장된 문자열은 바꿀 수가 없다는 뜻인데, 그렇다면 Str1이 할 수 있는 일은 고작 그 값을 참조해서 출력하거나 확인하는 것, 그게 다이다.  
str1도 문자열을 바꿀 수가 없다. str2도 독립된 인스턴스를 가지고 있었다 한들 참조하는 일이 다이다.
<br>

```java
public static void main(String[] args) {
    String str1 = "Simple String";
    String str2 = new String("Simple String");
}
```
- 정말 새로운 인스턴스가 꼭 필요한 경우가 있다고 한다면?   
자바는 이를 가능하게 해준다.     
new 하는 순간 새로운 인스턴스가 생성된다. 자바 가상머신이 무조건 만들어준다.
<br>
<br>


## 2.5 String 인스턴스 기반 switch문 구성
```java
public static void main(String[] args) {
        String str = "two";

        switch (str) {
            case "one":
                System.out.println("one");
                break;
            case "two":
                System.out.println("two");
                break;
            default:
                System.out.println("default");
        }

    }
```
- 1.7 버전부터 추가된 문법
- switch문을 구성할 때 문자열 정보를 switch문의 인자에 넣을 수 있게 되었다.
<br>
<br>


# 3. String 클래스의 메소드

## 3.1 문자열 연결시키기
*String 인스턴스는 Immutable 인스턴스라는 것을 기억하자*

```java
class StringConcat {
    public static void main(String[] args) {
        String st1 = "Coffee";
        String st2 = "Bread";

        String st3 = st1.concat(st2);
        System.out.println(st3);

        String st4 = "Fresh".concat(st3);
        System.out.println(st4);
    }
}
```
- 문자열을 연결한다 함은 문자열을 변경하는 것이 아니다.  
새 문자열을 생성한다는 의미이다. (Immutable!)  
덧붙여서 새로운 문자열을 생성하고 참조값을 반환한다.

```bash
CoffeeBread
FreshCoffeeBread
```
<br>
<br>


## 3.2 문자열의 일부 추출
```java
String str = "abcdefg";

str.substring(2);
str.substring(2, 4);
```

- substring(int beginIndex);  
  substring(int beginIndex, int endIndex);

- beginIndex부터 끝 문자열까지 반환.
    - cdefg

- beginIndex부터 **`endIndex-1`** 까지 반환.
  - cd 
<br>
<br>


## 3.3 문자열의 내용 비교

- ==
참조값을 비교한다.   
같은 인스턴스를 가리키고 있느냐 아니냐를 따지는 연산.

```java
 public static void main(String[] args) {
        String st1 = "Lexicographically";
        String st2 = "lexicographically";
        int cmp;

        if (st1.equals(st2)) {
            System.out.println("두 문자열은 같습니다.");
        } else {
            System.out.println("두 문자열은 다릅니다.");
        }

        cmp = st1.compareTo(st2);
        if (cmp == 0) {
            System.out.println("두 문자열은 일치합니다.");
        } else if (cmp < 0) {
            System.out.println("사전의 앞에 위치하는 문자열: " + st1);
        } else {
            System.out.println("사전의 앞에 위치하는 문자열: " + st2);
        }

        if (st1.compareToIgnoreCase(st2) == 0) {
            System.out.println("두 문자열은 같습니다.");
        } else {
            System.out.println("두 문자열은 다릅니다.");
        }
        
    }
```
- equals
내용을 비교해서 내용이 같으면 true, 다르면 false를 반환한다.

- compareTo
좀 더 상세하다.   
st1을 st2와 비교했을 때, 내용이 같으면 0을 반환한다.  
사전 편찬 순서 상 str1이 앞서면 0보다 작은 값을 반환한다.  
str1이 뒤에 있으면 0보다 큰 값을 반환한다.

- compareToIgnoreCase  
두 문자열을 비교를 하긴 하는데, 대소문자 비교를 하지 않겠다.

```bash
두 문자열은 다릅니다.
사전의 앞에 위치하는 문자: Lexicographically
두 문자열은 같습니다.
```
<br>
<br>


## 3.4 기본 자료형의 값을 문자열로 바꾸기
```java
double e = 2.718281;
String se = String.valueOf(e);
```
- valueOf  
기본 자료형 값을 문자열 인스턴스로 치환 해주는 메소드
valueOf는 static으로 선언되어 있다.
<br>
<br>


## 3.5 문자열 대상 + 연산과 += 연산
```java
System.out.println("funny" + "camp");
// 컴파일러에 의한 자동 변환
System.out.println("funny".concat("camp"));

String str = "funny";
str += "camp";  //str = str + "camp"
// 컴파일러가 하는 일
str = str.concat("camp");
```
- 덧붙여진다고 생각하지 말자.  
String 인스턴스는 못바꾼다. 결과적으로 str이 가지고 있는 문자열과 camp 라는 문자열을 더해서 새로운 문자열을 만드는 것이다!  

- str은 funny라는 문자열을 참조하고 있었는데,  str += "camp"; 이 연산에 의해서 참조하고 있는 대상이 바뀌어버렸다.  
 전혀 다른 새로운 인스턴스를 참조한다.
<br>
<br>


## 3.6 문자열과 기본 자료형의 + 연산
```java
String str = "age: " + 17;
// No
String str = "age: ".concat(17);

String str = "age: " + 17;
// Yes
String str = "age: ".concat(String.valueOf(17));
```
- concat은 String을 매개변수로 받는다.
- int를 String으로 변환하고 concat의 매개변수로 넘겨준다.
<br>
<br>


## 3.7 concat 메소드는 이어서 호출 가능
```java
String str = "AB".concat("CD").concat("EF");
```
- 이게 어떻게 가능한가?  
문자열이 만들어지는 과정을 살펴보자.

- String str = ("AB".concat("CD")).concat("EF");

- String str = "ABCD".concat("EF");

- String str = "ABCDEF";
<br>
<br>


## 3.8 문자열 결합의 최적화를 하지 않을 경우
```java
String birth = "<양>" + 7 + '.' + 16;
// ↓ 컴파일러가 하는 일 추측
String birth =
    "<양>".concat(String.valueOf(7)).concat(String.valueOf('.')).concat(String.valueOf(16));
```
- 너무 과도한 String 인스턴스 생성으로 이어진다. 따라서 컴파일러는 이렇게 변환하지 않는다.
- 이 문장에서 중간에 새로 생성되는 String의 인스턴스의 수는 너무 많다.  
인스턴스 생성을 해도 해도 너무 하고 있다. 
<br>
<br>


## 3.9 문자열 결합의 최적화를 진행 할 경우
```java
String birth = "<양>" + 7 + '.' + 16;
// ↓
String birth = (new StringBuilder("<양>").append(7).append('.').append(16)).toString();
```
- 최종 결과물에 대한 인스턴스 생성 이외에 중간에 인스턴스를 생성하지 않는다.  
따라서 컴파일러는 이 방식으로 변환을 진행한다.

- StringBuilder  
  이 안에 buffer가 있다. buffer는 저장 공간이라고 생각하자.  
  buffer 안에 인자로 전달하는 값을 단순히 저장해둔다고 생각하자.  
  
  인스턴스는 생성되면 그 인스턴스의 참조값이 반환이 되는데, 그 반환된 인스턴스를 대상으로 append 메소드를 호출하면서 7을 전달한다.   
  
  . 찍고 append를 계속 호출하고 있는데, 앞에서 살펴봤던 concat 메소드를 계속해서 호출하고 있는 맥락과 같다.  
  하지만 append 메소드는 같은 인스턴스를 반환 받는다.
  
  new StringBuilder의 참조값이 10번지라고 한다면 .append(7).append('.').append(16)은 같은 인스턴스를 반환 받고 있다.  
  (자기가 속한 인스턴스의 참조값을 반환. 10번지)

  buffer에 [양, 7, ., 16]을 저장만 해두고, toString 메소드를 호출했을 때 저장된 내용을 가지고 새로운 String 인스턴스를 만들어낸다. 그리고 인스턴스의 참조값을 반환한다.
<br>
<br>


## 3.10 StringBuilder
### 3.10.1 StringBuilder 예
```java
public static void main(String[] args) {
        // 문자열 "123"이 저장된 인스턴스의 생성
        StringBuilder stbuf = new StringBuilder("123");

        stbuf.append(45678);    // 문자열 덧붙이기
        System.out.println(stbuf.toString());

        stbuf.delete(0, 2);     // 문자열 일부 삭제
        System.out.println(stbuf.toString());

        stbuf.replace(0, 3, "AB");  // 문자열 일부 교체
        System.out.println(stbuf.toString());

        stbuf.reverse();    // 문자열 내용 뒤집기
        System.out.println(stbuf.toString());

        String sub = stbuf.substring(2, 4); // 일부만 문자열로 반환
        System.out.println(sub);
    }
```
<br>

### 3.10.2 결과

```bash
12345678
345678
AB678
876BA
6B
```
- 출력된 결과물을 예측해냈으면 다음으로 넘어가자.
<br>
<br>


## 3.11 StringBuffer
StringBuffer와 StringBuilder는 기능적으로는 완전히 동일하다.
- 생성자를 포함한 메소드의 수
- 메소드의 기능
- 메소드의 이름과 매개변수의 선언

하지만 차이점은 다음과 같다.
- StringBuffer는 쓰레드에 안전하다.
- 따라서 쓰레드 안정성이 불필요한 상황에서 StringBuffer를 사용하면 성능의 저하만 유발하게 된다.
- 그래서 StringBuilder가 등장하게 되었다.
<br>

StringBuilder 로 했던 것들을 StringBuffer로 바꾸고 실행해도 제대로 실행된다. 그렇다면 이 둘은 왜 존재하는가?  

원래 StringBuffer가 먼저 등장했고, StringBuffer는 쓰레드에 안전하도록 디자인되었다.  
물론 안전하다는 것은 좋은 게 맞지만, 좋게 만들다보니 성능적으로 문제가 있었다. 쓰레드 안전이 불필요한 상황에서는 속도만 늦어지는 상황이 발생하였고, 그래서 StringBuilder가 만들어지게 된 것이다.


```text
StringBuffer와 StringBuilder는 기능적으로 완전히 똑같다.
StringBuffer가 먼저 만들어졌고 쓰레드에 안전하다. 그러다보니 속도가 느렸고, 쓰레드에 안전성을 필요로 하지 않을 때 쓸 수 있도록 새로 만들어진 게 StringBuilder 이다.
``` 
