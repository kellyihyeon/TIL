## 학습 키워드

- `프로그램 입출력` `메모리 맵 입출력` `고립형 입출력` `인터럽트 기반 입출력` `DMA 입출력` `입출력 버스`
- 장치 컨트롤러는 CPU와 어떻게 정보를 주고받을까?

---

# 프로그램 입출력(programmed I/O)

- 기본적으로 프로그램 속 명령어로 입출력장치를 제어하는 방법
- CPU가 명령어를 실행하는 과정에서 입출력 명령어를 만나면 CPU는 입출력장치에 연결된 장치 컨트롤러와 상호작용하며 입출력 작업을 수행함
- 프로그램 입출력 방식에서의 입출력 작업은 CPU가 장치 컨트롤러의 레지스터 값을 읽고 씀으로써 이루어짐

## 메모리에 저장된 정보를 하드 디스크에 백업한다면?

*== 하드 디스크에 새로운 정보를 쓴다.*

![](/CS/hongong/img/프로그램_입출력_1.png)

- CPU는 하드 디스크 컨트롤러의 **제어 레지스터**에 **쓰기** 명령을 보냄

![](/CS/hongong/img/프로그램_입출력_2.png)

- 하드 디스크 컨트롤러는 하드 디스크 상태를 확인
- 하드 디스크가 준비된 상태라면 하드 디스크 컨트롤러는 **상태 레지스터**에 준비되었다고 표시

![](/CS/hongong/img/프로그램_입출력_3.png)

- ⓵ CPU는 상태 레지스터를 주기적으로 읽으면서 하드 디스크의 준비 여부를 확인
- ⓶ 하드 디스크가 준비됐음을 CPU가 알게 되면 백업할 메모리의 정보를 데이터 레지스터에 씀

## CPU가 장치 컨트롤러의 레지스터에 접근하는 방식

- CPU 내부에 있는 레지스터들과 달리 CPU는 여러 장치 컨트롤러의 레지스터들을 모두 알고 있기는 어려움

### 메모리 맵 입출력(memory-mapped I/O)

- 메모리에 접근하기 위한 주소 공간과 입출력장치에 접근하기 위한 주소 공간을 하나의 주소 공간으로 간주하는 방법
- 가령 1024개의 주소를 표현할 수 있는 컴퓨터가 있을 때 512개는 메모리 주소, 512개는 장치 컨트롤러의 레지스터를 표현하기 위해 사용

![](/CS/hongong/img/메모리_맵_입출력.png)
- 주소 공간 예시
    - 516번지: 프린터 컨트롤러의 데이터 레지스터
    - 517번지: 프린터 컨트롤러의 상태 레지스터
    - 518번지: 하드 디스크 컨트롤러의 데이터 레지스터
    - 519번지: 하드 디스크 컨트롤러의 상태 레지스터

- CPU는 ‘517번지를 읽어 들여라’ 라는 명령어로 프린터 상태를 읽을 수 있음
- ‘518번지에 a를 써라’ 라는 명령어로 하드 디스크 컨트롤러의 데이터 레지스터로 데이터를 보낼 수 있음
- 메모리에 접근하는 명령어와 입출력장치에 접근하는 명령어는 다를 필요가 없음

### 고립형 입출력(isolated I/O)

- 메모리를 위한 주소 공간과 입출력장치를 위한 주소 공간을 분리하는 방법

![](/CS/hongong/img/고립형_입출력.png)

![](/CS/hongong/img/고립형_입출력_버스.png)

- 가령 1024개의 주소를 표현할 수 있는 컴퓨터가 있을 때 제어 버스에 ‘메모리 읽기/쓰기’ 선 이외에 ‘입출력장치 읽기/쓰기’ 선이 따로 있다면 메모리에도 1024개의 주소 공간을 활용, 입출력장치도 1024개의 주소 공간을 활용
- 메모리 읽기/쓰기 선이 활성화되는 명령어를 실행할 때는 메모리에 접근
- 입출력장치 읽기/쓰기 선이 활성화되는 명령어를 실행할 때는 장치 컨트롤러에 접근
- CPU는 입출력장치에 접근하기 위해서 메모리에 접근하는 명령어와는 다른 입출력 명령어를 사용

### 메모리 맵 입출력과 고립형 입출력 요약

