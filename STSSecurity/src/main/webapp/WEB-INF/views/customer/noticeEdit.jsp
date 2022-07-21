<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- noticeEdit.jsp -->
<div id="content">
	<h2>공지사항</h2>
	<h3 class="hidden">방문페이지위치</h3>
	<ul id="breadscrumb" class="block_hlist">
		<li>HOME</li>
		<li>고객센터</li>
		<li>공지사항수정</li>
	</ul>
	<form action="" method="post" enctype="multipart/form-data">
		<div id="notice-article-detail" class="article-detail margin-large">
			<dl class="article-detail-row">
				<dt class="article-detail-title">제목</dt>
				<dd class="article-detail-data">
					&nbsp;<input name="title" value="${notice.title }" />
				</dd>
			</dl>
			<dl class="article-detail-row half-row">
				<dt class="article-detail-title">작성자</dt>
				<dd class="article-detail-data half-data">${notice.writer }</dd>
			</dl>
			<dl class="article-detail-row half-row">
				<dt class="article-detail-title">조회수</dt>
				<dd class="article-detail-data half-data">${notice.hit }</dd>
			</dl>
			<dl class="article-detail-row">
				<dt class="article-detail-title">첨부파일</dt>
				<dd class="article-detail-data">
					&nbsp;<input type="file" id="txtFile" name="file" />
					<!-- 해당 공지사항 글이 첨부파일이 있는지 여부를 확인하기 위해서 아래 input 태그 추가 -->
					<input type="text" name="o_filesrc" value="${notice.filesrc }" />
				</dd>
			</dl>

			<div class="article-content">
				<textarea id="txtContent" class="txtContent" name="content">${notice.content }</textarea>
			</div>
		</div>
		<p class="article-comment margin-small">
			<input type="submit" class="btn-save button" value="수정" />
			<!-- <a class="btn-save button" href="noticeEditProc.jsp">수정</a> -->
			<!-- a는 GET 방식 -->
			<!-- 자바스크립트로 하는게 더 맞을 거 같음 -->
			<a class="btn-cancel button"
				href="noticeDetail.htm?seq=${notice.seq }">취소</a>
		</p>
	</form>
</div>
