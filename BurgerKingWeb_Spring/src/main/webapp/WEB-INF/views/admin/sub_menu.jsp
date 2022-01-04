<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">

<nav id="sub_menu">
	<h1>관리 항목 목록</h1>
	<ul>
		<li><a href="burger.do?command=adminShortProductList&page=1&key=">상품리스트</a></li>
		<li><a href="burger.do?command=adminOrderList&page=1&key=">주문리스트</a></li>
		<li><a href="burger.do?command=adminMemberList&page=1&key=">회원리스트</a></li>
		<li><a href="burger.do?command=adminQnaList&page=1&key=">Q&amp;A리스트</a></li>
		<li><a href="burger.do?command=adminEventList&page=1&key=">이벤트리스트</a></li>
	</ul>
</nav>
