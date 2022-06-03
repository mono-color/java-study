package ch13_thread;

public class ThreadMain {

	public static void main(String[] args) {
//		printNumbers(20);
//		printNumbers(100);
		// 기본적으로 스레드를 나눠주지 않으면
		// 메인스레드 하나만 일을해서 위에서 부터 차례대로 코드가 실행된다.

		System.out.println("\n========================\n");

		// 1. Thread를 상속 받은 클래스를 이용해서 멀티 스레드를 구현하는 방법

		System.out.println("[메인 스레드] 카운트 시작");
		ExThread exThread = new ExThread(20, "달현");
		exThread.start();
		ExThread exThread2 = new ExThread(100, "현섭");
		exThread2.start();
		// 외부 스레드에 일을 줄때, 우선적으로 알아야하는 것은
		// 각각의 외부 스레드가 언제 일이 끝날지 모른다.
		System.out.println("[메인 스레드] 카운트 끝");

		// 2. Runnable 인터페이스를 구현(implement)
		// 한 클래스를 이용
		ExRunnable exRun = new ExRunnable(300, "혜연");
		Thread hyeyeon = new Thread(exRun, "혜연");
		hyeyeon.start();
		Thread nagyeom = new Thread(new ExRunnable(400, "나겸"));
		nagyeom.start();

		// 람다식 사용
		Thread seolri = new Thread(() -> {
			int num = 600;
			for (int i = num; i < num + 5; i++) {
				// 현재 실행중인 Thread의 이름
				System.out.println("설리 " + i);

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		seolri.start();
		
		
	}// main 끝

	// 입력받은 숫자부터 +5까지 콘솔창에 출
	static void printNumbers(int num) {
		for (int i = num; i < num + 5; i++) {
			System.out.println(i);

			try {
				// 실행에 텀(term)을 주고 싶을 때 사용
				// sleep() 안에 숫자는 밀리초 단위
				// 현재 이 코드를 실행중인 스레드를 멈추는 것,
				// 다른 스레드는 영향 받지 않음
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
