<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite4/assets/css/user.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function(){
		return true;   // 테스트용
		
		
		// 1. 이름체크
		if($("#name").val()==""){
			$("#dialog p").text("이름은 필수입력 항목입니다.");
			$("dialog").dialog();
			$("#name").focus();
			return false;
		}
		//2-1 이메일 체크
		if( $("#email").val()==""){
			$("#dialog p").text("이메일은 필수입력 항목입니다.");
			$("email").focus();
			return false;
		}
		// 2-2 이메일 중복체크 유무
		if($("#img-checkemail").is(":visible")==false){
			alert("이메일 중복체크를 해야합니다.");
			return false;
		}
		// 3. 비밀번호 체크
		if($("input[type='password']").val()==""){
			alert("비밀번호는 필수입력 항목입니다.")
		}
		// 4.약관동의
		if($("#agree-prov").IS(":checked")==false){
			alert("약관동의를 해야합니다.")
			return false;
		}
		return true;
	});
	
	$(function(){
		return true // 테스트용
		
		$("#btn-chkeamil").click(function(){
			var email=$("#email").val();
			console.log(email);
			if(email==""){
				return;
			}
			$.ajax({
				url:"${pageContext.request.contextPath }/user/api?a=checkemail&email="+email,
				type:"get",
				dataType:"json",
				data:"",
				// contentType:"application/json",
				success:function(response){
					console.log(response);
				},
				error:function(jqXHR,status,e){
					console.error(status+":"+e)
				}
			});
		});
	});
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/head.jsp"></jsp:include>

		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="">
					<input id="btn-chkeamil" type="button" value="중복체크">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
	<div id="dialog" title="가입폼 체크" display="none">
  		<p></p>
	</div>
</body>
</html>