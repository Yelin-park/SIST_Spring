package org.doit.web.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.doit.web.newlecture.dao.NoticeDao;
import org.doit.web.newlecture.service.MemberShipService;
import org.doit.web.newlecture.vo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping("/customer/*") // 공통적인 경로를 컨트롤러에 매핑
public class CustomerController {

	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private MemberShipService memberShipService;

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

	// 컨트롤러 메서드 선언
	// download.htm
	//?
	//p=customer/upload    경로
	//&
	//f=다운로드할 파일명
	@RequestMapping("download.htm")
	public void download (
			HttpServletResponse response
			, HttpServletRequest request
			, @RequestParam("p") String p
			, @RequestParam("f") String f
			) throws Exception{

		// A(서버) -> B(클라이언트 즉, 브라우저)
		// 아이스크림을 응답 -> 클라이언트가 아이스크림인걸 알고 먹어버림
		// 그렇게 하지 못하도록 표시[상자안에 아이스크림] 담고 응답 -> 어떤건지 모르기 때문에 저장 대화상자를 띄움
		// 응답하는 헤더 값을 건드릴 것
		String fname =  f;  
		response.setHeader("Content-Disposition","attachment;filename="+ new String(fname.getBytes(), "ISO8859_1"));      

		// String fullPath = request.getServletContext().getRealPath(   p + "/" + fname);
		// .getSession() 주니 빨간 줄이 사라짐
		String fullPath = request.getSession().getServletContext().getRealPath(   p + "/" + fname);

		FileInputStream fin = new FileInputStream(fullPath);
		ServletOutputStream sout = response.getOutputStream(); // 응답 스트림
		byte[] buf = new byte[1024];
		int size = 0;
		while((size = fin.read(buf, 0, 1024)) != -1) {
			sout.write(buf, 0, size); 
		}
		fin.close();
		sout.close();
	}

	@RequestMapping("noticeDel.htm")
	public String noticeDel(String seq, String filesrc, HttpServletRequest request) throws Exception{
		// 1. 첨부파일이 있을 경우엔 첨부파일을 upload 폴더에서 삭제
		String uploadRealPath = request.getSession().getServletContext().getRealPath("/customer/upload");

		// [2] 넘어오는 URL 자체에 filesrc를 주면 DB를 한번 더 안가도됨(파라미터에 String filesrc 추가)
		File delFile = new File(uploadRealPath, filesrc);
		if(delFile.exists()) { // 파일이 존재한다면 삭제하겠다.
			delFile.delete();
		}

		/*
		// [1] 첨부파일을 select로 알아왔다.
		Notice notice = this.noticeDao.getNotice(seq);
		String filesrc = notice.getFilesrc(); // 첨부된 파일 명을 가져옴
		if(filesrc != null) {
			File delFile = new File(uploadRealPath, filesrc);
			delFile.delete();
		}
		 */

		// 2.
		int rowCount = this.noticeDao.delete(seq);
		if(rowCount == 1) return "redirect:notice.htm";
		else return "redirect:notice.htm?seq="+seq;

	} // noticeDel

	// /customer/noticeEdit.htm?seq=4 -> URL로 파라미터 넘어오고 POST 방식으로 title, content 넘어옴
	@RequestMapping(value = {"noticeEdit.htm"}, method = RequestMethod.POST)
	public String noticeEdit(Notice notice, @RequestParam("o_filesrc") String ofilesrc, HttpServletRequest request) throws Exception{

		CommonsMultipartFile multipartFile = notice.getFile();
		String uploadRealPath = request.getSession().getServletContext().getRealPath("/customer/upload");
		if(!multipartFile.isEmpty()) { // 첨부된 파일이 있다면

			// 원래 첨부된 파일 삭제
			File delFile = new File(uploadRealPath, ofilesrc);
			if(delFile.exists()) { // 파일이 존재한다면 삭제하겠다.
				delFile.delete();
			}

			String originalFilename = multipartFile.getOriginalFilename(); 
			String filesystemName = getFileNameCheck(uploadRealPath, originalFilename); 
			File dest = new File(uploadRealPath, filesystemName); 
			multipartFile.transferTo(dest); 
			String filesrc = filesystemName; 
			notice.setFilesrc(filesrc); 
		} else {
			notice.setFilesrc(ofilesrc); // 첨부된 파일이 없다면 옛날 파일 그대로 유지
		}

		// 2. 수정된 제목과 내용만 update 하고 있음
		int rowCount = this.noticeDao.update(notice);
		if(rowCount == 1) {
			return "redirect:noticeDetail.htm?seq="+notice.getSeq();
		} else {
			return "redirect:noticeDetail.htm?seq=" + notice.getSeq() + "&error"; 
		}
	} // noticeEdit

	@RequestMapping(value = {"noticeEdit.htm"}, method = RequestMethod.GET)
	public String noticeEdit(String seq, Model model) throws Exception{

		Notice notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);

