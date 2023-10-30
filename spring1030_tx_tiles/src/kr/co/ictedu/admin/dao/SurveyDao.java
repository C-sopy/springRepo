package kr.co.ictedu.admin.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ictedu.mvc.dto.SurveyContentVO;
import kr.co.ictedu.mvc.dto.SurveyVO;

@Repository
public class SurveyDao {
	@Autowired
	private SqlSessionTemplate ss ;
	
	public void addSurvey(SurveyVO vo) {
		ss.insert("survey.add", vo);
	}
	
	public void addServeyContent(List<SurveyContentVO> list) {
		ss.insert("survey.addcontent", list);
	}
	
	public List<SurveyVO> listSurvey(){
		return ss.selectList("survey.listSurvey");
	}

	public SurveyVO adminDetail(int num) {
		SurveyVO vo = ss.selectOne("survey.adminDetail", num);
		//°Ë¼ö
		List<SurveyContentVO> list = vo.getSurvey();
		System.out.println("SurveyContentVO size : " + list.size());
		return vo;
	}
	
}
