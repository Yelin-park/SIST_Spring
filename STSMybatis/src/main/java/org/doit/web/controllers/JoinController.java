package org.doit.web.controllers;

import org.apache.ibatis.session.SqlSession;
import org.doit.web.newlecture.dao.MemberDao;
import org.doit.web.newlecture.dao.NoticeDao;
import org.doit.web.newlecture.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/joinus/*") // 공통적인 경로를 컨트롤러에 매핑
public class JoinController {
	/*
	@Autowired
	private MemberDao memberDao = null;
	*/
	@Autowired
	private SqlSession sqlSession;

	// 디폴트 생성자
	public JoinController() {
		super();
	}

	// 요청URL이 http://localhost/springTransaction/join.htm
	// 요청URL이 http://localhost/springTransaction/login.htm
	@RequestMapping(value={"join.htm"}, method = RequestMethod.GET)
	public String join() throws Exception{
		
		// return "join.jsp";
		return "joinus.join";
		
	}
	
	// 입력값 => Member member 커맨드 객체 생성
	// (주의) 입력받는 태그들의 name 속성과 Member 클래스의 필드명이 반드시 동일해야한다!
	@RequestMapping(value={"join.htm"}, method = RequestMethod.POST)
	public String join(Member member) throws Exception{
		
		// this.memberDao.insert(member); // 입력받은 회원정보를 insert
		MemberDao mybatisMemberDao = this.sqlSession.getMapper(MemberDao.class);
		mybatisMemberDao.insert(member);
		return "redirect:../index.htm"; // ../를 붙이면 joinus 폴더말고 상위 폴더로 와서 index.htm을 찾음
		
	}
	
	@RequestMapping("login.htm")
	public String login() throws Exception{
		
		// return "login.jsp";
		return "joinus.login";
		
	}

} // class

