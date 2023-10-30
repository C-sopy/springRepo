package kr.co.ictedu.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ictedu.admin.dao.SurveyDao;
import kr.co.ictedu.mvc.dto.SurveyContentVO;
import kr.co.ictedu.mvc.dto.SurveyVO;

@Service
public class SurveyService {
	// Dao�� ����ó���ϱ� ���� ���� Ŭ����
	@Autowired
	private SurveyDao surveyDao ;
	// AOP - ����, �α�, Ʈ�����
	// pour faire Transaction
	// �󿡼� ���� <tx:annotation-driven>, DataSourceTransactionManager�� ���ؼ� AOP����
	@Transactional
	public void addSurvey(SurveyVO vo, List<SurveyContentVO> list) {
		// ����ó�� ���� !
		surveyDao.addSurvey(vo); // commit X
		surveyDao.addServeyContent(list); // commit X
		// commit
		
	}
	
	public List<SurveyVO> listSurvey(){
		return surveyDao.listSurvey();
	}

	public SurveyVO adminDetail(int num) {
		return surveyDao.adminDetail(num);
	}
	
}
