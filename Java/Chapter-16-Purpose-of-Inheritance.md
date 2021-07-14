# 클래스의 상속 3: 상속의 목적

## 목차
1. [상속이 도움이 되는 상황의 소개](#1-상속이-도움이-되는-상황의-소개)  
   1.1 [단순한 인맥 관리 프로그램: 관리 대상이 둘!](#11-단순한-인맥-관리-프로그램-관리-대상이-둘)  
   1.2 [두 클래스를 대상을 하는 코드](#12-두-클래스를-대상을-하는-코드)  
   1.3 [상속 기반의 문제 해결: 두 클래스 상속 관계로 묶기](#13-상속-기반의-문제-해결-두-클래스-상속-관계로-묶기)  
   1.4 [상속으로 묶은 결과](#14-상속으로-묶은-결과)  

2. [Object 클래스와 final 선언 그리고 @Override](#2-object-클래스와-final-선언-그리고-override)  
   2.1 [모든 클래스는 Object 클래스를 상속합니다](#21-모든-클래스는-object-클래스를-상속합니다)  
   2.2 [모든 클래스가 Object를 직접 또는 간접 상속하므로](#22-모든-클래스가-object를-직접-또는-간접-상속하므로)  
   2.3 [프로그래머가 정의하는 toString은 메소드 오버라이딩](#23-프로그래머가-정의하는-tostring은-메소드-오버라이딩)  
   2.4 [클래스와 메소드의 final 선언](#24-클래스와-메소드의-final-선언)  
   2.5 [@Override](#25-override)  

<br>

# 1. 상속이 도움이 되는 상황의 소개
## 1.1 단순한 인맥 관리 프로그램: 관리 대상이 둘!
```java
class UnivFriend {  // 대학 동창
    private String name;
    private String major;
    private String phone;

    public UnivFriend(String na, String ma, String ph) {
        this.name = na;
        this.major = ma;
        this.phone = ph;
    }
    
    public void showInfo() {
        System.out.println("name = " + name);
        System.out.println("major = " + major);
        System.out.println("phone = " + phone);
    }
}
```
<br>

```java
class CompFriend {  // 직장 동료
    private String name;
    private String department;
    private String phone;

    public CompFriend(String na, String de, String ph) {
        this.name = na;
        this.department = de;
        this.phone = ph;
    }
    
    public void showInfo() {
        System.out.println("name = " + name);
        System.out.println("department = " + department);
        System.out.println("phone = " + phone);
    }
}
```
<br>
<br>


## 1.2 두 클래스를 대상을 하는 코드
```java
public static void main(String[] args) {
    UnivFriend[] ufrns = new UnivFriend[5];
    int ucnt = 0;

    CompFriend[] cfrns = new CompFriend[5];
    int ccnt = 0;

    ufrns[ucnt++] = new UnivFriend("LEE", "Computer", "010-333-555");
    ufrns[ucnt++] = new UnivFriend("SEO", "Electronic", "010-222-444");

    cfrns[ccnt++] = new CompFriend("YOON", "R&D 1", "02-123-999");
    cfrns[ccnt++] = new CompFriend("PARK", "R&D 2", "02-321-777");

    for (int i = 0; i < ucnt; i++) {
        ufrns[i].showInfo();
        System.out.println();
    }

    for (int i = 0; i < ccnt; i++) {
        cfrns[i].showInfo();
        System.out.println();
    }
}
```
- 클래스가 2개일 때에는 문제가 안되는 것 같아 보이지만, 이러한 클래스 디자인 기반에서 관리 대상이 넷, 다섯으로 늘어난다면 늘어나는 수만큼 코드가 복잡해진다.

- 데이터를 저장, 확인, 삭제 등을 하는 코드가 계속해서 늘어날텐데 이는 확장성이 떨어지는 코드이다.
<br>
<br>


## 1.3 상속 기반의 문제 해결: 두 클래스 상속 관계로 묶기
```java
class Friend {
    protected String name;
    protected String phone;

    public Friend(String na, String ph) {
        name = na;
        phone = ph;
    }
    
    public void showInfo() {
        System.out.println("name = " + name);
        System.out.println("phone = " + phone);
    }
}
```
<br>

```java
class UnivFriend extends Friend { 
    private String major;
    

    public CompFriend(String na, String ma, String ph) {
        super(na, ph);
        major = ma;
    }
    
    public void showInfo() {
        super.showInfo();
        System.out.println("department = " + department);
    }
}
```
<br>

```java
class CompFriend extends Friend { 
    private String department;
    

    public CompFriend(String na, String de, String ph) {
        super(na, ph);
        department = de;
    }
    
    public void showInfo() {
        super.showInfo();
        System.out.println("major = " + major);
    }
}
```
- 코드의 확장성을 위해서 상속을 하고 있다.

- 연관된 일련의 클래스들에 대해 공통적인 규악을 정의 및 적용할 수 있다. 
  - CompFriend와 UnivFriend 클래스에 대해 Friend 클래스라는 규약을 정의하고 적용할 수 있다.
<br>
<br>


## 1.4 상속으로 묶은 결과
```java
public static void main(String[] args) {
    Friend[] frns = new Friend[10];
    int cnt = 0;

    frns[cnt++] = new UnivFriend("LEE", "Computer", "010-333-555");
    frns[cnt++] = new UnivFriend("SEO", "Electronic", "010-222-444");

    frns[cnt++] = new CompFriend("YOON", "R&D 1", "02-123-999");
    frns[cnt++] = new CompFriend("PARK", "R&D 2", "02-321-777");

    // 모든 동창 및 동료의 정보 전체 출력
    for (int i = 0; i < cnt; i++) {
        frns[i].showInfo();     // 오버라이딩 한 메소드가 호출된다.
        System.out.println();
    }
}
```
- 배열이 하나로 줄었다.
저장하는 방법도 하나로 간결해졌다. 뿐만 아니라 전체 정보를 출력할 때도 간결해졌다.

- frns[i].showInfo();
Friend형의 showInfo 메소드를 호출하지만 메소드가 오버라이딩 됐기 때문에 UnivFriend, CompFriend의 showInfo 메소드가 호출이 된다.

- 이러한 클래스 디자인 기반에서 관리 대상이 넷, 다섯으로 늘어난다면 인스턴스 관리와 관련해서 코드가 복잡해지지 않는다.
<br>
<br>


# 2. Object 클래스와 final 선언 그리고 @Override
## 2.1 모든 클래스는 Object 클래스를 상속합니다
```java
class MyClass {...}

class MyClass extends Object {...}
```
- 상속하는 클래스가 없다면 컴파일러에 의해 java.lang.Object 클래스를 상속하게 코드가 구성된다.  
extends Object 이 부분을 컴파일러가 넣어준다.

```java
class MyClass extends OtherClass {...}
```
- 다른 클래스를 상속한다면 Object 클래스를 직접 상속하지는 않게 된다. 그러나 간접적으로 Object 클래스를 상속하게 된다.
  - Object 클래스를 상속하는 클래스를 상속하는 형태로
<br>
<br>


## 2.2 모든 클래스가 Object를 직접 또는 간접 상속하므로
```java
// System.out.println
public void println(Object x) {
    ...
    String s = x.toString();
    ...
}
```
- Object가 모든 클래스의 최상위 클래스이므로 모든 인스턴스들은 직접 또는 간접적으로 Object를 상속한다.  
그러므로 모든 인스턴스는 println 메소드의 매개변수의 인자로 전달이 가능하다.
  - 앞에서 println 메소드를 사용하면서 모든 형을 출력할 수 있었던 이유가 println 메소드가 Object형을 매개변수로 받았기 때문이었다.

- 모든 클래스는 Object를 상속하므로 위 메소드의 인자로 전달이 가능하다.   

- toString 메소드는 Object 클래스의 메소드였음을 알 수 있다.  
앞에서 toString 메소드를 정의한 적이 있었는데, 사실 그것은 Object에 있는 toString 메소드를 오버라이딩 했었던 것이었다.
<br>
<br>


## 2.3 프로그래머가 정의하는 toString은 메소드 오버라이딩
```java
class Cake {
    // Object 클래스의 toString 메소드를 오버라이딩
    public String toString() {
        return "My birthday cake";
    }
}

class CheeseCake extends Cake {
    // Cake 클래스의 toString 메소드를 오버라이딩
    public String toString() {
        return "My birthday cheese cake";
    }
}
```
- Cake가 Object 클래스를 상속할 것이고, CheeseCake는 Cake를 상속함으로써 Objcet 클래스를 간접 상속하는 것이다.
<br>
<br>


## 2.4 클래스와 메소드의 final 선언
```java
public final class MyLastCLS {...}
```
- MyLastCLS 클래스는 다른 클래스가 상속할 수 없다.

- **`final`**  
이 클래스는 다른 클래스가 상속하는 것을 허용하지 않겠다
<br>


```java
class Simple {
    public final void func(int n) {...}
}
```
- func 메소드는 다른 클래스에서 오버라이딩 할 수 없다.

- **`final`**   
이 메소드는 다른 메소드에서 오버라이딩 하는 것을 허용하지 않겠다.
<br>
<br>


## 2.5 @Override
```java
class ParentAdder {
    public int add(int a, int b) {
        return a + b;
    }
}
```
<br>

```java
class ChildAdder extends ParentAdder {
    
    @Override
    public double add(double a, double b) {
        System.out.println("덧셈을 진행합니다.");
        return a + b;
    }
}
```
- ParentAdder의 add 메소드와 ChildAdder의 add 메소드는 오버라이딩이 아니라 상속으로 두 클래스에 걸쳐서 형성된 메소드 **`오버로딩`** 이다.  
따라서 컴파일 오류가 발생한다.

- **`@Override`**  
상위 클래스의 메소드를 오버라이딩 하는 것이 목적이라는 선언이다.  
오버라이딩을 하는 형태가 아니면 컴파일러가 오류 메시지를 전달한다.

- @Override 어노테이션은 컴파일러에게 지금 등장하는 메소드는 메소드 오버라이딩이라는 정보를 전달해 주는 것이다. 

- @Override을 붙이지 않아도 컴파일은 되는데, @Override 어노테이션이 있기 때문에 `안전성을 부여`하게 된다.

- 코드를 보면 ChildAdder의 add 메소드는 오버라이딩 관계가 성립이 안된다.  
오버라이딩이 되려면 반환형, 메소드 이름, 매개변수 선언이 같아야 하는데, 매개변수 선언이 다르다. 그러므로 오버라이딩 선언이 안된다.  

- 프로그래머가 @Override 라고 했지만 확인해보니 오버라이딩이 아니라고 자바 컴파일러가 알려주는 것이다.  
@Override 어노테이션은 특별한 기능을 제공해주진 않지만 컴파일러에게 정보를 전달해줌으로써, 오버라이딩을 하고자 했는데 오버라이딩이 아니고 실수로 오버로딩이 되는 상황을 막을 수 있다.

- @Override 어노테이션을 빼고 코드를 작성하면 어떤 일이 벌어질지 생각해보면 된다.  
add 메소드를 호출하면서 매개변수로 정수를 전달하면 ParentAdder의 add 메소드가 호출되고, 실수를 전달하면 ChildAdder의 add 메소드가 호출이 된다.
오버라이딩이 되지 않았고 프로그래머의 실수에 의해서 오버로딩이 된 것을 확인할 수 있다.