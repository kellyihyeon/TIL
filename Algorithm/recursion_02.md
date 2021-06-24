>📗인프런 - **`영리한 프로그래밍을 위한 알고리즘 강좌`** 를 공부하며 정리한 내용

<br>

# 1-2. Recursion의 개념과 기본 예제

#### Recursive Thinking <i>순환적으로 사고하기</i>
___

## 1. Recursion은 수학 함수 계산에만 유용한가?

수학 함수뿐 아니라 다른 많은 문제들을 recursion으로 해결할 수 있다.
<br>
<br>

## 2. 문자열의 길이 계산
```
       이 문자열의 길이는  
┌───────────────────────────┐
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
  └─────────────────────────┘
   이 문자열의 길이 +1 이다.
```

- 맨 앞에 문자 한 칸을 떼고, 이에 나머지를 더한 값.

java
```java
public static int length(String str) {
        if (str.equals("")) {
            return 0;
        } else {
            return 1 + length(str.substring(1));
        }
    }
```
- **`substring(1)`**: 첫 글자 한 자를 제거한 나머지 문자열
- length("dev")
  - return 1 + **<u>length("ev")</u>**;
      - return 1 + **<u>length("v")</u>**;
          - return 1 + **<u>length("")</u>**;
            - return 0;
          - return 1;
      - return 2;
  - return 3;
<br>
<br>

## 3. 문자열의 프린트
```java
public static void printChars(String str) {
    if (str.length() == 0) {
        return;
    } else {
        System.out.println("str.charAt(0)" + str.charAt(0));
        printChars(str.substring(1));
    }
}
```
- charAt(0): 첫 글자를 return 해주는 메서드.
- 문자열의 첫 글자를 출력하고, printChars(str.substring(1)); 메서드를 실행(recursion). 문자열의 길이가 0이 될 때까지.
<br>
<br>

## 4. 문자열을 뒤집어 프린트
```
   (1)이 문자열을 뒤집어 프린트하려면
┌───────────────────────────────────┐
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
│ └─────────────────────────────────┘
│   (2)이 문자열을 뒤집어 프린트 한 후
└ (3) 마지막으로 첫 글자를 프린트 한다.
```

java
```java
public static void printCharsReverse(String str) {
    if (str.length() == 0) {
        return;
    } else {
        printCharsReverse(str.substring(1));
        System.out.print("str.charAt(0) = " + str.charAt(0));
    }
}
```
- 문자열은 "dev"라 가정하고, 이 문자열을 뒤집어 "ved"라고 출력해야 한다.
- ① printCharsReverse("dev")
  - ② printCharsReverse("ev")
    - ③ printCharsReverse("v")
      - ④ printCharsReverse("")
        - **`return; -> ③ printCharsReverse("v")`**
        - return 하게 되면 컨트롤은 항상 자기 자신을 호출했던 문장으로 넘어간다. 
    - ③ printCharsReverse("v"); -> 출력: v 
  - ② printCharsReverse("ev"); -> 출력: e
- ① printCharsReverse("dev"); -> 출력: d
- 문자열을 순서대로 출력하는 코드와 비교해보면, else문 안의 순서만 바뀌었다.
<br>
<br>

## 5. 2진수로 변환하여 출력
음이 아닌 정수 n을 이진수로 변환하여 인쇄한다.

```java
public void printInBinary(int n) {
    if (n < 2) {
        System.out.print(n);
    } else {
        // n을 2로 나눈 몫을 먼저 2진수로 변환하여 인쇄한 후
        printInBinary(n / 2);   
        // n을 2로 나눈 나머지를 인쇄한다.
        System.out.print(n % 2);
    }
}
```
- printInBinary(7)
  - printInBinary(3)
    - printInBinary(1)  -> 1
    - 1
  - printInBinary(3) -> 1 1
- 1
- 결과: 111
<br>
<br>

## 6. 배열의 합 구하기
data[0]에서 data[n-1]까지의 합을 구하여 반환한다.

```java
public static int sum(int n, int[] data) {
    if (n <= 0) {
        return 0;
    } else {
        return sum(n - 1, data) + data[n - 1];
    }
}
```
- int n = 4, int[] = {5,3,7,6,2,8} 이라고 가정.
- sum(4)
  - ① return **`sum(3)`** + data[3];
    - ② return **`sum(2)`** + data[2];
      - ③ return **`sum(1)`** + data[1];
        - ④ return **`sum(0)`** + data[0];
          - ⑤ return 0;
        - ④ return 0 + 5; -> 5
      - ③ return 5 + 3; -> 8
    - ② return 8 + 7; -> 15
  - ① return 15 + 6 -> 21
- sum(4) = 21;
<br>
<br>

## 7. 데이터파일로부터 n개의 정수 읽어오기
Scanner in이 참조하는 파일로부터 n개의 정수를 입력받아 배열 data의 data[0], ... ,data[n-1]에 저장한다.

```java
public void readFrom(int n, int[] data, Scanner in) {
    if (n == 0) {
        return;
    } else {
        readFrom(n-1, data, in);
        data[n - 1] = in.nextInt();
    }
}
```
- 사용자에게 n을 입력받고, n을 배열 data에 차례대로 저장한다.
- 배열의 합 구하는 코드 풀어낸 것처럼 하면 된다.
- `readFrom(4)`
  - ① `readFrom(3)`
    - ② `readFrom(2)`
      - ③ `readFrom(1)`
        - ④ `readFrom(0)`
            - return;
      - ③ readFrom(1): data[0] = in.nextInt();
    - ② readFrom(2): data[1] = in.nextInt();
  - ① readFrom(3): data[2] = in.nextInt();
- readFrom(4): data[3] = in.nextInt();
<br>
<br>

## 8. Recursion vs Iteration
- 모든 순환함수는 반복문(iteration)으로 변경 가능
- 그 역도 성립함. 즉 **모든 반복문은 recursion으로 표현 가능함**
- 순환함수는 복잡한 알고리즘을 단순하고 알기쉽게 표현하는 것을 가능하게 함
- 하지만 함수 호출에 따른 오버헤드가 있음 (매개 변수 전달, 액티베이션 프레임 생성 등)