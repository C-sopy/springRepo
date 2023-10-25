package kr.co.ictedu.mvc.dao;

import java.util.List;
import java.util.Map;

import kr.co.ictedu.mvc.dto.MemberVo;

public interface MemberDaoInter {
	public void add(MemberVo vo);
	public int idCheck(String id);
	public MemberVo loginCheck(MemberVo v);
	public MemberVo myPage(String id) ;
	public List<MemberVo> memList(Map<String, String> map);
	public int getcnt();
	//·Î±×ÀÎ ·Î±ë

}
