package kr.co.ictedu.mvc.dto;

public class JsonDTO {
	private String title;
	private int cnt ;
	
	public JsonDTO(String title, int cnt) {
		this.title = title;
		this.cnt = cnt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}
