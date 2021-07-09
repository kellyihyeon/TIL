# 배열

## 목차
1. []()
2. []()
3. []()
4. []()
5. []()
6. []()
<br>

# 1. 1차원 배열의 이해와 활용(1)
*배열은 동일한 자료형의 변수를 둘 이상 나란히 할당하는 것이다.*
## 1.1 1차원 배열의 이해와 선언 방법
- 1차원 배열이란?  
타입이 같은 둘 이상의 데이터를 저장할 수 있는 1차원 구조의 메모리 공간
  - 1차원 : 일렬로 나란히 할당된 메모리 공간

- 1차원 배열의 선언 방법  
int[] ref = new int[5];  
길이가 5인 int형 1차원 배열의 생성문
ref 라는 참조 변수가 배열을 가리키고 있다.  
  - = 을 기준으로 왼쪽은 참조변수 선언, 오른쪽은 배열 생성
<br>
<br>

## 1.2 배열 선언문에 대한 세세한 이해와 결과
![Declare Arrays](./img/Declare-Arrays.png)
- new 라는 키워드는 인스턴스 생성을 의미한다.  
 -> '자바에서는 배열도 사실은 인스턴스구나' 라고 짐작할 수 있다.
<br>
<br>

## 1.3 1차원 배열의 예
```java
public static void main(String[] args) {
    // 길이가 5인 int 형 1차원 배열의 생성
    int[] ar1 = new int[5];

    // 길이가 7인 double 형 1차원 배열의 생성
    double[] ar2 = new double[5];
    
    // 배열의 참조변수와 인스턴스 생성 분리
    float[] ar3;
    ar3 = new float[9];
    
    // 배열의 인스턴스 접근 변수
    System.out.println("배열 ar1 길이: " + ar1.length);
    System.out.println("배열 ar2 길이: " + ar2.length);
    System.out.println("배열 ar3 길이: " + ar3.length);
}
```
```bash
배열 ar1 길이: 5 
배열 ar2 길이: 7
배열 ar3 길이: 9
```
<br>
<br>

## 1.4 인스턴스 대상 1차원 배열의 예
```java
class Box {
    private String conts;

    Box(String cont) {
        this.conts = cont;
    }
    
    public String toString() {
        return conts;
    }
}

class ArrayIsInstance2 {
    public static void main(String[] args) {
        Box[] ar = new Box[5];  // 길이가 5인 Box 형 1차원 배열의 생성
        System.out.println("length = " + ar.length);    // length = 5
    }
}
```
<br>
<br>


# 2. 1차원 배열의 이해와 활용(2)
## 2.1 배열의 활용: 값의 저장과 참조
```java
int[] ar = new int[3];

ar[0] = 7;      // 값의 '저장': 첫 번째 요소
ar[1] = 8;      // 값의 '저장': 두 번째 요소
ar[2] = 9;      // 값의 '저장': 세 번째 요소

int num = ar[0] + ar[1] + ar[2];    // 값의 '참조'
```
<br>
<br>


## 2.2 값의 저장과 참조의 예
![Storage and Reference of Values](./Img/StorageAndReferenceOfValues-Arrays.png)
```java
class Box {
private String conts;

    Box(String cont) {
        this.conts = cont;
    }

    public String toString() {
        return conts;
}
```

```java
public static void main(String[] args) {
    Box[] ar = new Box[3];

    // 배열에 인스턴스 저장
    ar[0] = new Box("First");
    ar[1] = new Box("Second");
    ar[2] = new Box("Third");

    // 저장된 인스턴스의 참조
    System.out.println(ar[0]);
    System.out.println(ar[1]);
    System.out.println(ar[2]);
}
```
<br>
<br>


## 2.3 배열 기반 반복문 활용의 예
```java
public static void main(String[] args) {
    String[] sr = new String[7];
    sr[0] = new String("Java");
    sr[1] = new String("System");
    sr[2] = new String("Compiler");
    sr[3] = new String("Park");
    sr[4] = new String("Three");
    sr[5] = new String("Dinner");
    sr[6] = new String("Brunch Cafe");
    
    int cnum = 0;
    for (int i = 0; i < sr.length; i++) {
        cnum += sr[i].length();
    }

    System.out.println("총 문자의 수 = " + cnum);
}
```
- 배열 요소는 반복문을 통해 순차적 접근이 가능하며, 이것은 배열이 가진 큰 장점 중 하나이다.

- sr.length
배열의 인스턴스 변수 length에 접근한 것이다.

- sr[i].length()
String 인스턴스의 length() 메소드에 접근한 것이다.
<br>
<br>


