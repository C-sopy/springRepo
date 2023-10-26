package kr.co.ictedu.mvc.controller.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ictedu.mvc.dao.UpBoardDaoInter;
import kr.co.ictedu.mvc.dto.BoardCommVO;
import kr.co.ictedu.mvc.dto.BoardVO;
import kr.co.ictedu.mvc.dto.PageVO;

@Controller
@RequestMapping("/board")
public class UploadDemoController {
	
	@Autowired
	private UpBoardDaoInter upBoardDaoInter;
	@Autowired
	private PageVO pageVO ;
	
	@GetMapping("/upform")
	public String upform() {
		return "updemo/upform" ;
	}
	// uploadpro
	// @postMapping("/uploadpro")
	@PostMapping("/uploadpro")
	public String uploadpro(BoardVO vo, HttpServletRequest request) {
		
		// 파라미터 테스트
		System.out.println("title : " + vo.getTitle());
		
		//MultipartFrile객체에서 이름 확인
		MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();
		System.out.println("oriFn : " + oriFn);
		
		// 경로 테스트: 이미지를 저장할 경로
		String img_path = "resources\\imgfile" ;
		
		//이클립스 상에 저장할 이미지 경로
		String r_path = request.getSession().getServletContext().getRealPath("/");
		System.out.println("r_path : " + r_path);
		
		// 이미지 사이즈 및 contentType 확인
		long size = mf.getSize();
		String contentType = mf.getContentType();
		//contentType의 종류->카페에 있음. 확인
		System.out.println("파일크기 : " + size);
		System.out.println("파일의 type : " + contentType);
		
		
		// 메모리상(임시저장소)에 파일을 우리가 설정한 경로에 복사하겠다.
		
		// 이미지가 저장될 경로 만들기
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(img_path).append("\\");
		path.append(oriFn);
		System.out.println("FullPath : " + path);		
		
		// 추상경로(이미지를 저장할 경로) File 객체로 생성
		// File 클래스의 메서드는 반드시 복습할 것*****
		File f = new File(path.toString());

		// 임시 메모리에 담긴(업로드한 파일의 값) -> File클래스의 경로로 복사
		try {
			mf.transferTo(f);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		} 
		
		//Dao 데이터 입력처리
		vo.setImgn(oriFn);
		upBoardDaoInter.upboardAdd(vo);
		return "redirect:uplist" ;
	}
	
