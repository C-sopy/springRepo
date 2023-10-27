package myjava.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// 판교에 있는 리눅스 os에 구현한 서버라고 가정
public class MyServerSocket {
	// 1. 서버 소켓을 선언
	private ServerSocket ss;
	

	// 2. 클라이언트의 소켓을 선언
	// private Socket socket ;
	private ArrayList<ServerThread> cList;
	
	public MyServerSocket() {
		try {
			ss = new ServerSocket(9080);
			System.out.println("create new ServerSocket !");
			cList = new ArrayList<ServerThread>();
			execute();

		} catch (IOException e) { // IOException생기면 이미 그 호스트를 쓰고있다는 말 ( Address already in use )

			e.printStackTrace();
		}

	}

	private void execute() {

		while (true) {
			try {
				Socket socket = ss.accept(); // 대기하는 메서드,,외부에서 소켓이 접속을 했을 때 실행하고 등등등 // blocking
				ServerThread st = new ServerThread(socket, this);
				cList.add(st);
				st.start();

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		MyServerSocket ref = new MyServerSocket();

	}

	public void sendMsg(String str1, String str2, String str3, String str4, String str5, ServerThread serverThread) {
		// protocol은 서버와 클라이언트의 약속이다.
		// ex) draw/color/x/y
		String type1 = str1; // talk, draw, enter등의 프로토콜을 작성
		String type2 = str2; // nickName, color, all등의 프로토콜을 작성
		String type3 = str3; // none,x,speaker
		String type4 = str4; // none,y,say;
		// 전처리
		// 응답(response)
		String str = "";
		// talk/all/nickName/say
		if (type1.equals("talk")) {
			str = "talk/none/none/" + "[" + type3 + ":" + str5 + "]" + type4;
			System.out.println("Message :" + str);
		} else if (type1.equals("draw")) {
			// ex) draw/color/x/y
			str = type1 + "/" + type2 + "/" + type3 + "/" + type4;
		} else if (type1.equals("enter")) {
			// code
		}
		// 모든 유저에게 완성된 str을 스트림을 통해서 보내는 작업
		for (ServerThread c : cList) {
			c.getPw().println(str);
		}

	}

}
