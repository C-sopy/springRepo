package kr.co.ictedu.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ictedu.mvc.dto.BoardImageVO;
import kr.co.ictedu.mvc.dto.BoardVO;
import kr.co.ictedu.mvc.dto.BoardVideoVO;
import kr.co.ictedu.mvc.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;

	@RequestMapping(value = "/boardForm")
	public String boardForm() {
		return "board/boardform";
	}

	// uploadpro
	@PostMapping("/uploadpro")
	public String uploadFile(Model m, BoardVO vo, HttpServletRequest request) {
		// parameter 테스트
		System.out.println("나 왔다");
		BoardVideoVO bvvo = videoUpload(vo, request);
		System.out.println("비디오 왔다");
		List<BoardImageVO> list = uploadImage(vo, request);
		System.out.println("이미지 왔다");
		
		service.addBoard(vo, list, bvvo);
		return "redirect:upList";
	}
	
	public BoardVideoVO videoUpload(BoardVO vo, HttpServletRequest request) {
		System.out.println("비디오 안에 왔다");
		BoardVideoVO bvvo = new BoardVideoVO();
		MultipartFile vfile = vo.getVfile();
		String oriVFn = vfile.getOriginalFilename();
		
		String video_path = "resources\\videofile";
		
		String r_path = request.getSession().getServletContext().getRealPath("/");
		
		long size = vfile.getSize();
		String contentType = vfile.getContentType();
//			* contentType의 종류
//			- application/vnd.ms-excel
//			- application/pdf
//			- text/plain
//			- application/haansofthwp
//			- image/jpeg
		System.out.println("file size: " + size);
		System.out.println("file type: " + contentType);
		
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(video_path).append("\\");
		path.append(oriVFn);
		System.out.println("FullPath: " + path);
		
		File f = new File(path.toString());

		// 임시 메모리에 담긴 즉 업로드한 파일의 값 -> File클래스의 경로로 복사
		try {
			vfile.transferTo(f);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		
		bvvo.setVname(oriVFn);
		bvvo.setReip(vo.getReip());
		
		return bvvo;
	}
	
	public List<BoardImageVO> uploadImage(BoardVO vo, HttpServletRequest request){
		System.out.println("이미지 안에 왔다");
		List<BoardImageVO> list = new ArrayList();
		
		List<MultipartFile> mfList = vo.getMflist();
		String img_path = "resources\\imgfile";
		String r_path = request.getSession().getServletContext().getRealPath("/");
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(img_path).append("\\");
		
		for(MultipartFile e : mfList) {
			BoardImageVO bivo = new BoardImageVO();
			String oriIFn = e.getOriginalFilename();
			long size = e.getSize();
			String contentType = e.getContentType();

			System.out.println("file size: " + size);
			System.out.println("file type: " + contentType);
			path.append(oriIFn);
			System.out.println("FullPath: " + path);
			File f = new File(path.toString());

			// 임시 메모리에 담긴 즉 업로드한 파일의 값 -> File클래스의 경로로 복사
			try {
				e.transferTo(f);
			} catch (IllegalStateException | IOException error) {
				error.printStackTrace();
			}
			
			if(list.isEmpty()) {
				bivo.setIsThumb(1);
			} else {
				bivo.setIsThumb(0);
			}
			
			bivo.setIname(oriIFn);
			bivo.setReip(vo.getReip());
			
			list.add(bivo);
		}
		
		return list;
	}

}
