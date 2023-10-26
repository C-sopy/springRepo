package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ictedu.mvc.dto.MemberVO;
import kr.co.ictedu.mvc.dto.MyLoginLoggerVO;

@Repository
public class MemberDao implements MemberDaoInter {
	@Autowired
	private SqlSessionTemplate ss;

	@Override
	public void add(MemberVO vo) {
		ss.insert("mem.add", vo);
	}

	@Override
	public int idCheck(String id) {
		return ss.selectOne("mem.idcheck", id);
	}

	//<select id="loginchk" parameterType="mvo" resultType="mvo">
	@Override
	public MemberVO loginCheck(MemberVO v) {
		return ss.selectOne("mem.loginchk", v);
	}

	@Override
	public MemberVO myPage(String id) {
		return ss.selectOne("mem.mypage", id);
	}

	@Override
	public List<MemberVO> memList(Map<String, String> map) {
		return null;
	}

	@Override
	public int getcnt() {
		return 0;
	}

	@Override
	public void addLoginLogging(MyLoginLoggerVO vo) {
		ss.insert("mem.logger_in", vo);
	}

	@Override
	public List<MyLoginLoggerVO> logList(String idn) {		
		return ss.selectList("mem.log_list",idn);
	}

}
