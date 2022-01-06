<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<h1>상품 수정</h1>
	<form name="frm" method="post" action="adminShortProductUpdate" enctype="multipart/form-data">
		<input type="hidden" name="pseq" value="${productVO.pseq}">
		<input type="hidden" name="oldImage" value="${productVO.image}">
		<table id="list" width="500" border="1">
			<tr>
				<th>상품분류</th>
				<td width="50">
					<select name="kind1">
						<c:forEach items="${kindList1}" var="kind1" varStatus="status">
							<c:choose>
								<c:when test="${productVO.kind1==status.count}">
									<option value="${status.count}" selected="selected">${kind1}</option>
								</c:when>
								<c:otherwise>
									<option value="${status.count}">${kind1}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
				<th>분류번호</th>
				<td>
					<input type="text" name="kind2" size="3" maxlength="10-" value="${productVO.kind2}">
				</td>
				<th>세부</th>
				<td>
					<input type="text" name="kind3" size="3" maxlength="10-" value="${productVO.kind3}">
				</td>
				<th>사용유무</th>
				<td>
					<c:choose>
						<c:when test='${productVO.useyn == "1" }'>
							<input type="checkbox" name="useyn" value="1" checked="checked">
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="useyn" value="2">
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			<tr>
				<th>상품명</th>
				<td colspan="9">
					<input type="text" name="pname" size="80" maxlength="10-" value="${productVO.pname}">
				</td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td colspan="9">
					<img src="image/menu/${productVO.kind1}/${productVO.image}" width="200px"><br>
					<div id="imageName" style="float:left; font-size:120%;margin-right:20px;font-weight:bold"></div>
					<input type="button" class="btn" value="이미지선택" onClick="selectFile();"
					style="width:30%">
      				<input type="hidden" name="imgfilename" >* 주의 : 이미지를 수정할때에만 선택해주세요
				</td>
			</tr>
		</table>
		<input type="button" class="btn" value="수정" onclick="go_mod_save2()">
		<input type="button" class="btn" value="취소" onclick=
		"location.href='adminProductDetail?pseq=${productVO.pseq}'">
	</form>
</article>

<%@ include file="../../admin/footer.jsp"%>