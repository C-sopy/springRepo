package kr.co.ictedu.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ictedu.mvc.dao.BoardDaoInter;
import kr.co.ictedu.mvc.dto.BoardImageVO;
import kr.co.ictedu.mvc.dto.BoardVO;
import kr.co.ictedu.mvc.dto.BoardVideoVO;

@Service
public class BoardService {
	@Autowired
	private BoardDaoInter dao;
	
	@Transactional
	public void addBoard(BoardVO vo, List<BoardImageVO> list, BoardVideoVO bvvo) {
		dao.boardAdd(vo);
		dao.boardAddImg(list);
		dao.boardAddVideo(bvvo);
	}
	

}
