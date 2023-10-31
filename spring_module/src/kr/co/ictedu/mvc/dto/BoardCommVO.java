package kr.co.ictedu.mvc.dto;

public class BoardCommVO {
   private int num;
   private int cnum;
   private String writer;
   private String contetn;
   private String reip;
   private String date;
   public int getNum() {
      return num;
   }
   public void setNum(int num) {
      this.num = num;
   }
   public int getCnum() {
      return cnum;
   }
   public void setCnum(int cnum) {
      this.cnum = cnum;
   }
   public String getWriter() {
      return writer;
   }
   public void setWriter(String writer) {
      this.writer = writer;
   }
   public String getContetn() {
      return contetn;
   }
   public void setContetn(String contetn) {
      this.contetn = contetn;
   }
   public String getReip() {
      return reip;
   }
   public void setReip(String reip) {
      this.reip = reip;
   }
   public String getDate() {
      return date;
   }
   public void setDate(String date) {
      this.date = date;
   }
   
}