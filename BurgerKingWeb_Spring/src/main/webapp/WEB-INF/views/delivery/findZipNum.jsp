<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Find ZipNum</title>
<style type="text/css">
body{ font-family:Verdana; background:#F1E8E3;}
#popup{padding: 0 10px;}

table#zipcode{border-collapse:collapse; border-top:3px solid black;	border-bottom:3px solid #black; 
margin-top:15px; width:100%; font-size:100%;}
table#zipcode th, table#zipcode td{text-align:center; border-bottom: 1px dotted black;}
table th, td{  padding: 10px;}
table#zipcode  a{display:block; height:20px; text-decoration:none; padding:10px;}
table#zipcode a:hover{font-weight: bold;}

</style>
<script type="text/javascript">
function result( zipNum, sido, gugun, dong){
	opener.document.address.zip_num.value=zipNum;
	opener.document.address.addr1.value=sido+" "+gugun+" "+dong;
	self.close();
}
</script>
</head>
<body>
<div id="popup">
	<h1>우편번호검색</h1>
	<form method="post" name="formm" action="findZipNum">
		동 이름 : <input name="dong"  type="text">
		<input type="submit" value="찾기"  class="submit">
	</form>
	<table id="zipcode">
		<tr><th width="100">우편번호</th><th>주소</th></tr>
		<c:forEach items="${addressList}" var="AddressVo">
			<tr>
				<td>${AddressVo.zip_num}</td>
				<!-- onClick="result( 우편번호, 시도, 구군, 동);" -->
				<td><a href="#" onClick="result( '${AddressVo.zip_num}' , 
				'${AddressVo.sido}' , '${AddressVo.gugun}' , '${AddressVo.dong}'  );" >
				${AddressVo.sido} ${AddressVo.gugun} ${AddressVo.dong} ${AddressVo.bunji}</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>