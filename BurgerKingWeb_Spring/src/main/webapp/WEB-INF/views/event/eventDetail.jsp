<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<head profile="http://www.w3.org/2005/10/profile" />
<link rel="stylesheet" type="text/css" href="/css/event.css">

<article>
	<div class="location">
	<div class="web_container1">
		<ul>
			<li><a href="index">HOME</a>&nbsp;>&nbsp;</li>
			<li><a href="eventListForm">이벤트</a>&nbsp;>&nbsp;</li>
			<li>상세</li>
		</ul>
	</div>		
	</div>
	<div class="event_web_container">
	<div class="subtit">
			<h1 class="event_tit">이벤트</h1>
	<form name="frm" method="post" action="burder.do">
	<ul>
	<li>
	<div class="detailTit">
	<input type="hidden" name="eseq" value="${EventVO.eseq}" />
	<h2>${EventVO.subject}</h2>
	<p><fmt:formatDate value="${EventVO.startdate}" type="date"/> ~
	<fmt:formatDate value="${EventVO.enddate}" type="date"/>
	</p>
	</div>
	<div class="dt_ImgCon">
	<img class="detailImg" src="image/main/event/eventDetail/${EventVO.image} "  />
	<p>${EventVO.content}</p></div>
	</li>
	</ul>
	</form>
	</div>
	</div>
	
<div class="qr">

	<p><strong style="color:red;">버거킹 APP </strong>다운받고
	<br>
	<strong style="color:red;">더 많은 할인 혜택</strong>을
	<br>
	만나보세요!
	</p>
	<div class="qrCode">
	<a href="https://play.google.com/store/apps/details?id=kr.co.burgerkinghybrid&hl=ko">
	<img src="image/main/appQR.png">
	</a>
	</div>
	
	</div>
	<div class="c_btnItem2">
		<input type="button" class="btn01 m" onclick="location.href='eventListForm'" value="목록으로"/>
	</div>
	</article>
	<!--  
	  <div class="qr">
	<p><strong>버거킹 APP </strong>다운받고
	<br>
	<strong>더 많은 할인 혜택</strong>을
	<br>
	만나보세요!
	</p>
	<div class="qrCode">
	<img src="image/main/appQR.png">
	</div>
	</div>
	<a href="https://play.google.com/store/apps/details?id=kr.co.burgerkinghybrid&hl=ko">
	<img src="image/main/event/list_button" />
	</a>
	
	-->
	
	
	<%@ include file="../include/undermenu.jsp" %>
<%@ include file="../include/footer.jsp"%>