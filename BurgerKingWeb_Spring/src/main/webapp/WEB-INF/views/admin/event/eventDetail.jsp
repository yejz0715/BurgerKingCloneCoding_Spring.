<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<h1>이벤트 상세 보기</h1>
	<table id="list" border="1">
		<tr>
			<th>이벤트 번호</th>
			<td colspan="5">${eventVO.eseq}</td>
		</tr>
		<tr>
			<th align="center">이벤트명</th>
			<td colspan="5">${eventVO.subject}</td>
		</tr>
		<tr>
			<th>시작일</th><td>${eventVO.startdate}</td>
			<th>종료일</th><td>${eventVO.enddate}</td>
			<c:choose>
				<c:when test="${eventVO.state == 0}">
					<th>상태</th><td>종료</td>
				</c:when>
				<c:otherwise>
					<th>상태</th><td>진행중</td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<th>상세설명</th>
			<td colspan="5"><textarea rows="12" cols="80">${eventVO.content}</textarea></td>
		</tr>
		<tr>
			<th>이벤트이미지</th>
			<td colspan="5" align="center">
				<img src="image/main/event/eventDetail/${eventVO.image}" width="200px">
			</td>
		</tr>
	</table>
	<input type="button" class="btn" value="수정" onclick="go_event_mod('${eventVO.eseq}')">
	<input type="button" class="btn" value="목록" onclick="go_event_mov()">
</article>

<%@ include file="../../admin/footer.jsp"%>