<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/buger.css" >
<script type="text/javascript" src="static/script/buger.js"></script>
</head>
<body>



<div class="clear"></div>
<div class="mainWrap" style="background-color: #f2ebe6;" id="top";>
	   <div class="banner">
	      <div class="slide" id="slide">
	         <ul class="cnt" id="cnt">
	            <li><a href="eventDetailForm?eseq=4"><img
	                  src="image/main/banner/banner_3.png"></a></li>
	            <li><a href="eventDetailForm?eseq=2"><img
	                  src="image/main/banner/banner_1.png"></a></li>
	            <li><a href="eventDetailForm?eseq=3"><img
	                  src="image/main/banner/banner_2.png"></a></li>
	            <li><a href="deliveryUseForm"><img
	                  src="image/main/banner/banner_4.png"></a></li>
	            <li><a href="menudetailForm?pseq=155"><img
	                  src="image/main/banner/banner_5.png"></a></li>
	         </ul>
	   
	
	         <div class="btn">
	            <button type="button" class="prev" id="prev_btn">
	               <img src="image/main/banner/banner_prev.png">
	            </button>
	            <button type="button" class="next" id="next_btn">
	               <img src="image/main/banner/banner_next.png">
	            </button>
	            <div class="auto">
	               <button type="button" class="stop" id="stop">
	                  <img src="image/main/banner/banner_stop.png"></button>
	               <button type="button" class="play" id="play">
	                  <img src="image/main/banner/banner_play.png"></button>
	            </div>
	         </div>
	      </div>
	      </div>
	
	
	      <div class="test" id="test">
	         <div id="utube">
	            <iframe width="1440" height="580"
	               src="https://www.youtube.com/embed/AeDPdqHbF3w"
	               title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
	            </iframe>
	         </div>
	         <div class="home_snsWrap">
	            <div class="web_container9">
	               <ul class="list">
	                  <li class="youtube"><div class="cont1">
	                        <a
	                           href="https://www.youtube.com/channel/UCEKRI0fipK4LEgV98nI2CQA/featured"
	                           target="_blank"><span><img
	                              src="image/main/mainyoutube.png"></span></a>
	                     </div></li>
	                  <li class="facebook"><div class="cont1">
	                        <a href="https://www.facebook.com/burgerkingkorea"
	                           target="_blank"><span><img
	                              src="image/main/mainfacebook.png"></span></a>
	                     </div></li>
	                  <li class="instargram"><div class="cont1">
	                        <a href="https://www.instagram.com/burgerkingkorea/"
	                           target="_blank"><span><img
	                              src="image/main/maininstargram.png" style="background:#b08972;"></span></a>
	                     </div></li>
	               </ul>
	            </div>
	         </div>
	         <div class="home_guide">
	            <div class="web_container2">
	               <div class="guide">
	                  <div id="app">
	                     <a href="appGuideForm"> <img
	                        src="image/main/appimg.png"></a>
	                  </div>
	                  <div id="delivery77">
	                     <a href="deliveryUseForm"> 
	                     	<img src="image/main/deliveryimg.png">
	                     </a>
	                  </div>
	               </div>
	            </div>
	         </div>
	
	
	         <div class="web_container3">
	            <div class="cont2">
	              <h2 class="tit1">집에서 만나는 버거킹</h2>
                  <p class="WEB1">딜리버리 전화주문 안내 10:00–22:00 / 연중무휴 (매장별로 상이할 수
                     있습니다.)</p>
                  <p class="cs_call">
                     <strong>1599-0505</strong>
                  </p>
               </div>
            </div>
      </div>

      </div>
      <a href="#top" class="btn_top" style="display: inline; opacity: 1;">Top</a>


   <div class="clear"></div>

<%@ include file="../include/undermenu.jsp" %>
<%@ include file="../include/footer.jsp" %>
