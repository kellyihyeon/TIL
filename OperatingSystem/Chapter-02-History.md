# 역사


# os 발전
1. No o/s

2. Batch Processing (일괄처리)  
   - resident monitor

3. Multiprogramming System(다중 프로그래밍)
   - 컴퓨터는 비싼 자원
   - 빠른 CPU, 느린 i/o -> 메모리에 여러 개의 job이 있을 때 진행 과정
   - CPU scheduling, 메모리 관리, 보호
  
4. Time-Sharing System(TSS)
   - 강제 절환, interactive system(대화형)
   - 가상 메모리  
    hdd 일부를 메인 메모리인양 쓰는 것
   - 프로세스간 통신, 동기화

   - Unix(지금의 Linux)
   - 시간을 나눠서 일을 하게 하는 것.  
    동시에 여러 일이 일어나는 것처럼 보인다.  
    ex. 1초 동안 일을 분산 = TSS
   - 단일 CPU를 사용한 최신의 기술