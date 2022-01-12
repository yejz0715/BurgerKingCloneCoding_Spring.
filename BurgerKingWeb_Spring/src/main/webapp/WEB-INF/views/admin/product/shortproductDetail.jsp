<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<form method="post" name="frm" action ="adminShortProductUpdateForm">
	<input type="hidden" name="k1" value="${k1}">
	<input type="hidden" name="pseq" value="${pseq}">
	<h1>상품 상세 보기</h1>
	<table id="list" border="1">
		<tr>
			<th>상품분류</th>
			<td colspan="3">${kind1}</td>
			<th>분류번호</th><td>${productVO.kind2}</td>
			<th>세부</th><td>${kind3}</td>
		</tr>
		<tr>
			<th align="center">상품명</th>
			<td colspan="5">${productVO.pname}</td>
			<th align="center">사용유무</th>
			<td colspan="1">${useyn}</td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td colspan="7" align="center">
				<img src="image/menu/product/${productVO.image}" width="200px">
			</td>
		</tr>
	</table>
	<input type="submit" class="btn" value="수정" >
	<input type="button" class="btn" value="목록" onclick="go_mov2()">
	</form>
</article>

<%@ include file="../../admin/footer.jsp"%>