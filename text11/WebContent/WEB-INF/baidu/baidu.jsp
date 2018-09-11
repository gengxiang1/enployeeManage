<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
 <style type="text/css">
 #main{
 width:500px;
 margin:20px  auto;
 }

 </style>
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript">
  $().ready(function(){
	  $(":text").on("input",function(){
		  if($(this).val()!=""){
		  $.ajax(
				  {
					  url:"baidu",
					  type:"get",
				 	  data:{
						  type:"show",
						  content:$(this).val()
						  
						  
					  },
					  dataType:"text",
					  success:function(data){
						  $(content).html(data);
					  },
					  
				  })}
		  else{
			  
			  $("#content").html("");
		  }
				  
				  
			  });
			 
	  
  })
  
  </script>
</head>
<body>
<div id="main">
<input type="text"/>
<input  type="button" value="搜索"/>
<div id="content"></div>
</div>
</body>
</html>