# abstract 클래스

- 사전적 의미 : 추상적인
- 사용하는 경우 : `abstract`로 선언한 메소드가 0개이상 있는 경우 선언한다.
- 선언 방법 : abstract라는 예약어를 class 앞에 적어준다.
    - `public abstract class 클래스이름`

### 특징

- 인터페이스와 달리 구현되어있는 메소드가 있어도 상관없다.

```
public abstract class MemberMangerAbstract{
    public abstract boolean addMember(MemberDTO member);
    public abstract boolean removeMember(String name, String phone);
    public abstract boolean updateMmeber(MemberDTO member);
    public void pringLong(String data){
        System.out.println("Data=" + data);
    }
}
```

- `abstract`으로 선언된 메소드가 하나라도 있으면 클래스는 반드시 `abstract`로 선언되어야 함.
- `static`이나, `final`로 선언된 메소드가 있어도 된다. (인터페이스는 불가)
- 인터페이스가 아니라 클래스이기 때문에 `implements`가 아닌 `extends`를 통해 구현한다.
- `abstract` 클래스를 아래와 같이 상속 받았을때, `abstract` 메소드가 `abstact` 클래스 내에서 구현되어있지 않으면 `상속받은 클래스`에서 반드시 구현을 해야 컴파일 에러가 발생하지 않는다.

```
public class MemberManagerImpl2 extends MemberMangerAbstract{
    public boolean removeMember(String name, String phone);
        return false;
    public boolean addMember(MemberDTO member);
        return false;
    public boolean updateMmeber(MemberDTO member);
        return false;
    public void pringLong(String data){
        System.out.println("Data=" + data);
    }
}
```
---

## 인터페이스와 추상클래스, 그리고 클래스의 차이점 
|                     | 인터페이스     | abstract클래스    | 클래스    |
|---------------------|-----------|----------------|--------|
| 선언시 사용하는 예약어        | interface | abstract class | class  |
| 구현 안 된 메소드 포함 가능 여부 | 가능(필수)    | 가능             | 불가     |
| 구현 된 메소드 포함 가능 여부   | 불가        | 가능             | 가능(필수) |
| static 메소드 선언 가능 여부 | 불가        | 가능             | 가능     |
| static 메소드 선언 가능 여부 | 불가        | 가능             | 가능     |
| static 메소드 선언 가능 여부 | 불가        | 가능             | 가능     |
| static 메소드 선언 가능 여부 | 가능        | 불가             | 불가     |