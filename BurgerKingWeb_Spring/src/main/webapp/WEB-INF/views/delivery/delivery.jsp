<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/Delivery/deli_header.jsp"%>
<div class="clear"></div>

<script>
$(function(){
	$( 'document' ).ready(function() {
		  $('#pk${kind1}').css("color", "red");
		  $('#pk${kind1}').css("text-decoration", "underline");
	});
});
</script>


<article>
	<div class="location">
		<div class="web_container1">
			<div class="web_container1">
				<ul>
					<li><a href="deliveryForm&kind1=1">딜리버리</a>&nbsp;>&nbsp;</li>
					<li>카트</li>
				</ul>
			</div>
		</div>
	</div>
<div class="bg_w">
	<div class="web_container1">
		<div class="subtitWrap">
			<h3 class="page_tit">메뉴</h3>
			<div class="tab01" >
			<ul>
				<li><a href="deliveryForm?kind1=1">
					<button type="button" ><span id="pk1">스페셜&amp;할인팩</span></button></a></li>
				<li><a href="deliveryForm?kind1=2">
					<button type="button"><span id="pk2">프리미엄</span></button></a></li>
				<li><a href="deliveryForm?kind1=3">
					<button type="button"><span id="pk3">와퍼</span></button></a></li>
				<li><a href="deliveryForm?kind1=4">
					<button type="button"><span id="pk4">주니어&amp;버거</span></button></a></li>
				<li><a href="deliveryForm?kind1=5">
					<button type="button"><span id="pk5">올데이킹&amp;치킨버거</span></button></a></li> 
				<li><a href="deliveryForm?kind1=6">
					<button type="button"><span id="pk6">사이드</span></button></a></li>
				<li><a href="deliveryForm?kind1=7">
					<button type="button"><span id="pk7">음료&amp;디저트</span></button></a></li>
				<li><a href="deliveryForm?kind1=8">
					<button type="button"><span id="pk8">독퍼</span></button></a></li>
			</ul>
			</div>
		</div>
		<div class="menuarea">
			<ul>
				<c:forEach var="productVO" items="${productList}">
				<c:choose>
					<c:when test='${productVO.kind3=="4" }'>
					<li><a onclick="deliveryDetail('${productVO.pseq}')">
						<div class="menuImgdiv">
							<img class="menuImg" src="image/menu/${productVO.kind1}/${productVO.image}">					
						</div>
						<div class="menuContdiv">
							<p>${productVO.pname}</p>
							<p style="font-size:90%;color:#8d8d8d">${productVO.content}</p>
							<p style="color:red;font-weight:bold">&#8361;${productVO.price1}~</p>
						</div>
						</a></li>
					</c:when>
				</c:choose>
				</c:forEach>	
			</ul>
		</div>
		<div class="clear"></div>
		<div class="order_caution">
			<div class="tit03">
				<h4>유의사항</h4>
				<button type="button" class="btn_infoingredient"><strong>원산지, 영양분석, 알레르기 유발성분</strong></button>
			</div>
			<ul class="WEB txtlist01">
				<li>매장별 주문금액이 상이하니, 반드시 최소금액을 확인하기 바랍니다.</li>
				<li>배달 소요시간은 기상조건이나 매장 사정상 지연 또는 제한될 수 있습니다.</li>
				<li>고객님과 수 차례 연락을 시도한 후 연락이 되지 않는 경우 배달음식이 변질되거나 부패될 우려로 식품위생법상 위반될 여지가 있어 별도로 보관 하지 않으며,<strong> 재배달 또는 환불처리가 어려울 수 있습니다.</strong></li>
				<li>딜리버리 서비스 메뉴의 가격은 매장 가격과 상이하며, 딜리버리 시 타쿠폰을 사용하실 수 없습니다. (일부품목 배달 제외)</li>
				<li>배달 제품은 매장 행사(할인가격)에서 제외됩니다.</li>
				<li>제품 가격 및 메뉴 구성은 본사 사정상 변경될 수 있습니다.</li>
				<li>대량 주문의 경우 콜센터(1599-0505)주문으로만 가능합니다.</li>
				<li>주문 완료 후 변경 및 취소는 콜센터(1599-0505)에서만 가능합니다.</li>
			</ul>
		</div>
	</div>
</div>
	
</article>

<%@ include file="../include/footer.jsp" %>