	@RequestMapping("/uplist")
	public String upBoardList(Model model, @RequestParam Map<String, String> paramMap) {
		System.out.println("파라미터 출력");
		System.out.println("검색 시, 링크 시 넘어오는 파라미터 출력 : ");
		String cPage  = paramMap.get("cPage");
		
		System.out.println("cPage : " + cPage);
		System.out.println("searchType : " + paramMap.get("searchType"));
		System.out.println("searchValue : " + paramMap.get("searchValue"));
		System.out.println("*******************");
		
		//1. totalRecord.
		pageVO.setTotalRecord(upBoardDaoInter.getTotal(paramMap));
		int totalRecord = pageVO.getTotalRecord();
		System.out.println("1.TotalRecord : " + totalRecord);
		//2. totalPage
		// totalRecord/numPerPage
		// = (int)Math.ceil(totalRecord/(double)numPerPage);
		pageVO.setTotalPage((int)Math.ceil(totalRecord/(double)pageVO.getNumPerPage()));
		System.out.println("2.totalPage : " + pageVO.getTotalPage());
		
		/*
		 * 3. totalBlock // 전체 블록 구하기 => 전체 페이지(totalPage)/보여줄 블로 ㄱ수(pagePerBlock)
		 * totalBlock = 6/5 ; totalBlock =
		 * (int)Math.ceil((double)totalpage/pagepeervlock);
		 * System.out.println("3.totalBlock : " + totalBlock);
		 */
		pageVO.setTotalBlock((int)Math.ceil((double)pageVO.getTotalPage()/pageVO.getPagePerBlock()));
		System.out.println("3.totalblock : "+pageVO.getTotalBlock());
		//현재 페이지를 요청할 때 파라미터로 현재 페이지값을 받는다. 1~~~~~n.
		if(cPage != null) {
			pageVO.setNowPage(Integer.parseInt(cPage));
		}else {
			pageVO.setNowPage(1);
		}
		System.out.println("4.now page : " + pageVO.getNowPage());
		
		/*
		 * beginPerPage = (nowPage -1) * numPerPage + 1 ; 
		 * endPerPage = (beginPerPage -1) + numPerPage ;
		 * syso("5. beginPerPage = "+beginPerPage);
		 * syso("5. endPerPage = "+endPerPage);
		 */
		
		pageVO.setBeginPerPage((pageVO.getNowPage()-1) * pageVO.getNumPerPage()+1);
		pageVO.setEndPerPage((pageVO.getBeginPerPage() -1) + pageVO.getNumPerPage());
		System.out.println("5.beginPerPage = " + pageVO.getBeginPerPage());
		System.out.println("5.endPerPage = " + pageVO.getEndPerPage());
		
		//paging test
		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
		map.put("end", String.valueOf(pageVO.getEndPerPage()));
		
		// map에 paramMarp 합치기
		map.putAll(paramMap);
		
		//검수
		System.out.println("============MapAll=======");
		for(Map.Entry<String, String> e : map.entrySet()) {
			System.out.println(e.getKey() + ", "+ e.getValue());
		}
		
		List<BoardVO> list = upBoardDaoInter.upboardList(map);
		System.out.println("size : "+ list.size());
		
		/*
		 * int startPage = (int)((nowPage -1)/pagePerBlock) * pagePerBlock + 1;
		 * int endPage = startPage + pagePerBlock -1 ; // endPage의 연산의 범위가 우리가 구한 totalPage값 미만이라면 totalPage의 값으로 대입
		 * if(endPage > totalPage){
		 * endPage = totalPage ; }
		 * syso("6.startPage = " + startPage)
		 * syso("6.endPage = " + endPage)
		 * */
		int startPage = (int)((pageVO.getNowPage()-1)/pageVO.getPagePerBlock()) * pageVO.getPagePerBlock() + 1 ;
		int endPage = startPage + pageVO.getPagePerBlock() -1 ;
		if(endPage > pageVO.getTotalPage()) {
			endPage = pageVO.getTotalPage();
		}
		System.out.println("6.startPage = "+ startPage);
		System.out.println("6.endPage = "+ endPage);
		
		model.addAttribute("searchType", map.get("searchType"));
		model.addAttribute("searchValue", map.get("searchValue"));
		model.addAttribute("startPage", startPage); //블록의 시작페이지 값
		model.addAttribute("endPage", endPage); //블록의 종료 페이지 값
		model.addAttribute("page", pageVO); // nowPage, pagePerBlock, totalPage
		model.addAttribute("list", list);
		
		return "updemo/uplist" ;
	}
	
