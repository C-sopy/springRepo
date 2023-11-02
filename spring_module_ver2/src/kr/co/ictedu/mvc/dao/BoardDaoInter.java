package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.ictedu.mvc.dto.BoardCommVO;
import kr.co.ictedu.mvc.dto.BoardVO;

public interface BoardDaoInter {
	public void boardAdd(BoardVO bvo);
	public List<BoardVO> boardList(Map<String, String> map);
	public int getTotal(Map<String, String> map);
	public BoardVO boardDetail(int num);
	public void boardDelete(int num);
	
	//update
	public void boardUpdate(BoardVO bvo);
	public void hitUpdate(int num);

	// comment
	public void commAdd(BoardCommVO bcvo);
	public List<BoardCommVO> commList(Map<String, String> map);
	public int getCommTotal(Map<String, String> map);
}
