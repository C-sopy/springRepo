package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.ictedu.mvc.dto.BoardCommVO;
import kr.co.ictedu.mvc.dto.BoardVO;
// 구현 클래스 : UpBoardDao 정의하기
public interface UpBoardDaoInter {
	public void upboardAdd(BoardVO vo);
	public List<BoardVO> upboardList(Map<String, String> map);
	public int getTotal(Map<String, String> map);
	public BoardVO upboardDetail(int num);
	public void upboardUpdate(BoardVO vo);
	public void upboardDelete(int num);
	public void upboardHit(int num);
	
	//comment
	public void addBoardComm(BoardCommVO vo);
	public List<BoardCommVO> listCommBoard(Map<String, String> map);
	public int getCommTotal(Map<String, String> map);
}
