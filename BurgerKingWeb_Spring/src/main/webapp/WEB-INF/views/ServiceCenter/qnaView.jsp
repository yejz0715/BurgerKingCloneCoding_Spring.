<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<article>
	<div class="qna_webcon">
		<h2>1:1 고객 게시판</h2>
		<h3>고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>
		<form>
			<table border="1" style="margin: 30px 0 30px 0;">
				<tr>
					<th>제목</th>
					<td width="600" style="text-align: left;">${qnaVO.subject}</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td align="left" style="text-align: left;">
						<fmt:formatDate value="${qnaVO.indate}" type="date" />
					</td>
				</tr>
				<tr>
					<th>질문내용</th>
					<td align="left" style="text-align: left; font-size: 115%;">
						<pre>${qnaVO.content}</pre>
					</td>
				</tr>
				<tr>
					<th>답변 내용</th>
					<td align="left" style="text-align: left;">${qnaVO.reply}</td>
				</tr>
			</table>
			<div class="clear"></div>
			<div id="buttons" style="float: right">
				<input type="button" value="목록보기" class="btn01" style="height: 68px; margin-bottom: 30px;"
					onclick="qnaForm'">
			</div>
		</form>
	</div>
</article>

<%@ include file="../include/undermenu.jsp" %>
<%@ include file="../include/footer.jsp" %>