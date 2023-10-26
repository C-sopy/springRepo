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
		
		// �Ķ���� �׽�Ʈ
		System.out.println("title : " + vo.getTitle());
		
		//MultipartFrile��ü���� �̸� Ȯ��
		MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();
		System.out.println("oriFn : " + oriFn);
		
		// ��� �׽�Ʈ: �̹����� ������ ���
		String img_path = "resources\\imgfile" ;
		
		//��Ŭ���� �� ������ �̹��� ���
		String r_path = request.getSession().getServletContext().getRealPath("/");
		System.out.println("r_path : " + r_path);
		
		// �̹��� ������ �� contentType Ȯ��
		long size = mf.getSize();
		String contentType = mf.getContentType();
		//contentType�� ����->ī�信 ����. Ȯ��
		System.out.println("����ũ�� : " + size);
		System.out.println("������ type : " + contentType);
		
		
		// �޸𸮻�(�ӽ������)�� ������ �츮�� ������ ��ο� �����ϰڴ�.
		
		// �̹����� ����� ��� �����
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(img_path).append("\\");
		path.append(oriFn);
		System.out.println("FullPath : " + path);		
		
		// �߻���(�̹����� ������ ���) File ��ü�� ����
		// File Ŭ������ �޼���� �ݵ�� ������ ��*****
		File f = new File(path.toString());

		// �ӽ� �޸𸮿� ���(���ε��� ������ ��) -> FileŬ������ ��η� ����
		try {
			mf.transferTo(f);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		} 
		
		//Dao ������ �Է�ó��
		vo.setImgn(oriFn);
		upBoardDaoInter.upboardAdd(vo);
		return "redirect:uplist" ;
	}
	
	@RequestMapping("/uplist")
	public String upBoardList(Model model, @RequestParam Map<String, String> paramMap) {
		System.out.println("�Ķ���� ���");
		System.out.println("�˻� ��, ��ũ �� �Ѿ���� �Ķ���� ��� : ");
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
		 * 3. totalBlock // ��ü ��� ���ϱ� => ��ü ������(totalPage)/������ ��� ����(pagePerBlock)
		 * totalBlock = 6/5 ; totalBlock =
		 * (int)Math.ceil((double)totalpage/pagepeervlock);
		 * System.out.println("3.totalBlock : " + totalBlock);
		 */
		pageVO.setTotalBlock((int)Math.ceil((double)pageVO.getTotalPage()/pageVO.getPagePerBlock()));
		System.out.println("3.totalblock : "+pageVO.getTotalBlock());
		//���� �������� ��û�� �� �Ķ���ͷ� ���� ���������� �޴´�. 1~~~~~n.
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
		
		// map�� paramMarp ��ġ��
		map.putAll(paramMap);
		
		//�˼�
		System.out.println("============MapAll=======");
		for(Map.Entry<String, String> e : map.entrySet()) {
			System.out.println(e.getKey() + ", "+ e.getValue());
		}
		
		List<BoardVO> list = upBoardDaoInter.upboardList(map);
		System.out.println("size : "+ list.size());
		
		/*
		 * int startPage = (int)((nowPage -1)/pagePerBlock) * pagePerBlock + 1;
		 * int endPage = startPage + pagePerBlock -1 ; // endPage�� ������ ������ �츮�� ���� totalPage�� �̸��̶�� totalPage�� ������ ����
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
		model.addAttribute("startPage", startPage); //����� ���������� ��
		model.addAttribute("endPage", endPage); //����� ���� ������ ��
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
		
		// ����¡ �׽�Ʈ
		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
		map.put("end", String.valueOf(pageVO.getEndPerPage()));
		
		map.putAll(paramMap);
		// �˼�
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
		
		m.addAttribute("startPage", startPage); // ��Ͽ� ���������� ��
		System.out.println("1");
		m.addAttribute("endPage", endPage); // ��Ͽ� ���� ��������
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
		
		//MultipartFrile��ü���� �̸� Ȯ��
		MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();
		System.out.println("oriFn : " + oriFn);
		
		// ��� �׽�Ʈ: �̹����� ������ ���
		String img_path = "resources\\imgfile" ;
		
		//��Ŭ���� �� ������ �̹��� ���
		String r_path = request.getSession().getServletContext().getRealPath("/");
		System.out.println("r_path : " + r_path);
		
		// �̹��� ������ �� contentType Ȯ��
		long size = mf.getSize();
		String contentType = mf.getContentType();
		//contentType�� ����->ī�信 ����. Ȯ��
		System.out.println("����ũ�� : " + size);
		System.out.println("������ type : " + contentType);
				
		// �޸𸮻�(�ӽ������)�� ������ �츮�� ������ ��ο� �����ϰڴ�.		
		// �̹����� ����� ��� �����
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(img_path).append("\\");
		path.append(oriFn);
		System.out.println("FullPath : " + path);		
		
		// �߻���(�̹����� ������ ���) File ��ü�� ����
		// File Ŭ������ �޼���� �ݵ�� ������ ��*****
		File f = new File(path.toString());

		// �ӽ� �޸𸮿� ���(���ε��� ������ ��) -> FileŬ������ ��η� ����
		try {
			mf.transferTo(f);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		} 
		
		//Dao ������ �Է�ó��
		vo.setImgn(oriFn);
		
		upBoardDaoInter.upboardUpdate(vo);
		
		return "redirect:boardDetail?num="+vo.getNum() ;
	}
	
	@PostMapping("/upboardDelete")
	public String upboardDelete(int num) {
		upBoardDaoInter.upboardDelete(num);
		
		return "redirect:uplist" ;
	}
	
	
	//����Է�ó��
	//form�� action, method�� Ȯ���Ͽ� �ۼ�
	@PostMapping("/bcominsert")
	public String bcominsert(BoardCommVO cvo, Model model) {
		upBoardDaoInter.addBoardComm(cvo);
		return "redirect:boardDetail?num="+cvo.getUcode()+"&type=comm#comm" ;
	}
	
	
	public String listCommBoard() {
		
		
		return "redirect:boardDetail?num=" ;
	}
	
	
	
}
