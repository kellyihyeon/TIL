>📗인프런 - **`영리한 프로그래밍을 위한 알고리즘 강좌`** 를 공부하며 정리한 내용

<br>

# 1-3. Recursion의 개념과 기본 예제
#### Designing Recursion <i>순환 알고리즘의 설계</i>
<br>

### 요약
```
[순환적 알고리즘 설계]
① 적어도 하나의 base case, 즉 순환되지 않고 종료되는 case가 있어야 함.
② 모든 case는 결국 base case로 수렴해야 함.
``` 
<br>

암시적(implicit) 매개변수를 **`명시적(explicit) 매개변수`**로 바꾸어라.
<br>
<br>

## 1. 순차 탐색
```java
int search(int[] data, int n, int target) {
    for (int i = 0; i < n; i++) {
        if (data[i] == target) {
            return i;
        }
    }
    return -1;
}
```
- 이 함수의 미션은 data[0]에서 data[n-1] 사이에서 target을 검색하는 것이다.  
하지만 검색 구간의 시작 인덱스 0은 보통 생략한다. 즉 <u>**`암시적 매개변수`**</u>이다.
  - n-1은 명시적으로 표현해놨지만, 0은 암시된 것.  
    (0부터 시작할 것이라고 암묵적으로 동의)


- 순서에 관련해서 특정 조건이 주어지지 않는다면, 유일한 방법은 하나씩 순서대로 보는 것이다.
  - 순서가 정렬되어 있다면? 이진 검색 사용.
- 순차적으로 검사해서 타겟을 찾고, 끝까지 검사했으나 타겟이 없다면 return -1;
<br>
<br>

## 2. 매개변수의 명시화: 순차 탐색
```java
int search(int[] data, int begin, int end, int target) {

    if (begin > end) {
        return -1;
    } else if (target == data[begin]) {
        return begin;
    } else {
        return search(data, begin+1, end, target);
    }
}
```
- 이 함수의 미션은 data[begin]에서 data[end] 사이에서 target을 검색한다. 즉, 검색구간의 시작점을 명시적(explicit)으로 지정한다.
- 이 함수를 search(data, 0, n-1, target)으로 호출한다면 앞 페이지의 함수와 완전히 동일한 일을 한다.
<br>
<br>

### 2.1 순차 탐색: 다른 버전 (1)
```java
int searchReverse(int[] data, int begin, int end, int target) {

    if (begin > end) {
        return -1;
    } else if (target == data[end]) {
        return end;
    } else {
        return search(data, begin, end-1, target);
    }
}
```
- 내가 찾는 값과 마지막 값을 비교해서 찾는다. (뒤에서부터 비교)
<br>
<br>

### 2.2 순차 탐색: 다른 버전 (2)
```java
// ? debugging 필수.
static int searchUsingMid(int[] data, int begin, int end, int target) {

    if (begin > end) {
        return -1;
    } else {
        int middle = (begin+end)/2;
        if (data[middle] == target) {
            return middle;
        }
        int index = searchUsingMid(data, begin, middle - 1, target);
        if (index != -1) {
            return index;
        } else {
            return searchUsingMid(data, middle + 1, end, target);
        }
    }//else
}
```
- binary search와는 다르다.
- begin과 end로 검색지점을 설정하고, middle을 설정한 후 앞쪽에서 검사해보고 앞쪽에서 target을 발견하지 못했다면 middle 뒷쪽을 검사한다. 
- // ? int index 가 가리키는 지점, return -1 되는 지점이 엄청 헷갈린다.
<br>
<br>


## 3. 매개변수의 명시화: 최대값 찾기
```java
int findMax(int[] data, int begin, int end) {
    if (begin == end) {
        return data[begin];
    } else {
        return Math.max(data[begin], findMax(data, begin + 1, end));
    }
}
```
- 이 함수의 미션은 data[begin]에서 data[end] 사이의 최대값을 찾아 반환한다.  
(begin <= end라고 가정한다.)

- int[] data = {8, 5, 17, 9, 58};
- findMax(1,4)
  - return max(data[1], **`findMax(2,4)`**)
      - return max(data[2], **`findMax(3,4)`**)
        - return max(data[3], **`findMax(4,4)`**)
          - return data[4]
        - return max(data[3], **data[4]**) -> data[4]
      - return max(data[2], **data[4]**) -> data[4]
  - return max(data[1], **data[4]**) -> data[4]
- 결과: data[4]
- 데이터 검색 구간이 자꾸 변하는 경우 명시적으로 표현하는 것이 좋다.
<br>
<br>


### 3.1 최대값 찾기: 다른 버전
```java
static int findMaxUsingMid(int[] data, int begin, int end) {
    if (begin == end) {
        return data[begin];
    } else {
        int middle = (begin+end)/2;
        int max1 = findMaxUsingMid(data, begin, middle);
        int max2 = findMaxUsingMid(data, middle + 1, end);
        return Math.max(max1, max2);
    }
}
```
- begin에서 end까지 중에서 middle을 기준으로 앞쪽에서 최대값 하나(max1), 뒷쪽에서 최대값 하나(max2)를 찾은 후 max1과 max2를 비교한다. 
- // ? max1 은 구했는데, max2 부분 매개변수를 어떤 걸 들고 들어가야하는지, return 했을 때 어디로 돌아가야 하는지 디버깅해도 모르겠어서 넘어간다.
<br>
<br>



## 4. Binary Search
```java
public static int binarySearch(String[] items, String target, int begin, int end) {
    if (begin > end) {
        return -1;
    } else {
        int middle = (begin + end) /2;
        int compResult = target.compareTo(items[middle]);
        if (compResult == 0) {
            return middle;
        } else if (compResult < 0) {
            return binarySearch(items, target, begin, middle - 1);
        } else {
            return binarySearch(items, target, middle + 1, end);
        }
    }
}
```
