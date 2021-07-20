# README TIL: copy

## 1. 도형
[a] 한 개짜리 도형
```
┌────┐  
│ A  │ 
└────┘ 
```
<br>

[b] 두 개짜리 도형 (단반향)
```   
클라이언트                      서버
┌────┐                       ┌────┐
│ A  │ ────────post────────> │  B │
└────┘                       └────┘
```

[c] 두 개짜리 도형 (양방향)
```   
클라이언트                    서버
┌────┐ ───────────────────> ┌────┐
│ A  │                      │  B │
└────┘ <─────────────────── └────┘
```

[d] 두 개짜리 도형 (양방향) (여러 data)
```   
  기본 자료형의 값                  Wrapper 인스턴스
    ┌────────┐        Boxing         ┌─────────┐
    │ byte   │  ───────────────────> │ Byte    │
    │ short  │                       │ Short   │
    │ int    │                       │ Integer │
    │ float  │                       │ Float   │
    │ double │                       │ Double  │
    │ ...    │  <─────────────────── │ ...     │
    └────────┘        Unboxing       └─────────┘
```

## 2. 폴더 구조
```bash
├── 📁java
│   ├── 📦com.dev.blog
│   │   └── 📦test
│   │   │   └── BlogControllerTest.java
│   │   ├── BlogApplication.java
│   └── 📦com.dev.test
└── 📁resources
``` 

## 3. 숫자 매기기
① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ ⑩