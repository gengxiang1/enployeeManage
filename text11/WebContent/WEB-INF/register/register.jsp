<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<style type="text/css">
body {
	background: #2776d7;
	background: url("/pic/11.jpg");
	opactiy: 0.8;
}

#main {
	width: 400px;
	height: 300px;
	background: #fff;
	margin: 100px auto;
	opacity: 0.8;
	left: 0;
	top: 0;
	border-radius: 10px;
}

#bottom a{
	clear: both;
	font-width: bloder;
	text-decoration:underline;
	margin-left:250px;
}
#bottom a:hover{
color:red;
cursor:Pointer
}
#main .content {
	width: 400px;
	margin: 20px 60px;
}

#top {
	margin: 10px 20px;
	color: #999;
	font-size: 14px;
}

#register {
	border: 1px solid blue;
	text-align: center;
	margin: 10px 80px;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script>

$().ready(function(){
	

  
	
	$("form").submit(function(){
		if($("[name='userName']").val()==""){
			alert("账号不能为空");
			return false;
		  }else if($("[name='password1']").val()==""){
			alert("密码不能为空");
			return false;
	       }else if($("[name='password2']").val()==""){
			alert("确认密码不能为空");
			return false;
			}else if($("[name='password1']").val()!=$("[name='password2']").val()){
				alert("兩次輸入密碼不一樣");
				return false;
			}else{
				return true;
			}
				
})
 if(${str!=null}){
	 $("#meg").html(${str}); 
	 setTimeout(function(){
		 $("#meg").html(""); 
	 },2000);
 }

	
})


</script>

<title>Insert title here</title>
</head>
<body>
	<div id="main">
		<div id="top">管理系统注册</div>
		<div class="content">
			<form class="form-horizontal" role="form" action="reg" method="post">
				<input type="hidden" name="type" value="reg" />
				<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label">账号</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" placeholder="请输入名字"
							name="userName"><span id="meg"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="lastname" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-5">
						<input type="password" class="form-control" placeholder="请输入密码"
							name="password1" />
					</div>
				</div>

				<div class="form-group">
					<label for="lastname" class="col-sm-2 control-label">确认密码</label>
					<div class="col-sm-5">
						<input type="password" class="form-control" placeholder="请输入密码"
							name="password2" />
					</div>
				</div>

				<div id='center'>
					<div class="col-sm-5" id="log">
						<button type="submit"
							class="btn btn-primary col-sm-8 control-label" id="register">注册</button>
					</div>
				</div>

			</form>
			
		</div>
    
 <br/>
 <br/>
 <br/>
    <div id="bottom"><a href="log">已有账号，直接登录</a></div>
	
	</div>


</body>
</html>