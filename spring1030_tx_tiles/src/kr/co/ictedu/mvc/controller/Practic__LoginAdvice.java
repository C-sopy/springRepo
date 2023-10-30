package kr.co.ictedu.mvc.controller;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.mvc.dto.MemberVO;

//@Component
//@Aspect
public class Practic__LoginAdvice {
	
	//@Around("execution(* kr.co.ictedu.mvc.controller.member.LoginCheckController.loginf*(..))")
	public ModelAndView loginLogger(ProceedingJoinPoint pjp ) {
		//전처리
		//1. 로그인, 로그아웃의 메서드가 호출되고 난 반환값을 받기 위해서 선언
		ModelAndView rpath = null ;
		
		//2. 메서드의 이름을 가져와서 로그인과 로그아웃을 구분
		String methodName = pjp.getSignature().getName();
		System.out.println("method name => " + methodName);
		
		//3. 조인포인트로부터 타겟 객체 메서드의 인자값 받아오기 (HttpSession session, HttpServletRequest request, MemberVo vo, String userAgent) 4개(0~3)
		Object[] fd = pjp.getArgs(); //?
		for(Object e : fd) {
			System.out.println(e);
		}
		
		// 비지니스 모델의 메서드를 호출****
		try {
			rpath = (ModelAndView) pjp.proceed(); //=> loginf*(..)
			HttpSession session = (HttpSession) fd[0]; // session값은 로그인 후에 받는거니까 proceed 밑에 넣어야 session값을 보여줌. 
														//위에서는 null로 뜨고 로그아웃 할때나 알 수 있음
														//근데 이제 여기에 넣으면 메서드가 로그아웃하면서 session값을 지우니까 로그아웃 하고서 session값이 null이 나오는게 정상
			System.out.println("sessionID : " + session.getAttribute("sessionID"));
			System.out.println("sessionName : " + session.getAttribute("sessionName"));
			System.out.println("=================");
			
			if (methodName.equals("loginfProcess")) {
				MemberVO vo = (MemberVO) fd[2];
				System.out.println(vo.getPwd());
				System.out.println(vo.getTel()); // id랑 pwd는 메소드에서 가져오는데 나머지는 안가져와서 안나오는게 정상
				System.out.println("=========");
			}
			
			
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		// rpath.setViewName("")으로 메소드 호출하고서 메소드에서 보내는 뷰가 아니라 여기서 조작해서 보내버릴 수도 있음
		
		//후처리
		return rpath ;
		
	}
}
