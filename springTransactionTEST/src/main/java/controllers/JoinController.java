package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import newlecture.dao.MemberDao;
import newlecture.vo.Member;

@Controller
@RequestMapping("/joinus/*") // 공통적인 경로를 컨트롤러에 매핑
public class JoinController {

	// 디폴트 생성자
	public JoinController() {
		super();
	}

	// 요청URL이 http://localhost/springTransaction/join.htm
	// 요청URL이 http://localhost/springTransaction/login.htm
	@RequestMapping(value={"join.htm"}, method = RequestMethod.GET)
	public String join() throws Exception{
		
		return "join.jsp";
		
	}
	
	@Autowired
	private MemberDao memberDao = null;
	
	@RequestMapping(value={"join.htm"}, method = RequestMethod.POST)
	public String join(Member member) throws Exception{
		this.memberDao.insert(member); // 입력받은 회원정보를 insert
		return "redirect:../index.htm"; // ../를 붙이면 joinus 폴더말고 상위 폴더로 와서 index.htm을 찾음
	}
	
	@RequestMapping("login.htm")
	public String login() throws Exception{
		
		return "login.jsp";
		
	}
} // class

