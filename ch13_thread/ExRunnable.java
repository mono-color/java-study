package ch13_thread;

public class ExRunnable implements Runnable{
	int num;
	String name;
	
	ExRunnable(int num, String name){
		this.num = num;
		this.name = name;
	}
	
	@Override
	public void run() {
		for(int i = num; i <num +5; i++) {
			// 현재 실행중인 Thread의 이름
			System.out.println(name+ " " + i);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
