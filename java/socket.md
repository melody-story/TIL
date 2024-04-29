# Socket

- 소켓의 본질은 파일이다.
- OS 커널에 구현되어있는 프로토콜 요소에 대한 추상화된 인터페이스

프로세스가 소켓의 주체가 된다.
open, create, close, delete

대상체 파일이 무엇에 대한 추상화된 인터페이스를 제공하느냐에 따라 종류가 나뉜다.

TCP(send, receive), console, File

Stream :  append 모드로 열어 파일을 붙여 넣는다.

데이터의 단위가 바뀐다.

socket(S/W:usermode) -> segment(S/W:kernel mode L4 TCP) -> packet(S/W:kernel mode L3 IP) 


Stream 연속된 데이터
Stream의 데이터 조각 : segment 또는 packet

