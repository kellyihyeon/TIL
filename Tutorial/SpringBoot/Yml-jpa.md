# yml 설정
스프링은 blog 프로젝트가 시작되기 직전에 application.yml파일을 읽고 시작한다.

## jpa 설정
```yml
jpa:
    open-in-view: true
    hibernate:
        ddl-auto: create
        naming:
            physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
        use-new-id-generator-mappings: false
    show-sql: true
    properties:
        hibernate.format_sql: true
```
- use-new-id-generator-mappings: false  
 jpa가 사용하는 기본 넘버링 전략을 따라가지 않는다.   
 false로 설정함으로써 jpa가 아닌 MySql 넘버링 전략을 따라 갈 것이다.  (@GeneratedValue(strategy = GenerationType.IDENTITY))

- ddl-auto: create  
테이블을 새로 만든다. 기존에 User라는 테이블이 있어도 새로 만든다. 
  - drop -> create
  - 종류: update / none / create
    ```
    Hibernate: 
        
        drop table if exists User
    Hibernate: 
        
        create table User (
        id integer not null auto_increment,
            createDate datetime(6),
            email varchar(50) not null,
            password varchar(100) not null,
            role varchar(255) default 'user',
            username varchar(30) not null,
            primary key (id)
        ) engine=InnoDB
    ```

- show-sql: true  
true로 설정함으로써 위 코드가 console 창에 보인다.

- hibernate.format_sql: true
코드가 원래 console 창에 한 줄로 출력이 되는데, true로 설정함으로써 보기 좋게 정렬되어 나온다.

- naming:  
    physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

  - entity를 만들 때 변수명 그대로 데이터베이스 테이블에 필드를 만들어준다.  
  (myEmail -> myEmail)

  - org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy 전략을 쓰게 되면, 카멜 표기법으로 만들어준다.  
  (myEmail -> my_email)