package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import newlecture.dao.NoticeDao;
import newlecture.vo.Notice;

@Controller
@RequestMapping("/customer/*") // 공통적인 경로를 컨트롤러에 매핑
public class CustomerController {
	
	@Autowired
	private NoticeDao noticeDao;
	
	// 디폴트 생성자
	public CustomerController() {
		super();
	}
	
	// 생성자
	public CustomerController(NoticeDao noticeDao) {
		super();
		this.noticeDao = noticeDao;
	}

	// getter
	public NoticeDao getNoticeDao() {
		return noticeDao;
	}

	// setter
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
		
	@RequestMapping("noticeDel.htm")
	public String noticeDel(String seq) throws Exception{
		
	    int rowCount = this.noticeDao.delete(seq);
	    
		return "redirect:notice.htm";
		
	} // noticeDel
	
	// /customer/noticeEdit.htm?seq=4 -> URL로 파라미터 넘어오고 POST 방식으로 title, content 넘어옴
	@RequestMapping(value = {"noticeEdit.htm"}, method = RequestMethod.POST)
	public String noticeEdit(Notice notice) throws Exception{
		
		int rowCount = this.noticeDao.update(notice);
		
		if(rowCount == 1) {
			return "redirect:noticeDetail.htm?seq="+notice.getSeq();
		} else {
			return "noticeDetail.jsp?error"; 
		}
	} // noticeEdit
	
	@RequestMapping(value = {"noticeEdit.htm"}, method = RequestMethod.GET)
	public String noticeEdit(String seq, Model model) throws Exception{
		
	    Notice notice = this.noticeDao.getNotice(seq);
	    model.addAttribute("notice", notice);
	    
		return "noticeEdit.jsp";
	} // noticeEdit
	
	// noticeReg.htm 요청이 GET 방식으로 요청을 하면 해당 컨트롤러 메서드 실행
	@RequestMapping(value = {"noticeReg.htm"}, method = RequestMethod.GET)
	public String noticeReg() throws Exception{
		return "noticeReg.jsp";
	} // noticeReg
	
	// [3]
	// noticeReg.htm 요청이 POST 방식으로 요청을 하면 해당 컨트롤러 메서드 실행
	@RequestMapping(value = {"noticeReg.htm"}, method = RequestMethod.POST)
	public String noticeReg(Notice notice) throws Exception{ // 커맨드 객체
		
		notice.setWriter("yaliny"); // 작성자는 나중에 로그인 인증으로 id 값 가져옴
		int rowCount = this.noticeDao.insert(notice); // 자동으로 노티스 객체로 들어가짐
		
		if(rowCount == 1) {
			return "redirect:notice.htm";
		} else {
			return "noticeReg.jsp?error"; 
		}
		
	} // noticeReg
	
	/*
	// [2]
	// noticeReg.htm 요청이 POST 방식으로 요청을 하면 해당 컨트롤러 메서드 실행
	@RequestMapping(value = {"noticeReg.htm"}, method = RequestMethod.POST)
	public String noticeReg(String title, String content) throws Exception{
		
		Notice notice = new Notice();
		
		notice.setWriter("yaliny"); // 원래는 로그인해서 세션에서 id를 가져와야 함(yaliny라는 member를 테이블에 insert 함)
		notice.setTitle(title);
		notice.setContent(content);
		
		int rowCount = this.noticeDao.insert(notice);
		
		if(rowCount == 1) {
			// 리다이렉트 : 공지사항 목록
			// JSP : response.sendRedirect("list.do") 
			// 스프링에서 리다이렉트? redirect:접두어를 붙인다.
			return "redirect:notice.htm";
		} else {
			return "noticeReg.jsp?error"; // 글쓰기 페이지에 error 파라미터가 넘어오면 경고창 띄운다.
		}
		
	} // noticeReg
	*/
	
	/*
	// [1]
	// noticeRef 컨트롤러 메서드 선언 + GET 방식
	@RequestMapping("noticeReg.htm")
	public String noticeReg(HttpServletRequest request) throws Exception{
		String requestMethod = request.getMethod(); // GET인지 POST인지 체크해서 처리를 할 수 있음
		if(requestMethod.equals("GET")) {
			return "noticeReg.jsp";
		} else if(requestMethod.equals("POST")) {
			return null;
		} else {
			return null;
		}
		
	} // noticeReg
	*/
	
