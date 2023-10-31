package kr.co.ictedu.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ictedu.mvc.dto.BoardCommVO;
import kr.co.ictedu.mvc.dto.BoardImageVO;
import kr.co.ictedu.mvc.dto.BoardVO;
import kr.co.ictedu.mvc.dto.BoardVideoVO;
@Repository
public class BoardDao implements BoardDaoInter{
	
	@Autowired
	private SqlSessionTemplate ss;
	
	@Override
	public void boardAdd(BoardVO bvo) {
		ss.insert("board.add", bvo);
		
	}

	@Override
	public void boardAddImg(List<BoardImageVO> list) {
		ss.insert("board.addimg", list);
	}

	@Override
	public void boardAddVideo(BoardVideoVO bvvo) {
		ss.insert("board.addvideo", bvvo);
	}

	
	
}
