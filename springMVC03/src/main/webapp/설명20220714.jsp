<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>2022. 7. 13. - 오후 12:35:27</title>
<link rel="shortcut icon" type="image/x-icon" href="../images/SiSt.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<h3>설명20220714.jsp</h3>
<pre>
	springMVC02 프로젝트를 복사 붙여넣기해서 springMVC03 프로젝트 생성
	
	[단점 또는 문제점 파악]
	/customer/notice.htm -> NoticeController.java
	/customer/noticeDetail.htm -> NoticeDetailController.java
	> 하나의 기능별로 컨트롤러를 만들어야함
	
	----
	==> controllers.customer 패키지 안에
		ㄴ NoticeController.java와 NoticeDetailController.java 파일 삭제
	
	==> controllers 패키지 생성
		ㄴ 공지사항 컨트롤러메서드를 가지고 있는 클래스 1개	 - CustomerController.java
		ㄴ 회원 컨트롤러메서드를 가지고 있는 클래스 1개
		
	공지사항 -> NoticeController.java 하나만 생성(별도의 컨트롤러를 만드는 것이 아니라)
	기능들은 컨트롤러 메서드로 만들것
	 목록		컨트롤러 메서드
	 상세보기		컨트롤러 메서드
	 쓰기
	 수정
	 삭제
	
	==> p356 컨트롤러 메서드의 파라미터 타입과 리턴 타입

-------------------	
	[p356 컨트롤러 메서드의 파라미터 타입과 리턴 타입]
	컨트롤러 메서드 수정
		ㄴ String notices(@RequestRaram() int page, String field, String query, Model model){}
		ㄴ String norices(String seq, Model model){}
	
	공지사항 목록 - notices(){}
	공지사항 상세 - noticeDetail(){}
	공지사항 쓰기
		notice.jsp에서
			<a class="btn-write button" href="noticeReg.htm">글쓰기</a> .jsp를 .htm으로 수정
		noticeReg.htm 요청에 해당하는 컨트롤러 메서드 생성
		noticeReg.jsp
		
	(JSP/Servlet 때는
	Write.do 요청으로 GET방식으로 글쓰기 창(write.jsp)을 보여주고
	저장 버튼을 누르면 Write.do 요청으로 입력 받은 값을 가지고 POST 방식으로 DB에 새 글이 저장 후 글목록(list.jsp)을 보여줌)
	
	[공지사항 쓰기]
	글을 입력한 후에 저장하기 버튼을 클릭하는 작업
	/customer/noticeReg.html + 첨부파일 POST 방식 요청
		제목 title
		첨부파일 X
		내용 content
		작성자/작성일/조회수/비밀번호 등등 작업은 왜 안하냐?
		? 스프링을 사용해서 인증처리해야 가능하도록 나중에
		
	[공지사항 수정]
	공지사항 상세보기(noticeDetail.jsp) - 수정하기 버튼
	/customer/noticeEdit.htm?seq=1(수정할 공지사항 글 번호)
	 - 컨트롤러 메서드 선언 : noticeEdit
		1) 파라미터로 넘어오는 seq에 해당되는 글을 Notice 객체 생성
		2) noticeEdit.jsp 이동
		3) 제목, 내용 수정 후 저장하기 버튼 -> noticeDetail.jsp 상세 보기 페이지 이동
		
	[공지사항 삭제]
	<a class="btn-del button" href="noticeDel.htm?seq=${notice.seq }">삭제</a>
	noticeDel() 컨트롤러 메서드
	수정/삭제 권한 있는지 체크 X == 로그인 인증 정보에 따라 수정버튼/삭제버튼 활성화, 비활성화 되어있음
	인증에 따라 수정, 삭제 활성화/비활성화
	공지사항 목록으로 이동 redirect:notices.htm
		
</pre>
</body>
</html>