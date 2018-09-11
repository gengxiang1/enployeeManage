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
#pro {
	width: 800px;
	margin: 20px auto;
}

#pro .select {
	background: #337ab7
}


td {
	width: 30px;
	height: 20px；
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script>
$().ready(function(){
	var add=0;
	
	function add(){
		var i=0;
		  var pid=$("#proselect").val();
	 		
			$.ajax({
				url:"dep",
				type:"post",
			 data:{
				 type:"manage1",
				 did:${id},
			   	 pid:pid
				 
			  },
			  dataType:"text",
			 success:function(data){ 
			
				if(data="true"){
					 var pname="";
					$("#proselect").children().each(function(index,element){
						 if($(this).val()==pid){
					  	 pname=$(this).text();
					  	 i=index;
					  	var tr="<tr class='m' data-id($(pro.id))><td>"+pid+"</td><td>"+pname+"</td></tr>"
						$("#table").append(tr);
						$("#proselect").children().eq(i).remove();
						 }
					})
					
				 }
				if($("#proselect").children().length<=0){
					 $("#add").addClass("disabled");
				 	  $("#add").unbind("click")
				}
			 }	
				})
				
				 
			 }
		
		
			
	
	

// 	   <c:if test="${f:length(proList)==0}">
// 	  $("#delete").addClass("disabled");
// 	  $("#delete").unbind("click")
	  
// 	</c:if>
	  
// 	  <c:if test="${f:length(proListNo)==0}">
// 	  $("#add").addClass("disabled");
// 	  $("#add").unbind("click")
// 	</c:if>
	var ids="";
	$(document).on("click",".m",function(){
	
		$(this).toggleClass("select")
		
		
	})	

	
	
	
	$("#delete").click(function(){
		var tid=-1;
		if($("#pro .select").length>0){
			
		 tid=$("#pro .select").children().eq(0).text();
			
			

			}
			else{
				
				alert("请选中");
			}
		var i=0;
	  

		$.ajax({
			url:"dep",
			type:"post",
		 data:{
			 type:"manage2",
			 did:${id},
		  	 tid:tid
			 
		 },
		  dataType:"text",
		 success:function(data){ 
		
			if(data="true"){
				 var pname="";
				$(".m").each(function(index,element){
					 if($(this).children().eq(0).text()==tid){
				    	 pname=$(this).children().eq(1).text();
				  	      i=index;
				  	    var option="<option value='"+tid+"'>"+pname+"</option>"
						$(".m").eq(i).remove();
						$("#proselect").append(option);
					 }
				})
				
				if($(".m").length<=0){
			      $("#delete").addClass("disabled");
			 	  $("#delete").unbind("click")
					
				}
				if($("#proselect").children().length>0){
					$("#add").removeClass("disabled");
					$("#add").bind("click",function(){
					  alert(1321);
					})
					
				}
				
			 }
			
			 
		 }
			
		})
		
	})
	
	

	$("#add").click(function(){
		var i=0;
		  var pid=$("#proselect").val();
	 		
			$.ajax({
				url:"dep",
				type:"post",
			 data:{
				 type:"manage1",
				 did:${id},
			  	 pid:pid
				 
			  },
			  dataType:"text",
			 success:function(data){ 
			
				if(data="true"){
					 var pname="";
					$("#proselect").children().each(function(index,element){
						 if($(this).val()==pid){
					  	 pname=$(this).text();
					  	 i=index;
					  	var tr="<tr class='m' data-id($(pro.id))><td>"+pid+"</td><td>"+pname+"</td></tr>"
						$("#table").append(tr);
						$("#proselect").children().eq(i).remove();
						 }
					})
					
				 }
				if($("#proselect").children().length<=0){
					 $("#add").addClass("disabled");
				 	  $("#add").unbind("click")
				 	  
				 	  
				}
			
				
				
				
				
				
			 }	
				
			})
	    
			
		})
		
	
	$("#back").click(function(){
		 location.href="dep";
	})
	
})
	
					
</script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
<div id="pro">
  
  <h1> ${name}</h1>

		<table class="table table-bordered table-striped table-hover" id="table">
			<tr id="m123">
			
				<th>id</th>
				<th>项目</th>
	         
			
			</tr>
			<c:forEach items="${proList}" var="pro">
			<tr data-id=${pro.id} class="m">
			   <td name="id">${pro.id}</td>
				<td name="name"><a href="pro">${pro.name}</a></td>
				
			</tr>
		 </c:forEach>
		</table>

		
		
		<div  class="col-sm-5">
		
		<select class="form-control" id="proselect">
		<c:forEach items="${proListNo}" var="pro">
		<option value="${pro.id}" >${pro.name}</option>
		</c:forEach>
		
		</select>
		
		</div>
	
		
		<button id="add" type="submit" class="btn btn-primary">添加</button>
		
		<button id="delete" type="button" class="btn btn-primary">删除</button>
		
    <button id="back" type="button" class="btn btn-primary">返回</button>


	</div>









</body>
</html>