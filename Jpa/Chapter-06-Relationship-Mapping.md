# 다양한 연관관계 매핑

## 목차
👀 [학습 목표](#학습-목표)

1. [다대일](#1-다대일)  
  1.1 [다대일: 단방향](#11-다대일-단방향)  
  1.2 [다대일: 양방향](#12-다대일-양방향)

2. [일대다](#2-일대다)  
   2.1 [일대다: 단방향](#21-일대다-단방향)    
   2.2 [일대다: 양방향](#22-일대다-양방향)    

3. [일대일](#3-일대일)  

4. [다대다](#4-다대다)  
   4.1 [다대다: 단방향](#41-다대다-단방향)   
   4.2 [다대다: 양방향](#42-다대다-양방향)  
   4.3 [다대다: 매핑의 한계와 극복, 연결 엔티티 사용](#43-다대다-매핑의-한계와-극복-연결-엔티티-사용)  
   4.4 [다대다: 새로운 기본 키 사용](#44-다대다-새로운-기본-키-사용)  
   4.5 [다대다 연관관계 정리](#45-다대다-연관관계-정리)  

<br>
<br>

## 학습 목표
다대일, 일대다, 일대일, 다대다 연관관계를 단방향, 양방향으로 매핑하는 방법에 대해 알아보자.
<br>

[예제코드](https://github.com/kellykang-tech/Jpa-From-Scratch/tree/main/model3)
<br>
<br>

# 1. 다대일
- 데이터베이스 테이블의 1:N 관계에서 외래키는 항상 다쪽에 있다.

- MEMBER(N) : TEAM(1)
    ```text
    MEMBER                  TEAM
    MEMBER_ID(PK)     ┌────·TEAM_ID(PK)
    TEAM_ID(FK) ◀────┘     NAME
    USERNAME
    ```

- 객체 양방향 관계에서 연관관계의 주인은 항상 다쪽이다.

- MEMBER(N) : TEAM(1) - MEMBER 쪽이 연관관계의 주인이다.
<br>
<br>


## 1.1 다대일: 단방향
- 객체 연관관계
    ```text
    MEMBER     ─────────────＞  TEAM
    id         *            1   id
    TEAM team                   name
    username
    ```
<br>
<br>

## 1.2 다대일: 양방향
- 객체 연관관계
    ```text
    MEMBER     ─────────────＞  TEAM
    id         *            1   id
    TEAM team  ＜---------------List members
    username                    name
    ``` 

- 코드
  ```java
  @Entity
  public class Member {
      ...

      @ManyToOne
      @JoinColumn(name = "TEAM_ID")
      private Team team;


      public void setTeam(Team team) {
        this.team = team;

        if (!team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }
      }
    
      ...
  }
  ```

  ```java
  @Entity
  public class Team {
      ...

      @OneToMany(mappedBy = "team")
      private List<Member> members = new ArrayList<>();


      public void addMemeber(Member member) {
        this.members.add(member);
        if (member.getTeam() != this) {
            member.setTeam(this);
        }
    }
    
      ...
  }
  ```

- 양방향에서는 외래키가 있는 쪽이 연관관계의 주인이다.
  - 다(N)인 MEMBER 테이블이 외래키를 가지고 있고, Member.team이 연관관계의 주인이다.
  - JPA는 외래키를 관리할 때 `연관관계의 주인만 사용`한다.
  - 주인이 아닌 Team.members는 언제 사용하는가?
    - 조회를 위한 JPQL이나 객체 그래프를 탐색할 때 사용
<br>

- 양방향 연관관계는 항상 서로를 참조해야 한다.
  - 항상 서로 참조하게 하려면 연관관계 편의 메소드를 작성하는 것이 좋다.
  - 편의 메소드: member.setTeam(), team.addMember() 
  - 코드에서는 Member와 Team 양쪽에 작성하였고, 호출 할 때는 하나만 호출하면 된다.
<br>
<br>

# 2. 일대다
- 일대다 관계는 엔티티를 하나 이상 참조할 수 있으므로 자바 컬렉션인 Collection, List, Set, Map 중 하나를 사용해야 한다.
<br>
<br>

## 2.1 일대다: 단방향
- 하나의 팀은 여러 회원을 참조할 수 있다. 이를 일대다 관계라 한다.
  - Team1 에 소속된 회원: member2, member5, member 14 ...
  
- Team 은 members 를 참조하는데 member가 team을 참조하지 않으면 단방향 관계이다.  
(일대다 단방향 관계는 JPA 2.0부터 지원)

- 코드
  ```java
  @Entity
  public class Member {
      ...

      @Id
      @GeneratedValue
      @Column(name = "MEMBER_ID")
      private Long id;

      private String username;

      ...
  }

  ```

  ```java
    @Entity
    public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();
  ```
  - Team.members 로 MEMBER 테이블의 TEAM_ID 외래키를 관리한다.

- 일대다 관계에서 외래키는 항상 다쪽 테이블에 있지만 다쪽인 Member 엔티티에는 외래키를 매핑할 수 있는 참조 필드(team)가 없다.

- Team 엔티티에만 참조 필드 members가 있으므로 Team 엔티티에서 반대편 테이블의 외래키를 관리하는 특이한 모습이 나타난다.
<br>
<br>


### 2.1.1 일대다 단방향 단점
- 매핑한 객체가 관리하는 외래키가 다른 테이블에 있다.
  ```java
    @OneToMany
    @JoinColumn(name = "TEAM_ID")   // 테이블 연관관계
    private List<Member> members = new ArrayList<>();
  ```

- 본인 테이블에 외래키가 있으면 엔티티 저장, 연관관계 처리가 INSERT 문 하나로 끝날 수 있는데, 다른 테이블에 외래키가 있으면 연관관계 처리를 위해 UPDATE 문을 추가로 실행해야 한다.

```java
final Member member1 = new Member("member1");
final Member member2 = new Member("member2");

final Team team1 = new Team("team1");
team1.getMembers().add(member1);
team1.getMembers().add(member2);

em.persist(member1);
em.persist(member2);
em.persist(team1);
```
- persist 할 때 sql 문을 보자.  
  insert into Member (username, MEMBER_ID) values (?, ?)  
  insert into Member (username, MEMBER_ID) values (?, ?)
  insert into TEam (name, TEAM_ID) values (?, ?)  
  update Member set TEAM_ID=? where  MEMBER_ID=?  
  update Member set TEAM_ID=? where  MEMBER_ID=?  
<br>
<br>


## 2.2 일대다: 양방향
- 다대일 양방향과 사실 같은 말이다.

- 관계형 데이터베이스의 특성상 일대다, 다대일 관계에서는 항상 다 쪽에 외래키가 있다.

- 연관관계의 주인은 항상 다 쪽인 @ManyToOne을 사용한 곳이므로 @ManyToOne에는 mappedBy 속성이 없다.
<br>
<br>

### 2.2.1 일대다 양방향 매핑하기
- 일대다 단방향 매핑 반대편에 같은 외래키를 사용하는 다대일 단방향 매핑을 읽기 전용으로 추가하면 된다.

```java
@Entity
public class Member {
    ...
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)    // 읽기 전용
    private Team team;
    ...
}
```
- 일대다 단방향에서 한 것처럼 같은 외래키 (TEAM_ID)를 매핑한다. 

- 하지만 이렇게 해놓으면 Member와 Team 모두 같은 키를 관리하게 되므로 문제가 발생할 수 있으므로 다대일 쪽에 `insertable = false, updatable = false`로 설정해서 읽기 전용으로 만든다.

- 일대다 양방향으로 보이지만, 실은 일대다 단방향 매핑, 다대일 단방향 매핑 각각을 설정해 놓은 것이다. 

- 이렇게 설정해놓으면 일대다 단방향 매핑이 가지고 있는 단점을 그대로 가지기 때문에 다대일 양방향 매핑을 사용하는 것을 권장한다.
<br>
<br>


# 3. 일대일
- 일대일 관계는 주 테이블이나 대상 테이블 둘 중 어디에서나 외래키를 가질 수 있다.
- 예를 들어, MEMBER, LOCKER 테이블이 있다고 가정하자.  
(주 테이블: MEMBER, 대상 테이블: LOCKER)

- 단방향, 양방향

<br>
<br>


# 4. 다대다
- 관계형 데이터베이스는 정규화된 테이블 2개로 다대다 관계를 표현할 수 없다.

- 중간에 연결 테이블을 추가해서 다대다 관계를 일대다, 다대일 관계로 풀어낸다.

- 객체는 테이블과는 다르다.   
객체 2개를 이용해서 다대다 관계를 만들 수 있다.
  
- ```java
  Member.class

      @ManyToMany
      @JoinTable(name = "MEMBER_PRODUCT",
              joinColumns = @JoinColumn(name = "MEMBER_ID"),
              inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
      private List<Product> products = new ArrayList<>();
  ```
- 멤버 객체는 컬렉션을 사용해 상품들을 참조하고 있다. 그 반대로 상품 객체도 컬렉션을 사용해서 멤버들을 참조하면 된다.
<br>
<br>

## 4.1 다대다: 단방향
- @ManyToMany: 회원 엔티티와 상품 엔티티 매핑

- @JoinTable: 회원과 상품을 연결하는 중간 엔티티인 회원_상품 엔티티 없이 연결 테이블을 바로 매핑했다.
  - @JoinTable.name: 연결 테이블 지정
  - @JoinTable.joinColumns: 현재 방향인 멤버와 매핑할 조인 컬럼 정보 지정
  - @JoinTable.inverseJoinColumns: 반대 방향인 상품과 매핑할 조인 컬럼 정보 지정
<br>
<br>


## 4.2 다대다: 양방향
```java
Product.class

@ManyToMany(mappedBy = "products")
private List<Member> members = new ArrayList<>();

```
- 역방향에도 @ManyToMany를 사용하고, 멤버 객체를 연관관계 주인으로 지정한다.
<br>
<br>


## 4.3 다대다: 매핑의 한계와 극복, 연결 엔티티 사용
### 4.3.1 한계와 방법
- 실무에서 대부분 @ManyToMany를 사용하지 않는 이유는?

- @ManyToMany를 사용하면 연결 테이블을 자동으로 처리해주므로 도메인 모델이 단순해지고 편리한 점이 있다.

- 하지만 연결 테이블에 컬럼이 추가 된다면?  
  - 주문 수량, 주문한 날짜 컬럼을 추가한다고 가정하자.
  ```text
     ·Member_Product
    ┌───────────────────┐
    │MEMBER_ID(PK, FK)  │
    │PRODUCT_ID(PK, FK) │
    ├───────────────────┤
    │ORDER_AMOUNT       │
    │ORDER_DATE         │
    └───────────────────┘
  ```
  - 컬럼을 추가하게 되면 @ManyToMany는 사용할 수 없다.    
  주문 엔티티나 상품 엔티티에는 추가한 컬럼들을 매핑할 수가 없기 때문이다.

  - 결국 `연결 엔티티`를 만들어서 추가한 컬럼들을 매핑해야 한다.
<br>
<br>


### 4.3.2 연결 엔티티 사용
- 연결 엔티티(MemberProduct)를 만들고 추가한 컬럼을 매핑하자.

- MemberProduct
  ```java
  @Entity
  @IdClass(MemberProductId.class) // 복합 기본 키 매핑
  public class MemberProduct {

      @Id
      @ManyToOne
      @JoinColumn(name = "MEMBER_ID")
      private Member member;

      @Id
      @ManyToOne
      @JoinColumn(name = "PRODUCT_ID")
      private Product product;

      private int orderAmount;

      private LocalDate orderDate;

      ...
  ```
  - @IdClass(MemberProductId.class): 아래의 회원상품 식별자 클래스를 먼저 살펴보고 이해한 후 다시 보자.

  - `기본 키를 매핑하는 @Id`와 `외래 키를 매핑하는 @JoinColumn`을 동시에 사용했다.  
  (기본 키 + 외래 키 한번에 매핑하기)

  - @IdClass: 복합 키 매핑하기
<br>


- MemberProductId
  ```java
  // 식별자 클래스
  public class MemberProductId implements Serializable {

      private String member;

      private String product;

      @Override
      public boolean equals(Object o) {...}

      @Override
      public int hashCode() {...}

      ...
  ```
  - JPA 에서 복합키를 사용하려면 별도로 식별자 클래스를 만들어야 한다.

  - 위 코드와같이 MemberProductId 식별자 클래스를 만들고, MemberProduct 엔티티에 @IdClass 를 사용해서 식별자 클래스를 지정해준다.

  - 복합 키를 사용하기 위한 식별자 클래스의 특징
    - 복합키는 별도의 식별자 클래스로 만들어야 한다.
    - Serializable을 구현해야 한다.
    - equals와 hashcode 메소드를 구현해야 한다.
    - 기본 생성자가 있어야 한다.
    - 식별자 클래스는 public 이어야 한다.
    - @IdClass 사용 외에도 @EmbeddedId를 사용하는 방법도 있다.
<br>

- 저장
  ```java
  public static void save(EntityManager em) {
        // 회원 저장
        final Member member1 = new Member();
        member1.setId("member1");
        member1.setUsername("회원1");
        em.persist(member1);

        // 상품 저장
        final Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품1");
        em.persist(productA);

        // 회원상품 저장
        // member, product, orderAmount, orderDate
        final MemberProduct memberProduct = new MemberProduct();
        memberProduct.setMember(member1);
        memberProduct.setProduct(productA);
        memberProduct.setOrderAmount(2);
        memberProduct.setOrderDate(LocalDate.now());

        em.persist(memberProduct);
  }
  ```
<br>

- 조회
  ```java
  public static void find(EntityManager em) {
        // 복합 기본 키 값 생성
        final MemberProductId memberProductId = new MemberProductId();
        memberProductId.setMember("member1");
        memberProductId.setProduct("productA");

        final MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);
        final Member member = memberProduct.getMember();
        final Product product = memberProduct.getProduct();

        System.out.println("member.getUsername() = " + member.getUsername());
        System.out.println("product.getName() = " + product.getName());
        System.out.println("memberProduct.getOrderAmount() = " + memberProduct.getOrderAmount());
        System.out.println("memberProduct.getOrderDate() = " + memberProduct.getOrderDate());
    }
  ```

- 출력결과
  ```text
  member.getUsername() = 회원1
  product.getName() = 상품1
  memberProduct.getOrderAmount() = 2
  memberProduct.getOrderDate() = 2021-12-14
  ```
<br>
<br>

## 4.4 다대다: 새로운 기본 키 사용
- 기본 키 생성 전략을 써보자.

- 기본 키 타입이 String이 아닌 Long 타입을 사용하고, 데이터베이스에서 자동으로 생성해주는 전략을 사용한다.

- 장점?  
  - 간편하고, 거의 영구히 쓸 수 있고, 비즈니스에 의존하지 않는다.  
  - ORM 매핑 시에 위에서 학습했던 것처럼 복합 키를 사용하지 않아도 되므로 간단히 매핑을 할 수 있다.

- 코드
  ```java
  @Entity
  public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "ORDERS_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int ordersAmount;

    private LocalDate ordesrDate;
    
    ...
  ```
  - ⭐ 클래스명을 Order 로 했다가 sql exception 발생:   
  콘솔 찍어보니 commit 할 때 'insert ORDER ...' 에서 에러 발생.  
  원인은 ORDER가 sql keyword 여서였다...
<br>
<br>

## 4.5 다대다 연관관계 정리
- 다대다 관계를 일대다, 다대일로 풀어내기 위해서 연결 테이블을 만들 때 먼저 식별자를 어떻게 구성할 것인지 선택해야 한다.

- 식별 관계: 받아온 식별자를 기본 키 + 외래 키로 사용한다.   
(MemberProduct.class)

- 비식별 관계: 받아온 식별자는 외래 키로만 사용하고 새로운 식별자를 추가한다.   
(Order.class)