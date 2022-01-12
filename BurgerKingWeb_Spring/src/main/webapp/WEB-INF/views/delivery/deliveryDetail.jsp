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
<script src="script/index.js" type="text/javascript"></script>
<link rel="icon" href="image/main/favicon.ico">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Nanum+Myeongjo:wght@800&family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<body>
<div class="popWrap">
	<div class="popbox01 nobtn">
		<div class="M_headerWrap">
			<div class="titleBar">
				<div class="page_tit w_alignL"><span>메뉴 선택</span></div>
			</div>
		</div>
		<div class="popcont nopadding">
			<div class="prd_intro">
				<div class="prd_img">
					<span><img src="image/menu/product/${pvo.image}" style="display: inline; opacity: 1;"></span>
				</div>
				<div class="intro_txt">
					<h3 class="tit"><span>${pvo.pname }</span></h3>
					<p class="subtxt">${pvo.content }</p>
				</div>
			</div>
		</div>
		<ul class="menu_sub_list02" style="background: #f2ebe6;">
			<c:forEach var="list2" items="${list1}">
				<li>
					<div class="prd_img">
						<span><img src="image/menu/${list2.kind1}/${list2.image}" style="display: inline; opacity: 1;"></span>
					</div>
					<div class="cont">
						<div class="tit"><strong>${list2.pname}</strong></div>
						<div class="set"><span>${list2.content }</span></div>
						<div class="price" ><strong style="color:red;font-weight:bold">&#8361;${list2.price1}</strong></div>
					</div>
					<div>
						<button type="button" class="btn_detail" onclick="add_or_cart('${list2.kind1}','${list2.pseq}')"></button>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
</body>
</html>