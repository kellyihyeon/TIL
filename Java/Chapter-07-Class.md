# **클래스와 인스턴스**

## 목차
1. [클래스의 정의와 인스턴스의 생성](#1-클래스의-정의와-인스턴스의-생성)  
   1.1 [프로그램의 기본 구성](#11-프로그램의-기본-구성)  
   1.2 [클래스](#12-클래스)  
   1.3 [인스턴스](#13-인스턴스)  
   1.4 [참조변수](#14-참조변수)

2. [생성자(Constructor)와 String 클래스의 소개](#2-생성자constructor와-string-클래스의-소개)  
   2.1 [String 클래스에 대한 첫 소개](#21-string-클래스에-대한-첫-소개)  
   2.2 [클래스 정의 모델: 인스턴스 구분에 필요한 정보를 갖게 하자.](#22-클래스-정의-모델-인스턴스-구분에-필요한-정보를-갖게-하자)   
   2.3 [좋은 클래스 정의 후보를 위한 초기화 메소드](#23-좋은-클래스-정의-후보를-위한-초기화-메소드)  
   2.4 [초기화 메소드를 대신하는 생성자](#24-초기화-메소드를-대신하는-생성자)  
   2.5 [디폴트 생성자](#25-디폴트-생성자)  
   2.6 [좋은 클래스가 되기 위한 원칙](#26-좋은-클래스가-되기-위한-원칙)  

3. [자바의 이름 규칙(Naming Rule)](#3-자바의-이름-규칙naming-rule)  
   3.1 [클래스 이름 규칙](#31-클래스-이름-규칙)    
   3.2 [메소드와 변수의 이름 규칙](#32-메소드와-변수의-이름-규칙)  
   3.3 [상수의 이름 규칙](#33-상수의-이름-규칙)
<br>


# 1. 클래스의 정의와 인스턴스의 생성
*클래스, 인스턴스, 참조변수  
이 세 가지는 같이 공부해야 한다.*

## 1.1 프로그램의 기본 구성
```
데이터: 프로그램상에서 유지하고 관리해야 할 데이터

기능: 데이터를 처리하고 조작하는 기능
```

```java
class BankAccountPO {
    
    // 데이터
    static int balance = 0;    // 예금 잔액

    public static void main(String[] args) {
        deposit(10000);        // 입금 진행
        checkMyBalance();      // 잔액 확인
 
        withdraw(3000);        // 출금 진행
        checkMyBalance();      // 잔액 확인
    }

    // 기능
    public static int deposit(int amount) {     // 입금
        balance += amount;
        return balance;
    }

    public static int withdraw(int amount) {   // 출금
        balance -= amount;
        return balance;
    }

    public static int checkMyBalance() {     // 예금 조회
        System.out.println("잔액 = " + balance);
        return balance;
    }
}
```

- 주변에 있는 모든 것들을 쪼개다보면 그 구성이 분자로 이루어져있듯이 세상에는 게임, 운영체제 등 많은 프로그램이 있다.  
프로그램을 쪼개고 쪼개서 작은단위까지 내려가보면 모든 프로그램들은  **`데이터`** 와 **`기능`** 으로 구분이 된다.

- 데이터와 기능이 모여서 프로그램이 이루어진다.  
  - 프로그램은 A라는 데이터와 A라는 데이터를 처리하기 위한 기능(메소드)으로 하나의 묶음이 이루어져있다.

  - C라는 데이터, C라는 기능, Z라는 데이터, Z라는 기능...이렇게 한 프로그램을 쪼개보면 데이터와 기능으로 구분이 된다.

- C라는 기능이 A라는 데이터를 컨트롤한다?  
 -> 물론 이를 악물고 만들면 만들 수도 있지만 가급적이면 **`구분`** 이 되도록 소프트웨어를 작성하는 것이 명료하고 좋은 프로그램이다.
<br>
<br>

## 1.2 클래스
**`데이터와 기능을 묶어버릴 수 있는 도구`** 가 있으면 조금 더 편할텐데, 그래서 등장한 것이 **`클래스`** 이다.
<br>

### 1.2.1 데이터와 기능
```
인스턴스 변수: 클래스 내에 선언된 변수

인스턴스 메소드: 클래스 내에 정의된 메소드
```

```java
class BankAccount {
// 인스턴스 변수
int balance = 0;

// 인스턴스 메소드
public int deposit(int amount) {...}
public int withdraw(int amout) {...}
public int checkMyBalance(){...}

}
```
- 클래스 안에 있는 변수 balance는 `데이터`이다.

- deposit 메소드는 예금, withdraw 메소드는 출금, checkMyBalance 메소드는 예금 조회 `기능`을 한다.

- 예금, 출금, 예금 조회 기능은 balance 변수를 참조하고 컨트롤하기 위한 메소드들이다.

- 데이터와 기능을 하나의 클래스로 묶었다.
클래스라는 건 연관된 데이터와 연관된 기능을 묶어 놓는 도구이다.
<br>
<br>


## 1.3 인스턴스

- BankAccount로 나의 계좌를 관리한다고 한다면 다른 사람들 계좌 관리는 어떻게 하는 걸까? 변수 선언을 하나 더 해야 하는 걸까?  
클래스가 하나만 필요한 게 아니라 여러 개가 필요하고 별도로 만들어야 각각의 데이터들을 유지하게 될 것이다. (= 복사본이 여러 개가 필요하다.)

- 계좌를 개설할 때마다 필요하므로 클래스를 틀로 만들어놓고 원할 때마다 복사본을 하나씩 떠 만들어야한다.
  
- 클래스라는 건 틀이고, 이 틀을 꾸며 놓는 것이다.   
그리고 이 틀을 기반으로 해서 복사본을 만들어낸다. 틀은 실존하는 게 아니고 이 틀을 기반으로 해서 사본(실제 메모리 공간에 올라갈 수 있는)들을 만들어 내야한다.

- 이걸 가리켜 인스턴스라고 한다. 또는 객체라고 한다. 오브젝트라고도 이야기하는 경우도 있다. 
(일관되게 인스턴스라고 서술)

- 하나의 클래스를 만든다는 것은 틀을 정의하는 것이고, 
틀은 사실 그 자체적으로 기능을 갖고 있진 않다.  
클래스에는 내가 필요한 데이터와, 메소드를 가져다 넣게 되는데 틀인 상황에서는 변수와 메소드를 가져다 쓰지 못한다.  
이를 가지고 실제 현존하는 무엇인가로 만들어 내야 하는데, 이것이 인스턴스이다. (메모리 공간에 올라가는 것)

- static 선언이 다 빠져있으면 그것은 진짜 클래스이다. static 선언이 있으면 진짜 클래스가 아닌데, 이는 추후에 알아보자.
<br>
  
### 1.3.1 인스턴스를 만들어 내는 방법
```
new BankAccount();  -> BankAccount 인스턴스1  
new BankAccount();  -> BankAccount 인스턴스2
```

- new: '인스턴스를 만들어라' 라는 명령어이다.
- 어떤 틀을 대상으로 만드는지는 오른쪽에 정보가 온다.  
BankAccount라는 틀을 이용해서 인스턴스를 만들어라 라는 의미이다.

- 이렇게 인스턴스를 만들면 메모리 공간에 존재하게 되고, 인스턴스는 내가 원하는만큼 계속 만들 수 있다.
<br>
<br>


## 1.4 참조변수
```
new BankAccount();  -> BankAccount 인스턴스1  
new BankAccount();  -> BankAccount 인스턴스2
```

- 인스턴스를 2개 만들었다고 가정하자.  
marcelline의 계좌와 bubblegum의 계좌를 만들면 메모리 공간에 올라가고 그 다음 marcelline이나 bubblegum의 계좌에 접근을 해야하는데, 어떻게 접근을 할 수 있을까?

- 인스턴스를 생성만 하면 되는 게 아니라, 메모리 공간에 올라가 있는 이 인스턴스를 막대기로 가리키든 뭘로 가리키든지 간에 이 인스턴스에 대한 정보를 가지고 있어야 한다.

- **`참조변수라는 것은 인스턴스가 생성딜 때 반환되는 주소값을 저장하기 위한 변수이다.`**
  
- 그 변수를 통해 우리는 우리가 호출하고자 하는 메소드, 접근하고자 하는 메소드를 결정할 수 있다. 이것이 클래스와 인스턴스와 참조변수의 관계이다.
<br>

### 1.4.1 참조변수 선언하는 방법
*참조변수를 선언하는 방법은 기본자료형을 선언하는 것과 비슷하다.*

```java
BankAccount marcelline; 
BankAccount bubblegum;

marcelline = new BankAccount();  // marcelline 계좌
bubblegum = new BankAccount();   //bubblegum 계좌

marcelline.deposit(1000);
bubblegum.deposit(1000);
```

- 인스턴스를 생성했고, 이 인스턴스가 메모리 공간 10 번지에 저장이 돼있다.   
그럼 이 10번지라는 값이 반환이 돼서 marcelline에 저장이 된다.

- 실질적으로 marcelline은 10번지(marcelline의 계좌), bubblegum은 20번지(bubblegum의 계좌)를 가리키는 형태가 되고, marcelline과 bubblegum, 이것이 바로 **`참조변수`** 이다.

- marcelline.deposit(1000);  
deposit 메소드를 호출하는데 marcelline이 가리키고 있는 인스턴스의 deposit 메소드를 호출한다.
<br>
<br>


### 1.4.2 클래스, 인스턴스 관련 예제

① 클래스
```java
class BankAccount {
    
    int balance = 0;    // 예금 잔액

    
    public int deposit(int amount) {     
        balance += amount;
        return balance;
    }

    public int withdraw(int amount) {  
        balance -= amount;
        return balance;
    }

    public int checkMyBalance() {  
        System.out.println("잔액 = " + balance);
        return balance;
    }
}
```

② 인스턴스
```java
class BankAccountOO {
  public static void main(String[] args) {
    // 두 개의 인스턴스 생성
    BankAccount marcelline = new BankAccount();
    BankAccount bubblegum = new BankAccount();

    // 각 인스턴스를 대상으로 예금을 진행
    marcelline.deposit(5000);
    bubblegum.deposit(3000);

    // 각 인스턴스를 대상으로 출금을 진행
    marcelline.withdraw(2000);
    bubblegum.withdraw(2000);

    // 각 인스턴스를 대상으로 잔액을 조회
    marcelline.checkMyBalance();
    bubblegum.checkMyBalance();
  }

}
```
<br>

### 1.4.3 참조변수의 특성

#### 1.4.3.1 변경
```java
BankAccount marcelline = new BankAccount();
...
marcelline = new BankAccount();
...
```

```
marcelline ─────X────> new BankAccount();
        │ 
        └────────────> new BankAccount();
```

- int num = 10;   
변수를 선언하고 10으로 초기화시켰을 때, num은 항상 10만 가지고 있어야하는 것은 아니다. 값을 num = 20으로 바꿀 수도 있다.

- 마찬가지로 참조변수도 참조하는 인스턴스를 바꿀 수 있다.  
새 인스턴스를 생성하고 이 값을 저장하는 형태로 바꾸었는데, 이 marcelline이라는 참조변수는 기존에 가리키고 있던 인스턴스에 대한 정보를 잃어버린다.   관계가 끊겨버리고 대신 새 인스턴스를 참조하게 된다.
<br>

#### 1.4.3.2 동시 참조
```java
BankAccount marcelline = new BankAccount();
BankAccount bubblegum = marcelline;   // 같은 인스턴스 참조
```

```
marcelline ─────────> new BankAccount();
                          ^ 
bubblegum ────────────────┘
```

- bubblegum 이라는 참조변수를 하나 더 선언하고 bubblegum에 marcelline을 저장시켰다.

- marcelline이 참조하고 있는 인스턴스를 bubblegum도 참조하게 되는 형태이다. 이처럼 하나의 인스턴스를 둘 이상의 인스턴스가 동시에 참조하는 것도 가능하다.

- marcelline.deposit(), bubblegum.deposit()은 같은 인스턴스를 호출 하게 된다.

### 1.4.4 참조변수 관련 예제
```java
class DupRef {
  public static void main(String[] args) {
    BankAccount marcelline = new BankAccount();
    BankAccount bubblegum = new BankAccount();

    marcelline.deposit(3000);
    bubblegum.deposit(2000);
  
    marcelline.withdraw(400);
    bubblegum.withdraw(300);

    marcelline.checkMyBalance();
    bubblegum.checkMyBalance();
  }
}
```

### 1.4.5 참조변수의 매개변수 선언
```java
class PassingRef {

  public static void main(String[] args) {

    BankAccount marcelline = new BankAccount();
    marcelline.deposit(3000);
    marcelline.withdraw(300);
    check(marcelline);   // '참조 값'의 전달
  }

  public static void check(BankAccount acc) {
    acc.checkMyBalance();
  }

}
```

```
marcelline ─────────> new BankAccount();
                          ^ 
acc ──────────────────────┘
```

- 매개변수 자리에는 int num 이라고 선언할 수도 있고 전달되는 값을 저장할 수도 있듯이 인스턴스의 주소값을 받겠다는 의미이다.
  
- check(marcelline);
 marcelline이 가지고 있는 주소값을 전달한다.   인스턴스를 전달하는 것이 아니라 10번지라는 주소값을 전달하는 것이다.

- marcelline과 acc는 같은 인스턴스를 가리키는 형태이다. (같이 참조하게 되는 형태)
<br>
<br>

### 1.4.6 참조변수에 null 대입

#### 1.4.6.1 null의 의미
- null 이라는 것은 키워드이다.(약속에 대한 키워드)  
 
- true, false도 자체가 데이터고, true는 참, false는 거짓이라는 뜻을 가지고 있듯이 null도 데이터의 일종이다.

- null = 청소부다. 
null 이라는 것은 `비워버렸다. 지워버렸다` 라는 것을 의미하는 데이터이다.
<br>

```java
BankAccoutn marcelline = new BankAccount();
...
marcelline = null; // marcelline이 참조하는 인스턴스와의 관계를 끊음
```

- BankAccoutn marcelline = new BankAccount();  
BankAccount를 참조하고 있는데, 여기에 null을 저장해버리면 이를 가리키고 있던 값을 지워버린다. 그냥 비워버린다.  

- null을 참조변수에 저장하는 순간, marcelline 참조변수는 누구도 참조하지 않는, 즉 아무런 데이터도 저장되어있지 않은 상태로 초기화가 되어버린다. 
<br>
<br>

#### 1.4.6.2 비교연산 가능
```java
BankAccount marcelline = null;
...
if (marcelline == null)    // marcelline이 참조하는 인스턴스가 없다면
```
- null 저장 유무에 대한 비교 연산이 가능하다.

- 참조하고 있는 인스턴스가 없는지를 확인하고 싶을 때 비교연산을 할 수 있다.

- 'marcelline이라는 참조변수가 아무것도 가리키는 대상이 아무것도 없다면, 저장되어있는 데이터가 없다면' 이라고 해석하면 된다.
<br>
<br>


# 2. 생성자(Constructor)와 String 클래스의 소개
```
"Hello"
```
- 자바에서는 이것을 문자열로 인식 한다.
  그리고 자바는 문자열도 인스턴스로 표현하고 저장할 때도 인스턴스로 저장한다.

- 자바가상머신을 실행하면 과장을 좀 보태서, 이 문자열을 보는 순간 이 String 클래스의 인스턴스를 생성한다.
  - String 클래스: 자바에서 제공, 정의하는 클래스

- "Hello"라는 문자열 데이터를, 생성한 인스턴스에 쏙 넣어주고 이 인스턴스가 있는 주소를 반환해준다. 
  - 이 인스턴스에는 메소드도 몇 개가 들어가는데, 어떤 메소드가 있는지는 추후에 설명하겠다. 

- System.out.println("Hello");  
  실제로 println에 전달되는 것은 문자열이 아니고 String 인스턴스의 주소 값이 이 자리를 대체한다.  
  사실은 println 이라는 메소드에 String 인스턴스의 참조값이 전달이 됐던 것이다.
<br>
<br>

## 2.1 String 클래스에 대한 첫 소개
```java
public static void main(String[] args) {
  String str1 = "Happy";
  String str2 = "Birthday";
  System.out.println(str1 + " " + str2);

  printString(str1);
  printString(str2);
}

public static void printString(string str) {
  System.out.print(str);
}
```
- String str1 = "Happy";  
스트링 인스턴스를 생성하고 그 참조값을 str1이 가리키고 있다. (str1에 저장)

- 스트링형 str1 참조변수를 가지고 문자열을 참조할 수 있다.

- printString(str1);  
문자열을 메소드의 인자로 전달할 수 있다.

- public static void printString(string str)  
매개변수로 String형 참조변수를 선언하여 문자열을 인자로 전달받을 수 있다.


## 2.2 클래스 정의 모델: 인스턴스 구분에 필요한 정보를 갖게 하자.

```java
class BankAccount {
  int balance = 0; // 예금 잔액

  public int deposit(int amount) {...}
  public int withdraw(int amount) {...}
  public int checkMyBalance() {...}
}
```
문제있는 클래스 정의
<br>

```java
class BankAccount {
  String accNumber;  // 계좌번호
  String ssNumber;   // 주민번호
  int balance = 0;   // 예금 잔액

  public int deposit(int amount) {...}
  public int withdraw(int amount) {...}
  public int checkMyBalance() {...}
}
```
좋은 클래스 정의 후보

- 인스턴스를 구분할 수 있는 정보를 클래스에 정의할 필요가 있다. 각각의 인스턴스를 구분할 수 있는 정보를 갖게끔 해라.

- marcelline 인스턴스  
  bubblegum 인스턴스  
  jake 인스턴스  
  계좌번호, 주민번호를 위한 참조변수는 각각의 인스턴스를 구분하게 하는 고유정보이다.

- 만약 고유정보가 클래스 안에 없고 어쩌다보니 marcelline -> bubblegum -> jake 를 참조하고 있을 때, 실수가 일어났는지 확인하려면 클래스 안의 고유정보를 볼 수 있어야 한다.
<br>

## 2.3 좋은 클래스 정의 후보를 위한 초기화 메소드

### 2.3.1 초기화를 위한 메소드
```java
class BankAccount {
  String accNumber;  // 계좌번호
  String ssNumber;   // 주민번호
  int balance = 0;   // 예금 잔액

  public void initAccount(String acc, String ss, int bal) {
    accNumber = acc;
    ssNumber = ss;
    balance = bal;  // 계좌 개설 시 예금액으로 초기화
  }
  ...
}
```
- BankAccount 계좌를 생성하고 계좌번호, 주민번호, 예금잔액을 저장할 있는 변수만 딱 넣어둔다고 끝이 아니다. 그 정보를 marcelline 것으로 만들어서 저장을 해줘야 한다.

- 인스턴스 생성 후에 초기화를 해줘서 적당한 값으로 저장을 해줘야한다.  
(초기화 과정을 거쳐야 한다.)

- public void initAccount(String acc, String ss, int bal)  
초기화를 위한 메소드, 딱 한번만 호출하는 메소드.   
여러 번 호출하는 과정에서 계좌번호, 주민번호가 변경될 수 있으므로 두 번 호출하면 큰일 나는 메소드이다.

- 자바에서는 `초기화를 위한 메소드`를 직접 만들어서 호출하는 것을 명세화 시켰다.  
반드시 진행해야 하며, 인스턴스 변수*, 즉  멤버변수들을 초기화 하는 방법을 생성자라는 걸 통해서 하도록 자바의 문법으로 정해놨다.
  - '클래스를 이루는 멤버다'라고해서 멤버변수라고도 부른다.
  
- 생성자는 우리가 명시적으로 호출하지 않는다.
우리가 호출하지 않아도 자동으로 호출이 되면서 초기화가 이루어진다.   
= 프로그래머의 편의를 위한 것이다.
<br>

### 2.3.2 초기화
```java
public static void main(String[] args) {
  BankAccount marcelline = new BankAccount(); // 계좌 생성
  marcelline.initAccount("12-34-89", "990824-9090909", 10000);  // 초기화
  ...
}
```
- 우리가 초기화를 위한 메소드를 정의할 경우
인스턴스 생성문장, 초기화 문장을 각각 구분해야 하는데, 생성자를 사용하면 구분하지 않아도 된다.

- 하나의 문장으로 인스턴스 생성을 명령하면,  자동으로 생성자라는 것이 호출되면서 우리가 원하는 값으로 초기화가 이뤄진다.

## 2.4 초기화 메소드를 대신하는 생성자
```java
class BankAccount {
  String accNumber;  // 계좌번호
  String ssNumber;   // 주민번호
  int balance = 0;   // 예금 잔액

  // 생성자
  public BankAccount(String acc, String ss, int bal) {
    accNumber = acc;
    ssNumber = ss;
    balance = bal;  
  }
  ...
}
```
- 메소드와 생긴 형태는 같은데, 생성자가 자동으로 호출되기 위한 조건이 있다.  
① 생성자의 이름은 클래스의 이름과 동일해야 한다.  
② 생성자는 값을 반환하지 않고 반환형도 표시하지 않는다.

- 이 두 가지 조건만 만족하면 생성자이다. 인스턴스 생성할 때 자동으로 호출된다.
<br>

```java
public static void main(String[] args) {
  BankAccount marcelline = new BankAccount("12-34-89", "990824-9090909", 10000); 
  ...
}
```
- 하지만 혼자 생성되고 혼자 호출 되면 의미가 없으니 내가 원하는 계좌번호, 주민번호, 예금 잔액으로 초기화 시켜줘야 한다.(세 개의 매개변수 선언)

- ①계좌번호 초기화, ②주민번호 초기화, ③예금잔액 초기화의 의도로 코드를 작성했다.

- 이 값을 전달하는 방법은 인스턴스를 생성하면서 전달해주는 것이다.
<br>
<br>

## 2.5 디폴트 생성자
```java
class BankAccount {
  String accNumber;  // 계좌번호
  String ssNumber;   // 주민번호
  int balance = 0;   // 예금 잔액

  public BankAccount() {  // 컴파일러에 의해 자동 삽입되는 '디폴트 생성자'
    // empty
  }

  public int deposit(int amount) {...}
  public int withdraw(int amount) {...}
  public int checkMyBalance() {...}
}
```
- 생성자를 만들지 않으면 자바 컴파일러가 생성자를 넣어준다.

- new bankAccount();  
우리는 이렇게 인스턴스를 생성했는데, 이것은 **`인자를 전달받지 않는 생성자를 호출하라`** 라는 의미이다.

- class 안에 public BankAccount(){} 이 코드가 없다면 생성자가 없는 것이다.  
그렇다면 new BankAccount(); 이 호출은 불가능하다.

- 하지만 자바에서 아예 문법적으로 규칙을 정해놓았다.  
인스턴스 생성 과정에서는 무조건 생성자가 호출이 되어야 한다.

- 우리가 생성자를 넣지 않으면 `디폴트 생성자`가 컴파일 과정에서 삽입이 된다.  

- 디폴트 생성자가 하는 일은 없다.  
비어있으며 인자값도 없다. 무늬만 생성자이다.  
이런 생성자이지만 이렇게 정의해서 넣어줌으로인해 인스턴스의 생성문법의 기본 규칙을 유지하게끔 만들어준 것이다.
  - new BankAccount **`();`**
<br>
<br>


## 2.6 좋은 클래스가 되기 위한 원칙
- 가급적 모든 클래스는 기본적으로 생성자를 직접 정의하는 것이 의미있다.

- 데이터가 없이 메소드만 있는 클래스가 있을까?  
데이터만 있고 메소드가 없는 클래스가 있을까?

- 반드시 클래스 안에는 하나라도 데이터가 있고, 하나 이상의 메소드가 있다.  
데이터가 있다는 건 그 값을 적절히 초기화 시켜줄 의무가 있는 것이다.

- 원하는 값, 적절한 값, 필요한 값으로 초기화 시켜줄 필요가 있다.  
그래서 생성자를 직접 넣어주는 게 의미가 있다.

- 생성자를 초기화 시켜줄 필요가 없는 경우라도 명시적으로 생성자를 만들어서 `0`으로 초기화 하겠다는 문장을 넣어주는 것이 좋다.
  - 안적어도 0으로 초기화가 되지만 명시적으로 balance = 0; 작성.
<br>
<br>


# 3. 자바의 이름 규칙(Naming Rule)
관례이기는 하지만 전반적으로 지켜져야 하는 강제성을 띄는 규칙이다.  
(강제성은 없다.)
<br>

## 3.1 클래스 이름 규칙
- 클래스 이름의 첫 문자는 **`대문자`** 로 시작한다.
- 둘 이상의 단어가 묶여서 하나의 이름을 이룰 때, 새로 시작하는 단어는 대문자로 한다.
- Circle + Point = `CirclePoint `
- **`Camel Case`** 모델
<br>
<br>

## 3.2 메소드와 변수의 이름 규칙
- 메소드 및 변수 이름의 첫 문자는 **`소문자`** 로 시작한다.
- 둘 이상의 단어가 묶여서 하나의 이름을 이룰 때, 새로 시작하는 단어는 대문자로 한다.
- Add + Your + Money = `addYourMoney `   
  Your + Age = `yourAge`
- **`변형된 Camel Case`** 모델
<br>
<br>

## 3.3 상수의 이름 규칙
- 상수의 이름은 모든 문자를 **`대문자`** 로 구성한다.
- 둘 이상의 단어가 묶여서 하나의 이름을 이룰 때 단어 사이를 **`언더바`** 로 연결한다.
- final int `COLOR_RAINBOW` = 7;
- 전부 대문자이기 때문에 붙여서 적으면 의미 구분이 힘들어진다.
  - COLORRAINBOW