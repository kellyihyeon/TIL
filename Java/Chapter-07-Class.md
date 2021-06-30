# **클래스와 인스턴스**

## 목차
1. [클래스의 정의와 인스턴스의 생성](#1-클래스의-정의와-인스턴스의-생성)  
   1.1 [프로그램의 기본 구성](#11-프로그램의-기본-구성)  
   1.2 [클래스](#12-클래스)  
   1.3 [인스턴스](#13-인스턴스)  
   1.4 [참조변수](#14-참조변수)

2. [생성자(Constructor)와 String 클래스의 소개]()
3. [자바의 이름 규칙(Naming Rule)]()
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



# 3. 자바의 이름 규칙(Naming Rule)
