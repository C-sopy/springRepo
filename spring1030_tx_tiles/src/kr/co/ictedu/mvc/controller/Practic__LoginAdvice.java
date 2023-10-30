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
		//��ó��
		//1. �α���, �α׾ƿ��� �޼��尡 ȣ��ǰ� �� ��ȯ���� �ޱ� ���ؼ� ����
		ModelAndView rpath = null ;
		
		//2. �޼����� �̸��� �����ͼ� �α��ΰ� �α׾ƿ��� ����
		String methodName = pjp.getSignature().getName();
		System.out.println("method name => " + methodName);
		
		//3. ��������Ʈ�κ��� Ÿ�� ��ü �޼����� ���ڰ� �޾ƿ��� (HttpSession session, HttpServletRequest request, MemberVo vo, String userAgent) 4��(0~3)
		Object[] fd = pjp.getArgs(); //?
		for(Object e : fd) {
			System.out.println(e);
		}
		
		// �����Ͻ� ���� �޼��带 ȣ��****
		try {
			rpath = (ModelAndView) pjp.proceed(); //=> loginf*(..)
			HttpSession session = (HttpSession) fd[0]; // session���� �α��� �Ŀ� �޴°Ŵϱ� proceed �ؿ� �־�� session���� ������. 
														//�������� null�� �߰� �α׾ƿ� �Ҷ��� �� �� ����
														//�ٵ� ���� ���⿡ ������ �޼��尡 �α׾ƿ��ϸ鼭 session���� ����ϱ� �α׾ƿ� �ϰ� session���� null�� �����°� ����
			System.out.println("sessionID : " + session.getAttribute("sessionID"));
			System.out.println("sessionName : " + session.getAttribute("sessionName"));
			System.out.println("=================");
			
			if (methodName.equals("loginfProcess")) {
				MemberVO vo = (MemberVO) fd[2];
				System.out.println(vo.getPwd());
				System.out.println(vo.getTel()); // id�� pwd�� �޼ҵ忡�� �������µ� �������� �Ȱ����ͼ� �ȳ����°� ����
				System.out.println("=========");
			}
			
			
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		// rpath.setViewName("")���� �޼ҵ� ȣ���ϰ� �޼ҵ忡�� ������ �䰡 �ƴ϶� ���⼭ �����ؼ� �������� ���� ����
		
		//��ó��
		return rpath ;
		
	}
}
