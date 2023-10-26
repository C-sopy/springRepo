package kr.co.ictedu.mvc.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.mvc.dao.MemberDaoInter;
import kr.co.ictedu.mvc.dto.MemberVO;
import kr.co.ictedu.mvc.dto.MyLoginLoggerVO;

@Component
@Aspect
public class LoginAdvice {
	
	@Autowired
	private MemberDaoInter memberdaointer ;
	private String userAgent, resAgent ;	
	
	@Around("execution(* kr.co.ictedu.mvc.controller.member.LoginCheckController.loginf*(..))")
	public ModelAndView loginLogger(ProceedingJoinPoint pjp ) {
		//전처리
		//1. 로그인, 로그아웃의 메서드가 호출되고 난 반환값을 받기 위해서 선언
		ModelAndView rpath = null ;		
		//2. 메서드의 이름을 가져와서 로그인과 로그아웃을 구분
		String methodName = pjp.getSignature().getName();		
		//3. 조인포인트로부터 타겟 객체 메서드의 인자값 받아오기
		Object[] fd = pjp.getArgs(); //?
		
		if(methodName.equals("loginfProcess")) {
			// 비지니스 모델의 메서드를 호출****
			try {
				rpath = (ModelAndView) pjp.proceed(); //loginf*()
			} catch (Throwable e) {
				e.printStackTrace();
			}	
			System.out.println("로그인 후 로깅 받아오기");
			HttpSession session = (HttpSession) fd[0];
			String uid = (String) session.getAttribute("sessionID");
			String reip = ((HttpServletRequest)fd[1]).getRemoteAddr();
			userAgent = (String) fd[3]; // 이걸  복붙하느라 else if문에도 넣었더니 ArrayIndexOutOfBoundsException 오류가 발생함. 
										// 아마 여기서 이미 초기화 했는데 밑에서 또 하는바람에 생기는 오류인듯
			resAgent = patternUserAgent(userAgent);
			System.out.println("uid : " + uid);
			System.out.println("reip : " + reip);
			System.out.println("user Agent : " + userAgent);
			System.out.println(resAgent);
			System.out.println("****************");
			if(uid != null) {
				MyLoginLoggerVO lvo = new MyLoginLoggerVO();
				lvo.setIdn(uid);
				lvo.setStatus("login");
				lvo.setReip(reip);
				lvo.setUagent(resAgent);
				memberdaointer.addLoginLogging(lvo);
			}else {
				System.out.println("후처리 실행 안함");
			}
					
			
		}else if(methodName.equals("loginfoutProcess")) {
			//전처리를 사용한 로깅
			System.out.println("전처리 로깅작업");
			HttpSession session = (HttpSession) fd[0];
			String uid = (String) session.getAttribute("sessionID");
			String reip = ((HttpServletRequest)fd[1]).getRemoteAddr();
			System.out.println("uid : " + uid);
			System.out.println("reip : " + reip);
			System.out.println("user Agent : " + userAgent);
			System.out.println("****************");
			MyLoginLoggerVO lvo = new MyLoginLoggerVO();
			lvo.setIdn(uid);
			lvo.setStatus("logout");
			lvo.setReip(reip);
			lvo.setUagent(resAgent);
			memberdaointer.addLoginLogging(lvo);
			
			// 비지니스 모델의 메서드를 호출****
			try {
				rpath = (ModelAndView) pjp.proceed(); //loginf*()
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}else {
			
		}
		
		//후처리
		return rpath ;
		
	}
	
	public String patternUserAgent(String userAgent) {
		Pattern mp = Pattern.compile("(Mobile|Android|iPhone|iPod|Macintosh)");
		Matcher mc = mp.matcher(userAgent);
		boolean res = mc.find();
		System.out.println(res);
		StringBuilder sb = new StringBuilder();
		if(res) {
			System.out.println("모바일에서 접속");
			sb.append("mobile").append("/");
		}else {
			System.out.println("pc접속");
			sb.append("PC").append("/");
		}
		// \\d:숫자(digit)
		// +| :바로 앞의 요소가 하나 이상 반복됨
		Pattern mp1 = Pattern.compile("(Window NT[\\d.]+|Android [\\d.]+|iPhone)");
		Matcher mc1 = mp1.matcher(userAgent);		
		
		if(mc1.find()) {
			String device = mc1.group();
			System.out.println(device);
			sb.append(device);
		}
		
		return sb.toString() ;
	}
}
