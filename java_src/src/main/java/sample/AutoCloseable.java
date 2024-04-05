package main.java.sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AutoCloseable {
	/*
		"try-with-resources" 문은 Java 7에서 도입된 기능으로, 자원을 자동으로 해제하는 구조를 제공.
		이는 자원 관리를 더 효율적으로 할 수 있도록 도와준다.
		기존의 자원 해제 방식은 finally 블록을 사용하여 자원을 명시적으로 해제하는 것이었다.
		예를 들어, 파일을 읽는 작업을 수행할 때, 파일을 열고 사용한 후에는 반드시 파일을 닫아야 했다.
		이러한 코드는 보일러플레이트(boilerplate) 코드로 인해 코드의 가독성을 낮출 수 있고, 실수로 자원을 제대로 해제하지 않을 수도 있다.
		Java 7에서는 이러한 문제를 해결하기 위해 "try-with-resources" 문을 도입하였다.
		이 문은 자원을 사용하는 try 블록 뒤에 자원을 선언하고, 이를 사용한 후에는 자동으로 자원을 해제한다.
		이를 통해 보일러플레이트 코드를 줄이고, 자원 관리를 보다 안전하고 간단하게 할 수 있다.
	 */

	public static void main(String[] args) {
		try (BufferedReader reader = new BufferedReader(new FileReader("/Users/melody/Documents/MelodyGitHub/TIL/java_src/src/main/java/sample/file/example.txt"))) {
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
