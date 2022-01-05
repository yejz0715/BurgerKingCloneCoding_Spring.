<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="css/burger.css" rel="stylesheet">



		<article>
		 <div class="web_deliveryContainer">
				 <div class="deliveryOrder" onclick="go_order_select()">
				 <img class= "deliveryIcon" src="image/main/my_delivery.png" />
				 <c:choose>
					 <c:when test="${ovo.size() == 0}">
						 <dl>
							 <dt><strong>딜리버리 주문내역</strong></dt>
							 <dd>주문내역이 없습니다.</dd>
						 </dl>
					 </c:when>
					 <c:otherwise>
						 <dl>
							 <dt><strong>딜리버리 주문내역</strong>
								 <em class="count">
								 <span>${ovo.size()}</span>
								 </em>
							 </dt>
							 <dd>주문내역</dd>
						 </dl>
					 </c:otherwise> 
				 </c:choose>
				 </div>
				 
				 
				 <div class="deliveryCart" onclick="go_cart02('${loginUser}')">
				 <img class= "deliveryIcon" src="image/main/my_cart.png" />
				 <form name="cartForm" action="burger.do">
				<input type="hidden" name="pseq" value="">
				<input type="hidden" name="id" value="">
				<input type="hidden" name="id2" value="">
				<input type="hidden" name="command" value="deliveryCartForm">
				 <c:choose>
					 <c:when test="${cvo.size() == 0}"> 
						 <dl>
						 <dt><strong>카트</strong></dt>
						 <dd>카트에 담은 상품이 없습니다.</dd>
						 </dl>
					 </c:when>
					 <c:otherwise>
						 <dl>
							<dt><strong>카트</strong>
							<em class="count">
							<span>${cvo.size()}</span>
							</em>
							</dt>
						 	<dd>카트에 담은 상품</dd>
						 </dl>
					 </c:otherwise> 
				 </c:choose> 
				 </form>
				 </div>
			 </div>
			 </article>
