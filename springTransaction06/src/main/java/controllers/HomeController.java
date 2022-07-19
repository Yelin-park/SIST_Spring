package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/") // 공통적인 경로를 컨트롤러에 매핑
public class HomeController {

	// 디폴트 생성자
	public HomeController() {
		super();
	}

	// 요청URL이 http://localhost/springTransaction/index.htm 으로 왔을 때 메인페이지를 돌려주는 컨트롤러 메서드 생성
	@RequestMapping("index.htm")
	public String home() throws Exception{
		
		return "index.jsp";
		
	}

} // class

