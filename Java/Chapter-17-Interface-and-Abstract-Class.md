# 인터페이스와 추상클래스

## 목차
1. [인터페이스의 기본과 그 의미](#1-인터페이스의-기본과-그-의미)  
   1.1 [인터페이스](#11-인터페이스)  
   1.2 [추상 메소드만 담고 있는 인터페이스](#12-추상-메소드만-담고-있는-인터페이스)  
   1.3 [인터페이스를 구현하는 클래스](#13-인터페이스를-구현하는-클래스)  
   1.4 [상속과 구현](#14-상속과-구현)  
   1.5 [Printer Driver 관련 예제](#15-printer-driver-관련-예제)  

2. [인터페이스의 문법 구성과 추상 클래스](#2-인터페이스의-문법-구성과-추상-클래스)  
   2.1 [인터페이스에 선언되는 메소드와 변수](#21-인터페이스에-선언되는-메소드와-변수)  
   2.2 [인터페이스 간 상속: 문제 상황의 제시](#22-인터페이스-간-상속-문제-상황의-제시)  
   2.3 [제시한 문제의 해결책: 인터페이스의 상속](#23-제시한-문제의-해결책-인터페이스의-상속)  

<br>

# 1. 인터페이스의 기본과 그 의미
## 1.1 인터페이스
- 통신 도구 또는 수단이다.  
- 제공되는 기능이 있으면 그 기능을 활용하는 방법이다.

- 커피 자판기를 생각해보자.  
커피 자판기에서 커피를 뽑아 먹기 위해서 커피 자판기 내부의 메커니즘을 알 필요가 없다.  
밀크 커피가 300원이라면 단순하게 밀크 커피를 뽑아 먹을 수 있는 동전을 넣고 버튼을 누른다.  
종이컵이 내려오고 밀크 커피가 컵에 다 차면 그 컵을 빼서 마시면 된다.  
커피 자판기를 사용하는 방법, 이것이 인터페이스이다. 
커피 자판기를 사용하기 위해서 굳이 커피 자판기 머신이 구동되는 원리에 관한 모든 것을 알 필요가 없다.

- 또 다른 예로 게임 머신을 생각해보자.   
조이 스틱을 상하좌우로 움직여서 사용하는 것, 버튼을 누르면 미사일이 나가는 것처럼 게임 머신의 인터페이스는 조이 스틱과 버튼이다.   
게임기를 사용하는 방법이 인터페이스다.

- 이제 인스턴스를 생각해보자.  
인스턴스는 애초에 클래스가 있었으니 인스턴스가 만들어진다.   
클래스를 짱짱하게 만들어서 어마어마한 기능을 넣어놓았다. 적당한 클래스 안에 상당히 많은 기능이 들어있는 상태이다.  
클래스를 정의한 주체(나)와 사용하는 주체(너)가 다른 경우가 많은데, 이 클래스는 어떻게 생겼고 이런 기능들이 있으니 사용자에게 알아서 쓰라고 하면 사용자는 복잡하고 어려운 사용법에 화를 낸다.

- 클래스를 만든 사람은 사용자가 사용하기 좋도록 사용자에게 알려줘야한다. 그래서 이 클래스의 인터페이스를 만든다.  
이처럼 복잡한 클래스의 사용법을 담고 있는 것을 인터페이스라 한다.  
<br>
<br>


## 1.2 추상 메소드만 담고 있는 인터페이스
```java
Interface Printable {
    public void print(String doc);  // 추상 메소드
}
```
- 클래스 정의와 비슷하다.
Interface라는 키워드를 써주는 것이 다른 점이다.   
인터페이스에 담기는 내용들은 클래스와 마찬가지로 { }중괄호 사이에 등장하게 된다.  

- 인터페이스에는 특징이 있다.   
클래스 안에 존재하던 메소드와 달리 몸체가 없다  . 메소드의 선언부만 나열한 걸 가리켜 추상 메소드라 한다.  
완벽한 메소드의 정의는 아니다. 이러한 메소드가 있고 어떻게 생겼는지를 보여주는 것이다.  

- 추상 메소드 ──> 조이스틱, 버튼과 같은 역할을 하는 사용 방법에 해당된다.

- 인터페이스는 클래스가 아니므로 인터페이스를 대상으로 인스턴스 생성은 불가능하다.  
인터페이스는 클래스의 정의에 의해서 구현이 될 뿐이다.

- 인터페이스를 **`구현`** 한다는 것은 추상 메소드를 완성하겠다는 의미이다. 
  - 상속 X, 구현 O


- 인터페이스의 정의  
메소드의 몸체를 갖지 않는다.  
따라서 인스턴스 생성이 불가능 하지만 참조변수 선언은 가능하다.  
<br>
<br>


## 1.3 인터페이스를 구현하는 클래스
```java
class Printer implements Printable {
    public void print(String doc) {
        System.out.println(doc);
    }
}
```
- 구현하는 메소드와 추상 메소드 사이에도 메소드 오버라이딩 관계가 성립한다.   
따라서 @Override 어노테이션을 붙일 수 있다.  
(상속에서 Override 호출되는 것처럼 이해하면 쉽다.)
<br>

```java
Printable prn = new Printer();
prn.print("Hello");
```
- 인터페이스형 참조변수 선언은 가능하다.   
prn 이라는 Printable형 참조변수는 Printable을 구현한 클래스의 인스턴스를 참조할 수 있다.

- Printer 인스턴스를 prn으로 참조한다.   
이 인스턴스에 아무리 많은 메소드가 public으로 선언이 되어있다 할지라도 prn을 통해서 접근할 수 있는 메소드는 인터페이스 내에 정의된 추상메소드만 호출할 수 있다.  
즉 print 메소드 하나만 호출할 수 있다.  
<br>
<br>


## 1.4 상속과 구현
```java
class Robot extends Machine implements Movable, Runnable {...}
```
- Robot 클래스는 Machine 클래스를 상속한다.    
이렇듯 상속과 구현이 동시에 가능하다.

- Robot 클래스는 Movable과 Runnable 인터페이스를 구현한다.
이렇듯 둘 이상의 인터페이스 구현이 가능하다.

- 상속과 구현은 별개이다.
상속과 별개로, 더불어 하나 이상의 인터페이스를 구현 할 수도 있다.  

- Robot 인스턴스를 생성했을 때, 이 Robot 인스턴스를 참조할 수 있는 참조형은 Robot형, Machine형, Movable형, Runnable형 즉 4개이다.
<br>
<br>


## 1.5 Printer Driver 관련 예제
```java
interface Printable {   // MS 가 정의하고 제공한 인터페이스
    public void print(String doc);
}
```
<br>

```java
class SPrinterDriver implements Printable {

    @Override
    public void print(String doc) {
        System.out.println("From Samsung printer");
        System.out.println(doc);
    }
}
```
<br>

```java
class LPrinterDriver implements Printable {

    @Override
    public void print(String doc) {
        System.out.println("From LG Printer");
        System.out.println(doc);
    }
}
```
<br>

```java
public static void main(String[] args) {
    String myDoc = "This is a report about ...";

    // 삼성 프린터로 출력
    Printable prn = new SPrinterDriver();
    prn.print(myDoc);
    System.out.println();

    // LG 프린터로 출력
    prn = new LPrinterDriver();
    prn.print(myDoc);
}
```
- 세상에는 너무도 많은 프린터기와 그 프린터기를 제공하는 제조사가 있다.

- 프린터 드라이버의 사용방법을 제조사가 각자 만든다면 마이크로소프트 입장에서는 혼란스럽다.   
프린트 별로 사용 방법이 다 다르면 운영체제를 개발하는 입장에서는 어려울 수밖에 없다.

- 하지만 MS가 인터페이스를 정의하고 제공한다면, 제조사가 이를 기반으로 만들었을 때 제조사가 어디든 사용 방법이 같아진다.

- 삼성 프린터를 대상으로 출력을 하건 LG 프린터를 대상으로 출력을 하건 출력하는 방법에는 차이가 없다.   사용 방법이 같다는 것은 사용 방법을 명시한 인터페이스를 MS에서 제공 했다는 의미가 된다.  
<br>
<br>



# 2. 인터페이스의 문법 구성과 추상 클래스
## 2.1 인터페이스에 선언되는 메소드와 변수
```java
interface Printable {
    public void print(String doc);  //  추상 메소드
}
```
- 인터페이스는 외부에서 사용할 수 있는 사용 방법을 명시하는 것이기 때문에 기본적으로 인터페이스 안에 선언되는 추상 메소드는 public 선언을 하지 않더라도 public 선언이 된 것으로 간주된다.

- 디폴트 선언이 아니다!
<br>

```java
interface Printable {
    public static final int PAPER_WIDTH = 70;
    public static final int PAPER_HEIGHT = 120;
    public void print(String doc);
}
```
- static으로 선언된 상수는 Printable.PAPER_WIDTH 로 접근한다.

- static final 을 생략했다고 가정하자.  
public은 있으나 없으나 자동으로 public 선언이 된 것이고, static final이 없다면 int PAPER_WIDTH = 70;이 선언된 것인데, 클래스 관점에서보면 이것은 인스턴스 변수이다.   
하지만 인터페이스는 인스턴스 변수가 올 수가 없다.  
그래서 static final을 생략하더라도 자동으로 static final이 선언된 것으로 간주된다.
<br>
<br>


## 2.2 인터페이스 간 상속: 문제 상황의 제시
삼성과 LG에서 컬러 출력이 가능한 새로운 프린터기를 출시했다.   
기존 흑백 프린터기는 기존의 드라이버를 사용하면 되지만, 컬러 프린터기는 컬러까지 출력되는 드라이버를 사용해야 한다.

```java
interface Printable {
    void print(String doc); 
    void printCMYK(String doc);
}
```
- 컬러 출력을 위한 메소드가 추가된다면 시스템 전체에 문제가 발생한다. 
<br>

```java
class SPrinterDriver implements Printable {

    @Override
    public void print(String doc) {...}
}
```
- 이 클래스에서 printCMYK 메소드를 구현해야 한다.

- 메소드가 추가 됐으므로 새로운 메소드를 구현해야 하는데, 이 클래스는 printCMYK가 필요가 없다는 것이 문제이다.
<br>

```java
class LPrinterDriver implements Printable {

    @Override
    public void print(String doc) {...}
}
```
- 이 클래스에서도 printCMYK 메소드를 구현해야 한다.

- 인터페이스를 구현하는 클래스는 해당 인터페이스의 모든 추상 메소드를 구현해야 한다. 그래야 인스턴스 생성이 가능하다.
<br>
<br>


## 2.3 제시한 문제의 해결책: 인터페이스의 상속
```java
interface Printable {
    void print(String doc); 
}
```
- 이 인터페이스에 새로운 메소드를 만드는 것이 아니라 기존 것 그대로 둔다.
<br>

```java
interface ColorPrintable extends Printable {
    void printCMYK(String doc);
}
```
- 새로운 인터페이스를 만든다.  
그리고 Printable 인터페이스를 상속하게 만든다.

- 인터페이스 간 상속도 **`extends`** 로 표현한다.

- ColorPrintable 인터페이스에는 print 메소드와 printCMYK 메소드가 있게 된다.
<br>

```java
class SPrinterDriver implements Printable {

    @Override
    public void print(String doc) {...}
}
```
- 인터페이스가 바뀌지 않았으므로 기존 클래스를 수정할 필요가 없어진다.
<br>

```java
class Prn909Drv implements ColorPrintable {
    @Override
    public void print(String doc) {  // 흑백 출력 
        System.out.println("black & white ver");
        System.out.println(doc);
    }

    @Override
    public void printCMYK(String doc) {  // 컬러 출력 
        System.out.println("CMYK ver");
        System.out.println(doc);
    }
}
```
- ColorPrintable를 구현한 클래스를 만든다.
<br>
<br>
___

## 2.4 인터페이스의 디폴트 메소드: 문제 상황의 제시
- 총 256개의 인터페이스가 존재하는 상황에서 모든 인터페이스에 다음 추상 메소드를 추가해야 한다고 가정해보자.

```java
void printCMYK(String doc);
```
- 물론 인터페이스 간 상속으로 문제 해결이 가능하다.
다만, 인터페이스의 수가 256개 늘어날 뿐이다.

## 2.5 문제 상황의 해결책: 인터페이스의 디폴트 메소드
```java
default void printCMYK(String doc) { }  // 디폴트 메소드
```

## 2.6 디폴트 메소드의 효과
```java
interface Printable {
    void print(String doc); 
}
```

```java
interface Printable {
    void print(String doc); 
    default void printCMYK(String doc) { } 
}
```
- 인터페이스의 교체

```java
class SPrinterDriver implements Printable {

    @Override
    public void print(String doc) {...}
}
```
- 기존에 정의된 클래스
인터페이스 교체로 인해 코드 수정이 필요 없다.

```java
class Prn909Drv implements Printable {
    @Override
    public void print(String doc) {...}

    @Override
    public void printCMYK(String doc) {...}
}
```
- 새로 정의된 클래스


## 2.7 인터페이스의 static 메소드
```java
interface Printable {
    static void printLine(String str) {
        System.out.println(str);
    } 

    default void print(String doc) { 
        printLine(doc);     // 인터페이스의 static 메소드 호출
    } 
}
```
- 인터페이스에도 static 메소드를 정의할 수 있다.

- 그리고 인터페이스의 static 메소드 호출 방법은 클래스의 static 메소드 호출 방법과 같다.

## 2.8 인터페이스 대상의 instanceof 연산
```java
if (ca instanceof Cake) ...
```
- ca가 참조하는 인스턴스를 Cake 형 참조변수로 참조할 수 있으면 true 반환

- ca가 참조하는 인스턴스가 Cake를 직접 혹은 간접적으로 구현한 클래스의 인스턴스인 경우 true 반환

## 2.9 인터페이스 대상 instanceof 연산의 예
```java
interface Printable {
    void printLine(String str);
```

```java
class SimplePrinter implements Printable {
    public void printLine(String str) {
        System.out.println(str);
    }
}
```

```java
class MultiPrinter extends SimplePrinter {
    public void printLine(String str) {
        super.printLine("start of multi...");
        super.printLine(str);
        super.printLine("end of multi");
    }
}
```

```java
public static void main(String[] args) {
    Printable prn1 = new SimplePrinter();
    Printable prn2 = new MultiPrinter();

    if (prn1 instanceof Printable) {
        prn1.printLine("This is a simple printer.");
    }
    System.out.println();

    if (prn2 instanceof Printable) {
        prn2.printLine("This is a multiple printer.");
    }
}
```

```bash
This is a simple printer.

start of multi...
This is a multiple printer.
end of multi
```

## 2.10 인터페이스의 또 다른 용도: Marker 인터페이스
```java
interface Upper { }     // 마커 인터페이스
interface Lower { }     // 마커 인터페이스
```

```java
interface Printable {
    String getContents();
}
```

```java
class Report implements Printable, Upper {
    String cons;

    Report(String cons) {
        this.cons = cons;
    }

    public String getContents() {
        return cons;
    }
}
```

```java
public void printContents(Printable doc) {
    if (doc instanceof Upper) {
        System.out.println((doc.getContents()).toUpperCase());
    } else if (doc instanceof Lower) {
        System.out.println((doc.getContents()).toLowerCase());
    } else {
        System.out.println(doc.getContents());
    }
}
```
- 클래스에 특정 표시를 해 두기 위한 목적으로 정의된 인터페이스를 마커 인터페이스라 한다.
마커 인터페이스에는 구현해야 할 메소드가 없는 경우가 흔하다.

## 2.11 추상 클래스
```java
public abstract class Houser {  // 추상 클래스
    public void methodOne() {
        System.out.println("method one");
    }

    public abstract void methodTwo();   // 추상 메소드
}
```
- 하나 이상의 추상 메소드를 지니는 클래스를 가리켜 추상 클래스라 한다.
  
- 그리고 추상 클래스를 대상으로는 인스턴스 생성이 불가능하다. 물론 참조변수 선언은 가능하다.