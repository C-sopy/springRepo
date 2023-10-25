package kr.co.ictedu.mvc.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.mvc.dao.MemberDaoInter;
import kr.co.ictedu.mvc.dto.MemberVo;

@Controller
@RequestMapping("/login")
public class LoginCheckController {
	@Autowired
	private MemberDaoInter memberDaoInter ;
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "member/loginForm" ;
	}
	// login process : 인증, 세션 저장
	@PostMapping("/loginProcess")
	public ModelAndView loginProcess(HttpSession session, HttpServletRequest request, MemberVo vo, 
			@RequestHeader("User-Agent") String userAgent) {
		ModelAndView mav = new ModelAndView("redirect:/main");
		System.out.println("id : " + vo.getId());
		System.out.println("pwd : "+ vo.getPwd());
		MemberVo dto = memberDaoInter.loginCheck(vo);
		if(dto == null) {
			mav.setViewName("error/paramException");
			mav.addObject("emsg", "fail to login");
		}else {
			session.setAttribute("sessionName", dto.getName());
			session.setAttribute("sessionID", dto.getId());
			System.out.println("login complete et sauve session log => proceeding Call");
		}
		return mav ;
	}
	
	// logout : session delete
	@GetMapping("/logout")
	public ModelAndView loginfoutProcess(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		session.removeAttribute("sessionName");
		session.removeAttribute("sessionID");
		mav.setViewName("redirect:/main");
		System.out.println("logout complete et delete session log => proceeding Call");
		
		return mav ;
	}
	
}
