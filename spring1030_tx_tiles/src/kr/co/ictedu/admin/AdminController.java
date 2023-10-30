package kr.co.ictedu.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping(value = {"/","/welcome"})
	public String adminDefaultPage(Model model) {
		model.addAttribute("title", "ICT01 Admin Page");
		model.addAttribute("message", "여기는 관리자 페이지 입니다.");
		return "admin/welcomePage" ;
	}
}
