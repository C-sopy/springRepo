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

//Ŀ���� �並 �������� ��ȯ�Ǵ� ������ �˾Ƽ� ���, ObjectŸ���� ��� JsonŸ������ ����
@Controller
@RequestMapping("/board")
public class JsonDemoController {
	private List<Map<String, String>> useList;

	public JsonDemoController() {
		useList = new ArrayList<Map<String, String>>();
		
	}
	@GetMapping("/blog2/{bnum}/{bid}")
	// Ŀ���� �並 ����� ���� ��ȯ�Ǵ� ������ �˾Ƽ� ���, ���࿡ ��ȯ���� Object�� ��� JSONŸ������ �����Ѵ�.
	@ResponseBody
	public String myBlog(@PathVariable Integer bnum, @PathVariable String bid) {
		bnum = bnum - 10 ;
		String msg = "bnum : "+ bnum+ ", bid : "+ bid;
		System.out.println(msg);
		return msg ;
	}
	//http://localhost/spring0614_mvc/board/01/ict
	// ��ȯ���� Oject�� ��� JsonŸ������ �����ϱ� ���ؼ��� �ݵ�� pom.xml�� jackson-databind�� �߰�
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
		System.out.println("boardJsonList ����");
		List<BoardVO> list = new ArrayList();
		//�ӽ� ������ �����
		for(int i = 0 ; i<10 ; i++) {
			BoardVO vo = new BoardVO();
			vo.setNum(i+1);
			vo.setTitle("����"+i);
			vo.setContent("����"+i);
			vo.setWriter("sop");
			vo.setReip("192.1234.2.11");			
			vo.setBdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			list.add(vo);
		}
		
		return list ;
	}
	
	// Ajax�� ����� json������ �ڵ鸵 form *****
	@GetMapping("/ajaxBoard")
	public String ajaxDemoBoard() {
		return "/board/ajaxBoard";
		
	}
	
	//********
	//RequestParamDemo : Ajax�� ���ؼ� json������ �Ķ���͸� ó���ϱ� ���� ��� �Ұ�
	// @ResponseBody -> ���� ���� �� json�����͸� ���� ��
	// @RequestBody -> ��û�� �Ķ���Ͱ� json Object�� ��
	@PostMapping("/RequestParamDemo")
	@ResponseBody
	public List<Map<String, String>> RequestBodyDemo(@RequestBody Map<String, String> param){
		System.out.println("Test : "+param);
		useList.add(param);
		System.out.println(useList.size()+"�� ����..");
		return useList;
	}
	
	
	
	
}
