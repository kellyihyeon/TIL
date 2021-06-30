# Git 커밋 취소하기
*하루에 한 번씩은 커밋을 취소해야하는 일이 생긴다.  
그럴때마다 notion에서 카테고리 뒤지고 따라하는 게 번거로워서 정리한다.*
<br>

## 목차
1. [push까지 해버렸을 경우](#1-push까지-해버렸을-경우)  
2. [다행히 push까지는 안 한 경우 ](#2-다행히-push까지는-안-한-경우)
<br>


# 1. push까지 해버렸을 경우

① commit하기 전으로 돌아가자
```bash
git reset --soft HEAD^
```

② add도 취소해야할 경우
```bash
git restore --staged .
```

③ 다시 add 하기
```bash
git add .
```

④ commit 하기
```bash
git commit -m "commit message."
```

⑤ push 하기
```bash
git push -f origin 브랜치명
```


# 2. 다행히 push까지는 안 한 경우 
*위의 ① ~ ④과정까지는 동일하다.*

```bash
① commit하기 전으로 돌아가자
git reset --soft HEAD^

② add도 취소해야할 경우
git restore --staged .

③ 다시 add 하기
git add .

④ commit 하기
git commit -m "commit message."
```

⑤ push 하기
```bash
git push origin 브랜치명
```