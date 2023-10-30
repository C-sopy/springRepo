package kr.co.ictedu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternDemo1 {
	public static void main(String[] args) {
		//String userAgent = "Mozilla/5.0 (Linux; Android 12; SAMSUNG SM-G977N) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/22.0 Chrome/111.0.5563.116 Mobile Safari/537.36";
		String userAgent = "user Agent : Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36";
		Pattern mp = Pattern.compile("(Mobile|Android|iPhone|iPod|Macintosh)");
		Matcher mc = mp.matcher(userAgent);
		boolean res = mc.find();
		System.out.println(res);
		
		if(res) {
			System.out.println("����Ͽ��� ����");
		}else {
			System.out.println("pc����");
		}
		// \\d:����(digit)
		// +| :�ٷ� ���� ��Ұ� �ϳ� �̻� �ݺ���
		Pattern mp1 = Pattern.compile("(Window NT[\\d.]+|Android [\\d.]+|iPhone)");
		Matcher mc1 = mp1.matcher(userAgent);		
		
		if(mc1.find()) {
			String device = mc1.group();
			System.out.println(device);
		}
	}
}
