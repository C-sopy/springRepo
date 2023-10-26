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
		//��ó��
		//1. �α���, �α׾ƿ��� �޼��尡 ȣ��ǰ� �� ��ȯ���� �ޱ� ���ؼ� ����
		ModelAndView rpath = null ;		
		//2. �޼����� �̸��� �����ͼ� �α��ΰ� �α׾ƿ��� ����
		String methodName = pjp.getSignature().getName();		
		//3. ��������Ʈ�κ��� Ÿ�� ��ü �޼����� ���ڰ� �޾ƿ���
		Object[] fd = pjp.getArgs(); //?
		
		if(methodName.equals("loginfProcess")) {
			// �����Ͻ� ���� �޼��带 ȣ��****
			try {
				rpath = (ModelAndView) pjp.proceed(); //loginf*()
			} catch (Throwable e) {
				e.printStackTrace();
			}	
			System.out.println("�α��� �� �α� �޾ƿ���");
			HttpSession session = (HttpSession) fd[0];
			String uid = (String) session.getAttribute("sessionID");
			String reip = ((HttpServletRequest)fd[1]).getRemoteAddr();
			userAgent = (String) fd[3]; // �̰�  �����ϴ��� else if������ �־����� ArrayIndexOutOfBoundsException ������ �߻���. 
										// �Ƹ� ���⼭ �̹� �ʱ�ȭ �ߴµ� �ؿ��� �� �ϴ¹ٶ��� ����� �����ε�
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
				System.out.println("��ó�� ���� ����");
			}
					
			
		}else if(methodName.equals("loginfoutProcess")) {
			//��ó���� ����� �α�
			System.out.println("��ó�� �α��۾�");
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
			
			// �����Ͻ� ���� �޼��带 ȣ��****
			try {
				rpath = (ModelAndView) pjp.proceed(); //loginf*()
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}else {
			
		}
		
		//��ó��
		return rpath ;
		
	}
	
	public String patternUserAgent(String userAgent) {
		Pattern mp = Pattern.compile("(Mobile|Android|iPhone|iPod|Macintosh)");
		Matcher mc = mp.matcher(userAgent);
		boolean res = mc.find();
		System.out.println(res);
		StringBuilder sb = new StringBuilder();
		if(res) {
			System.out.println("����Ͽ��� ����");
			sb.append("mobile").append("/");
		}else {
			System.out.println("pc����");
			sb.append("PC").append("/");
		}
		// \\d:����(digit)
		// +| :�ٷ� ���� ��Ұ� �ϳ� �̻� �ݺ���
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
