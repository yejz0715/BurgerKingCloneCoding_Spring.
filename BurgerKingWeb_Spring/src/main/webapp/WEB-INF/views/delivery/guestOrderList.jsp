<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/Delivery/deli_header.jsp"%>
<form name="order" method="post" action="burger.do" style="background: #f2ebe6;">
<input type="hidden" name="order" value="">
<article>
	<div class="location">
<div class="web_container1">
	<ul>
		<li><a href="deliveryForm?kind1=1">딜리버리</a>&nbsp;>&nbsp;</li>
		<li>주문내역</li>
	</ul>
</div>
</div>
<div class="contentsBox01">
	<div class="web_container1">
		<div class="subtitWrap m_bg_basic">
			<h2 class="page_tit">주문내역</h2>
		</div>
		<c:choose>
		<c:when test="${empty ovo}">
			<div class="tab_cont">
				<div class="nodata"><p>주문내역이 없습니다.</p></div>
			</div>
		</c:when>
		<c:otherwise>
					<div class="container01 orderWrap">
			<h2 class="tit01 tit_ico delivery"><span>배달정보</span></h2>
		</div>
		<div class="container02 deli_info01">
			<div class="addrWrap01">
				<p class="txt_addr">
					<c:if test="${!empty mkind}">
						<div style="color: red;">화면의 주문번호는 비회원 주문내역 확인에 필요합니다.(주문번호-주문세부번호)</div>
					</c:if>
					<span>${address}</span>	
				</p>
				<!-- <button type="button" class="btn04 h02 rbtn"><span>변경</span></button> -->
			</div>
			<div class="info_list">
				<dl><dt>연락처</dt><dd>${userPhone}</dd></dl>
				<dl>
					<dt>진행상황</dt>
					<c:choose>
						<c:when test="${result == 1}">
							<dd>주문 확인 전</dd>
						</c:when>
						<c:when test="${result == 2}">
							<dd>주문 처리 중</dd>
						</c:when>
						<c:when test="${result == 3}">
							<dd>배달 중</dd>
						</c:when>
						<c:when test="${result == 4}">
							<dd>배달 완료</dd>
						</c:when>
					</c:choose>
				</dl>
			</div>
		</div>
		<div class="tit01 tit_ico burger tit_ordermenu">
			<h2><span>주문정보</span></h2>
		</div>
		<div class="container02 order_accWrap open">		
		<ul class="cart_list01">
			<c:forEach var="orderList"  items="${ovo}">
			<li>
				<div class="cont">
					<div class="menu_titWrap">
						<div class="menu_name">
							<p class="tit"><strong><span>${orderList.oseq}-${orderList.odseq} : ${orderList.pname}</span></strong></p>
						</div>
					</div>
					<div class="quantity"><strong class="tit">수량</strong>
						<div class="num_set">
							<div class="result">${orderList.quantity}</div>
						</div>
					</div>
				</div>
				<div class="sumWrap">
				<dl>
					<dt>상품금액</dt>
					<dd>
						<strong>
							<em><span></span><span class="unit">${orderList.price1}원</span></em>
						</strong>
					</dd>
				</dl>
				</div>
			</li>
			<c:choose>
				<c:when test="${empty spseqAm}">
					
				</c:when>
				<c:otherwise>
					<ul class="cart_list01">
					<li>
					<div class="cont" style="padding: 32px 64px; font-size:2rem;font-weight: bold;">
						<c:forEach items="${spseqAm}" var="spseqAm">
							<c:if test="${spseqAm.odseq == orderList.odseq}">
								<div style="width:100%;">
									${spseqAm.odseq} : ${spseqAm.sname}
									<div style="color:red; float:right;">${spseqAm.addprice}원</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
					</li>
					</ul>
				</c:otherwise>
			</c:choose>
			</c:forEach>
		</ul>
		</div>
		<h2 class="tit01 tit_ico money"><span>최종 결제금액</span></h2>
		<div class="container02">
			<div class="order_payment_list">
				<dl class="tot">
					<dt>총 주문금액</dt>
					<dd>
						<strong>
							<em><span></span><span class="unit">${totalPrice}원</span></em>
						</strong>
					</dd>
				</dl>
				<dl>
					<dt>제품 금액</dt>
					<dd>
						<strong>
							<em><span></span><span class="unit">${totalPrice}원</span></em>
						</strong>
					</dd>
				</dl>
				<dl>
					<dt>배달팁</dt>
					<dd>
						<strong>
							<em><span></span><span class="unit">0원</span></em>
						</strong>
					</dd>
				</dl>
			</div>
		</div>
		</c:otherwise>
		</c:choose>
	</div>
</div>
</article>
</form>
<%@ include file="../include/footer.jsp"%>