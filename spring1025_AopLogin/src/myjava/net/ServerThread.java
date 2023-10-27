package myjava.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;



public class ServerThread extends Thread {
	private Socket socket ; // 연결된 각각의 소캣의 정보,,
	private MyServerSocket server ; // 브로드캐스팅
	private PrintWriter pw;
	private BufferedReader in;
	private String reip ;
	private Map<String, String> map ;
	
	public ServerThread(Socket socket, MyServerSocket server) {
		this.socket = socket;  
		this.server = server ; 
		reip = socket.getInetAddress().getHostAddress();
		map = new HashMap<String, String>();
		map.put("bonjour", "bonjour !");
		map.put("comment ca va ? ", "Je vais biden merci ~");
		map.put("j'ai faim", "moi aussi");
		map.put("quelle est votre nom ?", "Je m'appelle sop");
		map.put("oh la la", "pourquoi ?");
		
		
		try {
			pw = new PrintWriter(socket.getOutputStream(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true) {
				String msg = in.readLine();
				System.out.println("Client msg : " + msg);
				transMsg(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void transMsg(String msg) {
		//심심이  ver.
		//String serMsg = map.get(msg);
		// ex) chat/hi
		// ex) draw/color/rouge/bleu,,,
		StringTokenizer stn = new StringTokenizer(msg, "/");
		String str1 = stn.nextToken();
		String str2 = stn.nextToken();
		String str3 = stn.nextToken();
		String str4 = stn.nextToken();
		String str5 = reip;
		server.sendMsg(str1, str2, str3, str4, str5, this);
		
	}

	public PrintWriter getPw() {
		return pw;
	}
	
	
}
