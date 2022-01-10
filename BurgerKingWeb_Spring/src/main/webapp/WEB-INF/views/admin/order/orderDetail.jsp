<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<h1>주문 상세 보기</h1>
	<table id="list" border="1">
		<tr>
			<th>아이디(이름)</th>
			<td colspan="5">${ovo.id}(${ovo.mname})</td>
		</tr>
		<tr>
			<th>주문 번호</th>
			<td colspan="2">${ovo.oseq}</td>
			<th>주문 상세 번호</th>
			<td colspan="2">${ovo.odseq}</td>
		</tr>
		<tr>
			<th align="center">상품명</th>
			<td colspan="5">${ovo.pname}</td>
		</tr>
		<tr>
			<th>주문 시간</th><td>${ovo.indate}</td>
			<th>회원 구분</th>
			<td>
				<c:if test="${ovo.memberkind == 1}">
					회원
				</c:if>
				<c:if test="${ovo.memberkind == 2}">
					비회원
				</c:if>	
			</td>
			<c:choose>
				<c:when test="${ovo.result == 1}">
					<th>처리상태</th><td>처리 전</td>
				</c:when>
				<c:when test="${ovo.result == 2}">
					<th>처리상태</th><td>처리 중</td>
				</c:when>
				<c:when test="${ovo.result == 3}">
					<th>처리상태</th><td>배달 중</td>
				</c:when>
				<c:otherwise>
					<th>처리상태</th><td>완료</td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<th>주소</th>
			<td colspan="5">${ovo.zip_num} - ${ovo.address}</td>
		</tr>
		<tr>
			<th>주문 수량</th>
			<td colspan="2">${ovo.quantity}</td>
			<th>주문 가격</th>
			<td colspan="2">${ovo.price1}</td>
		</tr>
	</table>
	
	<h1>재료 추가</h1>
	<table id="list" border="1">
		<c:choose>
			<c:when test="${empty spseqAm}">
				<tr>
					<th>추가 재료 없음</th>
				</tr>
				<tr>
					<th colspan="6">총가격 : ${totalPrice}</th>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${spseqAm}" var="spseqAm">
					<c:if test="${spseqAm.odseq == ovo.odseq}">
						<tr>
							<th>주문 상세 번호</th>
							<td>${spseqAm.odseq}</td>
							<th>추가 재료 번호</th>
							<td>${spseqAm.sposeq}</td>
							<th>추가 재료</th>
							<td>${spseqAm.sname}</td>
						</tr>
						<tr>
							<th>추가 가격</th>
							<td colspan="4">${spseqAm.addprice}원</td>
							<td>
								<button onclick="deleteSpo('${spseqAm.sposeq}', '${ovo.result}',
								 '${kind}', '${spseqAm.odseq}');">
									재료 취소
								</button>
							</td>
						</tr>
					</c:if>
				</c:forEach>
				<tr>
					<td colspan="6">총가격 : ${totalPrice}</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<input type="button" class="btn" value="목록" onclick="go_order_mov()">
</article>

<%@ include file="../../admin/footer.jsp"%>