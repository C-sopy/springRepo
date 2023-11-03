package kr.co.ictedu.mvc.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	// DB data
	private int num;
	private String title;
	private String writer;
	private String content;
	private int hit ;
	private String reip;
	private String bdate;
	private String imgn ; // 이미지 파일 이름
	private String vidn ; // 영상 파일 이름
	
	// view data
	private List<MultipartFile> mflist; // 이미지 파일 리스트
	private MultipartFile mfile; // 영상 파일
	private String[] imglist ; //이미지 파일 배열
	private int cnt ;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getReip() {
		return reip;
	}
	public void setReip(String reip) {
		this.reip = reip;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public List<MultipartFile> getMflist() {
		return mflist;
	}
	public void setMflist(List<MultipartFile> mflist) {
		this.mflist = mflist;
	}
	public String getImgn() {
		return imgn;
	}
	public void setImgn(String imgn) {
		this.imgn = imgn;
	}
	public String getVidn() {
		return vidn;
	}
	public void setVidn(String vidn) {
		this.vidn = vidn;
	}
	public MultipartFile getMfile() {
		return mfile;
	}
	public void setMfile(MultipartFile mfile) {
		this.mfile = mfile;
	}
	public String[] getImglist() {
		return imglist;
	}
	public void setImglist(String[] imglist) {
		this.imglist = imglist;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	
	
}
