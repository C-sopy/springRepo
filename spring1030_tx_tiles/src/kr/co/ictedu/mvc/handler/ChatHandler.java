package kr.co.ictedu.mvc.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import kr.co.ictedu.mvc.dto.ChatVO;

@Component
// ws://host//chat 접속시
@ServerEndpoint("/chat")
public class ChatHandler {
	private final static String PATH = "E:\\ICT\\gitSop\\spring1025_AopLogin\\WebContent\\chat.txt";

	private static List<ChatVO> users = new LinkedList<ChatVO>();
	private Gson gson = new Gson();

	// 웹소켓의 세션을 받아서 ChatSession을 탐색하는 메서드
	private ChatVO getSession(Session userSession) {
		System.out.println("1.userSession : " + userSession);
		Optional<ChatVO> data = users.stream().filter(x -> x.getSession() == userSession).findFirst();
		if (data.isPresent()) {
			return data.get(); // ChatVO 반환
		}
		// List<ChatVO>에 존재하지 않으면 null을 반환.
		return null;
	}

	// create session : if new session->create, else: return session
	private ChatVO createSession(ChatVO msg, Session userSession) {
		// 세션의 값 가져오기
		ChatVO session = getSession(userSession);
		if (session == null) {
			// create
			session = new ChatVO();
			// sauve
			session.setSession(userSession);
			// add to list
			users.add(session);
		}
		// sauve id
		session.setId(msg.getId());
		// return session
		return session;
	}

	// @OnOpen : WebSocket이 접속될 때 호출되는 함수에 사용되는 어노테이션 -> accept()
	// @OnMessage : 브라우저로부터 메시지가 오면 호출되는 함수 -> readLin()
	// @OnClose : WebSocket이 담기면 호출되는 함수
	@OnOpen
	public void handleOpen(Session userSession) {
		System.out.println("WebSocket connected");
		System.out.println("id: " + userSession.getId());
	}

	@OnMessage
	public void handleMessage(String message, Session userSession) {
		// json type
		ChatVO msg = gson.fromJson(message, ChatVO.class);
		System.out.println("Status: " + msg.getState());
		// if status == 0 -> first connect, == 1 -> 일반 메시지 판단, 프로토콜 0 -> enter, 1-> chat
		if (msg.getState() == 0) {
			createSession(msg, userSession);
			
			// File에 저장한 내용을 읽어 오기
			try {
				userSession.getBasicRemote().sendText(readFile());
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (msg.getState() == 1) {
			// check session
			if(getSession(userSession) != null) {
				// send message
				sendMessageData(msg.getId(), msg.getValue());
				// save in file
				saveFile(msg.getId(), msg.getValue());
			}
		}
	}

	private void saveFile(String id, String message) {
		//메시지 내용
		String sendMessage = id + ": " + message + "\n";
		
		try (PrintWriter pw = new PrintWriter(new FileOutputStream(PATH), true);) {			
			pw.println(sendMessage);
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		
	}

	private void sendMessageData(String id, String message) {
		// 메시지 내용
		String sendMessage = id + ": " + message + "\n";
		
		// 브로드 캐스팅
		for(ChatVO user : users) {
			//send message
			try {
				user.getSession().getBasicRemote().sendText(sendMessage);
			} catch (IOException e) {
				// TODO Auto-generated catch blocke.printStackTrace();
			}
			
		}
	}

	private String readFile() {
		File file = new File(PATH);
		if(!file.exists()) {
			return "Il n'existe pas" ;
		}
		try (BufferedReader rd = new BufferedReader(new FileReader(file));) {	
			StringBuilder sb = new StringBuilder();
			String msg = null ;
			while((msg = rd.readLine()) != null) {
				sb.append(msg);
			}
			return msg ;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "";
	}	
	
	@OnClose
	public void handleClose(Session userSession) {
		Optional<ChatVO> session = users.stream().filter(x -> x.getSession() == userSession).findFirst();
		if(session.isPresent()) {
			// delete session in List
			users.remove(session.get());
			System.out.println("user size : "+ users.size());
			if(users.size() == 0) {
				File file = new File(PATH);
				file.delete();
			}
			
		}
		
	}



}
