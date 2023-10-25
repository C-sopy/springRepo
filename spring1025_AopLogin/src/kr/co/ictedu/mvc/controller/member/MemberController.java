package kr.co.ictedu.mvc.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ictedu.mvc.dao.MemberDaoInter;
import kr.co.ictedu.mvc.dto.MemberVo;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberDaoInter memberDaoInter ;
	
	//회원가입 폼
	@GetMapping("/memberForm")
	public String memberForm() {
		
		return "member/memberForm" ;
	}
	
	//아이디 중복처리
	
	@GetMapping("/idcheck")
	@ResponseBody
	public String idCheck(@RequestParam(required = true) String id) {
//		String[] dbid = {"xman", "bigdaddy", "postman"};		
		int res = memberDaoInter.idCheck(id);		
//		for(String e : dbid) {
//			if(e.equals(id)) {
//				res = 1 ;
//				break ;
//			}
//		}
		System.out.println("res => " + res);
		return String.valueOf(res);
		
	}
	
	@PostMapping(value="/memberIn")
	public String memberIn(MemberVo vo, Model m, HttpServletRequest request) {
		vo.setReip(request.getRemoteAddr());
		memberDaoInter.add(vo);
		m.addAttribute("name",vo.getName());
		m.addAttribute("id", vo.getId());
		return "member/mysuccess" ;
	}
	
	@GetMapping("/mypage")
	public String myPage(HttpSession session) {
		if (session.getAttribute("sessionName") == null) {
			return "redirect:/main";
		}

		return "member/myPage";

	}
	
}
