<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
function popup3() {
	
	window.open("popup3", "a",
			"width=620, height=900, resizable=no");}

 
</script>


<div class="clear"></div>
<div class="contentsWrap">
	
		<div class="location" style="margin-bottom: 0px;">
		<div class="web_container1">	
			<ul>
				<li><a href="main">HOME</a>&nbsp;>&nbsp;</li>
				<li><a href="menuListForm?kind1=${pvo.kind1}">메뉴소개</a>&nbsp;>&nbsp;</li>
				<li><a>${pvo.pname}</a></li>
			</ul>
		</div>
	</div>
	<div class="prd_intro">
		<div class="web_container4">
			<div class="intro_txt"><strong class="tit"><span>${pvo.pname}</span></strong>
			<p class="subtxt"><span></span></p></div>
			<div class="prd_img"><img src="image/menu/product/${pvo.image}" /></div>
		</div>
	</div>
	<div class="menu_subinfoWrap">
		<div class="web_container4">
			<a class="btn_back" href="menuListForm?kind1=${pvo.kind1}"><span>메뉴 목록으로 돌아가기</span></a>
			<div class="nutrition_info"><button type="button" class="btn_nutrition" onClick="popup3();">
			<span>영양분석표, 알레르기 유발성분</span></button></div>
		</div>
	</div>
	<div class="bg_w">
		<div class="web_container5">
			<ul class="prdmenu_list prd_submenu"> 
			<c:forEach var="list2" items="${list2}">
				<li>
				<div class="prd_img"><img src="image/menu/product/${list2.image}" width="296" height="193"/></div>
				<div class="cont">
					<div class="tit"><strong>${list2.pname}</strong></div>
					<div class="set"><strong>${list2.content}</strong></div>
				</div>
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>
</div>
<%@ include file="../include/undermenu.jsp" %>
<%@ include file="../include/footer.jsp" %>