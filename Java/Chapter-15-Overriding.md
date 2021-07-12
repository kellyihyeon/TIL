# 클래스의 상속 2: 오버라이딩

## 목차
1. [상속을 위한 두 클래스의 관계](#1-상속을-위한-두-클래스의-관계)  
   1.1 [상속 관계에 놓은 두 대상의 관찰](#11-상속-관계에-놓은-두-대상의-관찰)  
   1.2 [상속과 IS-A 관계](#12-상속과-is-a-관계)  
   1.3 [IS-A 관계의 예](#13-is-a-관계의-예)  


<br>

# 1. 상속을 위한 두 클래스의 관계
*자바는 현실 세계를 모델링해서 만든 언어이다.  
상위 클래스와 하위 클래스의 관계를 잘 생각해보자.  
노트북이 지우개를 상속할 순 없다. 노트북 클래스가 지우개 클래스를 상속하게 프로그래밍을 할 수는 있지만, 이는 잘못된 기능이다.   
노트북이 지우개를 상속했다면 노트북을 가지고 노트를 지우겠다는 기능을 의미한다.*
<br>

## 1.1 상속 관계에 놓은 두 대상의 관찰
### 1.1.1 상속의 특성
- 하위 클래스는 상위 클래스의 모든 특성을 지닌다.
  
- 거기에 더하여 하위 클래스는 자신만의 추가적인 특성을 더하게 된다.

- 모바일폰(전화기능이 전부)을 스마트폰이 상속한다.  
    ```text
    class 스마트폰 extends 모바일폰 {...}  
    ```

- 이렇듯 상속 관계에 있는 두 대상은 IS-A 관계를 가져야 한다.
A is a B ─> A는 일종의 B이다.  
어렵게 생각하지말고 사과(A)는 일종의 과일(B)이다 를 떠올리자.  
스마트폰은 일종의 모바일폰이다. -> 성립  
노트북은 일종의 지우개이다. -> 문제 발생
<br>
<br>


## 1.2 상속과 IS-A 관계
..은 ~이다

- IS-A 관계가 성립한다고 해서 전부 상속 시켜야 하는 것은 아니다.  
IS-A 관계가 100가지가 있다고 한다면, 이 중 상속 관계로 묶어줘야 하는 것은 50가지도 안될 것이다.   
기본적으로 IS-A 관계는 상속을 위한 기본 최소 조건이다.  
IS-A 관계가 성립 되는 것들을 모아놓고, 이 중에서 상속을 시킬지 말지를 결정한다.

- IS-A 관계를 갖지 않는 두 클래스가 상속으로 연결되어 있다면, 적절한 상속인지 의심해야 한다.
<br>
<br>


## 1.3 IS-A 관계의 예
```java
class MobilePhone {
    protected String number;    // 전화번호

    public MobilePhone(String num) {
        number = num;
    }
    
    public void answer() {
        System.out.println("Hi~ from " + number);
    }
}
```
- 전화를 받는 기능이 있다.
<br>

```java
class SmartPhone extends MobilePhone {
    private String androidVer;  // 안드로이드 운영체제 네임(버전)
    
    public SmartPhone(String num, String ver) {
        super(num);
        androidVer = ver;
    }
    
    public void playApp() {
        System.out.println("App is running in " + androidVer);
    }
}
```
- 전화를 받는 기능뿐만 아니라 스마트폰만이 가질 수 있는 기능 playApp()을 가지고 있다.

<br>

```java
public static void main(String[] args) {
    SmartPhone phone = new SmartPhone("010-555-777", "Nougat");
    phone.answer();     // 전화를 받는다.
    phone.playApp();    // 앱을 선택하고 실행한다.
}
```
<br>

```bash
Hi~ from 010-555-777
App is running in Nougat
```
<br>
<br>


# 2. 메소드 오버라이딩
# 3. instanceof 연산자