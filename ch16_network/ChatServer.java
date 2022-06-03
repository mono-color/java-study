package ch16_network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	public static void main(String[] args) {
		try {
			// 포트번호 9001로 서버 열기
			ServerSocket server = new ServerSocket(9001);
			
			// 클라이언트 요청 대기
			Socket soc = server.accept();
			System.out.println("클라이언트 요청 수락");
			// IP 주소 가져오기
			System.out.println(soc.getRemoteSocketAddress());
			
			// 데이터 송수신
			SendThread send = new SendThread(soc, "동성");
			ReceiveThread receive = new ReceiveThread(soc);
			
			send.start();
			receive.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
}
