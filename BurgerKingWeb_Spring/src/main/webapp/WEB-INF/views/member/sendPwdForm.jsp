<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/Delivery/deli_header.jsp"%>

<article style="background-color: #f2ebe6;">
	<div class="web_container02">
		<h1 class="big_h1" style="font-size:1em; padding-top:50px; margin-bottom: 30px;">아이디/비밀번호 찾기</h1>
		<ul id="terms_wrap_ul" style="left: 0px; position:relative;">
			<li>
				<span>
					<a href="findIdForm" style="font-size: 1.45em;">아이디 찾기</a>
				</span>
			</li>
			<li><span><a href="findPwdForm?id=${memberVO.id}&name=${memberVO.name}" style="color: red; text-decoration: underline; font-size: 1.45em;">비밀번호 찾기</a></span></li>
		</ul>
		<div class="btnarea" style="height:0px; margin-top: 80px;"></div>
		<div class="textarea" style="height: 800px;">
			<div class="msgBox">
				<span class="showid_text">비밀번호 수정</span><br>
				<span class="showid_text"><small>새로운 비밀번호를 설정해주세요.</small></span>
			</div>
			<div class="inpbox">
				<c:choose>
					<c:when test="${empty memberVO}">
						
					</c:when>
					<c:otherwise>
						<form method="post" name="frm" action="updatePwd">
							<input type="hidden" name="mseq" value="${memberVO.mseq}">
							<div class="textareapwd" style="right: 140px;">
								<div class="inpbox" style="margin-bottom: 30px;">
									<div class="vtop">비밀번호</div>
									<label>
										<span class="hide">비밀번호</span>
										<input type="password" placeholder="비밀번호" id="pwd" name="pwd" onblur="check_input4();">
										<span id="pwd_coment" class="coment_text">새 비밀번호를 입력해 주세요.</span>
									</label>
								</div>
								<div class="inpbox">
								<div class="vtop">비밀번호 확인</div>
									<label>
										<span class="hide">비밀번호 확인</span>
										<input placeholder="비밀번호 확인" type="password" name="pwd_chk" id="pwd_chk" onblur="check_input5();">
										<span id="pwd_chk_coment" class="coment_text">비밀번호 재확인</span>
									</label>
								</div>
							</div>
							<div class="c_btn item2 findidalign" style="margin-left: -108px; margin-top:340px;">
								<input type="submit" class="btn01 m red" value="비밀번호 수정" onclick="return updatepwdChk();"/>
							</div>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</article>

<%@ include file="../include/footer.jsp" %>