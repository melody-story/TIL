# System.exit()를 사용하면 안되는 이유

- System.exit()메서드는 일반적으로 비정상적인 조건에 의해 즉시 프로그램을 종료해야 하는 경우 사용합니다.
- 또한 main() 메서드가 아닌 다른 메서드에서 프로그램을 종료해야 하는 경우에도 사용합니다.
- 참고로 System.exit() 메서드를 호출하여 프로그램을 종료하는 것은 좋은 방법이 아닙니다.
- System.exit() 메서드를 호출하면 main() 메서드를 종료한 것과 동일하게 동작하며,
- JVM이 종료될 때까지 System.exit() 메서드를 호출하는 스레드도 차단합니다.
- 만약, Shutdown hook(종료 후크)가 차단된 스레드에 작업을 진행하면 JVM이 종료되는 절차가 멈추게 되며 교착 상태(데드락)에 빠지게 됩니다.

>* 데드락(영어: deadlock)<br>
>두 개 이상의 작업이 서로 상대방의 작업이 끝나기 만을 기다리고 있기 때문에 결과적으로 아무것도 완료되지 못하는 상태

[참고](https://developer-talk.tistory.com/841 )