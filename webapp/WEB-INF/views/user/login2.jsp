<%@ page contentType="text/html;charset=UTF-8" %>
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
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
	
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post" action="${pageContext.servletContext.contextPath}/user2/login">
					<!-- <input type="hidden" name="a" value="login"/> -->
					<label class="block-label" for="email">이메일</label>
					<c:choose>
					<c:when test="${not empty param.email }">
						<input id="email" name="email" type="text" value="${param.email}">
					</c:when>
					<c:when test="${empty param.email }">
						<input id="email" name="email" type="text" value='${""}'>
					</c:when>
					</c:choose>
					<label class="block-label" >패스워드</label>
					<input name="password" type="password" value="">
					<c:if test='${"fail" eq requestScope.result }'>
						<p>
							로그인이 실패 했습니다.
						</p>
					</c:if>
					<input type="submit" value="로그인">
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>

	</div>
</body>
</html>