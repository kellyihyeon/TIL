# 자바의 기본 클래스

## 목차
1. [래퍼 클래스 (Wrapper 클래스)](#1-래퍼-클래스-wrapper-클래스)  
   1.1 [기본 자료형의 값을 감싸는 래퍼 클래스](#11-기본-자료형의-값을-감싸는-래퍼-클래스)  
   1.2 [래퍼 클래스의 종류와 생성자](#12-래퍼-클래스의-종류와-생성자)  
   1.3 [래퍼 클래스의 두 가지 기능](#13-래퍼-클래스의-두-가지-기능)  
   1.4 [박싱과 언박싱의 예](#14-박싱과-언박싱의-예)  
   1.5 [오토 박싱과 오토 언박싱](#15-오토-박싱과-오토-언박싱)  
   1.6 [오토 박싱, 오토 언박싱의 또 다른 예](#16-오토-박싱-오토-언박싱의-또-다른-예)  
   1.7 [Number 클래스](#17-number-클래스)  
   1.8 [Number 클래스의 추상 메소드 호출의 예](#18-number-클래스의-추상-메소드-호출의-예)  
   1.9 [래퍼 클래스의 다양한 static 메소드들](#19-래퍼-클래스의-다양한-static-메소드들)  

2. [BigInteger 클래스와 BigDecimal 클래스](#2-biginteger-클래스와-bigdecimal-클래스)  
   2.1 [매우 큰 정수를 표현하기 위한 java.math.BigInteger 클래스](#21-매우-큰-정수를-표현하기-위한-javamathbiginteger-클래스)  
   2.2 [오차 없는 실수를 표현 하기 위한 BigDecimal 클래스](#22-오차-없는-실수를-표현-하기-위한-bigdecimal-클래스)  

3. [Math 클래스와 난수의 생성 그리고 문자열 토큰](#3-math-클래스와-난수의-생성-그리고-문자열-토큰token의-구분)  
   3.1 [수학 관련 연산 기능을 제공하는 Math 클래스](#31-수학-관련-연산-기능을-제공하는-math-클래스)  
   3.2 [난수의 생성](#32-난수의-생성)  

4. [Arrays 클래스](#4-arrays-클래스)  
   4.1 [Arrays 클래스의 배열 복사 메소드](#41-arrays-클래스의-배열-복사-메소드)  
   4.2 [copyOf 메소드 호출의 예](#42-copyof-메소드-호출의-예)  
   4.3 [arraycopy 메소드 호출의 예](#43-arraycopy-메소드-호출의-예)  
   4.4 [두 배열의 내용 비교](#44-두-배열의-내용-비교)  
   4.5 [인스턴스 저장 배열의 비교 예](#45-인스턴스-저장-배열의-비교-예)  
   4.6 [Arrays의 equals 메소드가 내용을 비교하는 방식](#46-arrays의-equals-메소드가-내용을-비교하는-방식)  
   4.7 [Object 클래스의 equals 메소드는?](#47-object-클래스의-equals-메소드는)  
   4.8 [Object 클래스의 equals 메소드 오버라이딩 결과](#48-object-클래스의-equals-메소드-오버라이딩-결과)  
   4.9 [배열의 정렬: Arrays 클래스의 sort 메소드](#49-배열의-정렬-arrays-클래스의-sort-메소드)  
   4.10 [오름차순 정렬이란?](#410-오름차순-정렬이란)  
   4.11 [compareTo 메소드 정의 기준](#411-compareto-메소드-정의-기준)  
   4.12 [클래스에 정의하는 오름차순 기준](#412-클래스에-정의하는-오름차순-기준)  
   4.13 [다음과 같이 구현도 가능](#413-다음과-같이-구현도-가능)  
   4.14 [배열에 저장된 인스턴스들의 정렬의 예](#414-배열에-저장된-인스턴스들의-정렬의-예)  
   4.15 [배열의 탐색: 기본 자료형 값 대상](#415-배열의-탐색-기본-자료형-값-대상)  
   4.16 [배열의 탐색: 기본 자료형 값 대상의 예](#416-배열의-탐색-기본-자료형-값-대상의-예)  
   4.17 [배열의 탐색: 인스턴스 대상](#417-배열의-탐색-인스턴스-대상)  
   4.18 [배열의 탐색: 인스턴스 대상의 예](#418-배열의-탐색-인스턴스-대상의-예)  

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
- Number 클래스를 상속한 래퍼 클래스들은 각각 intValue(), longValue(), doubleValue() 메소드를 정의해놓고 있다.
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
- Integer n1 = Integer.valueOf(5);  
= new Integer(5); 와 같다.
static 메소드 호출을 통해서 인스턴스 생성이 가능하다.

```bash
큰 수: 1024
작은 수: 5
합: 1029

12의 2진 표현: 1100
12의 8진 표현: 14
12의 16진 표현: c
```
<br>
<br>


# 2. BigInteger 클래스와 BigDecimal 클래스
- BigInteger  
long보다 더 큰 정수를 표현해야 될 때 사용하는 클래스 

- BigDecimal  
아주 사소한 오차도 발생시키지 않는 실수 연산을 지원하기 위해 디자인 된 클래스
<br>

## 2.1 매우 큰 정수를 표현하기 위한 java.math.BigInteger 클래스
```java
public static void main(String[] args) {
    // long 형으로 표현 가능한 값의 크기 출력
    System.out.println("최대 정수: " + Long.MAX_VALUE);
    System.out.println("최소 정수: " + Long.MIN_VALUE);
    System.out.println();

    // 매우 큰 수를 BigInteger 인스턴스로 표현
    BigInteger big1 = new BigInteger("10000000000000000000");
    BigInteger big2 = new BigInteger("-9999999999999999999");

    // BigInteger 기반 덧셈 연산
    BigInteger r1 = big1.add(big2);
    System.out.println("덧셈 결과: " + r1);

    //  BigInteger 기반 곱셈 연산
    BigInteger r2 = big1.multiply(big2);
    System.out.println("곱셈 결과: " + r2);
    System.out.println();

    // 인스턴스에 저장된 값을 int 형 정수로 반환
    int num  = r1.intValueExact();
    System.out.println("Form BigInteger = " + num);
}
```
- BigInteger 클래스도 Immutable 인스턴스이다.

-  BigInteger big1 = new BigInteger("10000000000000000000");  
왜 문자열로 표현했을까?  
문자열이 아닌 리터럴 정수로 넣었다면 이를 컴파일러가 보는 순간 int 형으로 표현할 수 있는 값의 범위를 넘어섰다고 판단하고 종료시킨다.  
값의 범위를 넘어서는 값은 ""로 묶어서 문자열로 넘긴다.  
<br>

```bash
최대 정수: 9223372036854775807
최소 정수: -9223372036854775808

덧셈 결과: 1
곱셈 결과: -99999999999999999990000000000000000000

Form BigInteger = 1
```
<br>
<br>

## 2.2 오차 없는 실수를 표현 하기 위한 BigDecimal 클래스
```java
public static void main(String[] args) {
    BigDecimal d1 = new BigDecimal("1.6");
    BigDecimal d2 = new BigDecimal("0.1");
    System.out.println("덧셈 결과 = " + d1.add(d2));
    System.out.println("곱셈 결과 = " + d1.multiply(d2));
}
```
- BigDecimal 클래스도 Immutable 인스턴스이다.

- BigDecimal d1 = new BigDecimal("1.6");  
여기에는 왜 또 문자열로 표현했을까?  
1.6 리터럴 정수를 쓰면 메모리 공간 어딘가에 저장이 되어야한다.  
자바가 1.6 이라고 표현하는 순간 여기에는 오차가 존재한다.  
BigDecimal에 전달하는 1.6이라는 리터럴 정수는 오차가 존재하는 1.6이기 때문에 오차를 발생시키지 않기 위해서 문자열로 생성한 것이다.  
<br>

```bash
덧셈 결과 = 1.7
곱셈 결과 = 0.16
```
<br>

```java
덧셈    public BigDecimal add(BigDecimal augend)
뺄셈    public BigDecimal subtract(BigDecimal subtrahend)
곱셈    public BigDecimal multiply(BigDecimal multiplicand)
나눗셈  public BigDecimal divide(BigDecimal divisor)
```
<br>
<br>


## 3. Math 클래스와 난수의 생성 그리고 문자열 토큰(Token)의 구분
## 3.1 수학 관련 연산 기능을 제공하는 Math 클래스
```java
public static void main(String[] args) {
    System.out.println("원주율: " + Math.PI);
    System.out.println("2의 제곱근: " + Math.sqrt(2));
    System.out.println();

    System.out.println("파이에 대한 Degree: " + Math.toDegrees(Math.PI));
    System.out.println("2 파이에 대한 Degree: " + Math.toDegrees(2.0 * Math.PI));
    System.out.println();

    double radian45 = Math.toRadians(45);   // 라디안으로 변환
    System.out.println("싸인 45: " + Math.sin(radian45));
    System.out.println("코싸인 45: " + Math.cos(radian45));
    System.out.println("탄젠트 45: " + Math.tan(radian45));
    System.out.println();

    System.out.println("로그 25: " + Math.log(25));
    System.out.println("2의 16승: " + Math.pow(2, 16));
}
```
<br>

```bash
원주율: 3.141592653589793
2의 제곱근: 1.4142135623730951

파이에 대한 Degree: 180.0
2 파이에 대한 Degree: 360.0

싸인 45: 0.7071067811865475
코싸인 45: 0.7071067811865476
탄젠트 45: 0.9999999999999999

로그 25: 3.2188758248682006
2의 16승: 65536.0
```
- Math 클래스  
수학 관련된 연산을 하는 데 사용하는 클래스

- Math는 인스턴스를 생성할 필요가 없다.   
메소드가 static으로 선언되어있어서 가져다 쓰면 된다.
<br>
<br>


## 3.2 난수의 생성
```java
Random rand = new Random();

public boolean nextBoolean()    boolean형 난수 반환

public int nextInt()            int형 난수 반환

public long nextLong()          long형 난수 반환

public int nextInt(int bound)   0 이상 bound 미만 범위의 int형 난수 반환

public float nextFloat()        0.0 이상 1.0 미만의 float형 난수 반환

public double nextDouble()      0.0 이상 1.0 미만의 double형 난수 반환
```
- 난수  
무작위의 수를 말한다. 예측도 불가능하고 규칙을 찾을 수도 없다.

- pseudo random number   
가짜 난수. 컴퓨터가 뽑아낸 수. 난수처럼 보이는 난수.  
난수를 뽑아내는 알고리즘이 있고, 여기서 난수를 뽑아낸다.
<br>
<br>


## 3.3 난수 생성의 예
```java
public static void main(String[] args) {
    Random rand = new Random();
    for (int i = 0; i < 7; i++) {
        System.out.println(rand.nextInt(1000));
    }
}
```
```java
public Random() {
    // Random(long seed) 생성자를 호출한다.
    this(System.currentTimeMillis());
}
```
- 컴퓨터의 현재 시간을 반환한다. 밀리세컨까지 나오기 때문에 실행할 때마다 다른 결과가 나오기 때문에 진짜 난수처럼 보인다.
- 실행할 때마다 다른 결과를 보인다.
<br>

```java
public static void main(String[] args) {
    Random rand = new Random(12);
    for (int i = 0; i < 7; i++) {
        System.out.println(rand.nextInt(1000));
    }
}
```
- 실행할 때마다 같은 결과를 보인다.
<br>
<br>


## 3.4 문자열의 토큰 구분
```text
"PM:08:45"
```
- 이 문자열의 구분자가 :일 경우 토큰은 다음 세 가지이다.  
PM  08  45
<br>

```java
StringTokenizer st = new StringTokenizer("PM:08:45", ":");
```
- 위와 같이 토큰을 나누는 방법이다.
  
- public boolean hasMoreTokens()  
반환할 토큰이 남아 있는가?

- public String nextToken()
다음 토큰을 반환

- 구분자가 무엇이냐에 따라서 토큰이 달라진다.  
만일 구분자를 0으로 지정한다면 토큰은 2개가 된다. 

- 토큰이 남아있는지 확인을 하고 다음 토큰을 반환하는 메소드를 사용해야 코드의 안정성이 높아진다.
<br>
<br>


## 3.5 문자열의 토큰 구분의 예
```java
public static void main(String[] args) {
    StringTokenizer st1 = new StringTokenizer("PM:08:45", ":");

    while (st1.hasMoreTokens()) {
        System.out.print(st1.nextToken() + ' ');
    }
    System.out.println();


    StringTokenizer st2 = new StringTokenizer("12 + 36 - 8 / 2 = 44", "+-/= ");

    while (st2.hasMoreTokens()) {
        System.out.print(st2.nextToken() + ' ');
    }
    System.out.println();
}
```
- 공백도 구분자에 포함되므로 공백을 포함시켜서 숫자만 토큰으로 남게 하였다.
<br>

```bash
PM 08 45 
12 36 8 2 44 
```
<br>
<br>


# 4. Arrays 클래스
## 4.1 Arrays 클래스의 배열 복사 메소드
### 4.1.1 복제
*원본이 있으면 원본과 똑같은 형태를 새롭게 만들어 내는 것이다.*
```java
public static int[] copyOf(int[] original, int newLength)
```
- original에 전달된 배열을 첫 번째 요소부터 newLength의 길이만큼 복사
<br>

```java
public static int[] copyOfRange(int[] original, int from, int to)
```
- original에 전달된 배열을 인덱스 from부터 to 이전 요소까지 복사
<br>


### 4.1.2 복사
*A가 가지고 있는 내용을 B에 옮기는 것이다.  
A와 B라는 종이가 2장이 마련되어 있는 상황을 전제한다.*

```java
public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
```
- 배열 src의 srcPos에서 배열 dest의 destPos로 length 길이만큼 복사

- 복사할 원본 배열이 있고, 원본을 복사할 다른 배열이 존재한다.   
배열을 2개 마련해놓고 arraycopy 메소드를 호출한다. 
<br>
<br>


## 4.2 copyOf 메소드 호출의 예
```java
public static void main(String[] args) {
    double[] arOrg = {1.1, 2.2, 3.3, 4.4, 5.5};

    // 배열 전체를 복사
    double[] arCpy1 = Arrays.copyOf(arOrg, arOrg.length);

    // 세 번째 요소까지만 복사
    double[] arCpy2 = Arrays.copyOf(arOrg, 3);

    for (double d : arCpy1) {
        System.out.print(d + "\t");
    }
    System.out.println();

    for (double d : arCpy2) {
        System.out.print(d + "\t");
    }
    System.out.println();
}
```
```bash
1.1	2.2	3.3	4.4	5.5	
1.1	2.2	3.3	
```
<br>
<br>


## 4.3 arraycopy 메소드 호출의 예
```java
public static void main(String[] args) {
    double[] org = {1.1, 2.2, 3.3, 4.4, 5.5};
    double[] cpy = new double[3];

    // 배열 org의 인덱스 1에서 배열 cpy 인덱스 0으로 세 개의 요소 복사
    System.arraycopy(org, 1, cpy, 0, 3);

    for (double d : cpy) {
        System.out.print(d + "\t");
    }
    System.out.println();
}
```
```bash
2.2 3.3 4.4
```
- org
참조하는 배열

- cpy
복사할 배열이 미리 존재해야 한다.

- System.arraycopy(org, 1, cpy, 0, 3);  
첫번째, 두번째 인자는 원본을 말하고, 세번째 네번째 인자는 `어디로?`를 의미하고, 다섯번째 배열은 몇개를 복사할 것인지를 의미한다.
<br>
<br>


## 4.4 두 배열의 내용 비교
*Arrays의 equals 메소드*

```java
public static voolean equals(int[] a, int[] a2)
```
- static 메소드이다.

- 매개변수 a와 a2로 전달된 배열의 `내용을 비교`하여 true 또는 false 반환
<br>

```java
public static void main(String[] args) {
    int[] ar1 = {1, 2, 3, 4, 5};
    int[] ar2 = Arrays.copyOf(ar1, ar1.length);
    System.out.println(Arrays.equals(ar1, ar2));
}
```
```bash
true
```
- `내용 비교`를 한다는 사실이 매우 중요하다.
<br>
<br>


## 4.5 인스턴스 저장 배열의 비교 예
```java
class INum {
    private int num;

    public INum(int num) {
        this.num =  num;
    }
}
```

```java
class ArrayObjEquals {
    public static void main(String[] args) {
        INum[] ar1 = new INum[3];
        INum[] ar2 = new INum[3];
        ar1[0] = new INum(1);
        ar2[0] = new INum(1);

        ar1[1] = new INum(2);
        ar2[1] = new INum(2);

        ar1[2] = new INum(3);
        ar2[2] = new INum(3);

        System.out.println(Arrays.equals(ar1, ar2));
    }
}
```
```bash
false
```
- 내용만 비교하는데 왜 false가 나왔지?

- equals 메소드가 어떻게 정의되어 있는지에 따라 다르다.
equals 메소드는 인스턴스별 비교를 한다.  
ar1[0] = new INum(1);  
ar2[0] = new INum(1);  
이렇게 각 요소끼리 비교를 한다. 비교를 해서 각 요소가 저장하고 있는 인스턴스가 전부 같으면 true를 반환한다.

- 인스턴스끼리 비교는?   
equals 메소드를 호출해서 비교를 한다.

- INum 인스턴스를 보자.  
equals 메소드를 오버라이딩 하지 않았다. 이는 즉 Object의 equals 메소드를 사용한다는 의미이다.  (참조 값 비교)  
하나만 false가 나도 전체 false를 반환하는데, 세 인스턴스는 전부 false이다.
<br>
<br>


## 4.6 Arrays의 equals 메소드가 내용을 비교하는 방식
```java
publci static boolean equals(Object[] a, Obhect[] a2) {
    ...
    for (int i = 0; i < length; i++) {
        Object o1 = a[i];
        Object o2 = a2[i];

        if (!(o1 == null ? o2 == null : o1.equals(o2))) {
            return false;
        }
    }
    ...
}
```
<br>
<br>


## 4.7 Object 클래스의 equals 메소드는?
```java
public boolean equals(Object obj) {
    if(this == obj) {   // 두 인스턴스가 동일 인스턴스이면
        return true;
    } else {
        return false;
    }
}
```
- Object 클래스에 정의된 equals 메소드는 참조 값 비교를 한다.

- Arrays 클래스의 equals 메소드가 두 배열의 내용 비교를 하도록 하려면 비교 대상의 equals 메소드를 내용 비교의 형태로 오버라이딩 해야 한다.
<br>
<br>


## 4.8 Object 클래스의 equals 메소드 오버라이딩 결과
```java
class INum {
    private int num;

    public INum(int num) {
        this.num =  num;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.num == ((INum)obj).num) {
            return true;
        } else {
            return false;
        }
    }   
}
```
- [x] (this.num == ((INum)obj).num)  
비교하고 있는 게 int형 이어서 == 으로 참조값 비교했을 때 내용이 같으면 동일한 참조값이 나오는 거구나!
<br>

```java
public static void main(String[] args) {
    INum[] ar1 = new INum[3];
    INum[] ar2 = new INum[3];
    ar1[0] = new INum(1);
    ar2[0] = new INum(1);

    ar1[1] = new INum(2);
    ar2[1] = new INum(2);

    ar1[2] = new INum(3);
    ar2[2] = new INum(3);

    System.out.println(Arrays.equals(ar1, ar2));
}
```
```bash
true
```
<br>
<br>


## 4.9 배열의 정렬: Arrays 클래스의 sort 메소드
```java
public static void sort(int[] a)
```
- 매개변수 a로 전달된 배열을 오름차순(Ascending Numerical Order)으로 정렬
<br>

```java
public static void main(String[] args) {
    int[] ar1 = {1, 5, 3, 2, 4};
    double[] ar2 = {3.3, 2.2, 5.5, 1.1, 4.4};

    Arrays.sort(ar1);
    Arrays.sort(ar2);

    for (int n : ar1) {
        System.out.print(n + "\t");
    }
    System.out.println();

    for (double d : ar2) {
        System.out.print(d + "\t");
    }
    System.out.println();
}
```
```bash
1    2     3    4    5
1.1  2.2.  3.3  4.4  5.5
```
<br>
<br>

## 4.10 오름차순 정렬이란?
- 수의 경우는 오름차순에 대한 기준이 명확하다.  
하지만 인스턴스는 다르다. 인스턴스를 어떤 기준으로 오름차순 할 것인가?  
이 때에는 기준이 필요하다. 오름차순 순서상 크고 작음에 대한 기준이 필요하다.  
무엇을 기준으로 오름차순하느냐에 따라 결과가 달라지기 때문이다.  
<br>
<br>


## 4.11 compareTo 메소드 정의 기준
```java
interface Comparable
```
- int compareTo(Object o)

- 기준이 필요하다고 생각되면 Comparable 인터페이스를 구현하면 된다.

- 인자로 전달된 o가 작다면 양의 정수 반환.  
  인자로 전달된 o가 크다면 음의 정수 반환.  
  인자로 전달된 o와 같다면 0을 반환

- 위의 내용을 기준으로 메소드를 정의하면 반환값을 통해서 어떤 인스턴스가 어떤 인스턴스보다 크거나 작은지 판단이 가능하다.

- A.compareTo(B);  
반환 값이 헷갈리면 앞에 있는 인스턴스 A를 기준으로 생각해보자.  
A가 인자 값보다 더 크면 양의 정수, A가 인자 값보다 작으면 음의 정수, A와 인자 값이 같으면 0
<br>
<br>


## 4.12 클래스에 정의하는 오름차순 기준
```java
class Person implements Comparable {
    private String name;
    private int age;

    @Override
    public int compareTo(Object o) {
        Person p = (Person)o;

        if (this.age > p.age) {
            return 1;       // 인자로 전달된 o가 작다면 양의 정수 반환
        } else if (this.age < p.age) {
            return -1;      // 인자로 전달된 o가 크다면 음의 정수 반환
        } else {
            return 0;       // 인자로 전달된 o와 같다면 0을 반환
        }
    }
}
```
- Comparable 인터페이스를 구현한다는 것은 오름차순 순서상 크고 작음에 대한 기준을 제공한다는 의미이다.

- Person 클래스에서 나이를 기준으로 오름차순 순서를 정의했다.
<br>
<br>


## 4.13 다음과 같이 구현도 가능
```java
@Override
public int compareTo(Object o) {
    Person p = (Person)o;
    return this.age - p.age;
}
```
- return 되는 값이 기준에 맞기 때문에 이렇게 간단하게 구현을 할 수도 있다.
<br>
<br>



## 4.14 배열에 저장된 인스턴스들의 정렬의 예
```java
class Person implements Comparable {
    private String name;
    private int age;


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        return this.age - p.age;
    }

    public String toString() {
        return name + ": " + age;
    }
}
```
```java
public static void main(String[] args) {
    Person[] ar = new Person[3];
    ar[0] = new Person("Lee", 29);
    ar[1] = new Person("Goo", 15);
    ar[2] = new Person("Soo", 37);

    Arrays.sort(ar);
    for (Person p : ar) {
        System.out.println(p);
    }
}
```
```bash
Goo: 15
Lee: 29
Soo: 37
```
- sort 메소드 내에서 인스턴스들을 비교해보고 계산하여 오름차순 정렬을 해준다.
<br>
<br>


## 4.15 배열의 탐색: 기본 자료형 값 대상
```java
public static int binarySearch(int[] a, int key)
```
- 배열 a에서 key를 찾아서 있으면 key의 인덱스 값, 없으면 0보다 작은 수 반환

- binarySearch는 이진 탐색을 진행한다.  
그리고 이진 탐색을 위해서는 탐색 이전에 데이터들이 오름차순으로 정렬되어 있어야 한다.
<br>
<br>


## 4.16 배열의 탐색: 기본 자료형 값 대상의 예
```java
class ArraySearch {
    public static void main(String[] args) {
        int[] ar = {33, 55, 11, 44, 22};
        Arrays.sort(ar);    // 탐색 이전에 정렬이 선행되어야 한다.

        for (int n : ar) {
            System.out.print(n + "\t");
        }
        System.out.println();

        int idx = Arrays.binarySearch(ar, 33);  // 배열 ar 에서 33을 찾아라.
        System.out.println("index of 33 = " + idx);
    }
}
```
```bash
11	22	33	44	55	
index of 33 = 2
```
<br>
<br>



## 4.17 배열의 탐색: 인스턴스 대상
```java
public static int binarySearch(Object[] a, Object key)
```
- 마찬가지로 탐색 대상들은 오름차순으로 정렬되어 있어야 한다.  
그리고 탐색 대상의 확인 여부는 compareTo 메소드의 호출 결과를 근거로 한다.  
즉, 탐색 방식은 인스턴스의 내용 비교이다.
<br>
<br>


## 4.18 배열의 탐색: 인스턴스 대상의 예
```java
class Person implements Comparable {
    private String name;
    private int age;


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        return this.age - p.age;
    }

    public String toString() {
        return name + ": " + age;
    }

    public static void main(String[] args) {
        Person[] ar = new Person[3];
        ar[0] = new Person("Lee", 29);
        ar[1] = new Person("Goo", 15);
        ar[2] = new Person("Soo", 37);

        Arrays.sort(ar);
        int idx = Arrays.binarySearch(ar, new Person("Who are you?", 37));
        System.out.println(ar[idx]);
    }
}
```
```bash
Soo: 37
```
- 각 인스턴스의 compareTo 메소드를 호출해서 0이 반환되면 찾은 것