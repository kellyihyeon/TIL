# 운영체제 서비스

## 목차
0. [들어가며](#0-들어가며)
1. [프로세스 관리(Process management)](#1-프로세스-관리process-management)
2. [주기억장치 관리(Main memory management)](#2-주기억장치-관리main-memory-management)
3. [파일 관리(File management)](#3-파일-관리file-management)
4. [보조기억장치 관리(Secondary storage management) ](#4-보조기억장치-관리secondary-storage-management)
5. [입출력 장치 관리(I/O device management)](#5-입출력-장치-관리io-device-management)
6. [시스템 콜(System calls)](#6-시스템-콜system-calls)
<br>
<br>

# 0. 들어가며
운영체제가 하는 일은 무엇인가?   
마치 정부가 하는 일과 같다고 설명했다.

```text
App: CPU, memory, 하드디스크, 키보드... (자원 사용)
────────────────────────────────────────────────
OS: 자원을 효율적으로 나누어줌
────────────────────────────────────────────────
HW: CPU, memory, 하드디스크, 키보드...
```
OS는 아래와 같이 하는 일에 따라 부서가 나누어져있다.
```text
CPU 를 나눠주는 부서      : 프로세스 management
주기억장치를 나눠주는 부서 : 메인메모리 management
파일과 관련한 부서        : file management
입출력 장치를 관리하는 부서: I/O management
Network 부서
Protection 부서
```  
이제 각 management 를 알아보자.
<br>
<br>


# 1. 프로세스 관리(Process management)
- 사실 컴퓨터는 프로그램과 프로세스의 개념을 구별한다.
  - 프로세스  
    실제로 메인 메모리에서 실행 중인 프로그램 (program in execution)  
    하드디스크에 있는 프로그램이 메인 메모리에 올라가면 프로세스이다.

- 주요 기능
  - 프로세스의 생성, 소멸(creation, deletion)
  - 프로세스 활동 일시 중지, 활동 재개(suspend, resume)
  - 프로세스 간 통신(interprocess communication: IPC)
  - 프로세스간 동기화(synchronization)
  - 교착상태 처리 (deadlock handling)
<br>
<br>

# 2. 주기억장치 관리(Main memory management)
- 주요기능
  - 프로세스에게 메모리 공간 할당(allocation)
  - 메모리의 어느 부분이 어느 프로세스에게 할당되었는지 추적 및 감시
  - 프로세스 종료 및 메모리 회수(deallocation)
  - 메모리의 효과적 사용
  - 가상 메모리: 물리적 실제 메모리보다 큰 용량을 갖도록 함
<br>
<br>

# 3. 파일 관리(File management)
- 물리적으로는 Track/sector 인데 우리는 컴퓨터를 하면서 이런 개념을 사용하지 않고 논리적 관점으로 file 을 사용한다.
- 주요기능
  - 파일의 생성과 삭제(file creation & deletion)
  - 디렉토리(directory)의 생성과 삭제(또는 folder)
    - directory:  windows 에서 folder 라고 하는 것.   
    컴퓨터 용어로는 디렉토리이다.
  - 기본 동작 지원: open, close, read, write, create, delete
  - Track/sector - file 간의 매핑(mapping)
  - 백업(backup)
<br>
<br>

# 4. 보조기억장치 관리(Secondary storage management)
- 하드 디스크, 플래시 메모리 등
- 주요 기능
  - 빈 공간 관리(free space management)
    -  sector 들을 모은 것이 block 이고 block에 저장을 하게 된다. 컴퓨터를 사용하다보면 비어져있는 공간과 사용중인 공간으로 나뉘게 되는데 이 비어져있는 공간을 관리한다.
  - 저장공간 할당(storage allocation)
    - 비어있는 공간 중에 어느 곳을 할당할 것인가
  - 디스크 스케쥴링(disk scheduling)
<br>
<br>

# 5. 입출력 장치 관리(I/O device management)
- 주요 기능
  - 장치 드라이브(Device drivers)
  - 입출력 장치의 성능향상: buffering, caching, spooling
  - buffering:   
  입출력 장치에서 읽은 내용을 일단 메모리로 들고 오는 것이다. 한 번 메모리로 들고 오면 그 다음에는 빨리 읽을 수 있다.
  - spooling:   
  메모리대신 하드디스크를 중간 매체로 사용하는 것이다.   
  예를들어서 프린트로 출력할 때 프린트가 글자를 다 찍을 동안 CPU가 이를 기다리면 너무 늦기 때문에 일단 프린트보다는 빠르고 CPU 보다는 느린 디스크에 저장한다. 그리고 디스크에 있는 내용을 천천히 프린트에 보내면 CPU는 그동안 다른 작업을 할 수 있다.
<br>
<br>

# 6. 시스템 콜(System calls)
- 운영체제 서비스를 받기 위한 호출

- 일반 application 프로세스가 OS 서비스를 받기 위해 호출하는데 이를 시스템 콜이라 한다.

- 이 프로세스들은 OS 의 도움이 필요하다.  
```text
UserA: 파일을 하나 달라. 
UserB: 프린트를 해달라.
```

- 주요 시스템 콜
  - Process: end, abort, load, execute, create...
  - Memory: allocate, free
    - allocate:  
    자바에서 객체 생성할 때 (new Board();) 메모리가 필요한데 이때 OS한테 "hey~ 메모리 좀 줘."라고 콜 하는 것.
  - File: create, delete, open, close...
  - Device: request, release, read, write...
  - Information: get/set time, get/set system data
  - Communication: socket, send, receive   

- 시스템 콜 만드는 방법  
특정 레지스터에 특정 값을 담고, 소프트웨어 인터럽트를 건다. 