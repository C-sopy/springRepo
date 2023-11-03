package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ictedu.mvc.dto.BoardCommVO;
import kr.co.ictedu.mvc.dto.BoardVO;
@Repository
public class BoardDao implements BoardDaoInter{
	
	@Autowired
	private SqlSessionTemplate ss;
	
	@Override
	public void boardAdd(BoardVO bvo) {
		ss.insert("board.add", bvo);
	}

	@Override
	public List<BoardVO> boardList(Map<String, String> map) {
		return ss.selectList("board.list", map);
	}

	@Override
	public int getTotal(Map<String, String> map) {
		return ss.selectOne("board.totalCount", map);
	}

	@Override
	public BoardVO boardDetail(int num) {
		BoardVO vo = ss.selectOne("board.detail", num);
		String[] imglist = vo.getImgn().split(";");
		vo.setImglist(imglist);
		return vo;
	}

	@Override
	public void boardDelete(int num) {
		ss.delete("board.delete",num);
	}

	@Override
	public void boardUpdate(BoardVO bvo) {
		ss.update("board.update", bvo);
	}

	@Override
	public void hitUpdate(int num) {
		ss.update("board.updateHit",num);
	}

	// comment
	@Override
	public void commAdd(BoardCommVO bcvo) {
		ss.insert("board.addComm", bcvo);
	}
	
	@Override
	public List<BoardCommVO> commList(Map<String, String> map) {
		return ss.selectList("board.listComm", map);
	}

	@Override
	public int getCommTotal(Map<String, String> map) {
		return ss.selectOne("board.getCommTotal", map);
	}

	
	
}
