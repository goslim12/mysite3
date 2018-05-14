<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">
		
				<form id="join-form" name="modify" method="post" action="${pageContext.servletContext.contextPath}/user/modify">
					<input type="hidden" name="a" value="modify">
					<input type="hidden" name="no" value="${sessionScope.authUser.no}">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${sessionScope.authUser.name}">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="${sessionScope.authUser.email}">
					<!--  <input type="button" value="id 중복체크">-->
					
					<label class="block-label">기존 패스워드</label>
					<input name="password" type="password" value="">
					
					<label class="block-label">새로운 패스워드</label>
					<input name="newPassword" type="password" value="">
					
					<c:if test='${"female" eq authUser.gender }'>
						<fieldset>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
							<label>남</label> <input type="radio" name="gender" value="male">
						</fieldset>
					</c:if>
					<c:if test='${"male" eq authUser.gender }'>
						<fieldset>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female">
							<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
						</fieldset>
					</c:if>
					<!-- 
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					 -->
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp" /></div>
</body>
</html>