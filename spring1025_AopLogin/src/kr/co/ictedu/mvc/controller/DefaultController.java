package kr.co.ictedu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
	// value를 사용해서 문자열 배열로 요청의 값을 다양하게 지정할 수 있다
	@GetMapping(value = { "/main", "" })
	public String main() {
		return "main/index";
	}
}
