package kr.co.ictedu.mvc.controller.json;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ictedu.mvc.dto.BoardVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//커스텀 뷰를 만들어놓고 반환되는 내용을 알아서 출력, Object타입일 경우 Json타입으로 제공
@Controller
@RequestMapping("/board")
public class JsonDemoController {
	private List<Map<String, String>> useList;

	public JsonDemoController() {
		useList = new ArrayList<Map<String, String>>();
		
	}
	@GetMapping("/blog2/{bnum}/{bid}")
	// 커스텀 뷰를 만들어 놓고 반환되는 내용을 알아서 출력, 만약에 반환값이 Object일 경우 JSON타입으로 제공한다.
	@ResponseBody
	public String myBlog(@PathVariable Integer bnum, @PathVariable String bid) {
		bnum = bnum - 10 ;
		String msg = "bnum : "+ bnum+ ", bid : "+ bid;
		System.out.println(msg);
		return msg ;
	}
	//http://localhost/spring0614_mvc/board/01/ict
	// 반환값이 Oject일 경우 Json타입으로 제공하기 위해서는 반드시 pom.xml에 jackson-databind를 추가
	@GetMapping("/board/{bnum}/{bid}")
	@ResponseBody
	public BoardVO myBlog2(@PathVariable Integer bnum, @PathVariable String bid) {
		BoardVO vo = new BoardVO();
		vo.setNum(bnum);
		vo.setTitle("Bonjour a TOUS");
		vo.setWriter(bid);
		vo.setReip("192.1234.2.11");
		vo.setContent("C'est tres amusant");
		vo.setBdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return vo ;
	}
	
	@GetMapping("/boardJsonList")
	@ResponseBody	
	public List<BoardVO> myBlog2(){
		System.out.println("boardJsonList 동작");
		List<BoardVO> list = new ArrayList();
		//임시 데이터 만들기
		for(int i = 0 ; i<10 ; i++) {
			BoardVO vo = new BoardVO();
			vo.setNum(i+1);
			vo.setTitle("제목"+i);
			vo.setContent("내용"+i);
			vo.setWriter("sop");
			vo.setReip("192.1234.2.11");			
			vo.setBdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			list.add(vo);
		}
		
		return list ;
	}
	
	// Ajax를 사용한 json데이터 핸들링 form *****
	@GetMapping("/ajaxBoard")
	public String ajaxDemoBoard() {
		return "/board/ajaxBoard";
		
	}
	
	//********
	//RequestParamDemo : Ajax에 의해서 json값으로 파라미터를 처리하기 위한 방법 소개
	// @ResponseBody -> 모델이 수행 후 json데이터를 보낼 때
	// @RequestBody -> 요청의 파라미터가 json Object일 때
	@PostMapping("/RequestParamDemo")
	@ResponseBody
	public List<Map<String, String>> RequestBodyDemo(@RequestBody Map<String, String> param){
		System.out.println("Test : "+param);
		useList.add(param);
		System.out.println(useList.size()+"개 누적..");
		return useList;
	}
	
	
	
	
}
