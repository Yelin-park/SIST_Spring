package newlecture.vo;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Notice {
	private String seq;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int hit;
	private String filesrc; // 첨부된 파일의 경로를 테이블에 저장할 컬럼
	
	// 커맨드 객체를 통해서 파일 업로드 처리를 하기 위한 필드가 필요
	// <input type="file" id="txtFile" name="file" /> 인풋태그의 name 속성 값과 필드 이름을 동일하게 해주어야함
	private CommonsMultipartFile file;
	
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getFilesrc() {
		return filesrc;
	}
	public void setFilesrc(String filesrc) {
		this.filesrc = filesrc;
	}	
	
}
