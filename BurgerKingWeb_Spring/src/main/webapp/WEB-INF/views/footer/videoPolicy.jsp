<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<article style="background-color: #f2ebe6;">
	<div class="location">
	<div class="web_container1">
		<ul>
			<li><a href="index">HOME</a>&nbsp;>&nbsp;</li>
			<li>개인정보취급방침</li>
		</ul>
	</div>
	</div>
	<div class="wrap">
		<ul id="terms_wrap_ul">
			<li><span><a href="terms">이용약관</a></span></li>
			<li><span><a href="privacy" style="color: red; text-decoration: underline;">개인정보취급방침</a></span></li>
			<li><span><a href="legal">법적고지</a></span></li>
		</ul>
		<h1 class="big_h1">이용약관 및 정책</h1>
		
		<div class="btnarea">
				<span><input class="btn_c1" type="button" style="width: 185px;" 
				onclick="location.href='privacy'" value="개인정보처리방침"></span>
				<span><input class="btn_c1" type="button" style="background-color: #292929; width: 292px;"  
				onclick="location.href='videoPolicy'" value="영상정보처리기기운영관리방침"></span>
		</div>
		
		<div class="textarea">
			<%@ include file="text2-2.jsp" %>
		</div>
	</div>
	
</article>

<%@ include file="../include/undermenu.jsp" %>
<%@ include file="../include/footer.jsp" %>