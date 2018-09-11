<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Insert title here</title>

<style>
#main {
	width: 800px;
	margin: 20px auto;
}
#proNo,#pro{
 width:700px;
 height:200px;
 border:1px solid #337ab7;
 border-radius:3px;

}
#button{
width:120px;
margin:20px 300px;
 
}
#add{
margin-right:30px;
}
 .pro{
 font-size:15px;
 float:left;
 height:40px;
 background:#337ab7;
 line-height:40px;
 margin-left:10px;
 color:#fff;
 padding:0 10px;
 text-align:centre;
 margin-top:10px;
 border-radius:3px;
}
 .select{
background:red;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script>	
$().ready(function(){
	
	
	$(".pro").click(function(){
		$(this).toggleClass("select");
	})
	
	$("#add").click(function(){
		  var pids="";
		   if($("#proNo").find(".select").length>0){
			   
			   $("#proNo").find(".select").each(function(index,element){
					  pids+=$(this).data("id")+",";
				  })
			      pids=pids.substring(0,pids.length-1);
	
			
			  $.ajax({
				  url:"dep",
				  type:"post",
				  data:{
					  type:"manage4",
					  did:"${id}",
					  pid:pids
					 },
			    dataType:"text",
			    success:function(data){
				  if(data=="true"){
					 var dep=$("#proNo").find(".select");
					   dep.removeClass("select");
					  $("#pro").append(dep);
					  
					  
				  }
				  
			  }
				  
				  
			  })
		  }else{
			  alert("请选中");
		  }
		
		
		
	})
	
	
	
		$("#delete").click(function(){
		   var pids="";
		   if($("#pro").find(".select").length>0){
			  $("#pro").find(".select").each(function(index,element){
				  pids+=$(this).data("id")+",";
			  })
				 
				   
				   
			
			   pids=pids.substring(0,pids.length-1);
			   
			  $.ajax({
				  url:"dep",
				  type:"post",
				  data:{
					  type:"manage3",
					  did:"${id}",
					  tid:pids
					 },
			    dataType:"text",
			    success:function(data){
				  if(data=="true"){
					 var dep=$("#pro").find(".select");
					   dep.removeClass("select");
					  $("#proNo").append(dep);
					  
					  
				  }
				  
			  }
				  
				  
			  })
		  }else{
			  alert("请选中");
		  }
		
		
		
	})
	
	
	
	
})


</script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
<div id="main">


 <h1> ${name}</h1>
  <div id="pro">
 
			<c:forEach items="${proList}" var="pro">
			
			 <div class="pro" data-id="${pro.id}" draggable = "true">${pro.name}</div>

		 </c:forEach>   
		
</div>

  <div id="button">
  <button id="add" type="button" class="btn btn-primary">↑</button>
   <button id="delete" type="button" class="btn btn-primary">↓</button>
  
  
  </div>



		<div id="proNo">

			<c:forEach items="${proListNo}" var="pro">

          <div class="pro" data-id="${pro.id}" draggable = "true">${pro.name}</div>
		 </c:forEach>
		
	</div>
		
		

	</div>









</body>
</html>