	// [3]
	@RequestMapping("notice.htm")
	public String notices(HttpServletRequest request, HttpServletResponse response, Model model
			// ?page=1&field=title&query=hh 이렇게 넘어오는데
			// page라는 파라미터로 넘어오는 값을 ppage에 받고싶다면 @RequestParam 애노테이션을 사용하여 value 지정
			// 넘어오는 값이 없을 때 기본값을 지정하려면 @RequestParam 애노테이션 defaultValue 속성에 값을 지정
			// 자료형을 바꾸면 자동으로 파싱시켜줌
			, @RequestParam(value="page", defaultValue = "1") int ppage 
			, @RequestParam(value="field", defaultValue = "title") String pfield
			, @RequestParam(value="query", defaultValue = "") String pquery
			) throws Exception{
	    	    
		model.addAttribute("test", "Hello, Spring MVC World!"); 
	    // noticeDao.getNotices() 메서드 호출
	    List<Notice> list = this.noticeDao.getNotices(ppage, pfield, pquery);
	    model.addAttribute("list", list);
		
		return "notice.jsp";
	} // notices

	/*
	// [2]
	@RequestMapping("notice.htm")
	public ModelAndView notices(HttpServletRequest request, HttpServletResponse response
			, String page // 넘겨져오는 파라미터 이름과 동일해야 자동으로 가져옴
			, String field
			, String query
			) throws Exception{
		
		// String ppage = request.getParameter("page");
		// String pfield = request.getParameter("field");
		// String pquery = request.getParameter("query");
		
		int _page = 1;
		String _field = "title", _query="";
		
		// 파라미터 값이 없으면 변수로 초기화한 값으로 설정
		if ( page != null && !page.equals("") ) { _page = Integer.parseInt(page); }
	    if ( field != null && !field.equals("") ) { _field = field;  }
	    if ( query != null && !query.equals("") ) { _query = query;  }
	    
	    ModelAndView mv = new ModelAndView();
	    
	    mv.addObject("test", "Hello, Spring MVC World!"); 
	    
	    // noticeDao.getNotices() 메서드 호출
	    List<Notice> list = this.noticeDao.getNotices(_page, _field, _query);
	    mv.addObject("list", list);
	    
	    mv.setViewName("notice.jsp");
		
		return mv;
	} // notices
	*/
	
	/*
	// [1]
	// 공지사항 목록을 가져오는 [컨트롤러 메서드] <- NoticeController.java 클래스가 메서드로 전이
	// @RequestMapping("/customer/notice.htm")
	@RequestMapping("notice.htm")
	public ModelAndView notices(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String ppage = request.getParameter("page");
		String pfield = request.getParameter("field");
		String pquery = request.getParameter("query");
		
		int page = 1;
		String field = "title", query="";
		
		if ( ppage != null && !ppage.equals("") ) { page = Integer.parseInt( ppage ); }
	    if ( pfield != null && !pfield.equals("") ) { field = pfield;  }
	    if ( pquery != null && !pquery.equals("") ) { query = pquery;  }
	    
	    ModelAndView mv = new ModelAndView();
	    
	    mv.addObject("test", "Hello, Spring MVC World!"); 
	    
	    // noticeDao.getNotices() 메서드 호출
	    List<Notice> list = this.noticeDao.getNotices(page, field, query);
	    mv.addObject("list", list);
	    
	    mv.setViewName("notice.jsp");
		
		return mv;
	} // notices
	*/
	
	// [2]	
	// p356 컨트롤러 메서드의 파라미터 타입 + 리턴 타입
	@RequestMapping(value="noticeDetail.htm")
	public String noticeDetail(HttpServletRequest request, HttpServletResponse response
			, Model model, HttpSession session
			, String seq // ?seq=1
			) throws Exception {
	    
		// 매개변수에 String seq를 주면 아래처럼 가져오지 않아도 자동으로 seq 매개변수에 저장이 됨
	    // String seq = request.getParameter("seq");

	    Notice notice = this.noticeDao.getNotice(seq); // 글번호에 해당하는 공지사항을 가져옴
	    
	    // 자동으로 jsp 뷰에 데이터를 전달하기 위해 저장하는 객체
	    model.addAttribute("notice", notice); 
	    
		// session.getAttribute("logonID"); // 로그인한 id값을 체크할 수 있음
	    
		return "noticeDetail.jsp"; 
	}
	
	/*
	// [1]
	// 공지사항 상세보기하는 컨트롤러 메서드 <- NoticeDetailController.java 클래스가 메서드로 전이
	// @RequestMapping(value="/customer/noticeDetail.htm")
	@RequestMapping(value="noticeDetail.htm")
	public ModelAndView noticeDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    
	    String seq = request.getParameter("seq");

	    Notice notice = this.noticeDao.getNotice(seq); // 글번호에 해당하는 공지사항을 가져옴
	    
	    ModelAndView mv = new ModelAndView();
	    
	    mv.addObject("notice", notice); 
	    mv.setViewName("noticeDetail.jsp");
		
		return mv;
	}
	*/

} // class

