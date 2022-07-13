package controllers.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import newlecture.dao.NoticeDao;
import newlecture.vo.Notice;

// JSP의 ListHandler
// 공지사항 목록을 가져오는 컨트롤러
public class NoticeController implements Controller{
	
	private NoticeDao noticeDao;
	
	public NoticeController() {
		super();
	}

	public NoticeController(NoticeDao noticeDao) {
		super();
		this.noticeDao = noticeDao;
	}

	public NoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	// contextPath/customer/notice.htm?page=?&field=?&query=?   / field는 검색조건
	// page 기본은 1, field 기본은 title, query는 null
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// JSP 때는 Service -> DAO 호출
		
		String ppage = request.getParameter("page");
		String pfield = request.getParameter("field");
		String pquery = request.getParameter("query");
		
		int page = 1;
		String field = "title", query="";
		
		if ( ppage != null && !ppage.equals("") ) { page = Integer.parseInt( ppage ); }
	    if ( pfield != null && !pfield.equals("") ) { field = pfield;  }
	    if ( pquery != null && !pquery.equals("") ) { query = pquery;  }
	    
	    ModelAndView mv = new ModelAndView();
	    // request.setAttribute("test", "Hello, Spring MVC World!") 코딩과 같은 의미
	    mv.addObject("test", "Hello, Spring MVC World!"); 
	    
	    // noticeDao.getNotices() 메서드 호출
	    List<Notice> list = this.noticeDao.getNotices(page, field, query);
	    mv.addObject("list", list); // request.setAttribute("list", list) 코딩과 같은 의미
	    
	    mv.setViewName("notice.jsp"); // customer 폴더 안에 있음
		
		return mv;
	}

} // class
