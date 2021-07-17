# 예외처리

## 목차
1. [자바 예외처리의 기본](#1-자바-예외처리의-기본)  
   1.1 [자바에서 말하는 예외](#11-자바에서-말하는-예외)  
   1.2 [예외 상황의 예](#12-예외-상황의-예)  
   1.3 [예외 상황의 또 다른 예](#13-예외-상황의-또-다른-예)   
   1.4 [예외 상황을 알리기 위한 클래스](#14-예외-상황을-알리기-위한-클래스)   
   1.5 [예외의 처리를 위한 try ~ catch](#15-예외의-처리를-위한-try--catch)   
   1.6 [try ~ catch의 예](#16-try--catch의-예)  
   1.7 [예외 발생 이후의 실행 흐름](#17-예외-발생-이후의-실행-흐름)  
   1.8 [try로 감싸야 할 영역의 결정](#18-try로-감싸야-할-영역의-결정)  
   1.9 [둘 이상의 예외 처리를 위한 구성](#19-둘-이상의-예외-처리를-위한-구성)   
   1.10 [Throwable 클래스](#110-throwable-클래스)  
   1.11 [예외의 전달](#111-예외의-전달)  
   1.12 [printStackTrace 메소드 관련 예제](#112-printstacktrace-메소드-관련-예제)  
   1.13 [ArrayIndexOutOfBoundsException](#113-arrayindexoutofboundsexception)   

2. [예외처리에 대한 나머지 설명들](#2-예외처리에-대한-나머지-설명들)  
   2.1 [예외 클래스의 구분](#21-예외-클래스의-구분)  
   2.2 [Error 클래스를 상속하는 예외 클래스들의 특성](#22-error-클래스를-상속하는-예외-클래스들의-특성)  
   2.3 [RuntimeException 클래스를 상속하는 예외 클래스들의 특성](#23-runtimeexception-클래스를-상속하는-예외-클래스들의-특성)  
   2.4 [Exception 클래스를 상속하는 예외 클래스들의 특성](#24-exception-클래스를-상속하는-예외-클래스들의-특성)  
   2.5 [Exception을 상속하는 예외의 예](#25-exception을-상속하는-예외의-예)  
   2.6 [처리하거나 아니면 넘기거나](#26-처리하거나-아니면-넘기거나)  
   2.7 [둘 이상의 예외 넘김에 대한 선언](#27-둘-이상의-예외-넘김에-대한-선언)  
   2.8 [프로그래머가 정의하는 예외 클래스](#28-프로그래머가-정의하는-예외-클래스)  
   2.9 [프로그래머 정의 예외 클래스의 예](#29-프로그래머-정의-예외-클래스의-예)  
   2.10 [잘못된 catch 구문의 구성](#210-잘못된-catch-구문의-구성)  

<br>

# 1. 자바 예외처리의 기본
- 여기서 예외는 일반적으로 프로그램 사용자의 실수를 의미한다.

- 사용자가 예상과는 다른 액션을 취했을 때의 상황을 **`예외`** 라고 하고, 그 상황을 프로그래머가 처리해 주는 것을 **`예외처리`** 라고한다.
<br>

## 1.1 자바에서 말하는 예외
- 예외(Exception)  
**`예외적인 상황`** 을 줄여서 **`예외`** 라 한다.
단순한 문법 오류가 아닌 실행 중간에 발생하는 **`정상적이지 않은 상황`** 을 뜻한다.

  - 프로그래머가 기대했던 것과 다르게 사용자가 만들어낸 상황

- 예외처리  
예외 상황에 대한 처리를 의미한다.
자바는 **`예외처리 메커니즘`** 을 제공한다.
<br>
<br>

## 1.2 예외 상황의 예
- 사용자로부터 2개의 정수를 입력받고 (n1, n2),  n1 / n2 연산을 한 상황이다.
  - n1 = 8, n2 = 0  

- 이를 강행하면 프로그램이 종료가 되며 연산식의 결과를 확인하지 못한다.

- 이것이 예외이다.   
예외처리를 직접 처리해 주지 않았기 때문에 자바 가상머신은 이 프로그램을 종료시켜버렸다.

```bash
Exception in thread "main" java.lang.ArithmeticException: / by zero 
...
```
- 예외 상황이 발생하였고, 자바의 예외처리 메커니즘이 동작하였다.
<br>
<br>

## 1.3 예외 상황의 또 다른 예
- 사용자로부터 2개의 정수를 입력받고 (n1, n2),  n1 / n2 연산을 한 상황이다.
  - n1 = A, n2 = 8

- 정수를 입력 하라고 했더니 문자가 입력이 됐다면, 이 역시 예외이다.

- 프로그래머는 첫번째 값, 두번째 값으로 정수가 입력이 될 거라고 예상을 하고 있지만, 사용자가 예외를 발생시켰다.

- JVM은 예외처리를 해주지 않으면 프로그램을 종료시킨다.
  
```bash
Exception in thread "main" java.util.InputMismatchException
...
```
- 자바의 기본 예외처리 메커니즘은 문제가 발생한 지점에 대한 정보 출력과 프로그램 종료이다.

- 예외에 대한 처리 방법은 프로그래머가 결정할 수 있다.

<br>
<br>


## 1.4 예외 상황을 알리기 위한 클래스
- 예외 상황이 빈번히 발생하기 때문에 내가 작성한 코드에서 예외처리를 해야 하는 경우가 많이 생긴다.

- '만약에~ ' 이런 예외처리를 하는 모듈을 만드는 것은 귀찮은 일이다.   
그리고 일반적인 코드 흐름에 예외 코드를 덕지덕지 붙여 넣는 것도 번거롭고 보기에도 깔끔하지 않다.

- 일반적인 코드 루틴 중 예외처리 코드가 중간 중간 들어가 있으면, 전체 코드 흐름을 보는 데 있어서 예외처리 코드가 방해가 된다.

- 코드를 보는 순간 예외처리 코드라는 것이 한 눈에 드러나게끔 예외처리 코드를 구분할 수 있도록 자바에서는 try ~ catch를 제공해준다.
<br>

```java
java.lang.ArithmeticException

java.util.InputMismatchException
```
- java.lang.ArithmeticException  
수학 연산에서의 오류 상황을 의미하는 **`예외 클래스`**

- java.util.InputMismatchException  
클래스 Scanner를 통한 값의 입력에서의 오류 상황을 의미하는 **`예외 클래스`**

- 자바 가상머신이 특정 예외 상황을 알리기 위해서 정의한 클래스이다.
<br>
<br>


## 1.5 예외의 처리를 위한 try ~ catch
```java
try {
    ... 관찰 영역 ...
} 
catch (ArithmeticException e) {
    ... 처리 영역 ...
}
```
- 예외의 처리를 위한 코드를 별도로 구분하기 위해 디자인된 예외처리 메커니즘이 try ~ catch 이다.

- try와 catch는 하나의 문장이다.  
try 영역에서 발생하는 예외를, 이어서 등장하는 catch에서 처리하겠다는 의미이다.
catch 블럭을 만나면 예외 상황을 처리하기 위한   코드라는 것을 알 수 있다.
<br>
<br>

## 1.6 try ~ catch의 예
```java
public static void main(String[] args) {
    Scanner kb = new Scanner(System.in);

    try {
        System.out.print("a/b...a? ");
        int n1 = kb.nextInt();
        System.out.print("a/b...b? ");
        int n2 = kb.nextInt();
        System.out.print("%d / %d = %d \n", n1, n2, n1 / n2);   // 예외 발생 지점
    }
    catch(ArithmeticException e) {
        System.out.print(e.getMessage());
    }

    System.out.print("Good bye~~!");
}
```
<br>

```bash
a/b...a? 2
a/b...b? 0
/ by zero
Good bye~~!
```
- 정수값으로 0을 받은 것 자체는 예외가 발생되지 않지만, n1 / n2를 할 때 정수를 0으로 나누는 순간 예외가 발생한다.  
연산과 관련한 오류가 발생하고, 자바가상머신은  Arithmetic 클래스의 인스턴스를 생성한다. 

- 자바가상머신은 예외 발생 지점에서 발생된 영역이 try에 감싸져있는지 확인하고, catch가 올 것을 기대한다.   
(catch가 없다면 컴파일 오류가 나기 때문이다.)  
그러고나서 catch 영역에 생성한 인스턴스의 참조값을 던져준다.  
e라는 참조변수는 자바 가상머신이 만든 Arithmetic 인스턴스를 참조하게 된다.   

- 참조값을 던져주고 나면 자바가상머신의 임무는 끝이난다.   
예외를 어떻게 처리할지는 프로그래머의 몫이다.

- 만일, ArithmeticException 뿐만 아니라 InputMismatchException도 처리하고 싶다면 catch문을 이어서 또 써주면 된다.
<br>
<br>


## 1.7 예외 발생 이후의 실행 흐름
```java
try {
    1. ...
    2. 예외 발생 지점
    3. ...
}
catch(ArithmeticException e) {
    ...
}

4. 예외 처리 이후 실행 지점
```
- 2번에서 예외가 발생되었다면 try 블럭의 나머지 영역은 생략한다. try ~ catch 블럭을 다 건너뛴다.  

- 이렇게 디자인한 이유는 try 영역 안에 특정 위치에서 예외가 발생했을 때 뛰어넘을 것들을 묶어서 하나의 영역처럼 작업할 수 있기 때문이다.
<br>
<br>


## 1.8 try로 감싸야 할 영역의 결정
```java
try {
    System.out.print("a/b...a? ");
    int n1 = kb.nextInt();
    System.out.print("a/b...b? ");
    int n2 = kb.nextInt();
    System.out.print("%d / %d = %d \n", n1, n2, n1 / n2);   // 예외 발생 지점
}

```
- n1을 입력 받는 부분이나, n2를 입력 받는 부분에서 예외가 발생이 되면 나머지 작업을 진행하는 것이 의미가 없다.

- 일의 단위 즉, 예외가 발생한다면 건너 뛸 필요가 있는 코드까지 하나의 작업으로 보고 이들을 묶는 용도로 사용되는 것이 try이다.
<br>
<br>


## 1.9 둘 이상의 예외 처리를 위한 구성
```java
public static void main(String[] args) {
    Scanner kb = new Scanner(System.in);

    try {
        System.out.print("a/b...a? ");
        int n1 = kb.nextInt();
        System.out.print("a/b...b? ");
        int n2 = kb.nextInt();
        System.out.print("%d / %d = %d \n", n1, n2, n1 / n2);   // 예외 발생 지점
    }
    catch(ArithmeticException e) {
        e.getMessage();
    }
    catch(InputMismatchException e) {
        e.getMessage();
    }

    System.out.print("Good bye~~!");
}
```
- 위에 있는 catch부터 시작해서 아래로 내려가면서 예외를 처리한다. (순서대로)
  
```java
catch(ArithmeticException | InputMismatchException e) {
        e.getMessage();
    }
```
- 추가된 문법
<br>
<br>


## 1.10 Throwable 클래스

- java.lang.Throwable 클래스  
모든 예외 클래스의 최상위 클래스이다. 물론 Throwable도 Object를 상속한다.

- Throwable 클래스의 메소드  
  - public String getMessage()  
  예외의 원인을 담고 있는 문자열을 반환
  - public void printStackTrace()  
  예외가 발생한 위치와 호출된 메소드의 정보를 출력
<br>
<br>


## 1.11 예외의 전달
```java
class ExceptionMessage {
    public static void md1(int n) {
        md2(n, 0);    // 아래의 메소드 호출
    }

    public static void md2(int n1, int n2) {
        int r = n1 / n2;  // 예외 발생 지점
    }

    public static void main(String[] args) {
        md1(3);
        System.out.println("Good bye~~!");
    }
}
```
- 예외 발생 지점에서 예외를 처리하지 않으면 해당 메소드를 호출한 영역으로 예외가 전달된다.
<br>

### 1.11.1 예외 전달 과정
- int r = n1 / n2;   
여기에서 예외가 발생했고, 자바 가상머신은 try를 찾는다. try가 없으니 md2 메소드를 호출한 영역으로 예외를 넘긴다.

- md2(n, 0);   
여기로 가서 다시 try를 찾는다.  
역시 여기서도 try가 없으니 자바 가상머신은 이 책임을 md2(n, 0);를 감싸고 있는 md1 메소드를 호출한 영역으로 다시 예외를 넘긴다.  

- md1(3);   
역시 try가 없으니 main 메소드를 호출한 쪽으로 예외를 넘긴다.  
main 메소드 호출은 자바 가상머신이 한다.  

- 예외가 자바 가상머신으로까지 넘어왔다.   
자바 가상머신은 예외처리 메커니즘을 동작시킨다.   
예외의 printStackTrace 메소드를 호출시키고, 호출 후 자바 가상머신은 프로그램을 종료시킨다.

- 메소드 호출 내용은 아래와 같다.
    ```bash
    Exception in thread "main" java.lang.ArithmeticException: / by zero
        at ExceptionMessage.md2(ExceptionMessage.java:6)
        at ExceptionMessage.md1(ExceptionMessage.java:3)
        at ExceptionMessage.main(ExceptionMessage.java:9)
    ```
<br>
<br>


## 1.12 printStackTrace 메소드 관련 예제
```java
class ExceptionMessage2 {
    public static void md1(int n) {
        md2(n, 0);    // 이 지점으로 md2로부터 예외가 넘어온다.
    }

    public static void md2(int n1, int n2) {
        int r = n1 / n2;  // 예외 발생 지점
    }

    public static void main(String[] args) {
        try {
            md1(3);     // 이 지점에서 md1으로부터 예외가 넘어온다.
        } 
        catch(Throwable e) {
            e.printStackTrace();
        }
        
        System.out.println("Good bye~~!");
    }
}
```
- try ~ catch 문을 처리하고 실행의 흐름은 다시 System.out.println("Good bye~~!"); 부터 시작된다.

- 메소드 호출 내용은 아래와 같다.
    ```bash
    java.lang.ArithmeticException: / by zero
        at ExceptionMessage2.md2(ExceptionMessage2.java:6)
        at ExceptionMessage2.md1(ExceptionMessage2.java:3)
        at ExceptionMessage2.main(ExceptionMessage2.java:10)
    Good bye~~!
    ```
<br>
<br>

## 1.13 ArrayIndexOutOfBoundsException
```java
class ArrayIndexOutOfBounds {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};

        for(int i = 0; i < 4; i++) {
            System.out.println(arr[i]);
        }
    }
}
```
- System.out.println(arr[i]);  
인덱스 값 3은 예외를 발생시킨다.   
0, 1, 2까지가 전부인데 범위를 넘어섰기 때문에 예외를 발생시킨다.
<br>
<br>


# 2. 예외처리에 대한 나머지 설명들
## 2.1 예외 클래스의 구분
- **`Error 클래스`** 를 상속하는 예외 클래스

- **`Exception 클래스`** 를 상속하는 예외 클래스

- **`RuntimeException 클래스`** 를 상속하는 예외 클래스  
RuntimeException 클래스는 Exception 클래스를 상속한다.

- 상속하는 클래스가 무엇인가에 따라 예외 클래스의 성격이 달라진다.
<br>
<br>


## 2.2 Error 클래스를 상속하는 예외 클래스들의 특성
- Error는 엄밀히 따지면 예외가 아니다.  
Exception이 예외이다. 

- Error 클래스를 상속하는 예외 클래스의 예외 상황은 시스템 오류 수준의 예외 상황으로 프로그램 내에서 처리할 수 있는 수준의 예외가 아니다.

- 예시
    ```text  
    VirtualMachineError
    IOError
    ```
<br>
<br>


## 2.3 RuntimeException 클래스를 상속하는 예외 클래스들의 특성
- Exception을 상속하는 예외 클래스의 성격도 가지고 있지만 Errir를 상속하는 예외 클래스의 성격도 없지않아 있다.
  - Error와 Exception의 중간쯤에 있다고 생각하자.

- 코드 오류로 발생하는 경우가 대부분이다. 따라서 이 유형의 예외 발생 시 코드의 수정을 고려해야 한다.
  - 코드 뜯어 고쳐야 하는 에러에 가깝다.

- 예시
    ```text
    ArithmeticException
    ClassCastException
    IndexOutOfBoundsException
    NegativeArraySizeException
    NullPointerException
    ArraysStoreException
    ```
<br>
<br>


## 2.4 Exception 클래스를 상속하는 예외 클래스들의 특성
- 진정한 예외라고 할 수 있다. 자바 가상머신도 프로그래머가 예외처리 해줘야한다고 종용하고 있다.

- 코드의 문법적 오류가 아닌, 프로그램 실행 과정에서 발생하는 예외적 상황을 표현하기 위한 클래스들이다.  
따라서 예외의 처리를 어떻게 할 것인지 반드시 명시해 주어야 한다.

- 예시
  ```text
  java.io.IOException
  입출력 관련 예외 상황을 표현하는 예외 클래스
  ```
<br>
<br>


## 2.5 Exception을 상속하는 예외의 예
```java
public static void main(String[] args) {
    Path file = Paths.get("C:\\javastudy\\Simple.txt");
    BufferedWriter writer = null;

    try {
        writer = Files.newBufferedWriter(file);    // IOException 발생 가능
        writer.write('A');  // IOException 발생 가능
        writer.write('Z');  // IOException 발생 가능

        if(writer != null) {
            writer.close();     // IOException 발생 가능
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }  
}
```
- Exception을 상속하는 예외는 반드시 처리를 해야 한다. 그렇지 않으면 컴파일 오류로 이어진다.
<br>
<br>


## 2.6 처리하거나 아니면 넘기거나
```java
public static void main(String[] args) {
    try {
        md1();
    }  
    catch (IOException e) {
        e.printStackTrace();
    }
}

public static void md1() throws IOException {   // IOException 예외 넘긴다고 명시
    md2();
}

public static void md2() throws IOException {   // IOException 예외 넘긴다고 명시
    Path file = Paths.get("C:\\javastudy\\Simple.txt");
    BufferedWriter writer = null;

    writer = Files.newBufferedWriter(file);     // IOException 발생 가능
        writer.write('A');  // IOException 발생 가능
        writer.write('Z');  // IOException 발생 가능

        if(writer != null) {
            writer.close();     // IOException 발생 가능
        }
}
```
- 메소드의 throws절 선언을 통해 예외의 처리를 넘길 수 있다.

- public static void md2() throws IOException {...}  
md2 메소드를 호출한 영역으로 예외를 넘겨버리겠다.

- RuntimeException을 상속하는 예외 클래스의 경우에는 예외가 발생하면 자동으로 호출한 영역으로 넘어가는데, Exception을 상속하는 예외 클래스는 넘기든 직접 처리하든 이를 명시해야한다. 

- 명시해주지 않으면 컴파일 에러로 이어진다.
<br>
<br>


## 2.7 둘 이상의 예외 넘김에 대한 선언
```java
public void simpelWrite() throws IOException, IndexOutofBoundsException {
    ...
}
```
- throws절에 둘 이상의 예외를 넘길 수 있다.
<br>
<br>


## 2.8 프로그래머가 정의하는 예외 클래스
```java
class ReadAgeException extends Exception {  // Exception을 상속한다.
    public ReadAgeException() {
        super("유효하지 않은 나이가 입력되었습니다.");
    }
}
```
- 상황에 따라 논리적으로 예외가 발생하는 경우가 생긴다.
  - int age = 0;   
  한국에서 0살은 예외상황이지만, 미국에서는 예외상황이 아니다.

- Throwable 클래스의 getMessage 메소드가 반환할 문자열 지정
<br>
<br>


## 2.9 프로그래머 정의 예외 클래스의 예
```java
class MyExceptionClass {
    public static void main(String[] args) {
        System.out.print("나이 입력: ");

        try {
            int age = readAge();
            System.out.printf("입력된 나이: %d \n", age);
        }
        catch(ReadAgeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int readAge() throws ReadAgeException {
        Scanner kb = new Scanner(System.in);
        int age = kb.nextInt();

        if(age < 0) {
            throw new ReadAgeException();   // 예외의 발생
        }
        return age;
    }
}
```
<br>

```bash
나이 입력: 12
입력된 나이: 12

나이 입력 -7
유효하지 않은 나이가 입력되었습니다.
```
<br>
<br>


## 2.10 잘못된 catch 구문의 구성
```java
class FirstException extends Exception {...}
class SecondException extends FirstException {...}
class ThirdException extends SecondException {...}
```
- Exception 클래스를 직접 상속해야만 예외 클래스가 되는 것은 아니다. 간접 상속해도 된다.
<br>

```java
try {
    ...
}
catch (FirstException e) {...}
catch (SecondException e) {...}
catch (ThirdException e) {...}
```
- try 안에서 예외가 발생하면 catch 구문으로 가는데 위 코드는 상속관계에 의해서 FirstException이 예외를 다 받아줄 수 있기 때문에 SecondException과 ThirdException은 기회를 얻지 못한다.

- 다행히 컴파일러는 이 부분에 대해 컴파일 오류를 전달해 준다. 
  - 이렇게 하면 SecondException과 ThirdException은 실행되지 않는다고 알려준다.

- catch 문을 알맞게 수정하면 아래와 같다.
    ```java
    catch (ThirdException e) {...}
    catch (SecondException e) {...}
    catch (FirstException e) {...}
    ```
<br>
<br>


## 2.11 finally
