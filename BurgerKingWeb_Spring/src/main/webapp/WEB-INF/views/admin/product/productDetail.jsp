<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<h1>상품 상세 보기</h1>
	<table id="list" border="1">
		<tr>
			<th>상품분류</th>
			<td colspan="7">${kind1}</td>
		</tr>
		<tr>
			<th align="center">상품명</th>
			<td colspan="7">${productVO.pname}</td>
		</tr>
		<tr>
			<th>가격</th><td width="60">${productVO.price1}</td>
			<th>분류번호</th><td width="60">${productVO.kind2}</td>
			<th>세부</th><td>${kind3}</td>
			<th>등록일</th><td>${productVO.indate}</td>
		</tr>
		<tr>
			<th>상세설명</th>
			<td colspan="7"><pre>${productVO.content}</pre></td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td colspan="7" align="center">
				<img src="image/menu/${productVO.kind1}/${productVO.image}" width="200px">
			</td>
		</tr>
	</table>
	<input type="button" class="btn" value="수정" onclick="go_mod('${productVO.pseq}')">
	<input type="button" class="btn" value="목록" onclick="go_mov()">
</article>

<%@ include file="../../admin/footer.jsp"%>