<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>버거킹</title>
		<link href="css/burger.css" rel="stylesheet">
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="script/burger.js" type="text/javascript"></script>
		<script src="script/index.js" type="text/javascript"></script>
		<link rel="icon" href="image/main/favicon.ico">
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Nanum+Myeongjo:wght@800&family=Noto+Sans+KR&display=swap" rel="stylesheet">
		<script type="text/javascript">
			function sizeup(){
				var ul1 = document.getElementById('ul1');
				var con = ul1.parentNode.parentNode.parentNode;
				con.style.height = '442px';
			}
	
			function sizedown(){
				var ul1 = document.getElementById('ul1');
				var con = ul1.parentNode.parentNode.parentNode;
				con.style.height = '120px';
			}
		</script>
	</head>
	<body>
		<div class="header_container">
			<header>
				<div id="web_container">
					<a id="logo" href="burger.do?command=index"> 
					<img src="image/main/burgerkinglogo.png" style="vertical-align: middle; width:83; height:82;"/>
					</a>
		
					<div class="top_menu">
						<ul onmouseover="sizeup()" onmouseleave="sizedown()" id="ul1">
							<li class="menu_item"><span>메뉴소개</span>
						    <ul class="header_submenu">
								<li><a href="menuListForm?kind1=1"><span>스페셜&amp;할인팩</span></a></li>
								<li><a href="menuListForm?kind1=2"><span>프리미엄</span></a></li>
								<li><a href="menuListForm?kind1=3"><span>와퍼</span></a></li>
								<li><a href="menuListForm?kind1=4"><span>주니어&amp;버기</span></a></li>
								<li><a href="menuListForm?kind1=5"><span>올데이킹&amp;치킨버거</span></a></li> 
								<li><a href="menuListForm?kind1=6"><span>사이드</span></a></li>
								<li><a href="menuListForm?kind1=7"><span>음료&amp;디저트</span></a></li>
								<li><a href="menuListForm?kind1=8"><span>독퍼</span></a></li>
							</ul> 
							</li>
							<li class="menu_item"><span>이벤트</span>
								<ul class="header_submenu">
									<li><a href="eventListForm"><span>이벤트</span></a></li>
								</ul>
							</li>
							
							<li class="menu_item">
								<span>브랜드스토리</span>
								<ul class="header_submenu">
									<li><a href="brandStroyForm"><span>버거킹스토리</span></a></li>
								</ul>
							</li>
						</ul>
					</div>
					
					<c:choose>
						<c:when test="${empty loginUser}">
							<a id="order_btn" href="loginForm&non=1"> 
								<img	src="image/main/delivery1.png" width="160" height="50" />
							</a>
						</c:when>
						<c:otherwise>
							<a id="order_btn" href="deliveryForm&kind1=1"> 
								<img src="image/main/delivery1.png" width="160" height="50" />
							</a>
						</c:otherwise>
					</c:choose>
				</div>	
			</header>
		</div>
