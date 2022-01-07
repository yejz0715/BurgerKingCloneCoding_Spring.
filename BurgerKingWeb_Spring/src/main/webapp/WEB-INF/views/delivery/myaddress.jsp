<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/Delivery/deli_header.jsp"%>
<div class="clear"></div>

<article>
	<div class="location">
	<div class="web_container1">
		<ul>
			<li><a href="deliveryForm">딜리버리</a>&nbsp;>&nbsp;</li>
			<li><a href="deliveryMypageForm">MY킹</a>&nbsp;>&nbsp;</li>
			<li>MY배달지</li>
		</ul>
	</div>
	</div>

	<div class="webAddress">
		<form method="post" name="address" action="updateAddress">
			<fieldset>
				<legend>MY배달지</legend>
				<label>Zip Code</label><input type="text" name="zip_num" size="10" value="${zip_num}">
				<input type="button" value="주소 찾기" onclick="post_zip()"><br>
				<label>Address</label><input type="text" name="addr1" size="50" value="${addr1}"><br>
				<label>&nbsp;</label><input type="text" name="addr2" size="50" value="${addr2}">
				<br>
			</fieldset>
			<div class="c_btnItem2">
				<input type="reset" class="btn01 red" value="취소" />
				<input type="submit" class="btn01 m" value="저장하기" />
			</div>
		</form>
	</div>
</article>
<%@ include file="../include/footer.jsp"%>