		// return "noticeEdit.jsp";
		return "customer.noticeEdit";
		
	} // noticeEdit

	// noticeReg.htm 요청이 GET 방식으로 요청을 하면 해당 컨트롤러 메서드 실행
	@RequestMapping(value = {"noticeReg.htm"}, method = RequestMethod.GET)
	public String noticeReg() throws Exception{
		
		// return "noticeReg.jsp";
		return "customer.noticeReg";
		
	} // noticeReg

	// 글쓰기 + 파일 업로드
	@RequestMapping(value = {"noticeReg.htm"}, method = RequestMethod.POST)
	public String noticeReg(Notice notice, HttpServletRequest request) throws Exception{ // 커맨드 객체(업로드할 파일을 가지고 있음)
		// 1. 첨부된 파일의 유무를 확인한 후에 있다면 내가 원하는 경로에 파일을 저장하는 작업
		// VO에 CommonsMultipartFile 필드 객체가 있어서 객체를 생성하고 파일을 담음
		CommonsMultipartFile multipartFile = notice.getFile();

		// 서버 배포 실제 경로
		String uploadRealPath = null;

		if(!multipartFile.isEmpty()) { // 첨부된 파일이 있다면

			// 서버 배포 업로드할 경로
			uploadRealPath = request.getSession().getServletContext().getRealPath("/customer/upload");
			System.out.println("> uploadRealParh : " + uploadRealPath);

			// 실제 저장하는 파일 경로(이름)를 가져오기
			// A(클라이언트)가 test.txt 파일을 올리면 해당 이름 == 오리지널 파일 이름
			String originalFilename = multipartFile.getOriginalFilename(); 

			// 같은 파일명이 있다면 index를 붙이도록 직접 코딩해야함
			String filesystemName = getFileNameCheck(uploadRealPath, originalFilename); // 파일이 중복이 되는지 체크하는 함수

			// 서버 배포 실제 경로에 중복 체크한 후 생성한 파일 경로(이름)를 넣어줌
			File dest = new File(uploadRealPath, filesystemName); // 저장하는 경로
			multipartFile.transferTo(dest); // transferTo() : 내가 원하는 경로에 파일 경로(이름)을 저장하겠다는 메서드

			// 원래 올린 파일 경로(이름)는 originalFilename 이거이지만 저장한 파일 이름은 filesystemName
			// 즉, 중복 체크를 한 뒤에 생성된 이름
			String filesrc = filesystemName; // 저장할 파일 경로(이름)
			notice.setFilesrc(filesrc); // 저장할 파일 경로(이름)를 DB에 INSERT
		} // if

		// 2. Notices 테이블에 공지사항 INSERT 작업
		notice.setWriter("yaliny");
		/*
		int rowCount = this.noticeDao.insert(notice);
		if(rowCount == 1) {
			return "redirect:notice.htm";
		} else {
			return "noticeReg.jsp?error"; 
		}
		*/
		
		// 트랜잭션 처리 필요
		// this.noticeDao.insertAndPointUpOfMember(notice, "yaliny");
		this.memberShipService.insertAndPointUpOfMember(notice, "yaliny");
		
		return "redirect:notice.htm";

	} // noticeReg

	// 파일 중복 체크 검사 후 중복이 있으면 인덱스를 붙여서 돌려주는 메서드
	private String getFileNameCheck(String uploadRealPath, String originalFilename) {
		int index = 1;

		while (true) {
			File f = new File(uploadRealPath, originalFilename);

			if(!f.exists()) return originalFilename; // 파일이 존재하지 않으면 그 파일 이름 그대로 저장

			// Q. 파일 확장자 이름이 .txt라고 꼭 4자리일까? 
			// upload 폴더에 originalFilename 이름으로 파일이 존재하면
			// a.txt => a_1.txt 또는 a(1).txt 변환
			String fileName = originalFilename.substring(0, originalFilename.length() - 4); // 파일명
			String ext = originalFilename.substring(originalFilename.length() - 4); // .txt(확장자)
			originalFilename = fileName + "_" + (index) + ext; // 바꾼 파일이름이 계속해서 있다면 while 문을 돈다
			index++;
		} // while

	}

	/*
	// [2]
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
	 */

	/*
	// [1]
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

		// 스프링 타일즈 실습을 위해 코딩 수정
		// return "notice.jsp";
		return "customer.notice"; // 스프링 타일즈 ViewResolver 찾음 -> 선언하러!
		
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
	[1]
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

	// 격리성 관련하여 공지사항 상세보기 메서드 수정
	@RequestMapping(value="noticeDetail.htm")
	public String noticeDetail(HttpServletRequest request, HttpServletResponse response
			, Model model, HttpSession session
			, String seq // ?seq=1
			) throws Exception {
		
		this.noticeDao.hitUp(seq);
		Notice notice = this.noticeDao.getNotice(seq); // 글번호에 해당하는 공지사항을 가져옴
		model.addAttribute("notice", notice); 

		// return "noticeDetail.jsp"; 
		return "customer.noticeDetail"; 
	}
	
	/*
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

		return "noticeDetail.jsp"; 
	}
	*/
	
	/*
	[1]
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

