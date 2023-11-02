package kr.co.ictedu.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ictedu.mvc.dao.BoardDaoInter;
import kr.co.ictedu.mvc.dto.BoardCommVO;
import kr.co.ictedu.mvc.dto.BoardVO;
import kr.co.ictedu.mvc.dto.PageVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardDaoInter dao;
	@Autowired
	private PageVO pageVO;

	////------------ �Խñ� �輺 -----------////
	// �ۼ� �� �ҷ�����
	@RequestMapping(value = "/boardForm")
	public String boardForm() {
		return "board/boardForm";
	}
	//���� ���ε�
	@PostMapping(value = "/uploadpro")
	public String uploadFile(Model m, BoardVO bvo, HttpServletRequest request) {
		String r_path = request.getSession().getServletContext().getRealPath("/");

		bvo.setVidn(uploadVideo(bvo, r_path));
		bvo.setImgn(uploadImage(bvo, r_path));

		dao.boardAdd(bvo);
		return "redirect:boardList";
	}

	public String upload(String pathType, String r_path, MultipartFile mf) {

		String oriFn = mf.getOriginalFilename();
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(pathType).append("\\");
		path.append(oriFn);

		File f = new File(path.toString());

		try {
			if (!mf.isEmpty()) {
				mf.transferTo(f);
			}
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		return oriFn;
	}

	public String uploadVideo(BoardVO bvo, String r_path) {
		// ���� ����
		MultipartFile mfile = bvo.getMfile();
		// ���� ���� �̸�
		String video_path = "resources\\videofile";
		String vidn = upload(video_path, r_path, mfile);

		return vidn;
	}

	public String uploadImage(BoardVO bvo, String r_path) {
		List<MultipartFile> mfList = bvo.getMflist();
		String img_path = "resources\\imgfile";
		String imgn = "";

		for (MultipartFile e : mfList) {
			String oriIFn = upload(img_path, r_path, e);

			if (oriIFn != "") {
				imgn += oriIFn + ";";
			}

		}
		System.out.println(imgn);

		return imgn;
	}

	////--------- �Խñ� ��� ------------////
	@RequestMapping(value = "/boardList")
	public String boardList(Model m, @RequestParam Map<String, String> paramMap) {
		m = paging(m, paramMap);

		return "board/boardList";
	}
	
	////-------------- �Խù� �󼼺��� �� ��� ����Ʈ ------------////
	@RequestMapping(value = "/boardDetail")
	public String boardDetail(Model m, @RequestParam(required = true) int num,
			@RequestParam(defaultValue = "detail") String type, @RequestParam Map<String, String> paramMap) {
		if (!type.equals("comm")) {
			dao.hitUpdate(num);
		}
		// board detail
		BoardVO vo = dao.boardDetail(num);
		m.addAttribute("vo", vo);

//		// comment
//		Map<String, String> map = paging(paramMap);
//		List<BoardCommVO> list = dao.commList(map);
//		System.out.println("Size:" + list.size());
//		
//		m.addAttribute("startPage", startPage); // ��Ͽ� ���������� ��
//		m.addAttribute("endPage", endPage); // ��Ͽ� ���� ��������
//		m.addAttribute("page", pageVO); // nowPage, pagePerBlock, totalPage
//		m.addAttribute("listcomm", list);
//		m.addAttribute("num", num);

		return "board/boardDetail";
	}

	@RequestMapping(value = "/boardDelete")
	public String boardDelete(int num) {
		dao.boardDelete(num);
		return "redirect:boardList";
	}

	////----------- �Խñ� ���� -----------////
	// ���� �� �ҷ�����
	@RequestMapping(value = "/boardModify")
	public String boardModify(Model m, int num) {
		BoardVO vo = dao.boardDetail(num);
		m.addAttribute("vo", vo);

		return "board/boardModify";
	}

	// �����ϱ�
	// js�� �������� ���� checkbox value �� �������� �� ����
	@RequestMapping(value = "/boardEdit")
	public String boardEdit(BoardVO vo, String[] deletelist, HttpServletRequest request) {
		String r_path = request.getSession().getServletContext().getRealPath("/");

		// ������ �ִ� �̹��� ������ �޾ƿɴϴ�.
		BoardVO oldVO = dao.boardDetail(vo.getNum());
		String[] oldImgList = oldVO.getImglist();
		String oldVidn = oldVO.getVidn();
		
		// �޾ƿ� �̹��� ������ �迭�� �����ϰ� ����Ʈ�� ����ϴ�.					
		List<String> imgList = new ArrayList<String>(Arrays.asList(oldImgList));
		
		// deletelist�� checkbox���� ������ �̹��� ����Ʈ.
		if (deletelist != null) {
			for (int i = 0; i < deletelist.length; i++) {
				for (int j = 0; j < imgList.size(); j++) {
					if (deletelist[i].equals(imgList.get(j))) {
						imgList.remove(j); 
					}
				}
			}
		}
		// ���� �̹��� ���ϰ� ���� �߰��� �̹����� ���� �迭 ����.
		String[] finalImgList = imgList.toArray(String[]::new);
		
		// �����ͺ��̽��� ���� �̹����̸� ����
		String newImgn = uploadImage(vo, r_path);	
		for (String e : finalImgList) {
			newImgn += e + ";";
		}
		
		// ���� ���� �̸��� ������ �����ϸ� �״�� ����, ���� ������ ���ε��ϸ� ���ο� �̸����� ����
		String vidn = "" ;
		if(vo.getMfile().getOriginalFilename().equals("")) {
			vidn = oldVidn;
		} else {
			vidn = uploadVideo(vo, r_path);
		}
		
		//�����ͺ��̽��� ���� �̸� ����set
		vo.setVidn(vidn);
		vo.setImgn(newImgn);
		
		//������Ʈ
		dao.boardUpdate(vo);

		return "redirect:boardDetail?num=" + vo.getNum();
	}

	
	
	////--------------- ��� �ۼ� --------------////
	@PostMapping(value = "/comminsert")
	public String commentInsert(BoardCommVO bcvo, Model model) {
		dao.commAdd(bcvo);

		// type : ����ۼ� �� redirect�� �� ��ȸ���� �ø��� �ʱ� ���� ��ġ
		return "redirect:boardDetail?num=" + bcvo.getCnum() + "&type=comm#comm";
	}
	
	
	////------------ paging --------------////
	public Model paging(Model m, Map<String, String> paramMap) {

		String cPage = paramMap.get("cPage");
		System.out.println("cPage: " + cPage);

		pageVO.setTotalRecord(dao.getTotal(paramMap));
		int totalRecord = pageVO.getTotalRecord();
		System.out.println("total record: " + totalRecord);

		pageVO.setTotalPage((int) Math.ceil((totalRecord) / (double) pageVO.getNumPerPage()));
		System.out.println("total page: " + totalRecord);
		pageVO.setTotalBlock((int) Math.ceil((double) pageVO.getTotalPage() / pageVO.getPagePerBlock()));
		System.out.println("total block: " + totalRecord);
		if (cPage != null) {
			pageVO.setNowPage(Integer.parseInt(cPage));
		} else {
			pageVO.setNowPage(1);
		}

		pageVO.setBeginPerPage((pageVO.getNowPage() - 1) * pageVO.getNumPerPage() + 1);
		pageVO.setEndPerPage((pageVO.getBeginPerPage() - 1) + pageVO.getNumPerPage());
		System.out.println("beginPage: " + pageVO.getBeginPerPage());
		System.out.println("endPage: " + pageVO.getEndPerPage());

		int startPage = (int) ((pageVO.getNowPage() - 1) / pageVO.getPagePerBlock()) * pageVO.getPagePerBlock() + 1;
		int endPage = startPage + pageVO.getPagePerBlock() - 1;
		if (endPage > pageVO.getTotalPage()) {
			endPage = pageVO.getTotalPage();
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
		map.put("end", String.valueOf(pageVO.getEndPerPage()));
		map.putAll(paramMap);

		List<BoardVO> list = dao.boardList(map);
		for (BoardVO e : list) {
			String[] imglist = e.getImgn().split(";");
			e.setImglist(imglist);
		}

		m.addAttribute("boardList", list);
		m.addAttribute("searchValue", paramMap.get("searchValue"));
		m.addAttribute("searchType", paramMap.get("searchType"));

		m.addAttribute("startPage", startPage);
		m.addAttribute("endPage", endPage);
		m.addAttribute("page", pageVO);

		return m;
	}
}
