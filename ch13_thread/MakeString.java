package ch13_thread;

public class MakeString {

	public static void main(String[] args) {
		// 문자열을 아주 길~~~~게 만들어보자
		
		String str = "";
		StringBuffer strBuff = new StringBuffer();
		StringBuilder strBuild = new StringBuilder();
//		
//		for(int i = 0; i < 100000000; i++) {
////			str += (int)(Math.random()*10);
//			strBuff.append((int)(Math.random()*10));
//			if(i % 100000 == 0) {
//				System.out.println(i+"...");
//			}
//		}
//		System.out.println(strBuff.length());
//		System.out.println(str);
		
		
		// 람다식 사용
		Thread seolri = new Thread(() -> {
			for(int i = 0; i < 50000000; i++) {
//				str += (int)(Math.random()*10);
				strBuild.append((int)(Math.random()*10));
				if(i % 100000 == 0) {
					System.out.println(i+"...");
				}
			}
			System.out.println(strBuild.length());
		});
		seolri.start();
		
		Thread sungbin = new Thread(() -> {
			for(int i = 0; i < 50000000; i++) {
//				str += (int)(Math.random()*10);
				strBuild.append((int)(Math.random()*10));
				if(i % 100000 == 0) {
					System.out.println(i+"...");
				}
			}
			System.out.println(strBuild.length());
		});
		sungbin.start();
		
		
		
		
	}

}
