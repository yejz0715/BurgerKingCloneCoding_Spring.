<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<article>
	<div class="location">
		<ul>
			<li><a href="burger.do?command=index">HOME</a>&nbsp;>&nbsp;</li>
			<li>고객센터 &nbsp;>&nbsp;</li>
			<li>FAQ</li>
		</ul>
	</div>

	<div class="subtitWrap">
		<h3 class="page_tit">고객센터</h3>
		<ul id="terms_wrap_ul" style="right: 0; top:0">
			<li>
				<span>
					<a href="burger.do?command=faqForm&fnum=1" style="color: red; text-decoration: underline;">FAQ</a>
				</span>
			</li>
			<li><span><a href="burger.do?command=qnaForm">문의</a></span></li>
			<li><span><a href="burger.do?command=appGuideForm">버거킹앱 이용안내</a></span></li>
		</ul>
	</div>

	<div class="web_container1">
		<div class="tab_cont">
			<div class="faq_category">
				<label class="switch01">
					<input type="radio" name="faqcategory" onclick="location.href='faqListForm?fnum=1'">
					<span>전체</span>
				</label>
				<label class="switch01">
					<input type="radio" name="faqcategory" onclick="location.href='faqListForm?fnum=2'">
					<span>제품/품질/서비스</span>
				</label>
				<label class="switch01">
					<input type="radio" name="faqcategory" onclick="location.href='faqListForm?fnum=3'">
					<span>딜리버리 주문</span>
				</label>
				<label class="switch01">
					<input type="radio"	 name="faqcategory" onclick="location.href='faqListForm?fnum=4'">
					<span>임대 / 가맹</span>
				</label>
				<label class="switch01">
					<input type="radio" name="faqcategory" checked="checked" onclick="location.href='faqListForm?fnum=5'">
					<span>버거킹카드</span>
				</label>
				<label class="switch01">
					<input type="radio" name="faqcategory" onclick="location.href='faqListForm?fnum=6'">
					<span>기타</span>
				</label>
			</div>
			<div class="board_list">
				<table class="tlist01 acc_type">
					<colgroup>
						<col style="width: 10%;">
						<col style="width: 20%;">
						<col>
					</colgroup>
					<thead class="sc_table_thead">
						<tr class="sc_table_header">
							<th scope="col">NO.</th>
							<th scope="col">구분</th>
							<th scope="col">제목</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="3">등록된 FAQ가 없습니다</td>
						</tr>
					</tbody>
					<tbody class="" style="display: none;">
						<tr class="acc_tit_tr">
							<td class="WEB">9</td>
							<td class="category"><span>딜리버리 주문</span></td>
							<td><div class="acc_tit">
									<span class="tit">대량주문에 대한 제한이 있는지요?</span>
									<button type="button" class="btn_acc">Q</button>
								</div></td>
						</tr>
						<tr class="acc_cont">
							<td colspan="3"><div class="text_area">A. 대량주문의 경우 매장 상황을
									파악한 후 버거킹 딜리버리 콜센터에서 확인전화를 드리며 확인 전화가 완료되어야 주문이 접수됩니다. 원재료 및 제품의
									생산시간을 고려하여 가능 여부를 안내해드립니다.</div></td>
						</tr>
					</tbody>
					<tbody class="" style="display: none;">
						<tr class="acc_tit_tr">
							<td class="WEB">8</td>
							<td class="category"><span>딜리버리 주문</span></td>
							<td><div class="acc_tit">
									<span class="tit">딜리버리 주문을 미리 예약하고 싶은데요?</span>
									<button type="button" class="btn_acc">Q</button>
								</div></td>
						</tr>
						<tr class="acc_cont">
							<td colspan="3"><div class="text_area">A. 예약 주문은 현재 시간으로
									부터 2시간 이후에 가능합니다.</div></td>
						</tr>
					</tbody>
					<tbody class="" style="display: none;">
						<tr class="acc_tit_tr">
							<td class="WEB">7</td>
							<td class="category"><span>딜리버리 주문</span></td>
							<td><div class="acc_tit">
									<span class="tit">현금영수증 발행이 가능하나요?</span>
									<button type="button" class="btn_acc">Q</button>
								</div></td>
						</tr>
						<tr class="acc_cont">
							<td colspan="3"><div class="text_area">A. 결제시 현금영수증을 선택하여
									발행 여부를 선택하실 수 있습니다.</div></td>
						</tr>
					</tbody>
					<tbody class="" style="display: none;">
						<tr class="acc_tit_tr">
							<td class="WEB">6</td>
							<td class="category"><span>제품/품질/서비스</span></td>
							<td><div class="acc_tit">
									<span class="tit">매장에서 판매하는 모든 메뉴를 배달하나요?</span>
									<button type="button" class="btn_acc">Q</button>
								</div></td>
						</tr>
						<tr class="acc_cont">
							<td colspan="3"><div class="text_area">A. 배달이 어려운 일부 디저트
									메뉴를 제외하고, 버거킹 매장에서 판매하고 있는 대부분의 제품을 배달해 드리고 있습니다. 메뉴 창에서 주문이 가능한
									제품을 확인하실 수 있습니다.</div></td>
						</tr>
					</tbody>
					<tbody class="" style="display: none;">
						<tr class="acc_tit_tr">
							<td class="WEB">5</td>
							<td class="category"><span>제품/품질/서비스</span></td>
							<td><div class="acc_tit">
									<span class="tit">주문 가능한 최소 금액이 있나요?</span>
									<button type="button" class="btn_acc">Q</button>
								</div></td>
						</tr>
						<tr class="acc_cont">
							<td colspan="3"><div class="text_area">A. 예, 버거킹 딜리버리 서비스는
									최소 주문 금액제도에 따라 지역별, 매장별로 상이 할 수 있습니다.</div></td>
						</tr>
					</tbody>
					<tbody class="" style="display: none;">
						<tr class="acc_tit_tr">
							<td class="WEB">4</td>
							<td class="category"><span>제품/품질/서비스</span></td>
							<td><div class="acc_tit">
									<span class="tit">가격은 어떻게 확인이 가능한가요?</span>
									<button type="button" class="btn_acc">Q</button>
								</div></td>
						</tr>
						<tr class="acc_cont">
							<td colspan="3"><div class="text_area">A. 제품 주문 시에 가격을 확인할
									수 있으며, 버거킹 가격 정책에 따라 매장 가격과 배달 시 가격은 다르게 책정되어 있습니다.</div></td>
						</tr>
					</tbody>
					<tbody class="" style="display: none;">
						<tr class="acc_tit_tr">
							<td class="WEB">3</td>
							<td class="category"><span>제품/품질/서비스</span></td>
							<td><div class="acc_tit">
									<span class="tit">배달 가능한 시간은 어떻게 되는지요?</span>
									<button type="button" class="btn_acc">Q</button>
								</div></td>
						</tr>
						<tr class="acc_cont">
							<td colspan="3"><div class="text_area">A. 전매장이 동일하게 오전
									10시에서 오후 10시까지 딜리버리 서비스를 제공하고 있습니다. 매장 상황에 따라 배달이 어려운 경우 주문시 안내를
									드리고 있으며, 기상 악화시에는 배달 라이더들의 안전을 위해 배달이 중단, 제한, 지연 될 수 있습니다.</div></td>
						</tr>
					</tbody>
					<tbody class="" style="display: none;">
						<tr class="acc_tit_tr">
							<td class="WEB">2</td>
							<td class="category"><span>제품/품질/서비스</span></td>
							<td><div class="acc_tit">
									<span class="tit">배달 시간이 얼마나 걸리나요?</span>
									<button type="button" class="btn_acc">Q</button>
								</div></td>
						</tr>
						<tr class="acc_cont">
							<td colspan="3"><div class="text_area">A. 버거킹 딜리버리 서비스의
									배달 시간은 기상조건이나 주문량 및 매장 상황에 따라 소요 시간이 다르며, 고객님의 주문 시에 배달 가능한 예상
									시간을 미리 안내드리고 있습니다. 안내 드린 시간 내 음식을 제공할 수 있도록 노력하겠습니다.</div></td>
						</tr>
					</tbody>
					<tbody class="" style="display: none;">
						<tr class="acc_tit_tr">
							<td class="WEB">1</td>
							<td class="category"><span>제품/품질/서비스</span></td>
							<td>
								<div class="acc_tit">
									<span class="tit">주문을 변경,취소하고 싶은데 가능하나요?</span>
									<button type="button" class="btn_acc">Q</button>
								</div>
							</td>
						</tr>
						<tr class="acc_cont">
							<td colspan="3">
								<div class="text_area">A. 완료된 주문의 취소, 변경을
									원하시는 경우 1599-0505로 연락 후 가능 여부를 확인받으실 수 있습니다
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
	</div>

</article>

<%@ include file="../include/undermenu.jsp" %>
<%@ include file="../include/footer.jsp" %>