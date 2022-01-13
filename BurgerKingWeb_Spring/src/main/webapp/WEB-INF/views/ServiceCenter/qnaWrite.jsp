<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<article>
	<div class="location">
	<div class="web_container1">
		<ul>
			<li><a href="main">HOME</a>&nbsp;>&nbsp;</li>
			<li>고객센터 &nbsp;>&nbsp;</li>
			<li>문의</li>
		</ul>
	</div>
	</div>
	
	<div class="subtitWrap">
		<h3 class="page_tit">고객센터</h3>
		<ul id="terms_wrap_ul" style="right: 0; top:0">
			<li>
				<span>
					<a href="faqForm&fnum=1">FAQ</a>
				</span>
			</li>
			<li><span><a href="qnaForm" style="color: red; text-decoration: underline;">문의</a></span></li>
			<li><span><a href="appGuideForm">App이용안내</a></span></li>
		</ul>
	</div>
	
	<div class="qna_webcon">
		<h2>1:1 고객 게시판</h2>
		<h3>고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>
		<form name="frm" method="post" action="qnaWrite">
			<input type="hidden" name="command" value="qnaWrite">
			<input type="hidden" name="id" value="${id}">
			<fieldset style="border: 0px; background-color: #f2ebe6;">
				<legend>QnA Write</legend>
				<label>제목</label><br>
				<input type="text" name="subject" size="100"><br>
				<label>질문내용</label>
				<textarea rows="10" cols="120" name="content"></textarea>
				<label>비밀번호</label>
				<input type="password" name="pass" size="4">
			</fieldset>
			<div class="clear"></div>
			 
			<div class="qna_button_area">
				<div class="tab_cont">
					<div class="faq_category">
					<label>${message}</label>
						<label class="switch01">
							<input type="radio" name="faqcategory" checked="checked" onclick="qna_write_chk()">
							<span>작성</span>
						</label>
						<label class="switch01">
							<input type="radio" name="faqcategory" onclick="location.href='qnaForm'">
							<span>취소</span>
						</label>
					</div>
				</div>
			</div>
		</form>
	</div>
</article>

<%@ include file="../include/undermenu.jsp" %>
<%@ include file="../include/footer.jsp" %>