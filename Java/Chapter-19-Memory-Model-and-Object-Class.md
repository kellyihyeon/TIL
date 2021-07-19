# 자바의 메모리 모델과 Object 클래스

## 목차
1. [자바 가상머신의 메모리 모델](#1-자바-가상머신의-메모리-모델)  
   1.1 [운영체제 입장에서 자바 가상머신](#11-운영체제-입장에서-자바-가상머신)  
   1.2 [자바 가상머신의 메모리 모델](#12-자바-가상머신의-메모리-모델)  
   1.3 [메소드 영역](#13-메소드-영역)  
   1.4 [스택 영역](#14-스택-영역)  
   1.5 [힙 영역](#15-힙-영역)  
   1.6 [자바 가상머신의 인스턴스 소멸시키기](#16-자바-가상머신의-인스턴스-소멸시키기)  

2. [Object 클래스](#2-object-클래스)  
   2.1 [Object 클래스의 finalize 메소드](#21-object-클래스의-finalize-메소드)  
   2.2 [finalize 메소드의 오버라이딩 예](#22-finalize-메소드의-오버라이딩-예)  
   2.3 [인스턴스의 비교: equals 메소드](#23-인스턴스의-비교-equals-메소드)  
   2.4 [String 클래스의 equals 메소드](#24-string-클래스의-equals-메소드)     
   
<br>   

# 1. 자바 가상머신의 메모리 모델
## 1.1 운영체제 입장에서 자바 가상머신
- 운영체제의 관점에서는 가상머신도 프로그램의 하나이다.

- 운영체제가 일반 프로그램에게 4G의 메모리 공간을 할당해준다면, JVM에게도 4G 메모리 공간을 할당해준다.    
JVM이나 일반 프로그램이나 별다를 게 없다.

- 자바 프로그램이 두 개 실행되면, 가상머신도 두 개가 실행된다.
<br>
<br>


## 1.2 자바 가상머신의 메모리 모델
- 필요한 것을 빨리 찾고, 불필요한 것을 삭제하기 위한 용도로 공간을 나눠서 사용하고 있다.

- 메모리 공간 활용의 효율성을 높이기 위해 메모리 공간을 세 개의 영역으로 구분하였다.
    ```
    ┌───────────────┐  
    │ [Method Area] │
    │ [Stack  Area] │
    │ [Heap   Area] │
    └───────────────┘ 
    ```
<br>

### 1.2.1 메소드 영역(Method Area)
- 메소드의 바이트 코드, static 변수.  

- 메소드 코드가 올라간다는 의미는?  
A.java ───compile──> A.class   
클래스 파일에는 바이트 코드로 가득차있다. 
자바 프로그램을 실행시키면 바이트 코드가 실행이 되는데, 그 바이트 코드가 올라가는 영역이라고 이해하자.  

- static 변수  
특정 클래스 정보를 읽었다고 가정하자. (A.class)  
JVM은 A클래스를 이루고 있는 메소드들(바이트코드들)을 메소드 영역에 올린다.   
이 과정에서 A 클래스를 스캔했더니 static 변수가 있다면 클래스 정보를 읽어들이는 그 순간에 static변수를 메소드 영역에 할당해버릴 뿐만 아니라 초기화까지 끝내버린다.

- static변수는 왜 메소드 영역에?  
바이트코드를 메소드 영역에 넣는 건 이해가 되는데 static변수(클래스변수)는 변수인데 왜 메소드 영역에 넣는 것일까?  
**`static변수`** 는 한번 저장(기록)하면, 즉 메모리 공간에 넣어버리면 **`프로그램이 종료될 때까지`** 메모리 공간에서 꺼내지 않는다는 특징이 있기 때문이다.
<br>

### 1.2.2 스택 영역(Stack Area)
- 지역변수, 매개변수
<br>

### 1.2.3 힙 영역(Heap Area)
- 인스턴스
<br>
<br>


## 1.3 메소드 영역
```java
class Boy {
    static int average = 0;

    public void Run() {...}
}

class MyMain {
    public static void main(String[] args) {
        Boy b = new Boy();  // 인스턴스 생성
        Boy.average += 5;   // 클래스 변수 접근
        ...
    }
}
```
- 메소드 영역:   
바이트 코드와 static 변수가 할당되는 메모리 공간이다.  
**`이 영역에 저장된 내용은 프로그램 종료 시 소멸된다.`**

- 저장 과정:  
  - main메소드 영역을 집어 넣는다. 집어 넣을 때 MyMain이라는 클래스를 통째로 집어 넣는다.  
  main은 메소드이므로 당연히 메소드 영역에 들어간다.  
  그리고 MyMain에 static변수가 있었다면 static 변수도 메소드 영역에 들어간다.  

  - main 메소드 내에서 Boy 인스턴스를 생성하고 있다.  
  Boy 인스턴스를 생성하기 위해서 Boy클래스가 필요하다.  
  Boy 클래스 정보를 쭉 읽다보니 static 변수가 있다.  
  따라서 Boy 클래스 정보를 읽어들이는 그 순간에 static변수 average를 메소드 영역에 할당하고 0으로 초기화까지 해버린다.   
  설령 사용하지 않는다하더라도 할당한다.
<br>
<br>


## 1.4 스택 영역
```java
public static void main(String[] args) {
    int num1 = 10;
    int num2 = 20;

    adder(num1, num2);

    System.out.println("end of program");
}

public static void adder(int n1, int n2) {
    int result = n1 + n2;
    return result;
}
```
- 스택 영역:  
지역변수, 매개변수가 할당되는 영역.  
**`이 영역에 저장된 변수는 해당 변수가 선언된 메소드 종료 시에 소멸된다.`**

- **`임시 저장`**  
메소드 영역에 저장되는 것들은 프로그램이 종료될 때까지 저장이 된다.  
반면 잠깐 저장해야 되는 것들은 스택 영역에 저장한다.  
adder 메소드에서 잠깐 저장해야 되는 것들을 찾아보자.  
-> n1과 n2 그리고 result   
프로그램 전체 흐름에서 adder 메소드가 호출되는 순간 메모리에 저장됐다가 adder 메소드를 빠져나가는 순간 지워져야 되는 변수이다.
<br>
<br>


## 1.5 힙 영역
```java
public static void simpleMethod() {
    String str1 = new String("My String");
    String str2 = new String("Your String");
    ...
}
```
<br>

```text
Stack Area                   Heap Area
┌────────┐              ┌─────────────────┐  
│ [str1]·┼──reference───┼>["My String"]   │
│ [str2]·┼──reference───┼>["Your String"] │
└────────┘              └─────────────────┘ 
Reference-                    Instance
Variables 
```
<br>

### 1.5.1 힙 영역
- 힙 영역:  
인스턴스가 저장되는 영역.  
**`가비지 컬렉션의 대상이 되는 영역이다.`**

- 인스턴스를 저장하기 위한 영역  
인스턴스는 모조리 여기에 저장된다.   
왜 인스턴스는 스택에 저장하면 안될까?

- str1과 str2는 참조변수이자 지역변수이기 때문에 스택에 저장된다.  
simpleMethod 메소드를 빠져나가면  str1과 str2는 사라진다.

- 참조변수가 없어져버리면 인스턴스를 어떻게 참조해???  
str1과 str2는 각각 인스턴스를 참조하고 있었는데 사라져버리면 참조하고 있던 인스턴스가 무의미해진다.   
인스턴스를 가리키고 있는 참조변수가 사라졌는데 인스턴스를 힙 영역에 저장해놓는 게 무슨 의미가 있는 것일까? 굳이 왜 나눠놓은 것일까?
<br>

### 1.5.2 스택 영역과 힙 영역
- 스택영역과 힙영역은 다르다.  
    ```java
    String str1 = new String("My String");
    String str2 = str1; 
    ```
    str1, str2 ───> My String   

    위 코드의 경우, 하나의 인스턴스를 두 개의 참조변수가 참조하고 있다.

    str1이 소멸되면 str1이 참조하고 있던 인스턴스도 무의미하니 이 인스턴스를 지워버린다고 가정해보자.  

    str2는 어떻게 되는가???  
    다른 변수를 통해서도 인스턴스에 접근이 가능한데, 참조변수 1개가 없어졌다고해서 인스턴스를 함부로 지워버리면 안되므로 인스턴스는 별도로 관리한다.

- 이처럼 하나의 참조변수가 사라진다고 해서 그 참조변수가 참조하는 인스턴스까지 한번에 지워버리면 안된다.  
지우기 전에 이 인스턴스를 참조하고 있는 참조변수가 있는지 없는지 확인을 해야하고, 
이 인스턴스는 더 이상 그 누구도 참조하지 않는다는 확신이 섰을 때 지워야한다. 
<br>

### 1.5.3 가비지 컬렉션
- 인스턴스를 지우는 일이 보통이아닌데 다행히도 우리가 지우지 않아도 된다.  
자바 가상머신이 이 일을 대신해준다.  
힙 영역에 존재하는 인스턴스를 스캔하고 인스턴스를 누가 참조하는지 파악하고 아무도 참조하고 있지 않은 인스턴스를 찾아내고 지우는 역할을 자바 가상머신이 해주고 있다.  
그리고 이런 액션을 가리켜 **`가비지 컬렉션`** 이라고 한다.

- 인스턴스를 힙 영역에 별도로 저장한 이유는 조심스럽게 지우기 위해서이다.
<br>
<br>


## 1.6. 자바 가상머신의 인스턴스 소멸시키기
```java
public static void simpleMethod() {
    String str1 = new String("My String");
    String str2 = new String("Your String");
    ...
    str1 = null;    // 참조 관계 소멸
    str2 = null;    // 참조 관계 소멸
    ...
}
```
- 힙 영역의 인스턴스를 보고, 어떤 변수도 인스턴스를 참조 하지 않으면 그 순간에 지우면 된다.  

- 그 누구도 참조하지 않는 인스턴스가 가비지가 되는 것이고 그 가비지들을 모아서 버리는 것을 자바 가상머신이 대신 해준다.   
그 액션을 가리켜 가비지 컬렉션이라고 한다.
<br>

```text
Stack Area                   Heap Area
┌────────┐              ┌─────────────────┐  
│ [str1]·┼──────X───────┼>["My String"]   │
│ [str2]·┼──────X───────┼>["Your String"] │
└────────┘              └─────────────────┘ 
Reference-                    Instance
Variables 
```
- 참조 관계가 끊어진 인스턴스는 접근이 불가능하다.  
따라서 가비지 컬렉션의 대상이 된다.
<br>
<br>


# 2. Object 클래스
## 2.1 Object 클래스의 finalize 메소드
```java
protected void finalize() throws Throwable
```
- Object 클래스에 정의되어 있는 이 메소드는 인스턴스 소멸 시 자동으로 호출이 된다.  
자식 클래스에서 오버라이딩 할 수 있다.

- 자바 가상머신에 의해서 가비지 컬렉션 될 때 자동으로 호출된다.  
인스턴스가 사라질 때 호출되는 메소드이다.

- 가비지 컬렉션을 할 때는 2단계로 나뉘어진다.  
일단 훑는다. 가비지 컬렉션을 할 대상이 있나 찾아본다.  
가비지 컬렉션을 해야되는 상황이면 일단 체크를 해놓는다. 체크만 하고 멈추고 자바 프로그램이 실행되도록 한다.  
다음으로 체크해 놓은 것을 지우는 과정이 일어난다.  
이처럼 체크하는 과정과 삭제하는 과정이 구분될 수 있다.

- finalize 메소드는 가비지 컬렉션을 체크할 때 호출되는 것이 아니라 인스턴스를 삭제할 때 호출된다.
<br>
<br>


## 2.2 finalize 메소드의 오버라이딩 예
```java
class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();   // 상위 클래스의 finalize 메소드 호출
        System.out.println("destroyed: " + name); 
    }
}
```
- Object 클래스의 finalize 메소드를 호출하면서 기존의 메소드를 건드리지 않고, 끼워 넣기 형태로 오버라이딩 하고 있다.
<br>

```java
public static void main(String[] args) {
    Person p1 = new Person("Yoon");
    Person p2 = new Person("Park");
    p1 = null;  // 참조대상을 가비지 컬렉션의 대상으로 만듦
    p2 = null;  // 참조대상을 가비지 컬렉션의 대상으로 만듦

    // System.gc();
    // System.runFinalization();

    System.out.println("end of program");
}
```
```bash
end of program
```
- 실행 결과를 보면 가비지 컬렉션의 대상인 Person 인스턴스의 finalize 메소드가 실행이 되지 않았다.  
실행이 됐다면 "destroyed: name" 문자열이 출력이 됐을 것이다.  
왜 실행이 되지 않은 것일까?

- 프로그램이 종료될 때는 메소드 영역, 스택 영역, 힙 영역이 전부 사라진다.   
프로그램이 종료 될 텐데 굳이 가비지 컬렉션을 호출할 필요가 없으니 실행이 되지 않은 것이다.

- 호출이 될 수도 있고, 안 될 수도 있는 메소드는 활용을 잘 하지 않는다. (신뢰가 떨어진다.)

- ```java
  System.gc();
  System.runFinalization();
  ```
    이름에서 벌써 유추가능하다.  
    gc 메소드를 호출하는 명령이 아니라 재촉하는 것이다.   
    "가비지 컬렉션 좀 해달라." (역시 호출이 될 수도 안될 수도 있다.)

    runFinalization 메소드는 "체크만 하지말고 제발 지워버려라"라고 요청하는 것이다.

    주석을 해제하면 finalize 메소드를 호출한 결과를 실제로 확인할 수 있다.
<br>
<br>


## 2.3 인스턴스의 비교: equals 메소드
- 인스턴스의 내용 비교를 위한 기능을 equals 메소드에 담아 정의한다.  

- equals는 Object 클래스의 메소드이다.

- == 연산자를 통해서 비교하는 방법  
참조 값을 비교한다.   
동일한 인스턴스를 참조하는 경우 true를 반환한다.

- Onject의 equals 메소드도 동일하다.  
참조 값을 비교한다.

- ```java
    INum num1 = new INum(10);
    INum num2 = new INum(12);
    INum num3 = new INum(10);

    if(num1.equals(num3)) {
        System.out.println("num1, num3 내용이 동일하다.");
    } else {
        System.out.println("num1, num3 내용이 다르다.");
    }
   ```  
   false를 반환한다.

- == 연산자와 Object의 equals 메소드는 동일한데 equals 메소드는 하위 클래스에서 오버라이딩해서 사용할 수 있다.

- 같다, 다르다의 기준을 프로그래머가 클래스 내에서 equals 메소드를 정의한다.
<br>
<br>


## 2.4 String 클래스의 equals 메소드
```java
public static void main(String[] args) {
    String str1 = new String("So Simple");
    String str2 = new String("So Simple");

    // 참조 대상을 비교하는 if ~ else문
    if (str1 == str2) {
        System.out.println("str1, st2는 참조 대상이 동일하다.");
    } else {
        System.out.println("str1, st2는 참조 대상이 다르다.");
    }

    // 두 인스턴스의 내용을 비교하는 if ~ else문
    if (str1.equals(str2)) {
        System.out.println("str1, st2는 내용이 동일하다.");
    } else {
        System.out.println("str1, st2는 내용이 다르다.");
    }
}
```
- String 클래스는 내용 비교를 하는 형태로 equals 메소드를 오버라이딩 하고 있다.
<br>

```bash
str1, st2는 참조 대상이 다르다.
str1, st2는 내용이 동일하다.
```