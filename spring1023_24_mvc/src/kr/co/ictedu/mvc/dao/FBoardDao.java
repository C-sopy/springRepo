package kr.co.ictedu.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ictedu.mvc.dto.FboardDTO;
//Dao���� @Repository �ڿ� ���� ���� ����ؼ� ���
@Repository
public class FBoardDao implements FBoardDaoInter {
	// DI���Թޱ� - �ڵ�bean����, byName
	@Autowired
	private SqlSessionTemplate ss ;

	@Override
	public void addFBoard(FboardDTO vo) {
		ss.insert("fb.add", vo);
	}

	@Override
	public List<FboardDTO> listFBoard() {
		return ss.selectList("fb.list");
	}

	@Override
	public void updateHit(int num) {
		ss.update("fb.hit", num);
	}

	@Override
	public FboardDTO detailFboard(int num) {
		return ss.selectOne("fb.detail", num);
	}

	@Override
	public void deleteFboard(int num) {
		ss.delete("fb.del", num);
	}

	@Override
	public void updateFboard(FboardDTO vo) {
		ss.update("fb.update", vo);
	}

}