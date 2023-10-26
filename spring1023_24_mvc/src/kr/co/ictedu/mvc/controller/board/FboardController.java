package kr.co.ictedu.mvc.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.ictedu.mvc.dao.FBoardDaoInter;
import kr.co.ictedu.mvc.dto.FboardDTO;

@Controller
public class FboardController {

	@Autowired
	private FBoardDaoInter fBoardDaoInter; // byType ??

	// form
	@GetMapping("/fBoardWrite")
	public String fboardForm() {
		return "/fboard/write";
	}
	// 메서드 이름 : tboardInsert
	// syso로 입력값 출력
	// 반환형 : void

	@PostMapping("/fboardInsert")
	public String fboardInsert(FboardDTO vo) {

		System.out.println("-----------");
		System.out.println("subject : " + vo.getSubject());
		System.out.println("writer : " + vo.getWriter());
		System.out.println("content : " + vo.getContent());
		fBoardDaoInter.addFBoard(vo); // wow

		// 리다이렉트로 다시 컨트롤러에게 요청을 하면서, 요청에 따른 핸들러 매핑이 진행됨
		return "redirect:fboardList";
	}

	@GetMapping("/fboardList")
	public String fboardList(Model m) {
		List<FboardDTO> list = fBoardDaoInter.listFBoard();
		m.addAttribute("list", list);
		return "/fboard/list";
	}

	// list에서 상세보기 링크를 클릭하면 hit 호출되고 +1로 업데이트 한 후
	// redirect:fboardDeatil을 사용해서 num을 파라미터로 전송한다.
	// hit
	@GetMapping("/fboardHit")
	public String fboardHit(int num) {
		fBoardDaoInter.updateHit(num);
		return "redirect:fboardDetail?num=" + num;
	}

	// detail - select * from fboard where num=1
	@GetMapping("/fboardDetail")
	public String fboardDetail(int num, Model model) {
		FboardDTO v = fBoardDaoInter.detailFboard(num);
		model.addAttribute("v", v);
		return "/fboard/info";
	}

	@GetMapping("/fboardModify")
	public String fboardModify(Model m, int num) {
		FboardDTO v = fBoardDaoInter.detailFboard(num);
		m.addAttribute("v", v);
		return "/fboard/modify";
	}

	@PostMapping("/fboardUpdate")
	public String fboardUpdate(FboardDTO vo) {
		fBoardDaoInter.updateFboard(vo);
		
		return "redirect:fboardDetail?num="+vo.getNum();
	}
	
//	@GetMapping("/fboardDelete")
//	public String fboardDelete(int num) {
//		fBoardDaoInter.deleteFboard(num);		
//		
//		return "redirect:fboardList";
//	}
	@PostMapping("/fboardDelete")
	public String fboardDelete( int num) {
		fBoardDaoInter.deleteFboard(num);		

		return "redirect:fboardList";
	}

}
