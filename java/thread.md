# 쓰레드 (Thread)

-
    1. java 라는 명령어를 실행해서 클래스실행
-
    2. jvm이 시작되면서 자바 프로세스가 시작
-
    3. main() 메소드가 시작되면서 하나의 쓰레드가 시작

    - 많은 쓰레드가 필요하면, main() 메소드에서 여러 쓰레드를 생성해주면 된다.
    - 하나의 프로세스 내에 여러 쓰레드가 수행된다.
    - 기본적으로 jvm을 관리하기 위한 여러 쓰레드가 존재한다.
    - 자바의 객체를 청소하는 GC 관련 쓰레드도 이에 속한다.

- 쓰레드 사용예
    - 자바를 사용하여 웹을 제공할때
    - Tomcat과 같은 WAS를 사용한다. 이 WAS도 main()메소드에서 생성한 쓰레드들이 수행되는 것이다.

## 쓰레드를 만든 이유?

- 프로세스 하나가 시작하면 많은 자원들이 필요하다.
- 만약 하나의 작업을 동시에 수행하려 할때 여러개의 프로세스를 띄우려면 각각의 프로세스가 메모리 공간을 차지하게 된다.
- JVM은 기본적으로 아무런 옵션 없이 실행하면 32MB~64MB 의 물리 메모리를 점유한다.(이 공간은 OS마다 다르다.)
- 쓰레드 1개 추가시 1MB이내의 메로리를 점유한다.
- 때문에 쓰레드를 경량 프로세스라고 한다.
- 대부분의 작업은 단일 쓰레드로 실행하는 것보다 다중 쓰레드로 실행하는 것이 더 빠른 시간에 결과를 제공해준다.
- 따라서 보다 빠른 처리를 할 필요가 있을 때, 쓰레드를 사용하면 빠르게 처리할 수 있다.

## 쓰레드 생성방법

1. Runnable 인터페이스를 구현
2. Thread 클래스를 확장
    - Thread 클래스는 `Runnable 인터페이스를 구현`한 클래스로 `매우 많은` `생성자`와 `메소드`를 제공하고 있다.

> 위 두 방법 모두 java.lang 패키지에 해당하므로, 별도의 import는 필요 없다.
> 모두 쓰레드로 실행할 수 있다는 공통점이 있으나, 두 쓰레드 클래스를 실행하는 방식은 서로 다르다.

### 1. Runnable 인터페이스를 구현

- 단 하나의 `run()` 메소드가 선언되어있다.
- 쓰레드가 시작되기 전 수행되는 메소드

```java
package e.thread;

public class RunnableSample implements Runnable {
	public void run() {
		System.out.println("This is RunnableSample's run() method.");
	}
}
```

### 쓰레드 시작 방법

```java
package e.thread;

public class RunThreads {
	public static void main(String[] args) {
		RunThreads threads = new RunThreads();
		threads.runBasic();
	}

	public void runBasic() {
		RunnableSample runnable = new RunnableSample();
		new Thread(runnable).start(); // 쓰레드 셍성자의 매개변수로 구현체가 들어감.
	}
}
```

### 2. Thread 클래스를 확장

```java
package e.thread;

public class ThreadSample extends Thread {
	public void run() {
		System.out.println("This is ThreadSample's run() method.");
	}
}
```

### 쓰레드 시작 방법

```java
package e.thread;

public class RunThreads {
	public static void main(String[] args) {
		RunThreads threads = new RunThreads();
		threads.runBasic();
	}

	public void runBasic() {
		ThreadSample thread = new ThreadSample();
		thread.start();
	}
}
```

>### 쓰레드 생성방법이 2가지 있는 이유
>- 클래스는 다상속이 불가능하므로 이미 상속받은 클래스가 있는 경우 해당클래스를 쓰레드로 만들수없다. 
>- 하지만, 인터페이스는 여러개의 인터페이스를 구현해도 전혀 문제가 발생하지  않는다. 
>- 따라서,이러한경우에는 Runnable 인터페이스를 구현해서 사용하면 된다.
