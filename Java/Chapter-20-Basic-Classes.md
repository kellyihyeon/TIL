# 자바의 기본 클래스

## 목차
1. [래퍼 클래스 (Wrapper 클래스)](#1-래퍼-클래스-wrapper-클래스)  
   1.1 [기본 자료형의 값을 감싸는 래퍼 클래스](#11-기본-자료형의-값을-감싸는-래퍼-클래스)  
   1.2 [래퍼 클래스의 종류와 생성자](#12-래퍼-클래스의-종류와-생성자)  
   1.3 [래퍼 클래스의 두 가지 기능](#13-래퍼-클래스의-두-가지-기능)  
   1.4 [박싱과 언박싱의 예](#14-박싱과-언박싱의-예)  
   1.5 [오토 박싱과 오토 언박싱](#15-오토-박싱과-오토-언박싱)  
   1.6 [오토 박싱, 오토 언박싱의 또 다른 예](#16-오토-박싱-오토-언박싱의-또-다른-예)  

2. []()
3. []()
4. []()

<br>

# 1. 래퍼 클래스 (Wrapper 클래스)
*감싸는 용도의 클래스
기본 자료형의 값을 인스턴스를 이용해서 감싼다.*

## 1.1 기본 자료형의 값을 감싸는 래퍼 클래스
```java
class UseWrapperClass {
    
    public static void showData(Object obj) {
        System.out.println(obj);
    }

    public static void main(String[] args) {
        Integer iInst = new Integer(3);
        showData(iInst);
        showData(new Double(7.15));
    }
}
```
- showData(Object obj)  
인스턴스를 요구하는 메소드  
이 메소드를 통해서 정수나 실수를 출력하려면 해당 값을 **`인스턴스화`** 해야 한다.  

- 기본 자료형의 값을 인스턴스로 감싸는 목적의 클래스를 가리켜 래퍼 클래스라 한다.
<br>
<br>


## 1.2 래퍼 클래스의 종류와 생성자
```text
Boolean     public Boolean(boolean value)

Character   public Character(char value)

Byte        public Byte(byte value)

Short       public Short(short value)

Integer     public Integer(int value)

Long        public Long(long value)

Float       public Float(float value),  public Float(double value)

Double      public Double(double value)
```
<br>
<br>


## 1.3 래퍼 클래스의 두 가지 기능
```   
  기본 자료형의 값                  Wrapper 인스턴스
    ┌────────┐        Boxing         ┌─────────┐
    │ byte   │  ───────────────────> │ Byte    │
    │ short  │                       │ Short   │
    │ int    │                       │ Integer │
    │ float  │                       │ Float   │
    │ double │                       │ Double  │
    │ ...    │  <─────────────────── │ ...     │
    └────────┘        Unboxing       └─────────┘
```
<br>
<br>


## 1.4 박싱과 언박싱의 예
```java
public static void main(String[] args) {
    Integer iObj = new Integer(10);    // 박싱
    Double dObj = new Double(3.14);    // 박싱
    ...

    int num1 = iObj.intValue();        // 언박싱
    double num2 = dObj.doubleValue();  // 언박싱
    ...

    // 래퍼 인스턴스 값의 증가 방법
    iObj = new Integer(iObj.intValue() + 10);
    dObj = new Double(dObj.doubleValue() + 1.2);
    ...
}
```
<br>
<br>


## 1.5 오토 박싱과 오토 언박싱
```java
class AutoBoxingUnboxing {
    public static void main(String[] args) {
    Integer iObj = 10;    // 오토 박싱 진행
    Double dObj = 3.14;   // 오토 박싱 진행
    ...

    int num1 = iObj.intValue();        // 오토 언박싱 진행
    double num2 = dObj.doubleValue();  // 오토 언박싱 진행
    ...
    }
}
```
- 인스턴스가 와야 할 위치에 기본 자료형 값이 오면 오토 박싱 진행

- 기본 자료형 값이 와야 할 위치에 인스턴스가 오면 오토 언박싱 진행
<br>
<br>


## 1.6 오토 박싱, 오토 언박싱의 또 다른 예
```java
public static void main(String[] args) {
    Integer num = 10;
    num++;    // new Integer(num.intValue() + 1)
    ...

    num += 3;   // new Integer(num.intValue() + 3)
    ...

    int r = num + 5;     // 오토 언박싱 진행
    Integer rObj = num - 5;  // 오토 박싱 진행
    ...
}

```
<br>

``` java
num++;
num += 3;   
```
- 오토 박싱과 오토 언박싱을 동시에 진행하고 있다.  
  
- 먼저 언박싱으로 int형 값을 꺼내고, 그 값에 + 1 연산을 한 뒤 이 값을 Integer형 인스턴스로 박싱을 한다.
  
- int r = num + 5;  
-> int r = num.intValue() + 5;   
num은 Integer형 인스턴스라서 연산을 할 수가 없다. num의 int형 값을 꺼내서 연산을 해야 한다.  

- Integer rObj = num - 5;  
-> Integer rObj = new Integer(num.intValue() - 5);  
num.intValue() - 5가 되어야 하고, 그 결과 int 형 값이 나오는데 이를 Integer로 감싸줘야 한다.
<br>
<br>


## 1.7 Number 클래스
- java.long.Number
모든 래퍼 클래스가 상속하는 클래스

- java.lang.Number에 정의된 추상 메소드들  
  public abstract int intValue()  
  public abstract long longValue()  
  public abstract double doubleValue()  

  즉 래퍼 인스턴스에 저장된 값을 원하는 형의 기본 자료형 값으로 반환할 수 있다.
<br>
<br>



## 1.8 Number 클래스의 추상 메소드 호출의 예
```java
public static void main(String[] args) {
    Integer num1 = new Integer(29);
    System.out.println(num1.intValue());    // int형 값으로 반환
    System.out.println(num1.doubleValue());    // double형 값으로 반환

    Double num2 = new Double(3.14);
    System.out.println(num2.intValue());    // int형 값으로 반환
    System.out.println(num2.doubleValue());    // double형 값으로 반환
}
```
<br>

```bash
29
29.0
3
3.14
```
<br>
<br>


## 1.9 래퍼 클래스의 다양한 static 메소드들
```java
public static void main(String[] args) {
    // 클래스 메소드를 통한 인스턴스 생성 방법 두 가지
    Integer n1 = Integer.valueOf(5);    // 숫자 기반 Integer 인스턴스 생성
    Integer n2 = Integer.valueOf("1024");    // 문자열 기반 Integer 인스턴스 생성

    // 대소 비교와 합을 계산하는 클래스 메소드
    System.out.println("큰 수: " + Integer.max(n1, n2));
    System.out.println("작은 수: " + Integer.min(n1, n2));
    System.out.println("합: " + Integer.sum(n1, n2));
    System.out.println

    // 정수에 대한 2진, 8진, 16진수 표현 결과를 반환하는 클래스 메소드
    System.out.println("12의 2진 표현: " + Integer.toBinaryString(12));
    System.out.println("12의 8진 표현: " + Integer.toOctalString(12));
    System.out.println("12의 16진 표현: " + Integer.toHexString(12));
}
```