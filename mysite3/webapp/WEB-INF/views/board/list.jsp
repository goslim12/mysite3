<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/board.css"	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.servletContext.contextPath}/board"
					method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(map.list) }" />
					<c:forEach items="${map.list}" var="vo" varStatus="status">
						<tr>
							<td>${map.totalRecord-status.index-(map.nowPage-1)*map.numberPerPage}</td>
							<td style="text-align:left; padding-left:${20*vo.depth}px">
								<c:if test='${"0" ne vo.depth }'>
									<img src="${pageContext.servletContext.contextPath}/assets/images/reply.png" />
								</c:if> <a
								href="${pageContext.servletContext.contextPath}/board/view?no=${vo.no}">${vo.title}</a>
							</td>
							<td>${vo.userName}</td>
							<td>${vo.hit}</td>
							<td>${vo.writeDate}</td>
							<c:if test='${vo.userNo eq sessionScope.authUser.no}'>
								<td><a
									href="${pageContext.servletContext.contextPath}/board/delete?no=${vo.no}"
									class="del">삭제</a></td>
							</c:if>
							<c:if test='${vo.userNo ne sessionScope.authUser.no}'>
								<td><a href="${pageContext.servletContext.contextPath}/board"
									class="del">삭제</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<!-- 					 	<li><a href="${pageContext.servletContext.contextPath}/board">◀</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/board">1</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/board">2</a></li>
						<li class="selected">3</li>
						<li><a href="${pageContext.servletContext.contextPath}/board">4</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/board">5</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/board">▶</a></li>
 	 -->
						<c:if test='${map.nowBlockPageStartNum gt 1}'>
							<li><a
								href="${pageContext.servletContext.contextPath}/board?p=${map.nowBlockPageStartNum-1}&kwd=${kwd}">◀</a></li>
						</c:if>
						<!-- 			<c:forEach begin="${nowBlockPageStartNum}" end="${nowBlockPageFinishNum}" var="i">
							<li><a href="${pageContext.servletContext.contextPath}/board?p=${i}">${i}</a></li>
						</c:forEach> -->

						<c:forEach begin="${map.nowBlockPageStartNum}"
							end="${map.nowBlockPageFinishNum}" var="i">
							<c:if test='${map.nowPage eq i}'>
								<li class="selected"><a
									href="${pageContext.servletContext.contextPath}/board?p=${i}&kwd=${kwd}">${i}</a></li>
							</c:if>
							<c:if test='${map.nowPage ne i}'>
								<li><a
									href="${pageContext.servletContext.contextPath}/board?p=${i}&kwd=${kwd}">${i}</a></li>
							</c:if>
						</c:forEach>
						<c:if test='${map.totalPage gt map.nowBlockPageFinishNum}'>
							<li><a
								href="${pageContext.servletContext.contextPath}/board?p=${map.nowBlockPageFinishNum+1}&kwd=${kwd}">▶</a></li>
						</c:if>
					</ul>
				</div>
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath}/board/write"
						id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>