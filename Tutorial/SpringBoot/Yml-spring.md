# yml 설정
스프링은 blog 프로젝트가 시작되기 직전에 application.yml파일을 읽고 시작한다.

## spring 설정
```yml
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
```
- prefix: 컨트롤러가 리턴할 때 앞에 붙여주는 경로명
- suffix: 뒤에 붙여주는 경로명
- jsp파일을 요청하면 톰캣이 해당 파일을 컴파일해서 html파일로 던져준다.
<br>