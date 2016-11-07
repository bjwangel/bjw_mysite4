
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/main.css"
	rel="stylesheet" type="text/css">

</head>
<body>

	<div id="container">
		<c:import url="/WEB-INF/views/includes/head.jsp"></c:import>
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile"
						src="${pageContext.request.contextPath }/images/ab.jpg"
						style="width: 200px">
					<h2>안녕하세요.변준우의 mysite에 오신 것을 환영합니다.</h2>
					<p>
						안녕하세요.<br> 방명록,게시판 구현중입니다.
						둘러보세요~!<br> <br> <a href="#">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="main"></c:param>
		</c:import>

		<c:import url="/WEB-INF/views/includes/footer.jsp">
		</c:import>

	</div>
</body>
</html>