package kr.co.ictedu.admin.survey;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.admin.service.SurveyService;
import kr.co.ictedu.mvc.dto.SurveyContentVO;
import kr.co.ictedu.mvc.dto.SurveyVO;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	@Autowired
	private SurveyService surveyService ;
	
	@RequestMapping(value = "/surveyForm")
	public String surform() {
				
		return "/survey/surveyAddform" ;
	}
	
	@PostMapping(value = "/addsurvey")
	public ModelAndView addsurvey(SurveyVO vo, HttpServletRequest request) {
		//같은 이름으로 넘어오는 파라미터는 배열로 받을 수 있다.
		String[] surveytitle = request.getParameterValues("surveytitle");
		
		//DB에 전달하기 위한 List만들기
		List<SurveyContentVO> list = new ArrayList();
		char stype = 'A' ;
		for(String e : surveytitle) {
			SurveyContentVO sv = new SurveyContentVO();
			sv.setSurveytitle(e);
			sv.setSurveycnt(0);
			sv.setSubtype(String.valueOf(stype));
			System.out.println("survetTitle => "+e);
			list.add(sv);
			stype++ ; //알파벳을 증가
		}
		System.out.println("sub => "+ vo.getSub());
		// SurveyVO에 설문 타이틀을 저장한 list<SurveyContentVO> 인자로 전달
		vo.setSurvey(list);
		// service에 전달
		surveyService.addSurvey(vo, list);
		ModelAndView mav = new ModelAndView("redirect:surveylist");
		return mav ;
	}
	
	@RequestMapping(value = "/surveylist")
	public ModelAndView surveylist() {
		ModelAndView mav = new ModelAndView("survey/surveylist");
		mav.addObject("list", surveyService.listSurvey());
		return mav ;
	}
	
	@RequestMapping(value = "/surveyAdminDetail")
	public ModelAndView surveyDetail(int num){
		ModelAndView mav = new ModelAndView("survey/surveyDetail");
		SurveyVO vo = surveyService.adminDetail(num);
		mav.addObject("vo", vo);
		return mav ;
	}
	
	
}
