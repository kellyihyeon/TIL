## 학습 키워드

- `물리 주소` `논리 주소` `MMU` `베이스 레지스터` `한계 레지스터`
- 두 종료의 주소로 나뉘게 된 배경
- 논리 주소를 물리 주소로 변환하는 방법

---

# 물리 주소와 논리 주소

예를 들어 현재 메모리에 메모장, 게임, 인터넷 브라우저 프로그램이 적재되어 있다고 가정한다.
메모장, 게임, 인터넷 브라우저 프로그램은 현재 다른 프로그램들의 물리 주소가 무엇인지 굳이 알 필요가 없다. 새로운 프로그램이 언제든 적재될 수 있고, 실행되지 않는 프로그램은 언제든 메모리에서 사라질 수있기 때문이다.

## 물리 주소(physical address)

- 메모리 하드웨어가 사용하는 주소

## 논리 주소(logical address)

- CPU와 실행 중인 프로그램이 사용하는 주소
- 실행 중인 프로그램 각각에게 부여된 0번지부터 시작되는 주소
- 프로그램마다 같은 논리 주소가 얼마든지 있을 수 있고 CPU는 이 논리 주소를 받아들이고 해석하고 연산함
    - ‘10번지’라는 주소는 메모장, 게임, 인터넷 브라우저에도 논리주소로써 존재할 수 있음

## 요약
![](/CS/hongong/img/물리주소와_논리주소.png)

- **메모리**가 사용하는 주소는 하드웨어상의 주소인 **물리 주소**
- CPU와 실행 중인 프로그램이 사용하는 주소는 각각의 프로그램에 부여된 **논리 주소**

## 논리 주소와 물리 주소 간의 변환

- 논리 주소와 물리 주소 간에 어떠한 변환도 이루어지지 않는다면 CPU와 메모리는 서로 이해할 수 없는 주소 체계를 가지고 있기 때문에 상호작용할 수없음
- CPU가 인터넷 브라우저의 0번지에 ‘A’ 저장, 게임의 0번지 데이터 삭제 명령을 실행한다면? 메모리 입장에서는 0번지는 하나밖에 없기 때문에 이해할 수 없음
- 논리 주소와 물리 주소 간의 변환은 CPU와 주소 버스 사이에 위치한 **메모리 관리 장치**(MMU; Memory Management Unit)라는 하드웨어에 의해 수행
    - [CPU와 주소 버스 그림 참고 링크](https://kellyihyeon.gitbook.io/today-i-learned/cs/hongong/chapter-04-2)로 이동
    
    ![](/CS/hongong/img/MMU.png)
    
- MMU는 CPU가 발생시킨 **논리 주소**에 **베이스 레지스터 값**을 더하여 논리 주소를 물리 주소로 변환
- **베이스 레지스터**는 프로그램의 가장 작은 물리 주소, 즉 프로그램의 첫 물리 주소를 저장
- **논리 주소**는 프로그램의 시작점으로부터 떨어진 거리

# 메모리 보호 기법

![](/CS/hongong/img/잘못된_명령어.png)

- 프로그램의 논리 주소 영역을 벗어났기 때문에 위와 같은 명령어는 실행되어서는 안됨

![](/CS/hongong/img/잘못된_명령어_실행.png)

- 메모장 프로그램 명령어는 인터넷 브라우저 프로그램에 숫자 100을 저장하게 됨
- 논리 주소 범위를 벗어나는 명령어 실행을 방지하고 실행 중인 프로그램이 다른 프로그램에 영향을 받지 않도록 보호할 방법이 필요함

## 한계 레지스터(limit register)

- 논리 주소의 최대 크기를 저장함
- 프로그램의 물리주소 범위
    - 베이스 레지스터 값 이상, 베이스 레지스터 값 + 한계 레지스터 값 미만

![](/CS/hongong/img/한계_레지스터.png)

- CPU가 접근하려는 논리 주소는 한계 레지스터가 저장한 값보다 커서는 안 됨
- 한계 레지스터보다 높은 주소 값에 접근하는 것은 곧 프로그램의 범위에 벗어난 메모리 공간에 접근하는 것과 같음

## CPU가 한계 레지스터보다 높은 논리 주소에 접근한다면?

![](/CS/hongong/img/한계_레지스터_체크.png)

- CPU는 메모리에 접근하기 전에 접근하고자 하는 논리 주소가 한계 레지스터보다 작은지를 항상 검사함
- CPU가 한계 레지스터보다 높은 논리 주소에 접근하려고 하면 인터럽트(트랩)를 발생시켜 실행을 중단함