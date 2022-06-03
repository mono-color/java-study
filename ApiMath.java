package ch11_java_api;

public class ApiMath {

	public static void main(String[] args) {
		final double PI = 3.141592;

		// 반올림
		long roundPI = Math.round(PI);
		System.out.println(roundPI);

		// 소수 넷째자리에서 반올림하여 3.142 만들기
		double fourPI = (Math.round(PI * 1000)) / 1000.0;
		System.out.println(fourPI);
		
		// 올림
		double ceilPI = Math.ceil(PI);
		System.out.println(ceilPI);
		
		// 내림(버림)
		double floorPI = Math.floor(PI);
		System.out.println(floorPI);
		
		
		
	}

}
