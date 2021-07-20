# 자바의 기본 클래스

## 목차
1. [래퍼 클래스 (Wrapper 클래스)](#1-래퍼-클래스-wrapper-클래스)  
   1.1 [기본 자료형의 값을 감싸는 래퍼 클래스](#11-기본-자료형의-값을-감싸는-래퍼-클래스)  
   1.2 [래퍼 클래스의 종류와 생성자](#12-래퍼-클래스의-종류와-생성자)  
   1.3 [래퍼 클래스의 두 가지 기능](#13-래퍼-클래스의-두-가지-기능)  
   1.4 [박싱과 언박싱의 예](#14-박싱과-언박싱의-예)  

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