# 3. 1차원 배열의 이해와 활용(3)
## 3.1 배열을 생성과 동시에 초기화 
```java
// 배열 생성
int[] arr = new int[3];

// 배열 생성 및 초기화 1
int[] arr = new int[]{1, 2, 3};

// 배열 생성 및 초기화 2
int[] arr = {1, 2, 3};
```
- int[] arr = new int[]{1, 2, 3};  
내가 정한 값으로 초기화를 하려면, 배열의 길이 정보를 컴파일러가 알아서 계산해서 넣어주도록 [] 안에는 길이 정보를 넣으면 안된다.
<br>
<br>


## 3.2 배열 대상 참조변수 선언의 두 가지 방법
```java
int[] arr = new int[3];     // 조금 더 선호하는 방법

int arr[] = new int[3];
```
- 둘 다 가능하지만 가급적이면 첫 번째 방법을 쓰도록 하자.
<br>
<br>


## 3.3 배열의 참조 값과 메소드
```java
public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7};
    int sum = sumOfAry(arr);    // 배열의 참조 값 전달
    ...
}

static int sumOfAry(int[] arr) {
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
        sum += arr[i];
    }
    return sum;
}

static int[] makeNewIntAry(int len) {
    int[] ar = new int[len];
    return ar;
}
```
- 매개 변수 선언 위치에도 참조 변수 선언을 할 수 있다.  
메소드의 전달 인자로 배열의 참조값이 전달된다는 의미이다.

- main 메소드 안에 있는 arr은 main 메소드에서만 유효한 arr이고, sumOfAry의 매개변수로 선언된 arr은 sumOfAry 메소드에서 유효한 arr이다.

- 배열의 참조 값도 반환이 가능하다.  
배열은 반환형 정보로도 명시할 수 있다.

- 참조 값을 가지고 있으면 누구나 배열에 접근이 가능하고, 참조 값은 주거니 받거니가 가능하다. 이것이 배열의 특성이다.
<br>
<br>


# 4. 1차원 배열의 이해와 활용(4)
## 4.1 배열의 디폴트 초기화
- 기본 자료형 배열은 모든 요소 **`0`** 으로 초기화  
int[] ar = new int[10];

- 인스턴스 배열(참조변수 배열)은 모든 요소 **`null`** 로 초기화  
String[] ar = new String[10];
<br>
<br>


## 4.2 배열의 초기화 메소드
```text
public static void fill(int[] a, int val)  
-> 두 번째 인자로 전달된 값으로 배열 초기화

public static void fill(int[] a, int fromIndex, int toIndex, int val)
-> 인덱스 fromIndex ~ (toIndex-1)의 범위까지 val의 값으로 배열 초기화
```

- java.util.Arrays 클래스에 정의되어 있는 메소드  
원하는 값으로 배열 전부 또는 일부를 채울 때 사용하는 메소드

- **`toIndex - 1`** 까지인 걸 잊지 말자.
<br>
<br>


## 4.3 배열 복사 메소드
```text
public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)

복사 원본의 위치: 배열 src의 인덱스 srcPos
복사 대상의 위치: 배열 dest의 인덱스 destPos
복사할 요소의 수: length
```

```text
src  = 1 2 3 4 5 6 7
dest = 0 0 2 3 4 0 0 
```
- src에 있는 것을 dest에 복사해라.
srcPos = 1, destPos = 2 -> src의 인덱스 1번 째부터 복사해서 값들을 dest의 2번째 인덱스에서부터 가져다 놓기 시작해라.
length = 3 -> 3개만 복사하여라.

- java.lang.System 클래스에 정의되어 있는 메소드, 한 배열에 저장된 값을 다른 배열에 복사할 때 사용하는 메소드
<br>
<br>


## 4.4 배열 초기화와 복사의 예
```java
import java.util.Arrays;


class ArrayUtils {
    public static void main(String[] args) {
        int[] ar1 = new int[10];
        int[] ar2 = new int[10];

        Arrays.fill(ar1, 7);    // 배열 ar1 을 7로 초기화
        System.arraycopy(ar1, 0, ar2, 3, 4);    // 배열 ar1을 ar2로 부분 복사

        for (int i = 0; i < ar1.length; i++) {
            System.out.println(ar1[i] + " ");
        }
        System.out.println();   // 단순 줄 바꿈

        for (int i = 0; i < ar2.length; i++) {
            System.out.println(ar2[i] + " ");
        }
    }
}
```

```bash
7 7 7 7 7 7 7 7 7 7
0 0 0 7 7 7 7 0 0 0 
```


## main 메소드의 매개변수 선언


## main의 매개변수로 인자를 전달하는 예




