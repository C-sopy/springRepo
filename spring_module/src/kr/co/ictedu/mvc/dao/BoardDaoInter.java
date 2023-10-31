package kr.co.ictedu.mvc.dao;

import java.util.List;

import kr.co.ictedu.mvc.dto.BoardCommVO;
import kr.co.ictedu.mvc.dto.BoardImageVO;
import kr.co.ictedu.mvc.dto.BoardVO;
import kr.co.ictedu.mvc.dto.BoardVideoVO;

public interface BoardDaoInter {
	public void boardAdd(BoardVO bvo);
	public void boardAddImg(List<BoardImageVO> list);
	public void boardAddVideo(BoardVideoVO bvvo);
}
