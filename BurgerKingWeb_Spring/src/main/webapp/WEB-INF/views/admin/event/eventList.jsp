<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/admin/header.jsp" %>
<%@ include file="/admin/sub_menu.jsp"%>

<article>
	<h1>이벤트리스트</h1>
	<form name="frm" method="post">
		<table style="margin: 0 auto;">
			<tr>
				<td width="800">상품명<input type="text" name="key" value="${key}">
				<input class="btn" type="button" name="btn_search" value="검색" onclick="go_event_search();">
				<input class="btn" type="button" name="btn_total" value="전체보기" onclick="go_event_total();">
				<input class="btn" type="button" name="btn_wrt" value="이벤트등록" onclick="go_event_wrt();">
			</tr>
		</table>
		<table id="productList">
			<tr>
				<th>이벤트번호</th>
				<th>이벤트명</th>
				<th>시작일</th>
				<th>종료일</th>
				<th>상태</th>
				<th>삭제</th>
			</tr>
			<c:forEach items="${eventList}" var="eventVO">
				<tr>
					<td height="23" align="center">${eventVO.eseq}</td>
					<td style="text-align: left; padding-left: 50px;">
						<a href="#" onClick="go_event_detail('${eventVO.eseq}')">${eventVO.subject}</a>
					</td>
					<td><fmt:formatDate value="${eventVO.startdate}"/></td>
					<td><fmt:formatDate value="${eventVO.enddate}"/></td>
					<td>
						<c:choose>
							<c:when test='${eventVO.state=="0" }'>종료</c:when>
							<c:otherwise>진행중</c:otherwise>
						</c:choose>
					</td>
					<td>
						<input type="checkbox" name="delete" value="${eventVO.eseq}">
					</td>
				</tr>
			</c:forEach>
		</table><br>
		<input type="button" class="btn" style="float: right;" value="삭제" onclick="del_event();">
	</form>
	<jsp:include page="/admin/paging/paging.jsp">
		<jsp:param name="page" value="${paging.page}"/>
		<jsp:param name="beginPage" value="${paging.beginPage}"/>
		<jsp:param name="endPage" value="${paging.endPage}"/>
		<jsp:param name="prev" value="${paging.prev}"/>
		<jsp:param name="next" value="${paging.next}"/>
		<jsp:param name="command" value="burger.do?command=adminEventList"/>
	</jsp:include>
	
</article>

<%@ include file="/admin/footer.jsp"%>