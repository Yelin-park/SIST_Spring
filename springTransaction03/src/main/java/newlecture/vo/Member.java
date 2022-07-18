package newlecture.vo;

import java.util.Date;

public class Member {

	// 필드
	private String id; // uid -> id
	private String pwd;
	private String name;
	private String gender;
	private String birth;
	private String is_lunar; // isLunar -> is_lunar
	private String cphone; // cPhone -> cphone
	private String email;
	private String habit;
	private Date   regdate; // regDate -> regdate

	// 트랜잭션 처리를 위한 point 컬럼 추가
	private int point;

	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	// getter,setter	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getIs_lunar() {
		return is_lunar;
	}
	public void setIs_lunar(String is_lunar) {
		this.is_lunar = is_lunar;
	}
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHabit() {
		return habit;
	}
	public void setHabit(String habit) {
		this.habit = habit;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
