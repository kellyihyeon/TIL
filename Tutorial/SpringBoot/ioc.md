# IoC(Inversion of Controll)는 무엇인가?
스프링은 **`IoC (제어의 역전)`** 를 한다.

제어의 역전?  

```java
Class Test{
    void hello(){
        A a = new A();
    }
}
```
- 너(you)가 new 해서 띄우지마. 내가(spring) new해서 메모리에 띄울게.  

- 목적: 싱글톤 패턴 사용 + 레퍼런스 변수를 스프링이 관리.

메모리
> new -> `heap`이라는 공간을 만들고 그 공간을 a가 가리킨다.   
여기서 a는 hello() 메서드 내부에 선언한 지역변수이다. 지역변수는 메서드 호출 시에 메모리에 뜨고, 호출이 종료될 때 ( **}** ) 메모리에서 사라진다.
어떤 변수를 선언하든 hello()를 호출하지 않는 이상 메모리에 뜨지 않는다.
<br>
<br>


# 컴포넌트 스캔

어떤 패키지가 있을 때 그 패키지 이하를 전부 스캔해서 필요한 것들을 메모리에 로드한다. (= IoC. 싱글톤 패턴으로 관리.)  

스프링 컨테이너에서 모든 객체에 대한 변수들을 관리한다.

## 패키지 스캔을 할 때 그 기준은?
```bash
├── 📁java
│   ├── 📦com.dev.blog
│   │   └── 📦test
│   │   │   └── BlogControllerTest.java
│   │   ├── BlogApplication.java
│   └── 📦com.dev.test
└── 📁resources
``` 
- com.dev.blog 패키지 내부에서 사용할 목적으로 BlogControllerTest를 만들면 스프링은 com.dev.blog 패키지 이 이하만 스캔한다.
  
- com.dev.`blog` 패키지 내부에서 사용할 목적인데 com.dev.`test` 패키지 안에 자바 파일을 만들면 스프링은 내가 사용하고자 하는 객체들을 스캔하지 않을 것이고, 스캔을 하지 않으면 메모리에 뜨지 않는다.
  
- 스프링이 com.dev.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 new 하는 것은 아니고 특정 어노테이션이 붙어있는 클래스 파일들을 new 해서(IoC) 스프링 컨테이너에 관리해준다.