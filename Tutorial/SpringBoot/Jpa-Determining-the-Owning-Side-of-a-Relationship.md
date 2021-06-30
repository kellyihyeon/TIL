# 연관관계의 주인

***연관관계 주인 = FK를 가진 오브젝트***   
*'FK를 누가가졌느냐'*
<br>


# 1.OneToMany

```java
public class Board {
    ...
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private List<Reply> reply;
    ...
}   
```

- 한 게시글에 달리는 댓글의 수는 0개 ~ 수만개이다. Board와 Reply의 연관관계를 OneToMany로 매핑한다.


## 1.1. @JoinColumn
DB에 생성될 컬럼명은 어떻게 해야할까?  
Board 테이블이 만들어지면서 replyId 컬럼도 생긴다고 가정해보자.
<br>

*`한 게시글에 댓글이 100개가 달려있는 상황이다.`*

|id|title|content|...|userId|replyId|
|-|-|-|-|-|-|
|1|스프링부트로 게시판 만들기|연관관계의 주인|...|2|3, 5, 9...|
<br>

- replyId를 ,로 연결해서 100개를 적을 수는 없다.
1정규화가 깨진다.
    - 데이터 베이스 내 하나의 컬럼은 원자성을 가진다. 
  
- FK는 여기에 필요 없다. 데이터베이스에는 replyId가 만들어지면 안되기 때문에 @JoinColumn은 필요가 없다.
<br>


## 1.2 mappedBy
- 연관관계의 주인이 아니라는 의미이다. (FK가 아니다.)  
- 'DB에 컬럼을 만들지마' 라는 의미.

- **`@OneToMany(mappedBy = "board")`**  
  Board를 select할 때 조인문을 통해서 값을 얻기 위해서 필요한 것이다라는 의미이다.

- '내가 주인이 아니라 주인은 Reply 테이블의 board가 주인이다'라는 의미이다.
<br>

  
## 1.3 fetch
- OneToMany에서는 기본전략이 LAZY이다.
    ```java
    @OneToMany(fetch = FetchType.LAZY)
    ```

- OneToMany 라는 건 데이터(리플)가 한 건이 있을 수도 있지만 수만 건이 있을 수도 있기 때문에 필요하면 들고오고 필요하지 않으면 안들고 온다는 전략을 취한다.

- @OneToMany:  
  fetch를 적지 않아도 기본적으로 LAZY로 설정되어 있다.
<br>


# 2. ManyToOne
```java
public class Reply {

    @ManyToOne
    @JoinColumn(name = "boardId") 
    private Board board;

}
```

```java
public class Board {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

}
```

- ManyToOne 에서는 기본전략이 EAGER이다.
  ```java
  @ManyToOne(fetch = FetchType.EAGER)
  ```

- ManyToOne이라는 건 user 데이터가 한 건이라는 의미이므로 Board테이블을 select 하면 user정보는  한 건밖에 없으니까 바로 가져온다.