|  | 메모리 맵 입출력 | 고립형 입출력 |
| --- | --- | --- |
| 주소 공간 | 메모리와 입출력장치는 같은 주소 공간 사용 | 메모리와 입출력장치는 분리된 주소 공간 사용 |
| 메모리 주소 공간 | 메모리 주소 공간이 축소됨 | 메모리 주소 공간이 축소되지 않음 |
| 명령어 | 메모리와 입출력장치에 같은 명령어 사용 가능 | 입출력 전용 명령어 사용 |

# 인터럽트 기반 입출력(Interrupt-Driven I/O)

- 인터럽트를 기반으로 하는 입출력을 인터럽트 기반 입출력이라고 함
    - ***인터럽트***에 대한 설명은 [여기](https://kellyihyeon.gitbook.io/today-i-learned/cs/hongong/chapter-04-3)를 클릭하시면 이동합니다.
- 입출력장치에 의한 하드웨어 인터럽트는 정확히 말하자면 장치 컨트롤러에 의해 발생함

![](/CS/hongong/img/인터럽트_기반_입출력.png)

- CPU는 장치 컨트롤러에 입출력 작업을 명령하고, 장치 컨트롤러가 입출력장치를 제어하며 입출력을 수행하는 동안 CPU는 다른 일을 할 수 있음
- 장치 컨트롤러가 입출력 작업을 끝낸 뒤 CPU에게 인터럽트 요청 신호를 보내면 CPU는 하던 일을 잠시 백업하고 인터럽트 서비스 루틴을 실행함

## 다중 인터럽트를 처리하는 방법

![](/CS/hongong/img/다중_인터럽트_처리_방법.png)

- ‘키보드로 글자를 입력하고 있고, 모니터는 실시간으로 입력한 글자들을 띄워 주고 있으며, 유튜브로 음악을 재생하고 있다. 그리고 유튜브에서 광고가 나오면 스킵하기 위해 마우스를 움직인다’는 가정을 해보자.

### 순서대로 처리

- 인터럽트가 발생한 순서대로 인터럽트를 처리하는 방법
- CPU가 플래그 레지스터 속 인터럽트 비트를 비활성화한 채 인터럽트를 처리하는 경우
- 인터럽트 A를 처리하는 도중 인터럽트 B의 요청이 있을 때, 인터럽트 A 서비스 루틴이 끝나면 인터럽트 B 서비스 루틴을 실행

![](/CS/hongong/img/다중인터럽트_순서대로_처리.png)

### 우선순위가 높은 순으로 처리

- CPU는 인터럽트 간에 우선순위를 고려하여 우선순위가 높은 인터럽트 순으로 여러 인터럽트를 처리

![](/CS/hongong/img/다중인터럽트_우선순위_순으로_처리.png)

- 플래그 레지스터 속 인터럽트 비트가 활성화되어 있는 경우 우선순위가 높은 인터럽트부터 처리
- 인터럽트 비트를 비활성화해도 무시할 수 없는 인터럽트인 **NMI**(Non-Maskable Interrupt)가 발생한 경우 우선순위가 높은 인터럽트부터 처리

## PIC(Programmable Interrupt Controller)

- PIC는 여러 장치 컨트롤러에 연결되어 장치 컨트롤러에서 보낸 하드웨어 인터럽트 요청들의 우선순위를 판별한 뒤 CPU에 지금 처리해야 할 하드웨어 인터럽트는 무엇인지 알려주는 장치이다.
- 많은 컴퓨터에서는 프로그래머블 인터럽트 컨트롤러라는 하드웨어를 사용함

![](/CS/hongong/img/PIC.png)

- PIC에 달려있는 각 핀에는 CPU에 하드웨어 인터럽트 요청을 보낼 수 있는 약속된 하드웨어가 연결되어 있다.
- PIC에 연결된 장치 컨트롤러들이 동시에 하드웨어 인터럽트 요청을 보내면 PIC는 우선순위를 판단하여 CPU에 가장 먼저 처리할 인터럽트를 알려준다.
- PIC는 무시할 수 없는 인터럽트인 NMI까지 우선순위를 판별하지는 않는다. NMI는 우선순위가 가장 높아서 우선순위 판별이 불필요하기 때문이다.
- PIC가 우선순위를 조정해 주는 인터럽트는 인터럽트 비트를 막을 수 있는 하드웨어 인터럽트이다.

### PIC의 다중 인터럽트 처리 과정

- ⓵ PIC가 장치 컨트롤러에서 **인터럽트 요청 신호**를 받아들임

- ⓶ PIC는 인터럽트 우선순위를 판단한 뒤 CPU에 처리해야 할 **인터럽트 요청 신호**를 보냄

- ⓷ CPU는 PIC에 **인터럽트 확인 신호**를 보냄

- ⓸ PIC는 데이터 버스를 통해 CPU에 **인터럽트 벡터**를 보냄

- ⓹ CPU는 인터럽트 벡터를 통해 인터럽트 요청의 주체를 알게 되고, 해당 장치의 **인터럽트 서비스 루틴**을 실행함

### PIC의 구성

![](/CS/hongong/img/PIC의_구성.png)

- 일반적으로 많고 복잡한 장치들의 인터럽트를 관리하기 위해 PIC를 두 개 이상 계층적으로 구성함

# DMA(Direct Memory Access) 입출력

## 프로그램 기반 입출력과 인터럽트 기반 입출력의 공통점

- 입출력장치와 메모리 간의 데이터 이동은 CPU가 주도하고, 이동하는 데이터도 반드시 CPU를 거친다는 점

![](/CS/hongong/img/CPU를_거치는_입출력1.png)

- 입출력장치 데이터를 메모리에 저장하는 경우
    - 1. CPU는 장치 컨트롤러에서 입출력장치 데이터를 하나씩 읽어 레지스터에 적재
    - 2. 적재한 데이터를 메모리에 저장

![](/CS/hongong/img/CPU를_거치는_입출력2.png)

- 메모리 속 데이터를 입출력 장치에 내보내는 경우
    - 1. CPU는 메모리에서 데이터를 하나씩 읽어 레지스터에 적재
    - 2. 적재한 데이터를 하나씩 입출력장치에 내보냄

## DMA 입출력의 등장 배경

- 입출력장치와 메모리 사이에 전송되는 모든 데이터가 반드시 CPU를 거쳐야 한다면 CPU는 입출력장치를 위한 연산 때문에 시간을 뺏김
- 그래서 입출력 장치와 메모리가 CPU를 거치지 않고도 상호작용할 수 있는 입출력 방식인 **DMA**가 등장

## DMA 입출력

- 직접 메모리에 접근할 수 있는 입출력 기능
- DMA 입출력을 하기 위해서는 시스템 버스에 연결된 **DMA 컨트롤러**라는 하드웨어가 필요함

![](/CS/hongong/img/DMA_컨트롤러.png)

## DMA 입출력 과정

```
1. CPU는 DMA 컨트롤러에 입출력장치의 주소, 수행할 연산(읽기/쓰기), 읽거나 쓸 메모리의 주소 등과 같은 정보로 입출력 작업을 명령
2. DMA 컨트롤러는 CPU 대신 장치 컨트롤러와 상호작용하며 입출력 작업을 수행. 이때 DMA 컨트롤러는 필요한 경우 메모리에 직접 접근하여 정보를 읽거나 씀
3. 입출력 작업이 끝나면 DMA 컨트롤러는 CPU에 인터럽트를 걸어 작업이 끝났음을 알림
```

- CPU는 오로지 입출력의 시작과 끝에만 관여

## DMA의 시스템 버스 이용

- DMA 컨트롤러는 시스템 버스로 메모리에 직접 접근은 가능하지만, 시스템 버스는 공용 자원이기에 동시 사용 이 불가능
- DMA 컨트롤러는 CPU가 시스템 버스를 이용하지 않을 때마다 시스템 버스를 이용하거나 CPU가 일시적으로 시스템 버스를 이용하지 않도록 허락을 구하고 시스템 버스를 집중적으로 이용

## 입출력 버스(input/output bus)

- CPU, 메모리, DMA 컨트롤러, 장치 컨트롤러가 모두 같은 버스를 공유하는 구성에서는 DMA가 메모리에 접근할 때마다 시스템 버스를 두 번 사용하게 되는 부작용이 있다.
- DMA 가 시스템 버스를 자주 사용하면 그만큼 CPU가 시스템 버스를 이용하지 못함
    
    ```
    1. 메모리에서 DMA 컨트롤러로 데이터를 가져오기 위해 시스템 버스 한 번 사용
    2. DMA 컨트롤러의 데이터를 장치 컨트롤러로 옮기기 위해 시스템 버스 한 번 사용
    ```
    
    ![](/CS/hongong/img/시스템_버스를_두번_사용.png)
    
- 이 문제는 DMA 컨트롤러와 장치 컨트롤러를 **입출력 버스**라는 별도의 버스에 연결하여 해결할 수 있음
- 장치 컨트롤러는 시스템 버스가 아닌 입출력 버스로 DMA 컨트롤러와 연결되므로 시스템 버스의 사용 빈도를 줄일 수 있음

![](/CS/hongong/img/입출력_버스.png)