package controllers.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import newlecture.dao.NoticeDao;
import newlecture.vo.Notice;

// 공지사항 상세보기 컨트롤러
// @Component
@org.springframework.stereotype.Controller
@RequestMapping("/customer/noticeDetail.htm") // 이 요청URL로 해당 컨트롤러를 찾음
public class NoticeDetailController implements Controller{
	
	@Autowired
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


	// contextPath/customer/noticeDetail.htm?seq=?
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    
	    String seq = request.getParameter("seq");

	    Notice notice = this.noticeDao.getNotice(seq); // 글번호에 해당하는 공지사항을 가져옴
	    
	    ModelAndView mv = new ModelAndView();
	    
	    mv.addObject("notice", notice); // request.setAttribute("list", list) 코딩과 같은 의미
	    mv.setViewName("noticeDetail.jsp");
	    // 위의 2개 코딩이 front controller에게 전달하여 front controller가 알아서 ViewResolver에게 전달
	    // ViewResolver은 기본적으로 .jsp로 등록(p386)
		
		return mv;
	}

} // class
