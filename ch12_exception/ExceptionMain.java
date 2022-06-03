package ch12_exception;

import java.text.ParseException;

public class ExceptionMain {

	public static void main(String[] args) {
		int[] intArray = {1, 2, 3};
		
		try {
			System.out.println(intArray[300]);
		}catch(ArrayIndexOutOfBoundsException exception) {
			exception.printStackTrace();
			// 에러가 콘솔창에 출력되는 시점과
			// syso가 콘솔창에 출력되는 시점이 다르다.
			System.out.println("예외 발생!!!!!!!!");
			System.err.println("빨간 글씨");
		}
		
		try {
			System.out.println(intArray[300]);
		}catch(Exception e) {
			e.printStackTrace();
			// 에러가 콘솔창에 출력되는 시점과
			// syso가 콘솔창에 출력되는 시점이 다르다.
			System.out.println("예외 발생!!!!!!!!");
			System.err.println("빨간 글씨");
			// 웹사이트라면 에러 페이지로 이동시킨다.
		}
		
		// 초보자일수록 예외처리는 충분한 테스트 이후
		// 적용하는 것을 권장.
		// 예외처리를 해가며 개발을 하려면 각각
		// 주요 메소드마다 로그를 찍어가며 개발해야 한다.
		// 로그에는 메소드명, 각각의 주요 변수들에
		// 담긴 값 등을 기록한다.
		
		
		System.out.println("안녕?");
		
		try {
			System.out.println("인덱싱 시작");
			System.out.println(intArray[300]);
			System.out.println("인덱싱 종료");
		}catch(ArrayIndexOutOfBoundsException exception) {
			System.out.println("해당 배열의 인덱스를 넘어갔습니다.");
		}catch(Exception e) {
			System.out.println("예측하지 못한 예외가 발생했네요");
		}
		
		// 충분히 예측되는 예외의 경우
		// 따로 Exception 클래스를 만들어서 처리한다.
		// 비즈니스 Exception -> BizException
		System.out.println("\n========================\n");
		
		try {
			long result = ExMethod.dateMillSec("2022.05.20");
			System.out.println(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n====================\n");
		
		
		try {
			ExMethod.printName("");
		} catch (BizException e) {
			e.printStackTrace();
			System.out.println("에러코드: " + e.getErrCode());
			System.out.println("에러메시지: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		} finally {
			// 예외가 발생하든 말든 실행할 명령어 입력
			// 일반적인 경우라면 굳이 finally에 쓸 이유가 없다.
			// 주로 try문에서 사용된 객체를 close할때 사용
			System.out.println("에러가 발생하든말든 실행됨");
		}
		
		
		
		
		
	}

}
