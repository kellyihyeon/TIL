# yml 설정
스프링은 blog 프로젝트가 시작되기 직전에 application.yml파일을 읽고 시작한다.

## server 설정

```yml
server:
  port: 8000
  servlet:
    context-path: /blog
    encoding:
      charset: UTF-8
      enabled: true
      force: true
```
- context-path: 내 프로젝트에 들어가기 위한 진입점을 의미한다.  

- 위 코드를 작성하지 않으면 기본적으로 port: 8080, context-path: / 로 설정된다.

- localhost:8080/http/test -> localhost:**`8000`**/**`blog`**/http/test
<br>


```bash
├── 📁resources
│   ├── 📁static
│   │   └── ⓐtest.jsp
│   └── 📁templates
│
``` 
- 스프링부트는 기본적으로 jsp를 지원 하지 않는다.
  - 컨트롤러에서 jsp파일을 return해줘도 정상적으로 동작하지 않는다.
  - jsp 파일을 동작시키려면 pom.xml 파일에 jsp 템플릿 엔진을 의존성 설정 해줘야 한다.
    ```xml
    <dependency>
        <groupId>org.apache.tomcat.embed</groupId>
        <artifactId>tomcat-embed-jasper</artifactId>
    </dependency>
    ```

- 기본 경로가 static이기 때문에 static에 jsp파일을 넣으면 제대로 인식 하지 않는다. static은 정적 파일들을 넣는 폴더이기 때문이다. (브라우저가 인식할 수 있는 파일)
  
- jsp는 동적인 파일, 컴파일이 일어나야 하는 파일이므로
브라우저가 인식을 하지 못하기 때문에 경로를 바꿔줘야 한다.

    ```bash
    ├── 📁resources
    │   ├── 📁static
    │   └── 📁templates
    └── 📁webapp
        └── 📁WEB-INF
            └── 📁views
                └── test.jsp
    ``` 
<br>