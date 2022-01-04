<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>AdminLogin</title>
		<link rel="stylesheet" href="admin/css/admin.css">
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Nanum+Myeongjo:wght@800&family=Noto+Sans+KR&display=swap" rel="stylesheet">
	</head>
	<body>
		<div id="wrap">
			<header>
				<div class="adminLogin_header">
					<span>BurgerKing Manager Page</span>
				</div>
			</header>
			<div class="clear"></div>
			
			<article>
				<div id="loginform">
					<form method="post" action="adminLogin">
						<div class="adminlogo">
							<a id="logo"> 
								<img src="image/main/burgerkinglogo.png"/>
							</a>	
						</div>
						<table>
							<tr>
								<td>아이디</td>
								<td><input type="text" name="id" size="10" value="${dto.id }"></td>
							</tr>
							
							<tr>
								<td>비밀번호</td>
								<td><input type="password" name="pwd" size="10"></td>
							</tr>
							
							<tr align="center">
								<td colspan="2">
									<input type="submit" class="btn" value="로그인" >
									<br><br>
									<h4 style="color: red">${message}</h4>
								</td>
							</tr>	
						</table>
					</form>
				</div>
			</article>
		</div>
	</body>
</html>