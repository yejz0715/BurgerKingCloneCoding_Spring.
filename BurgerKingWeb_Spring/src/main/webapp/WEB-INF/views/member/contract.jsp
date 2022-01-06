<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/Delivery/deli_header.jsp"%>
<script src="member.js" type="text/javascript"></script>



<article>
	<div class="location">
	<div class="web_container1">
		<ul>
			<li><a href="main">HOME</a>&nbsp;>&nbsp;</li>
			<li><a href="loginForm&non=1">로그인</a>&nbsp;>&nbsp;</li>
			<li><a href="join">회원가입</a>&nbsp;>&nbsp;</li>
			<li><a href="contract">약관동의 및 본인인증</a>&nbsp;>&nbsp;</li>
		</ul>
	</div>
	</div>

	<script type="text/javascript">
	/* 팝업창 */
		function go_pop1() {
			window.open("popup1", "a",
					"width=570, height=760, left=50, top=50,resizable=no");}
	
		function go_pop2() {
			window.open("popup2", "a",
					"width=570, height=650, left=50, top=50,resizable=no");}
		
		
		/* 전체체크 */
		function selectAll(selectAll)  {
			  const checkboxes 
			       = document.getElementsByName('agree');
			  
			  checkboxes.forEach((checkbox) => {
			    checkbox.checked = selectAll.checked;
			  })
			}
	
		
		
		
		/* 약관동의 
		function agree() { 
			var chkbox = document.getElementsByName('agree'); 
			var chk = false; 
			for(var i=0 ; i<chkbox.length-2 ; i++) { 
				if(chkbox[i].checked) {
					chk = true; 
					} else { 
						chk = false; 
						} 
				} 
			if(chk) {
				document.contractFrm.action ="shop.do?command=joinForm";
				document.contractFrm.submit(); 
				return false; 
				} else { alert("모든 약관에 동의해 주세요.") } }*/
				
				
				
				
				function agree()  {
					  // 선택된 목록 가져오기
					  const query = 'input[name="agree"]:checked';
					  const selectedEls = 
					      document.querySelectorAll(query);
					  
					  // 선택된 목록에서 value 찾기
					  let result = '';
					  selectedEls.forEach((el) => {
					    result += el.value + ' ';
					  });
					  
						if( result == "1 2 " || result=="1 2 3 4 " || result=="1 2 3 " || result=="1 2 4 " || result=="1 2 3 4 5 ")
						{	
							document.contractFrm.action ="joinpage";
							document.contractFrm.submit(); 
						}
						else
						{
							alert('필수 이용약관에 동의해 주세요.');
						}
					}				
				

	</script>



	<form method="post" name="contractFrm" >
		<div class="contentsBox01">
			<div class="web_container">
				<div class="subtitWrap m_bg_basic">
					<h2 class="page_titl">회원가입</h2>
				</div>
				<div class="container01">
					<h3 class="tit01 tit_ico noti">
						<span>이용약관</span>
					</h3>
					<div class="container02 auth_list">
						<div class="titbox">
							<p>회원가입 약관 동의 및 본인인증을 진행합니다.</p>
						</div>
						<div style="overflow-xy: hidden">
							<label><input type="checkbox" id="chk1" class="check02"
								name="agree" value="1"><span>버거킹 이용약관(필수)</span></label>
							<button type="button" value="popbox" class="submit"
								onClick="go_pop1();" style="overflow-xy: hidden">
								<span>상세보기</span>
							</button>
						</div>
						<div>
							<label><input type="checkbox" class="check02"
								name="agree" value="2"><span>개인정보처리방침(필수)</span></label>
							<button type="button" value="popbox" class="submit"
								onClick="go_pop2();">
								<span>상세보기</span>
							</button>
						</div>
						<div>
							<label><input type="checkbox" class="check02"
								name="agree" value="3"><span>E-mail 광고성 정보 동의(선택)</span></label>
						</div>
						<div class="st02">
							<label><input type="checkbox" class="check02"
								name="agree" value="4"><span>SMS 광고성 정보 동의(선택)</span></label><span
								class="info">다양한 이벤트와 주문 정보를 보내 드립니다.</span>
						</div>
						<div class="divide">
							<label><input type="checkbox" class="check02"
								name="agree" onclick='selectAll(this)' value="5"><span>약관
									전체 동의</span></label>
						</div>
					</div>

					<div class="c_btn">
						<a href="javascript:callFunction();" class="btn77"
							onclick="agree();"><span>가입하기</span></a>
						<!--	<a href="burger.do?command=joinpage"class="btn019" ><span>가입하기</span></a>-->



					</div>
				</div>
			</div>
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			
		</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	</form>
</article>

<%@ include file="../include/undermenu.jsp" %>
<%@ include file="../include/footer.jsp" %>