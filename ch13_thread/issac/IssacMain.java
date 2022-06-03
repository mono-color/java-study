package ch13_thread.issac;

public class IssacMain {

	public static void main(String[] args) {
		Customer nagyeom = new Customer("나겸", 2);
		Customer hyeyeon = new Customer("헤연", 2);
		Customer dalhyeon = new Customer("달현", 4);
		
		Chef chef = new Chef(8);
		chef.endCook = new EndCook() {

			@Override
			public void endOfCook() {
				System.out.println("토스트 8개 굽기 완료");
			}
			
		};
		
		chef.start();
		nagyeom.start();
		hyeyeon.start();
		dalhyeon.start();
		
	}
}
