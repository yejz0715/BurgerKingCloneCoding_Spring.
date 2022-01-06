<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Meterial</title>
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
	<div class="popbox01">
		<div class="pop_header01 st02">
			<div class="titleBar">
				<h1>재료를 추가해 더 맛있게 즐겨보세요!</h1>
			</div>
		</div>
		<div class="popcont bg_basic"> 
			<ul class="menu_sub_list check_mode">
				<c:forEach items="${subProductVO}" var="subproductVO" >
					<li>
						<input type="checkbox" name="Meterial" value="${subproductVO.spseq}" title="재료추가" class="check03">
						<div class="prd_img">
							<img src = "image/menu/plus/${subproductVO.image}" alt="재료">
						</div>
						<div class="cont">
							<p class="tit"><span>${subproductVO.sname}</span></p>
							<p class="price"><span>${subproductVO.addprice}</span></p>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="pop_btn c_btn item2">
			<button type="button" class="btn02 dark m_btn01_s" onclick="go_cart('${pseq}')">
				<span>추가안함</span>
			</button>
			<button type="button" class="btn02 red m_btn01_s" onclick="go_add_Meterial('${pseq}')">
			<span>추가함</span></button>
		</div>
	</div>
</div>
</body>
</html>