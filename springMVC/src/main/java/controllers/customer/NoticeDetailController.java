package controllers.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import newlecture.dao.NoticeDao;
import newlecture.vo.Notice;

// 상세 게시글을 가져오는 컨트롤러
public class NoticeDetailController implements Controller{
	
	private NoticeDao noticeDao;
	
	public NoticeDetailController() {
		super();
	}

	public NoticeDetailController(NoticeDao noticeDao) {
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
		
	    ModelAndView mv = new ModelAndView();
	    Notice notice = null;
	    String seq = request.getParameter("seq");

	    notice = this.noticeDao.getNotice(seq);
	    mv.addObject("notice", notice); // request.setAttribute("list", list) 코딩과 같은 의미
	    
	    mv.setViewName("noticeDetail.jsp"); // customer 폴더 안에 있음
		
		return mv;
	}

} // class
