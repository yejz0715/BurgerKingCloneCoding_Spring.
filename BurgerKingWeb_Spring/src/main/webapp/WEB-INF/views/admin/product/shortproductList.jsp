<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<h1>상품리스트</h1>
	<form name="frm" method="post">
		<table style="margin: 0 auto;">
			<tr>
				<td width="800">상품명<input type="text" name="key" value="${key}">
				<input class="btn" type="button" name="btn_search" value="검색" onclick="go_search2();">
				<input class="btn" type="button" name="btn_total" value="전체보기" onclick="go_total2();">
				<input class="btn" type="button" name="btn_wrt" value="세부품목보기" onclick="location.href='adminProductList?page=1&key='">
				<input class="btn" type="button" name="btn_wrt" value="상품등록" onclick="go_wrt2();">
			</tr>
		</table>
		<table id="productList">
			<tr>
				<th>번호</th>
				<th>상품명</th>
				<th>상품분류</th>
				<th>분류번호</th>
				<th>세부</th>
				<th>사용유무</th>
				<th>삭제<input type="checkbox" name="deleteAll" onclick="deleteAllCheck(this);"></th>
			</tr>
			<c:forEach items="${shortproductList}" var="productVO">
				<tr>
					<td height="23" align="center">${productVO.pseq}</td>
					<td style="text-align: left; padding-left: 50px;">
						<a href="#" onClick="go_detail2('${productVO.pseq}')">${productVO.pname}</a>
					</td>
					<td><fmt:formatNumber value="${productVO.kind1}"/></td>
					<td><fmt:formatNumber value="${productVO.kind2}"/></td>
					<td><fmt:formatNumber value="${productVO.kind3}"/></td>
					<td>
						<c:choose>
							<c:when test='${productVO.useyn==2 }'>미사용</c:when>
							<c:otherwise>사용</c:otherwise>
						</c:choose>
					</td>
					<td>
						<input type="checkbox" name="delete" value="${productVO.pseq}">
					</td>
				</tr>
			</c:forEach>
		</table><br>
		<input type="button" class="btn" style="float: right;" value="삭제" onclick="del_product();">
	</form>
	
	<jsp:include page="../../admin/paging/paging.jsp">
		<jsp:param name="page" value="${paging.page}"/>
		<jsp:param name="beginPage" value="${paging.beginPage}"/>
		<jsp:param name="endPage" value="${paging.endPage}"/>
		<jsp:param name="prev" value="${paging.prev}"/>
		<jsp:param name="next" value="${paging.next}"/>
		<jsp:param name="command" value="adminShortProductList"/>
	</jsp:include>
	
</article>

<%@ include file="../../admin/footer.jsp"%>