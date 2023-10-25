package kr.co.ictedu.mvc.dao;

import java.util.List;

import kr.co.ictedu.mvc.dto.FboardDTO;

public interface FBoardDaoInter {
	public void addFBoard(FboardDTO vo); //입력
	public List<FboardDTO> listFBoard(); // 리스트
	public void updateHit(int num); //hit
	public FboardDTO detailFboard(int num); //상세보기 및 수정
	public void deleteFboard(int num); //삭제
	public void updateFboard(FboardDTO vo); //수정
}
