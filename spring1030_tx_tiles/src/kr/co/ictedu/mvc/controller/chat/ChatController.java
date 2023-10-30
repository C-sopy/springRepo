package kr.co.ictedu.mvc.controller.chat;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//request => chatdemo/chat
@Controller
@RequestMapping("/chatdemo")
public class ChatController {
	
	@RequestMapping("/chat")
	public ModelAndView viewChattingPage(HttpSession session) {
		ModelAndView mav = new ModelAndView("chat/chatdemo");
		if(session.getAttribute("sessionID") == null) {
			mav.setViewName("erreur/paramException");
			mav.addObject("emsg", "fail to login");
		}else {
			String img = "" ;
			if(session.getAttribute("sessionID").equals("yeon")) {
				img = "acnh05.jpg" ;
			}else {
				img = "dog1.jpg";
			}
			mav.addObject("cimg", img);
			mav.addObject("ssid", session.getAttribute("sessionName"));
			
		}
		return mav ;
	}
}
