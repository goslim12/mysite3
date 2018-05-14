<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath}/assets/css/user.css"
	rel="stylesheet" type="text/css">
<script
	src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>
<script>
	$(function() {
		$("#btn-checkemail")
				.click(
						function() {
							var email = $("#email").val();
							if (email == "") {
								return;
							}
							$.ajax({
										url : "${pageContext.servletContext.contextPath}/api/user/checkemail?email="
												+ email, //요청할 URL
										dataType : "json", //응답받을 데이터타입
										type : "get", //요청 방식
										data : "", //서버에 요청시 보낼 파라미터 ex) {name:홍길동}
										success : function(response) { //요청 및 응답에 성공했을 경우
											if (response.result != "success") {
												console.log(respone.message);
												return;
											}
											if (response.data == "exist") {
												alert("이미 사용 중인 이메일 입니다.");
												$("#email").val("").focus();
												return;
											}
											
											$("#img-check").show();
											$("#btn-checkemail").hide();
										},
										error : function(xhr, status, e) { //요청 및 응답에 실패 한 경우
											console.error(status + ":" + e);

										}
									});
							<!--
							console.log("clicked");
							-->
						});
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form:form 
				modelAttribute="userVo" 
				id="join-form" 
				name="joinForm" 
				method="post"
				action="${pageContext.servletContext.contextPath}/user/join">
					<!-- <input type="hidden" name="a" value="join"> -->
					<label class="block-label" for="name">이름</label> 
					<form:input path="name"/>
					<p style="padding:0; font-weight:blod; text-align:left; color:#f00">
						<form:errors path="name"/>
					</p>
					<label class="block-label" for="email">이메일</label> 
					<form:input path="email"/>
					 <img
						id='img-check' style="display: none"
						src="${pageContext.servletContext.contextPath}/assets/images/check.png" />
					<input type="button" value="id 중복체크" id="btn-checkemail">
					 <label
						class="block-label">패스워드</label> 
						<input name="password"
						type="password" value="">
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('password') }">
							<p style="padding: 0; text-align =left; color: #869">
								<strong> 
								<spring:message 
								code="${errors.getFieldError('password').codes[0]}" 
								text="${errors.getFieldError('password').defaultMessage}"/>  <!-- 이거쓰지마삼 form 쓰삼 -->
								<!--  ${errors.getFieldError( 'password' ).defaultMessage } -->
								</strong>
							</p>
						</c:if>
					</spring:hasBindErrors>
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					<input type="submit" value="가입하기">
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>