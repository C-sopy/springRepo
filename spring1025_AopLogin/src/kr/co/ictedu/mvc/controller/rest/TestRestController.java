package kr.co.ictedu.mvc.controller.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ictedu.mvc.dto.JsonDTO;

@RestController
public class TestRestController {

	@GetMapping(value="/restHello", produces="application/json; charset=euc-kr")
	public String viewMessage() {
		return "bonjour";
	}
	
	// VO�� ��ȯ�ϴ� JSON -> {"key1" : val1, "key2" : val2}
	@GetMapping(value="/restJsonDTO", produces="application/json; charset=utf-8")
	public JsonDTO viewJsonDto() {
		JsonDTO vo = new JsonDTO("la mer �ٴ�", 1000);
		
		return vo ;
	}
	
	//List<JSON> -> [{"key1" : val1, "key2" : val2}, {"key1" : val1, "key2" : val2}]
	// viewListJsonDto
	@GetMapping(value="/viewListJsonDto", produces="application/json; charset=utf-8")
	public List<JsonDTO> viewListJsonDto() {
		List<JsonDTO> json = new ArrayList<JsonDTO>();
		String[] key = {"�ٴ�", "��", "���"};		
		int[] val = {10, 20, 30};
		for(int i = 0; i<key.length; i++) {
			JsonDTO vo = new JsonDTO(key[i], val[i]);
			json.add(vo);
		}
		return json ;
	}
	@GetMapping(value="/jsonTest1", produces="application/json; charset=utf-8")
	public String surveyDetail_title() {
		List<JsonDTO> list = new ArrayList<JsonDTO>();
		String[] key = {"mer", "��", "movie", "maison", "cafe"};
		int[] val = {10, 20, 30, 40, 50};
		
		for(int i = 0; i<key.length; i++) {
			JsonDTO vo = new JsonDTO(key[i], val[i]);
			list.add(vo);
		}
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(JsonDTO e : list) {
			map.put(e.getTitle(), e.getCnt());
		}
		System.out.println("size : "+ list.size());
		String result = null ;
		ObjectMapper mapper = new ObjectMapper();
		try {
			//map�� ���ڿ� json object�� ������ ��������ִ� ������ ��
			result = mapper.writeValueAsString(map);
			System.out.println(result);
			result = "[{\"question\" : \"�����ϴ� ��Ҵ� ?\"}, " + result + "]";
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result ;
		
	}
}
