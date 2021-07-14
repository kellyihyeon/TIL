# 클래스의 상속 3: 상속의 목적

## 목차
1. [상속이 도움이 되는 상황의 소개](#1-상속이-도움이-되는-상황의-소개)  
   1.1 [단순한 인맥 관리 프로그램: 관리 대상이 둘!](#11-단순한-인맥-관리-프로그램-관리-대상이-둘)  
   1.2 [두 클래스를 대상을 하는 코드](#12-두-클래스를-대상을-하는-코드)  
   1.3 [상속 기반의 문제 해결: 두 클래스 상속 관계로 묶기](#13-상속-기반의-문제-해결-두-클래스-상속-관계로-묶기)  
   1.4 [상속으로 묶은 결과](#14-상속으로-묶은-결과)  

2. []()

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


