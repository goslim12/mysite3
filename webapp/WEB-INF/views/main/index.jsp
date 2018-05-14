<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  


<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" src="${pageContext.servletContext.contextPath}/assets/images/duckJa.png"  height=250px >
					<h2>안녕하세요. 김덕자의  mysite에 오신 것을 환영합니다.</h2>
					<p>
						이 사이트는  '덕자'견주 김경한의 덕자를 위한 사이트입니다.<br>
						메뉴는  사이트 소개, 방명록, 게시판이 있고요. 저와 덕자의 아름다운 추억을 공유하는 것이 주된 목적입니다. 많은 방문 부탁드립니다.
						냥이족 방문 금지!!!
						<br><br>
						<a href="${pageContext.servletContext.contextPath}/guestbook">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="main"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>