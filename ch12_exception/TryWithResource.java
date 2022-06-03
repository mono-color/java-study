package ch12_exception;

import java.util.Scanner;

public class TryWithResource {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("아무말이나 입력");
			System.out.print(">>> ");
			String inputText = sc.nextLine();
			System.out.println(inputText);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			sc.close();
		}

		// try-with-resource 구문
		// 닫아야할 객체를 try() 괄호 안에 선언 후
		// try 블록{} 내에서 해당 객체를 사용
		// 객체의 클래스는 AutoCloseable 인터페이스를
		// 구현하고 있어야 함

		try (Scanner sca = new Scanner(System.in)) {
			System.out.println("아무말이나 입력 두번째");
			System.out.print(">>> ");
			String inputText = sca.nextLine();
			System.out.println(inputText);
		}
		// try-with-resource의 경우, catch를
		// 붙이지 않아도 된다. (닫는건 잘 닫음)

		// try() 괄호 안에 객체 여러개 선언
		try (Scanner scan = new Scanner(System.in); Scanner scann = new Scanner(System.in)) {
			System.out.print("아무말 세번째: ");
			System.out.println(scan.nextLine());
			System.out.print("아무말 네번째: ");
			System.out.println(scann.nextLine());
		}

	}
}
