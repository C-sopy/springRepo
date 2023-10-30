package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ictedu.mvc.dto.BoardCommVO;
import kr.co.ictedu.mvc.dto.BoardVO;

@Repository
public class UpBoardDao implements UpBoardDaoInter {
	@Autowired
	private SqlSessionTemplate ss ;

	@Override
	public void upboardAdd(BoardVO vo) {
		ss.insert("upboard.add", vo);
	}

	@Override
	public List<BoardVO> upboardList(Map<String, String> map) {
		return ss.selectList("upboard.list", map);
	}

	@Override
	public int getTotal(Map<String, String> map) {

		return ss.selectOne("upboard.totalCount", map); // selectOne:하나만 불러오는거 할때
	}

	@Override
	public BoardVO upboardDetail(int num) {
		
		return ss.selectOne("upboard.detail", num);
	}

	@Override
	public void upboardUpdate(BoardVO vo) {
		ss.update("upboard.update", vo);
		
	}

	@Override
	public void upboardDelete(int num) {
		ss.delete("upboard.delete",num);
		
	}

	@Override
	public void upboardHit(int num) {
		ss.update("upboard.hit", num);
		
	}

	// comment 
	
	@Override
	public void addBoardComm(BoardCommVO vo) {
		ss.insert("upboard.addComm", vo);
		
	}

	@Override
	public List<BoardCommVO> listCommBoard(Map<String, String> map) {
		
		return ss.selectList("upboard.commList",map);
	}

	@Override
	public int getCommTotal(Map<String, String> map) {
		
		return ss.selectOne("upboard.commCount", map);
	}
	
	

}
