<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
#main{
width:900px;
margin:20px auto;

}
.pro{
width:400px;
float:left;
margin:10px 50px 10px 0px;
border:1px dashed #ccc;

}
#saveBtn{
clear:both;
margin:10px auto;
text-align:center;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$().ready(function(){
	var pros="";
	$("#save").click(function(){
		$(".pro").each(function(index,element){
			var id=$(this).find("[name=id]").val();
		
			var name=$(this).find("[name=name]").val();
			
		  pros+=(id+","+name+";");
		})
	   pros=pros.substring(0,pros.length-1);
         location.href="pro?type=update&&pros="+pros;
	})
	
	
})

</script>
</head>
<body>

<div id="main">
<c:forEach  items="${pros}" var="pro">
 <form class="form-horizontal pro" role="form" mothod="post">
<input type="hidden" name="id" value="${pro.id }" /> 
  <input type="hidden" name="type" value="updatabatch1"/>
  <div class="form-group">
    <label for="firstname" class="col-sm-2 control-label">项目</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="name" value="${pro.name }">
    </div>
  </div>

</form>

</c:forEach>
<div id="saveBtn">
<button id="save" type="button" class="btn btn-primary">保存</button>
</div>

</div>



</body>
</html>