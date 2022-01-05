<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<form name="frm" method="post" enctype="multipart/form-data">
		<table id="list" border="1" height="300">
			<tr>
				<th>상품명</th>
				<td width="343" colspan="5">
					<input type="text" name="pname" size="60">
				</td>
				<th>사용유무</th>
				<td>
					<input type="checkbox" name="useyn" value="1" checked="checked">
				</td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td colspan="7" width="343">
					<input type="file" name="image" class="image_icon" style="font-size:5px;">
				</td>
			</tr>
			<tr>
				<th>상품분류</th>
				<td colspan="7">
					<select name="kind1" class="kind1">
						<option value="">선택</option>
						<c:forEach items="${kindList}" var="kind" varStatus="status">
							<option value="${status.count}">${kind}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>가격</th>
				<td colspan="4">
					<input type="text" name="price1" size="30">
				</td>
				<th>분류번호</th>
				<td colspan="2">
					<input type="text" name="kind2" size="3">
				</td>
			</tr>
		</table>
		<input type="button" class="btn" value="등록" onclick="go_save2()">
		<input type="button" class="btn" value="목록" onclick="go_mov2()">
	</form>
</article>

<%@ include file="../../admin/footer.jsp"%>