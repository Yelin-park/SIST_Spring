package org.doit.web.newlecture.dao;

import java.sql.SQLException;

import org.doit.web.newlecture.vo.Member;

public interface MemberDao {
	// 자동으로 public abstract이기 때문에 public 지워도 상관없음
	// 멤버 정보를 가져오는 메서드
	public Member getMember(String id) throws ClassNotFoundException, SQLException;
	
	// 회원가입메서드
	public int insert(Member member) throws ClassNotFoundException, SQLException;

}
