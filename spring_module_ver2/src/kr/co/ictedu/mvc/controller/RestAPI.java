package kr.co.ictedu.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ictedu.mvc.dao.BoardDaoInter;
import kr.co.ictedu.mvc.dto.BoardCommVO;

@RestController
@RequestMapping("/api/board")
public class RestAPI {
	@Autowired
    private BoardDaoInter dao;

    @GetMapping("/boardcommlist")
    public ResponseEntity<Map<String, Object>> getUpboardList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) int num,            
            @RequestParam(required = false) String searchValue,
            @RequestParam(required = false) Integer searchType) {

        int itemsPerPage = 10; // 페이지당 보여줄 게시물 수
        int offset = (page - 1) * itemsPerPage;

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("begin", String.valueOf(offset + 1));
        paramMap.put("end", String.valueOf(offset + itemsPerPage));
        paramMap.put("searchValue", searchValue);
        paramMap.put("searchType", String.valueOf(searchType));
        paramMap.put("num", String.valueOf(num));
        for (Map.Entry<String, String> e : paramMap.entrySet()) {
			System.out.println(e.getKey() + "," + e.getValue());
		}
        List<BoardCommVO> commList = dao.commList(paramMap);

        int totalCount = dao.getCommTotal(paramMap);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("commList", commList);
        responseMap.put("totalCount", totalCount);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

}
