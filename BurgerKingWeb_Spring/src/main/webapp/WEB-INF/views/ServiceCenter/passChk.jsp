<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
		<div style="text-align: center">
			<form name="frm" method="post" action="burger.do">
				<input type="hidden" name="command" value="passCheck">
				<input type="hidden" name="qseq" value="${qvo.qseq}">
				<span>
					<h2>작성 시 입력했던 비밀번호 4자리를 입력해주세요.</h2><br>
					<input type="password" name="pass" size="4" style="height: 30px;">
					<input type="submit" class="qna_btn01" value="확인">
					<input type="button" class="qna_btn01" value="뒤로가기" onclick="location.href='burger.do?command=qnaForm'">
				</span>
			</form>
		</div>
<%@ include file="/undermenu.jsp" %>
<%@ include file="/footer/footer.jsp" %>