<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<article>
	<div class="location">
	<div class="web_container1">
		<ul>
			<li><a href="mian">HOME</a>&nbsp;>&nbsp;</li>
			<li>고객센터 &nbsp;>&nbsp;</li>
			<li>버거킹앱 이용안내</li>
		</ul>
	</div>
	</div>
	
	<div class="subtitWrap">
		<h3 class="page_tit">고객센터</h3>
		<ul id="terms_wrap_ul" style="right: 0; top:0">
			<li>
				<span>
					<a href="faqListForm?fnum=1">FAQ</a>
				</span>
			</li>
			<li><span><a href="qnaForm">문의</a></span></li>
			<li><span><a href="appGuideForm" style="color: red; text-decoration: underline;">버거킹앱 이용안내</a></span></li>
		</ul>
		
		<h4 class="subtit"><span>버거킹 APP(앱) 다운로드</span></h4>
		<p class="txt">
			<span>아래 방법 중 편리한 방법으로 <strong>버거킹앱을</strong></span>
			<span><strong>다운로드</strong>하세요.</span>
		</p>

		<div class="WEB_guide_img">
			<img src="image/main/img_guide_appdown.jpg" alt="앱 화면 이미지">
		</div>
	</div>

	<div class="web_container1">
		<div class="appdown">
			<dl class="store_down">
				<dt>스토어 다운로드</dt>
				<dd>
					<p>
						애플 앱스토어와 구글 플레이스토어에서 <strong>버거킹 앱</strong>을 다운로드 해보세요.
					</p>
					<div class="btn_area">
						<a href="https://play.google.com/store/apps/details?id=kr.co.burgerkinghybrid"
							target="_blank" class="btn_googleplay">
							<span>Google Play</span>
						</a>
						<a href="https://itunes.apple.com/kr/app/apple-store/id1017567032"
							target="_blank" class="btn_appstore">
							<span>App Store</span>
						</a>
					</div>
				</dd>
			</dl>
			<dl class="WEB">
				<dt>QR코드 다운로드</dt>
				<dd>
					<p>
						QR코드를 스캔하여 <br>
						<strong>버거킹 앱</strong>을 다운로드 해보세요.
					</p>
					<div class="QRcode">
						<img	src="image/main/appQR.png">
					</div>
				</dd>
			</dl>
		</div>
	</div>
</article>

<%@ include file="../include/undermenu.jsp" %>
<%@ include file="../include/footer.jsp" %>