<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/admin/header.jsp" %>
<%@ include file="/admin/sub_menu.jsp"%>

<article>
	<h1>회원 정보 수정</h1>
	<form name="frm" method="post" action="burger.do">
		<input type="hidden" name="command" value="adminMemberUpdate">
		<input type="hidden" name="mseq" value="${memberVO.mseq}">
		<input type="hidden" name="id" value="${memberVO.id}">
		<table id="list" width="500" border="1">
			<tr>
				<th>아이디</th>
				<td>
					${memberVO.id}
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td colspan="3">
					<input type="text" name="name" value="${memberVO.name}">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="pwd" size="10" value="">
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td>
					<input type="password" name="pwd_chk" size="10" value="">
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>
					<input type="text" name="phone" value="${memberVO.phone}">
				</td>
			</tr>
		</table>
		<input type="submit" class="btn" value="수정" onclick="return go_member_save();">
		<input type="button" class="btn" value="취소" onclick=
		"location.href='burger.do?command=adminMemberList'">
	</form>
</article>

<%@ include file="/admin/footer.jsp"%>