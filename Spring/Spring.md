# 스프링이란


## 스프링

- 특정한 하나가 아니라 여러가지 기술들의 모음이라고 볼 수 있다.

- 필수  
  - **`스프링 프레임워크`**   
    스프링의 핵심이라고 할 수 있다. 가장 중요하다.
  - 스프링 부트  
    여러 스프링 기술들을 편리하게 사용할 수 있도록 도와준다.

- 선택  
  - 스프링 데이터  
    대표적으로 스프링 데이터 JPA가 있다.
  - 스프링 세션  
    세션 기능을 편리하게 사용할 수 있도록 도와준다.
  - 스프링 시큐리티  
    보안과 관련된 것
  - 스프링 Rest Docs  
    api 문서를 편리하게 해주는 것
  - 스프링 배치  
    천 만명의 데이터를 한 번에 업데이트 해야할 때 천 건씩 저장하는 것을 배치 처리 라고 하는데, 이와 관련된 기술.
  - 스프링 클라우드
    클라우드 기술에 특화된 기술.
<br>
<br>

## 스프링 프레임워크
- 핵심 기술: 스프링 DI 컨테이너, AOP, 이벤트, 기타
- 웹 기술: 스프링 MVC, 스프링 WebFlux
- 데이터 접근 기술: 트랜잭션, JDBC, ORM 지원, XML 지원
- 기술 통합: 캐시, 이메일, 원격접근, 스케쥴링
- 테스트: 스프링 기반 테스트 지원
- 언어: 코틀린, 그루비
- 최근에는 스프링 부트를 통해서 스프링 프레임워크의 기술들을 편리하게 사용
<br>
<br>


## 스프링 부트
- **`스프링을 편리하게 사용할 수 있도록 지원, 최근에는 기본으로 사용`**
- 단독으로 실행할 수 있는 스프링 애플리케이션을 쉽게 생성
- Tomcat 같은 웹 서버를 내장해서 별도의 웹 서버를 설치하지 않아도 됨
- 손쉬운 빌드 구성을 위한 starter 종속성 제공
-  스프링과 3rd parth(외부) 라이브러리 자동 구성
   - 스프링 부트가 외부 라이브러리들과 스프링 버전이 조합이 맞는지 테스트를 하고 버전까지 지정을 해서 다운로드 해주므로 외부 라이브러리 버전에 대해서 크게 고민하지 않아도 된다.
- 메트릭, 상태 확인, 외부 구성 같은 프로덕션 준비 기능 제공
-  관례에 의한 간결한 설정
<br>
<br>


## 스프링은 왜 만들었을까?
*이 기술을 왜 만들었는가?  
이 기술의 핵심 컨셉은?*
<br>

### 스프링의 핵심
- 스프링은 자바 언어 기반의 프레임워크
- 자바 언어의 가장 큰 특징: **`객체 지향 언어`**
- 스프링은 객체 지향 언어가 가진 강력한 특징을 살려내는 프레임워크
- 스프링은 좋은 객체 지향 애플리케이션을 개발할 수 있게 도와주는 프레임워크

- EJB를 사용 하던 시절, EJB를 상속받고 하면서 코드가 굉장히 지저분하고 EJB에 의존적으로 개발을 했어야 했다.  
객체지향이 가진 좋은 장점들을 잃어버리고 EJB에 종속돼서 개발을 할 수밖에 없는 문제가 생겨났고, 순수한 객체지향으로 돌아가자는 POJO가 나타나게 되었다. (Plain Old Java Object)