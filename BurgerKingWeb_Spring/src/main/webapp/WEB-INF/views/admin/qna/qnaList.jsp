<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<h1>Q&amp;A 게시글 리스트</h1>
	<form name="frm" method="post">
		<table style="margin: 0 auto;">
			<tr>
				<td>
				ID 검색 <input type="text" name="key" value="${key}">
				<input type="button" class="btn" value="검색" onclick="go_qna_search();">
				<input type="button" class="btn" name="btn_total" value="전체보기" onclick="go_qna_total();">
				</td>
			</tr>
		</table>
	</form>
	<table id="productList">
		<tr>
			<th>번호(답변여부)</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>삭제</th>
		</tr>
		<form name="frm2" method="post" action="burger.do">
			<c:forEach items="${qnaList}" var="qnaVO">
				<tr>
					<td>
						${qnaVO.qseq}
						<c:choose>
							<c:when test='${qnaVO.rep=="1" }'>
								(미처리)
							</c:when>
							<c:otherwise>
								(답변처리완료)
							</c:otherwise>
						</c:choose>
					</td>
					<td><a href="#" onclick="javascript:go_qna_view('${qnaVO.qseq}')">${qnaVO.subject}</a></td>
					<td>${qnaVO.id}</td>
					<td><fmt:formatDate value="${qnaVO.indate}"/></td>
					<td>
						<input type="checkbox" name="delete" value="${qnaVO.qseq}">
					</td>
				</tr>
			</c:forEach>
		</form>
	</table><br>
	<input type="button" class="btn" style="float: right;" value="삭제" onclick="del_qna();">
	
	<jsp:include page="../../admin/paging/paging.jsp">
		<jsp:param name="page" value="${paging.page}"/>
		<jsp:param name="beginPage" value="${paging.beginPage}"/>
		<jsp:param name="endPage" value="${paging.endPage}"/>
		<jsp:param name="prev" value="${paging.prev}"/>
		<jsp:param name="next" value="${paging.next}"/>
		<jsp:param name="command" value="adminQnaList"/>
	</jsp:include>
</article>

<%@ include file="../../admin/footer.jsp"%>