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
	// Dao를 단위처리하기 위한 서비스 클래스
	@Autowired
	private SurveyDao surveyDao ;
	// AOP - 보안, 로깅, 트랜잭션
	// pour faire Transaction
	// 빈에서 설정 <tx:annotation-driven>, DataSourceTransactionManager에 의해서 AOP적용
	@Transactional
	public void addSurvey(SurveyVO vo, List<SurveyContentVO> list) {
		// 단위처리 적용 !
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
