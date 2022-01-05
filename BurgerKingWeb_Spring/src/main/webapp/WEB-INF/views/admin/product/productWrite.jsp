<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<form name="frm" method="post" enctype="multipart/form-data">
		<table id="list" border="1">
			<tr>
				<th>상품분류</th>
				<td>
					<select name="kind1">
						<option value="">선택</option>
						<c:forEach items="${kindList}" var="kind" varStatus="status">
							<option value="${status.count}">${kind}</option>
						</c:forEach>
					</select>
				</td>
				<th>분류번호</th>
				<td>
					<input type="text" name="kind2" size="3">
				</td>
				<th>세부</th>
				<td>
					<input type="text" name="kind3" size="3">
				</td>
				<th>가격</th>
				<td width="70"><input type="text" name="price1" size="10"></td>
			</tr>
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
				<th>상세설명</th>
				<td colspan="7">
					<textarea rows="8" cols="80" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td colspan="7" width="343">
					<input type="file" name="image">
				</td>
			</tr>
		</table>
		<input type="button" class="btn" value="등록" onclick="go_save()">
		<input type="button" class="btn" value="목록" onclick="go_mov()">
	</form>
</article>

<%@ include file="../../admin/footer.jsp"%>