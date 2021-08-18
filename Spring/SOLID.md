# 좋은 객체 지향 설계의 5가지 원칙 (SOLID)

## 목차
1. [SOLID](#1-solid)  
   1.1 [SRP 단일 책임 원칙](#11-srp-단일-책임-원칙)  
   1.2 [OCP 개방-폐쇄 원칙](#12-ocp-개방-폐쇄-원칙)  
   1.3 [LSP 리스코프 치환 원칙](#13-lsp-리스코프-치환-원칙)  
   1.4 [ISP 인터페이스 분리 원칙](#14-isp-인터페이스-분리-원칙)  
   1.5 [DIP 의존관계 역전 원칙](#15-dip-의존관계-역전-원칙)  

2. [정리](#2-정리)

<br>
<br>


# 1. SOLID
*클린코드로 유명한 로버트 마틴이 좋은 객체 지향 설계의 5가지 원칙을 정리*

- SRP: 단일 책임 원칙(Single Responsibility Principle)

- OCP: 개방-폐쇄 원칙(Open/Closed Principle)

- LSP: 리스코프 치환 원칙(Liskov Substitution Principle)

- ISP: 인터페이스 분리 원칙(Interface Segregation Principle)

- DIP: 의존관계 역전 원칙(Dependency Inversion Principle)
<br>
<br>


## 1.1 SRP 단일 책임 원칙
*Single Responsibility Principle*

- 한 클래스는 하나의 책임만 가져야 한다.

- 하나의 책임이라는 것은 모호하다.
  - 클 수 있고, 작을 수 있다.
  - 문맥과 상황에 따라 다르다.

- **`중요한 기준은 변경`** 이다.   
변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것이다.

- 예: UI 변경, 객체의 생성과 사용을 분리
<br>
<br>

## 1.2 **`OCP 개방-폐쇄 원칙`**
*Open/Closed Principle*

- 소프트웨어 요소는 `확장`에는 열려 있으나 `변경`에는 닫혀 있어야 한다

- 다형성을 활용해보자.  
인터페이스를 구현한 새로운 클래스를 하나 만들어서 새로운 기능을 구현하는 것은 변경이 아니다.
  - MemberRepository 를 구현한 JdbcMemberRepository를 만드는 것
  - 역할과 구현을 떠올리자.

    ```java
    public class MemberService {
        // private MemberRepository memberRepository = new MemoryMemberRepository();

        private MemberRepository memberRepository = new JdbcMemberRepository();
    }
    ```
<br>
<br>

### 1.2.1 OCP 개방-폐쇄 원칙: 문제점
- MemberService 클라이언트가 구현 클래스를 직접 선택하고 있다.
    ```java
    public class MemberService {
        private MemberRepository memberRepository = new MemoryMemberRepository();   // 기존 코드

        private MemberRepository memberRepository = new JdbcMemberRepository(); // 변경 코드
    }
    ```

- 구현 객체를 변경하려면 클라이언트 코드를 변경해야 한다.  
변경에는 닫혀있어야 한다고 했는데, 닫혀있지가 않다.


- 분명 다형성을 잘 사용했다.  
인터페이스도 잘 만들었고 구현체도 만들었다. 하지만 적용을 하려다보니까 OCP 원칙이 깨지고 있다. (클라이언트가 변경해야 하는 상황)

- 이 문제를 어떻게 해결해야 할까?  
객체를 생성하고, 연관관계를 맺어주는 별도의 조립, 설정자가 필요하다.  
이것이 바로 스프링 컨테이너이다.
<br>
<br>

## 1.3 LSP 리스코프 치환 원칙
*Liskov Substitution Principle*

- 자동차 시스템 프로그램을 만들 때, 엑셀이라는 기능을 구현해야 한다.
엑셀은 밟으면 앞으로 가야하지만 엑셀을 밟았을 때 뒤로가는 차를 만들 수도 있다. 그래도 컴파일 오류는 나지 않는다.

- 리스코프 치환 원칙은 단순히 컴파일 단계를 말하는 것이 아니다. 인터페이스의 규약, '엑셀은 앞으로 가야한다'라는 이 규약을 무조건 맞춰야 한다.   
기능적으로 보장해줘야한다는 원칙이다.

- 자동차 인터페이스의 엑셀은 앞으로 가라는 기능인데, 뒤로 가게 구현하면 리스코프 치환 원칙을 위반하는 것이다.   
느리더라도 앞으로 가게 만들면 리스코프 치환 원칙을 지키는 것이다.

- 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.

- 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다.   
다형성을 지원하기 위한 원칙, 인터페이스를 구현한 구현체는 믿고 사용하려면 이 원칙이 필요하다.
<br>
<br>


## 1.4 ISP 인터페이스 분리 원칙
*Interface Segregation Principle*

- 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.

- 자동차 인터페이스 -> 운전 인터페이스, 자동차 정비 인터페이스로 분리한다.  
분리하면 사용자 클라이언트를 운전자 클라이언트와 정비사 클라이언트로 분리할 수 있다.

- 정비에 관해 문제가 발생하면, 자동차 정비 인터페이스와 정비사 클라이언트 부분을 바꾸면 된다.

- 분리하면 정비 인터페이스 자체가 변해도 운전자 클라이언트에 영향을 주지 않는다.

- 인터페이스가 명확해지고, 대체 가능성이 높아진다.
<br>
<br>


## 1.5 **`DIP 의존관계 역전 원칙`**
*Dependency Inversion Principle*

- 프로그래머는 **`“추상화에 의존해야지, 구체화에 의존하면 안된다.”`**  
의존성 주입은 이 원칙을 따르는 방법 중 하나다.

- 쉽게 말하면, 클라이언트 코드가 구현 클래스를 바라보지 말고 인터페이스만 바라보라는 뜻이다. 
  - MemberService가 MemberRepository를 바라보게 하고, MemoryMemberRepository나 JdbcMemberRepository를 몰라야한다.

- 앞에서 이야기한 **`역할(Role)`** 에 의존하게 해야 한다는 것과 같다.   
객체 세상도 클라이언트가 인터페이스에 의존해야 유연하게 구현체를 변경할 수 있다.   
구현체에 의존하게 되면 변경이 아주 어려워진다.

- 그런데 OCP에서 설명한 MemberService는 인터페이스에 의존하지만, 구현 클래스도
동시에 의존한다.
  - MemberService는 MemberRepository뿐만 아니라 MemoryMemberRepository도 알고 있다.

- MemberService 클라이언트가 구현 클래스를 직접 선택하고 있다.

    ```java
    MemberRepository m = new MemoryMemberRepository();
    ```
    - DIP 위반
<br>
<br>


# 2. 정리
- 객체 지향의 핵심은 다형성이다.

- 다형성만으로는 쉽게 부품을 갈아 끼우듯이 개발할 수 없다.

- 다형성만으로는 구현 객체를 변경할 때 클라이언트 코드도 함께 변경된다.

- 다형성만으로는 OCP, DIP를 지킬 수 없다. 뭔가 더 필요하다.

    ```java
    public class MemberService {

        private MemberRepository memberRepository;
    }
    ``` 
  - 위 코드처럼 DIP만 지키면 구현체가 없어서 동작하지 않는다. NullPointerException이 터질 것이다.