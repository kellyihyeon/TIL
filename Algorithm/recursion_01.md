>📗인프런 - **`영리한 프로그래밍을 위한 알고리즘 강좌`** 를 공부하며 정리한 내용

<br>

# 1-1. Recursion의 개념과 기본 예제

## Recursion (순환)
- 자기 자신을 호출하는 함수
- 순환 혹은 재귀함수라고 부른다.

```java
public class Code01 {
    
    public static void main(String[] args){
        func();
    }


    public static void func(){
        System.out.println("Hello");
        func();
    }
}
```

- run: 무한루프에 빠짐
```java
Hello
Hello
Hello
Hello
...
```
<br>

## 1. recursion은 항상 무한루프에 빠질까?
```java
public class Code02 {

    public static void main(String[] args) {
        int n = 4;
        func(n);
    }

    public static void func(int k) {
        if (k <= 0) {
            return;
        } else {
            System.out.println("Hello");
            func(k - 1);
        }
    }
```
- 코드를 어떻게 작성하느냐에 따라 무한루프에 빠질 수도 빠지지 않을 수도 있다. 
- k가 0보다 작거나 같으면 **`return`**.
  - return 하게 되면 컨트롤은 항상 자기 자신을 호출했던 문장으로 넘어간다.  
  - else 문 안의 func(0);으로 넘어가고 종료-> func(1); 종료 -> func(2); 종료-> func(3); 종료 -> func(4); 종료 메인 메서드 func(4); }를 빠져나오며 메서드 종료. 
- run
```
Hello
Hello
Hello
Hello
```
<br>
<br>

## 2. 무한루프에 빠지지 않으려면?
```java
public static void func(int k) {
        if (k <= 0) {
            return;
        } else {
            System.out.println("Hello");
            func(k - 1);
        }
    }
```

### 2.1 Base case
```java
if (k <= 0)
    return;
```
적어도 하나의 recursion에 빠지지 않는 경우가 존재해야 한다.
<br>


### 2.2 Recursive case
```java
else 
    System.out.println("Hello");
    func(k - 1);    
```
- recursion을 반복하다보면 결국 base case로 수렴해야 한다.
- 단순히 base case가 존재한다는 것만으로 무한루프에 빠지지 않는 것은 아니다.  
base case가 있어도 `func(k + 1)`인 경우에는 다시 무한루프에 빠지게 된다.
<br>
<br>


## 3. 1 ~ n까지의 합
```java
public class Code03 {

    public static void main(String[] args) {
        int result = func(4);
        System.out.println("result = " + result);
    }

    public static int func(int n) {
        if (n == 0) {
            return 0;
        } else {
            return n + func(n - 1);
        }
    }
}
```
- run:  
 `result = 10`
- func(4):  
  return 4 + func(3);
    - func(3):  
  return 3 + func(2);
        - func(2):  
  return 2 + func(1);
            - func(1):  
  return 1 + func(0);  
                - func(0):  
                    return 0;


### 3.1 recursion의 해석
```java
public static int func(int n) {     //1
        if (n == 0) {
            return 0;               //2
        } else {
            return n + func(n - 1); //3
        }
}
```
- 1번: 이 함수의 mission은 0 ~ n까지의 합을 구하는 것이다.
- 2번: n = 0이라면 합은 0이다.
- 3번: n이 0보다 크다면 0에서 n까지의 합은 0에서 n-1까지의 합에 n을 더한 것이다.
<br>
<br>


## 4. Factorial: n!
```
0! = 1
n! = n x (n-1)!     * n > 0
```
0!은 0이 아니고 1이다. (Factorial의 정의)

```java
public static int factorial(int n) {
    if (n == 0) {
        return 1;
    } else {
        return n * factorial(n - 1);
    }
}
```
<br>
<br>


## 5. X<sup>n</sup>

> if n > 0  
    X<sup>0</sup> = 1  
    X<sup>n</sup> = X * X<sup>n-1</sup>   

```java
public static double power(double x, int n) {
    if (n == 0) {
        return 1;
    } else {
        return x * power(x, n - 1);
    }
}
```
<br>
<br>


## 6. Fibonacci Number
>조건: n > 1  
    f<sub>0</sub> = 0  
    f<sub>1</sub> = 1  
    f<sub>n</sub> = f<sub>n-1</sub> + f<sub>n-2</sub>
- 0번째 피보나치 수 = 0  
- 1번째 피보나치 수 = 1
- n번째 피보나치 수   
    = n-1번째 피보나치 수 + n-2번째 피보나치 수


```java
public int fibonacci(int n) {
    if (n < 2) {
        return n;
    } else {
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
```
<br>
<br>


## 7. 최대 공약수: Euclid Method

### 7.1 Euclid Method 
```java
public static double gcd(int m, int n) {
    if (m < n) {
        int tmp = m;
        m = n;
        n = tmp;    //swap m and n
    }
    
    // 12, 8
    if (m % n == 0) {
        return n;
    } else {
        return gcd(n, m%n);   //8, 4
    }
}
```
m >= 인 두 양의 정수 m과 n에 대해서 m이 n의 배수이면 gcd(m, n) = n이고, 그렇지 않으면 gcd(m, n) = gcd(n, m%n)이다.
<br>


### 7.2 Euclid Method: 좀 더 단순한 버전

```java
public static int gcdSimple(int p, int q) {
    // 12, 8
    if (q == 0) {
        return p;
    } else {
        return gcdSimple(q, p%q);   //8, 4
    }
}
```
<br>
<br>

     