# **Let's Start Java**

# 1. 자바의 세계

## 1.1 JDK 
*Java Development Kit* 
자바 프로그램을 개발하는 데 키트.

## 1.2. 환경 변수 Path
### 1.2.1 자바의 기본 도구  
*도구 = 소프트 웨어라고 생각하자.*

```
javac.exe
 자바 컴파일러(Java Compiler)
java.exe 
 자바 런처(Java Launcher)
```

- cmd에서 `javac.exe`, `java.exe` 를 실행하기 위해서는 `환경 변수 Path` 설정을 해줘야 한다.  
cmd에 javac.exe 라고 입력을 하게되면 cmd는 javac.exe를 찾게 되는데 이 때 필요한 것이 환경 변수이기 때문이다.
  -  cmd (명령프롬프트) - 프로그램을 찾아서 대신 실행시켜주는 인터프리터.
  -  환경변수 Path - 문자열 정보.

- Path(경로)에 javac와 java가 있는 위치를 등록해 놓는다.  
cmd가 javac.exe와 java.exe를 찾을 때 Path가 알려주는 경로에 가서 이를 찾고 실행시키기 때문이다.
  
```
C:\Program Files (x86)\Java
``` 
![경로](/Img/JavaBin.png)

- 다운로드 받을 때 특정 위치를 선택하지 않았다면 위 경로를 따라가면 Java 폴더 안에 jdk1.8.0_271 폴더가 있고, jdk1.8.0_271 안에 javac.exe, java.exe 가 있는 걸 확인할 수 있다.

- Path에 경로 정보를 잘못 지정했다면 cmd는 찾지 못할 것이다. 우리가 흔히 저지르는 실수가 이런 것이다. (대부분 Path 설정을 잘못한 경우)

### 1.2.2 환경 변수 설정

![경로](/Img/JavaBin.png)
- javac.exe와 java.exe가 있는 bin까지의 경로를 Path에 지정해 놓아야한다. 

- 사용자 변수:  
  개인 사용자에 대한 환경변수 설정
- 시스템 변수:  
  사용자에 상관없이 전체 시스템에 대한 환경변수 설정

- Path라는 변수를 추가해보자.  
  - ![Path설정](/Img/Path.png)
  - 변수 이름: Path
  - 값: bin이 있는 위치

- cmd에서 java, javac를 입력하면 많은 코드들이 창에 나타날텐데 이는 자바가 실행됐다는 걸 의미한다. (cmd가 찾아다 준 결과)


## 1.3 실습
*JavaStudy라는 폴더 안에 아래 파일을 만들고 저장*

```java
class FirstJavaProgram
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to Java");
        System.out.println("First Java program");
    }
}
```

- C:\JavaStudy>javac FirstJavaProgram.java를 실행하면 **`javac`** 가 실행이 되면서 컴파일 하는 과정을 거치고 그 결과 아무것도 뜨지 않는다. (무언가 뜬다면 문제가 생긴 것)
    - 'FirstJavaProgram.java'라는 파일을 컴파일 하라는 의미한다.
    - 이 파일을 자바 가상머신에 의해서 실행될 수 있는 형태로 바꾸라는 정보를 전달 한 것이다.  

- JavaStudy 에 들어가보면 클래스 파일이 하나 만들어져있을 것이다.(`.class`)  
= 컴파일이 제대로 된 것.

- 여기까지 진행이 되면 실행을 할 수가 있는데, java.exe로 실행 한다.
  
- C:\JavaStudy>java FirstJavaProgram  
  - FirstJavaProgram 이라는 클래스파일을 실행하라.
  - FirstJavaProgram.class 파일을 찾아서 실행을 하게 된다.   
    (명령어를 입력할 때는 .class가 아닌 FirstJavaProgram 라고 입력)