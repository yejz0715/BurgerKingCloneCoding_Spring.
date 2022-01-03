<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>딜리버리</title>
		<link href="css/burger.css" rel="stylesheet">
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="script/burger.js" type="text/javascript"></script>
		<link rel="icon" href="image/main/favicon.ico">
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Nanum+Myeongjo:wght@800&family=Noto+Sans+KR&display=swap" rel="stylesheet">
	</head>
	<body style="margin-top:168px;"  onload="play()">
		<div id="header_container" style="background-color: #e22219;">
			<div class="web_container">
			
				<c:choose>
					<c:when test="${empty loginUser}">
						<h1 class="WEB_logo">
							<a href="loginForm"><span>버거킹</span></a>
						</h1>
					</c:when>
					<c:otherwise>
						<h1 class="WEB_logo">
							<a href="deliveryForm&kind1=1"><span>버거킹</span></a>
						</h1>
					</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${empty loginUser}">
						<div class="WEB utilWrap">
							<a href="burger.do?command=index"><span>브랜드홈</span></a>
							<a style="display: none;"><span>로그아웃</span></a>
							<a style="display: none;"><span>MY킹</span></a>
							<a href="burger.do?command=loginForm&non=1"><span>로그인</span></a>
							<a href="faqList1"><span>고객센터</span></a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="WEB utilWrap">
							<a href="burger.do?command=index"><span>브랜드홈</span></a>
							<a href="burger.do?command=logout"><span>로그아웃</span></a>
							<a href="burger.do?command=deliveryMypageForm"><span>MY킹</span></a>
							<a style="display: none;"><span>로그인</span></a>
							<a href="burger.do?command=faqForm&fnum=1"><span>고객센터</span></a>
						</div>
						<div class="WEB user">
							<p><span>${loginUser.name}</span>님 안녕하세요</p>
							<a href="burger.do?command=deliveryMypageForm"><strong>MY킹 바로가기</strong></a>
						</div>
					</c:otherwise>
				</c:choose>
				
				<c:if test="${empty loginUser}">
					<a id="delivery_Signup_btn" href="joinForm"> 
						<img src="image/main/deliverysignup.PNG" width="160" height="50" />
					</a>
				</c:if>
			</div>
		</div>
		<%-- <c:if test="${!empty loginUser}">
			<%@ include file="../Delivery/deli_orderCart.jsp"%>		
		</c:if> --%>
