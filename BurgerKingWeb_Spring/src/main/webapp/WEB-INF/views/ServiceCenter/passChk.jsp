<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
		<div style="text-align: center">
			<form name="frm" method="post" action="passChk">
				<input type="hidden" name="qseq" value="${qseq}">
				<span>
					<h2>작성 시 입력했던 비밀번호 4자리를 입력해주세요.</h2><br>
					<label style="color: red;">${message}</label><br><br>
					<input type="password" name="pass" size="4" style="height: 30px;">
					<input type="submit" class="qna_btn01" value="확인">
					<input type="button" class="qna_btn01" value="뒤로가기" onclick="location.href='qnaForm'">
				</span>
			</form>
		</div>
<%@ include file="../include/undermenu.jsp" %>
<%@ include file="../include/footer.jsp" %>