	@GetMapping("/boardDetail")
	   public String upboardDetail(Model m, 
	         @RequestParam Map<String, String> paramMap,
	         @RequestParam(required = true) int num, 
	         @RequestParam(defaultValue = "detail") String type) {
	      if (!type.equals("comm")) {
	    	  upBoardDaoInter.upboardHit(num);;
	       } 
	      
	      BoardVO b = upBoardDaoInter.upboardDetail(num);
	      m.addAttribute("b", b);		
		
		
		String cPage = paramMap.get("cPage");
		System.out.println(upBoardDaoInter.getCommTotal(paramMap));
		// 1.
		pageVO.setTotalRecord(upBoardDaoInter.getCommTotal(paramMap));
		int totalRecord = pageVO.getTotalRecord();
		//2. total page
		pageVO.setTotalPage((int) Math.ceil(totalRecord / (double) pageVO.getNumPerPage()));
		System.out.println("2. totalPage :" + pageVO.getTotalPage());
		// 3. total block
		pageVO.setTotalBlock((int) Math.ceil((double) pageVO.getTotalPage() / pageVO.getPagePerBlock()));
		System.out.println("3. totalBlock :" + pageVO.getTotalBlock());
		// 4. nowPage
		
		if (cPage != null) {
			pageVO.setNowPage(Integer.parseInt(cPage));
		} else {
			pageVO.setNowPage(1);
		}
		System.out.println("4. nowPage:" + pageVO.getNowPage());
		
		pageVO.setBeginPerPage((pageVO.getNowPage() - 1) * pageVO.getNumPerPage() + 1);
		pageVO.setEndPerPage((pageVO.getBeginPerPage() - 1) + pageVO.getNumPerPage());
		System.out.println("5. beginPerPage = " + pageVO.getBeginPerPage());
		System.out.println("5-1. endPerPage = " + pageVO.getEndPerPage());
		
		// 페이징 테스트
		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
		map.put("end", String.valueOf(pageVO.getEndPerPage()));
		
		map.putAll(paramMap);
		// 검수
		System.out.println("===========Map All=============");
		for (Map.Entry<String, String> e : map.entrySet()) {
			System.out.println(e.getKey() + "," + e.getValue());
		}
		List<BoardCommVO> list = upBoardDaoInter.listCommBoard(map);
		System.out.println("Size:" + list.size());
		
		int startPage = (int) ((pageVO.getNowPage() - 1) / pageVO.getPagePerBlock()) * pageVO.getPagePerBlock() + 1;
		int endPage = startPage + pageVO.getPagePerBlock() - 1;
		if (endPage > pageVO.getTotalPage()) {
			endPage = pageVO.getTotalPage();

		}
		System.out.println("6.startPage = " + startPage);
		System.out.println("6-1. endPage =" + endPage);
		
		m.addAttribute("startPage", startPage); // 블록에 시작페이지 값
		System.out.println("1");
		m.addAttribute("endPage", endPage); // 블록에 종료 페이지값
		System.out.println("2");
		m.addAttribute("page", pageVO); // nowPage, pagePerBlock, totalPage
		System.out.println("3");
		m.addAttribute("listcomm", list);
		System.out.println("4");
		m.addAttribute("num", num);
		System.out.println("5");
		return "updemo/updetail" ;
	}
	
	@GetMapping("/upboardEdit")
	public String upboardEdit(Model m, int num) {
		BoardVO b = upBoardDaoInter.upboardDetail(num);
		m.addAttribute("b", b);
		return "updemo/upedit" ;
	}

	@PostMapping("/upboardUpdate")
	public String upboardUpdate (BoardVO vo,  HttpServletRequest request) {
		
		System.out.println("title : " + vo.getTitle());
		
		//MultipartFrile객체에서 이름 확인
		MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();
		System.out.println("oriFn : " + oriFn);
		
		// 경로 테스트: 이미지를 저장할 경로
		String img_path = "resources\\imgfile" ;
		
		//이클립스 상에 저장할 이미지 경로
		String r_path = request.getSession().getServletContext().getRealPath("/");
		System.out.println("r_path : " + r_path);
		
		// 이미지 사이즈 및 contentType 확인
		long size = mf.getSize();
		String contentType = mf.getContentType();
		//contentType의 종류->카페에 있음. 확인
		System.out.println("파일크기 : " + size);
		System.out.println("파일의 type : " + contentType);
				
		// 메모리상(임시저장소)에 파일을 우리가 설정한 경로에 복사하겠다.		
		// 이미지가 저장될 경로 만들기
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(img_path).append("\\");
		path.append(oriFn);
		System.out.println("FullPath : " + path);		
		
		// 추상경로(이미지를 저장할 경로) File 객체로 생성
		// File 클래스의 메서드는 반드시 복습할 것*****
		File f = new File(path.toString());

		// 임시 메모리에 담긴(업로드한 파일의 값) -> File클래스의 경로로 복사
		try {
			mf.transferTo(f);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		} 
		
		//Dao 데이터 입력처리
		vo.setImgn(oriFn);
		
		upBoardDaoInter.upboardUpdate(vo);
		
		return "redirect:boardDetail?num="+vo.getNum() ;
	}
	
	@PostMapping("/upboardDelete")
	public String upboardDelete(int num) {
		upBoardDaoInter.upboardDelete(num);
		
		return "redirect:uplist" ;
	}
	
	
	//댓글입력처리
	//form의 action, method를 확인하여 작성
	@PostMapping("/bcominsert")
	public String bcominsert(BoardCommVO cvo, Model model) {
		upBoardDaoInter.addBoardComm(cvo);
		return "redirect:boardDetail?num="+cvo.getUcode()+"&type=comm#comm" ;
	}
	
	
	public String listCommBoard() {
		
		
		return "redirect:boardDetail?num=" ;
	}
	
	
	
}
