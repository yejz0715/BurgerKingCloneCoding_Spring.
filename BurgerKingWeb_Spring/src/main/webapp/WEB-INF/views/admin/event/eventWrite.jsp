<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<form name="frm" method="post" enctype="multipart/form-data">
		<table id="list" border="1">
			<tr>
				<th>이벤트명</th>
				<td>
					<input type="text" name="subject">
				</td>
				<th>종료일</th>
				<td>
					<input type="text" name="enddate">
				</td>
			</tr>
			<tr>
				<th>상세설명</th>
				<td colspan="3">
					<textarea rows="8" cols="80" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<th>썸네일</th>
				<td colspan="3">
					<input type="file" name="thumbnail">
				</td>
			</tr>
			<tr>
				<th>상세이미지</th>
				<td colspan="3">
					<input type="file" name="image">
				</td>
			</tr>
		</table>
		<input type="button" class="btn" value="등록" onclick="go_event_save()">
		<input type="button" class="btn" value="목록" onclick="go_event_mov()">
	</form>
</article>

<%@ include file="../../admin/footer.jsp"%>