<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<h1>이벤트 수정</h1>
	<form name="frm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="eseq" value="${eventVO.eseq}">
		<input type="hidden" name="oldImage" value="${eventVO.image}">
		<table id="list" width="500" border="1">
			<tr>
				<th>이벤트명</th>
				<td colspan="7">
					<input type="text" name="subject" value="${eventVO.subject}">
				</td>
				<th>종료일</th>
				<td>
					<input type="text" name="enddate" size="80" value="${eventVO.enddate}">
				</td>
			</tr>
			<tr>
				<th>상세설명</th>
				<td colspan="9">
					<textarea rows="8" cols="80" name="content">${eventVO.content}</textarea>
				</td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td colspan="9">
					<img src="image/main/event/eventDetail/${eventVO.image}" width="200px"><br>
					<input type="file" name="image">
				</td>
			</tr>
		</table>
		<input type="button" class="btn" value="수정" onclick="go_event_mod_save()">
		<input type="button" class="btn" value="취소" onclick=
		"location.href='adminEventDetail?eseq=${eventVO.eseq}'">
	</form>
</article>

<%@ include file="../../admin/footer.jsp"%>