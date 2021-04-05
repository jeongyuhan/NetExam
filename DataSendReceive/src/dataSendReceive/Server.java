package dataSendReceive;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1) 파일명 : Server.java
 * 2) 작성자 : 정유한
 * 3) 작성일자 : 2021-04-05
 * 4) 설명 : Client.java 클라이언트가 접속할 서버를 구축한 클래스  
 */


public class Server {

	public static void main(String[] args) {
		
		ServerSocket server = null;
		
		try {
			server = new ServerSocket();
			server.bind(new InetSocketAddress("localhost", 1234));
			System.out.println("서버 실행중!");
			
			while(true) {
				
				Socket client = server.accept();
				InetSocketAddress isa = (InetSocketAddress)client.getRemoteSocketAddress();
				System.out.println("접속된 클라이언트 : [" + isa.getHostName() + "]");
				
				InputStream is = client.getInputStream();
				byte[] b = new byte[1024];
				int length = is.read(b);
				String fromClient = new String(b, 0, length, "UTF-8");
				System.out.println("클라이언트(" + isa.getHostName() + ")로부터 \"" + fromClient + "\" 데이터를 받았습니다.");

			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(!server.isClosed()) server.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
