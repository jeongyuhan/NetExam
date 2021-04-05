package dataSendReceive;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * 1) 파일명 : Client.java
 * 2) 작성자 : 정유한
 * 3) 작성일자 : 2021-04-05
 * 4) 설명 : Server.java 서버에 접속하는 클라이언트를 구축한 클래스 
 */

public class Client {

	public static void main(String[] args) {
		
		Socket client = null;
		Scanner sc = null;
		
		try {
			client = new Socket();
			client.connect(new InetSocketAddress("localhost", 1234));
			System.out.println("서버로 접속되었습니다.");
			
			sc = new Scanner(System.in);

			OutputStream os = client.getOutputStream();
			System.out.print("서버에게 전송할 메시지를 입력하세요>>>");
			
			String toServer = sc.nextLine();
			os.write(toServer.getBytes("UTF-8"));
			os.flush();
			if(toServer != null) {
				System.out.println("서버(" + client.getLocalAddress() + ")로 \"" + toServer + "\" 데이터를 전송했습니다." );
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(!client.isClosed()) client.close();				
			} catch(Exception e) {
				e.printStackTrace();
			}
		} 
		
		
		
	}

}
