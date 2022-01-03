<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/Delivery/deli_header.jsp"%>

<article style="background-color: #f2ebe6;">
	<div class="location">
	<div class="web_container1">
		<ul>
			<li><a href="/">HOME</a>&nbsp;>&nbsp;</li>
			<li><a href="oginForm">로그인</a>&nbsp;>&nbsp;</li>
			<li><a href="findPwdForm">아이디/비밀번호 찾기</a></li>
		</ul>
	</div>
	</div>
	
	<div class="web_container02">
		<form name="frm" action="findPwd" method="post">
			<h1 class="big_h1" style="margin-bottom: 30px;">아이디/비밀번호 찾기</h1>
			<ul id="terms_wrap_ul" style="left: 0px; position:relative;">
				<li>
					<span>
						<a href="findIdForm" style="font-size: 1.45em;">아이디 찾기</a>
					</span>
				</li>
				<li><span><a href="findPwdForm" style="color: red; text-decoration: underline; font-size: 1.45em;">비밀번호 찾기</a></span></li>
			</ul>
			<div class="btnarea" style="text-align: center; margin-top: 80px;">
				<c:if test="${empty message}">
					<span style="font-family: 'Noto Sans KR', sans-serif;">회원님의 정보(이메일)로 비밀번호 재설정을 위한 경로를 보내 드립니다.</span>
				</c:if>
				<c:if test="${!empty message}">
					<span class="coment_text">${message}</span>
				</c:if>
			</div>
			<div class="textarea" style="height: 520px;">
				<div class="inpbox" style="margin-bottom: 30px;">
					<div class="vtop">이름</div>
					<label>
						<span class="hide">ID</span>
						<input type="text" placeholder="이름" id="name" name="name" value="${name}" onblur="check_input1();">
						<span id="name_coment" class="coment_text">이름을 입력해 주세요.</span>
					</label>
				</div>
				<div class="inpbox">
				<div class="vtop">아이디(이메일)</div>
					<label>
						<span class="hide">ID(email)</span>
						<input placeholder="아이디(이메일)" type="email" name="id" value="${id}" id="id" onblur="check_input3();">
						<span id="id_coment" class="coment_text">아이디를 입력해 주세요.</span>
					</label>
				</div>
				<ul class="find_id_text">
					<li>ㆍ가입시 입력한 이메일로 비밀번호 재설정을 위한 경로가 발송 됩니다.</li>
					<li>ㆍ네이버, 카카오톡, 삼성 앱카드, 애플아이디로 가입하신 회원님께서는 해당 서비스에서 아이디/비밀번호 찾기를 진행해 주세요.</li>
				</ul>
			</div>
			<div class="c_btn item2 findidalign">
				<input type="submit" class="btn01 m" value="비밀번호 찾기" onclick="return inputpwdChk()"/>
			</div>
		</form>
	</div>
</article>

<%@ include file="../include/footer.jsp" %>