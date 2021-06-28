# 설정파일 확장자
*파일 포맷 차이*

## yml
```java
spring.datasource.data-username=user
spring.datasource.data-password=user1234
```
- 위와 같이 **`.`** 으로 연결해서 코드를 작성한다.
- 중복되는 코드가 많아진다.  

## properties
```
spring:
  datasource:
    data:
        username: user
        password: user1234
```
- 중복되는 코드가 적고, 들여쓰기를 해서 코드를 작성하기 때문에 한 눈에 볼 수 있다.