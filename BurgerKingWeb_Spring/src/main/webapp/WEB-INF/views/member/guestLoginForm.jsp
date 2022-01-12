<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/Delivery/deli_header.jsp"%>

<article style="background: #f2ebe6;">
	<div class="location">
		<div class="web_container1">
			<ul>
				<li><a href="main">HOME</a>&nbsp;>&nbsp;</li>
				<li><a href="loginForm">로그인</a>&nbsp;>&nbsp;</li>
				<li><a href="guestLoginForm">비회원 주문</a></li>
			</ul>
		</div>
	</div>
	
	<div class="web_container02">
		<h2 class="page_tit02 bg_w" style="background: #f2ebe6;">비회원 주문</h2>

		<div class="accWrap01">
			<div class="container02 auth_list acc_list">
				<div class="acc_tit">
					<label>
						<input type="checkbox" name="guest_checkbox" class="check02">
						<span>버거킹 이용약관</span>
					</label>
					<button type="button" class="btn_acc" style="width: 100px;" onclick="term_open()">
						<span>상세보기</span>
					</button>
				</div>
				<div class="acc_cont" id="acc_cont" style="height: 0px;">
					<p>
						<strong>수집하는 개인정보 항목</strong><br> - 전화번호, 성명, 주소
					</p>
					<br>
					<p>
						<strong>수집 목적 </strong><br> ① 성명, 주소: 제품의 전달, 청구서, 정확한 제품
						배송지의 확보.
					</p>
					<br>
					<p>
						<strong>개인정보 보유기간 </strong><br> ① 계약 또는 청약철회 등에 관한 기록 : 6개월<br>
						② 대금결제 및 재화 등의 공급에 관한 기록 : 1년<br> ③ 소비자의 불만 또는 분쟁처리에 관한 기록 :
						1년
					</p>
					<br>
					<p>
						<strong>비회원 주문 시 제공하신 모든 정보는 상기 목적에 필요한 용도 이외로는 사용되지
							않습니다. 기타 자세한 사항은 '개인정보처리방침'을 참고하여주시기 바랍니다.</strong>
					</p>
				</div>
			</div>
		</div>
		
		<form action="guestLogin" method="post" name="frm">
			<h2 class="tit01 tit_ico setting"><span>비회원 설정</span></h2>
	
			<div class="container02">
				<div class="dlist01">
					<dl>
						<dt class="nonmember_WEB">이름</dt>
						<dd>
							<div class="inpbox st02">
								<input type="text" placeholder="받는 분의 이름을 입력해 주세요." name="name" class="st02" style="width: 80%">
								<button type="button" class="btn_del01 " style="display: none;">
									<span>입력 텍스트 삭제</span>
								</button>
							</div>
						</dd>
					</dl>
					<dl>
						<dt class="nonmember_WEB">휴대폰 번호</dt>
						<dd>
							<div class="inpbox st02">
								<input type="text" placeholder="핸드폰 번호를 입력해 주세요" name="phone" class="st02" style="width: 80%">
							</div>
						</dd>
					</dl>
				</div>
			</div>
			
			<h2 class="tit01 tit_ico key"><span>주문서 비밀번호</span></h2>
	
			<div class="container02">
				<div class="WEB nonmembertitbox">
					<p>주문 내역 확인을 위한 비밀번호를 입력하세요.</p>
				</div>
				<div class="dlist01 nontitbox_margin20">
					<dl>
						<dt class="nonmember_WEB">비밀번호</dt>
						<dd>
							<div class="inpbox">
								<input placeholder="4~6자리 이내의 숫자로만 입력하세요." maxlength="6"
									type="password" class="st02" style="width: 80%" name="pwd">
							</div>
							<div class="inpbox">
								<input placeholder="비밀번호를 다시 입력하세요." maxlength="6"
									type="password" class="st02" style="width: 80%" name="pwd_chk">
							</div>
						</dd>
					</dl>
				</div>
			</div>
			
			<br><br><br>
			
			<input type="submit" class="btn01 m Btncenter" value="비회원 주문"
			 onclick="return check_Term();">
		 </form>
	</div>
</article>

<%@ include file="../include/footer.jsp"%>