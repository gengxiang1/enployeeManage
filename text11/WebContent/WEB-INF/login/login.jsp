<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"/>
<style type="text/css">
body{
background:#2776d7;
background:url("/pic/14.jpg");
opactiy:0.8;
}
#main{
position:relation;
width:400px;
height:550px;
background:#fff;
margin: 50px auto ;
opacity:0.8;
left:0;
top:0;
border-radius:10px;
}
#title{
margin-left:10px;
clear:both;
color:red;
font-width:bloder;
}
#main .content{
width:400px;
margin:20px 60px;
}
#img1{
margin:40px 50px;
}

#top{
margin:10px 20px;
  color: #999;
  font-size: 14px;
  display: inline-block


}
#login{
border:1px solid blue;
text-align:center;
margin:10px 80px;
}
#register{
width:40px;
  margin:0 350px;
  text-decoration:underline;
  
}
#register:hover{
color:red;
cursor:Pointer;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script>

$().ready(function(){
	
	
	if(self!=top){
		
		top.location="log";
	}
	
	$("#image").click(function(){
		
		$(this).attr("src","log?type=randomImage&"+Math.random())
		
		
	})
  
	
	$("form").submit(function(){
		if($("[name='name']").val()==""){
			alert("账号不能为空");
			return false;
		} else if($("[name='password']").val()==""){
			alert("密码不能为空");
			return false;
         }else{
        	 
        	 return true;
         }
				
})
 if(${str!=null}){
	 $("#title").html('${str}');
	 setTimeout(function(){
		 $("#title").html(""); 
	 },2000);
 }

	
})


</script>

<title>Insert title here</title>
</head>
<body>
<div id="main">
<div id="top">管理系统登录</div>
<div id="img1">
<img src="/pic/15.jpg" width="300px" height="200px"/>

</div>

<div class="content">
<form class="form-horizontal" role="form" action="log" method="post">
<input type="hidden" name="type" value="log"/>
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">账号</label>
		<div class="col-sm-5">
			<input type="text" class="form-control" 
				   placeholder="请输入名字" name="name" value="${name}">
		</div>
	</div>
	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">密码</label>
		<div class="col-sm-5">
			<input type="password" class="form-control"  
				   placeholder="请输入密码" name="password">
		</div>
	</div>
	<div class="form-group">
	<label class="col-xs-2 control-label">验证</label>
		<div class="col-xs-4">
			<input type="text" class="form-control"  
				   placeholder="请输入验证码" name="random">
		</div>
		<div class="col-xs-4">
		<img id="image" src="log?type=randomImage"/>
		</div>
	</div>
	<div id='center'>
		<div class="col-sm-5" id="log">
		<button type="submit" class="btn btn-primary col-sm-8 control-label" id="login">登录</button>
		</div>
	</div>
	
</form>
</div>

<div id="title"></div>
<div id="register"><a href="reg">注册</a></div>
</div>


</body>
</html>