package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ictedu.mvc.dto.MemberVo;

@Repository
public class MemberDao implements MemberDaoInter {
	@Autowired
	private SqlSessionTemplate ss;

	@Override
	public void add(MemberVo vo) {
		ss.insert("mem.add", vo);
	}

	@Override
	public int idCheck(String id) {
		return ss.selectOne("mem.idcheck", id);
	}

	//<select id="loginchk" parameterType="mvo" resultType="mvo">
	@Override
	public MemberVo loginCheck(MemberVo v) {
		return ss.selectOne("mem.loginchk", v);
	}

	@Override
	public MemberVo myPage(String id) {
		return ss.selectOne("mem.mypage", id);
	}

	@Override
	public List<MemberVo> memList(Map<String, String> map) {
		return null;
	}

	@Override
	public int getcnt() {
		return 0;
	}

}
