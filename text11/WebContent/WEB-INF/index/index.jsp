<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">


*{
margin:0;
padding:0;
}
#main{

overflow:hidden;
}
#top h3{
height:30px;
margin-top:5px;
text-align:center;
line-height:80px;
color:red;
}

#right{
width:900px;
height:600px;
float:left;
margin:60px 100px;
}
#left{
width:180px;
height:800px;
float:left;
background:rgb(75,81,84);
}
#top ,#bottom{
height:80px;
background:blue;
clear:both;
}
.yi{
height:30px;
width:160px;
color:#fff;
line-height:30px;
text-align:center;
margin:30px 10px;
background:blue;


}
.er{
width:160px;
list-style:none;
margin-left:5px;
}
.er li{

margin-top:5px ;
background:#ccc;
text-align:center;
color:#fff;
height:30px;
line-height:30px;
font-size:14px;
}
.er a{
color:#000;
text-decoration:none;


}
#mes{
height:20px;
width:200px;
margin-left:1200px;

margin-top:0;
color:red;

}
#mesNum{
height:20px;
width:200px;
margin-left:1200px;

margin-top:0;
color:red;

}

</style>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
var websocket = null;
//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
	websocket = new WebSocket("ws://192.168.0.155:8080/text11/websocket");
} else {
	alert('没有建立websocket连接')
}

//接收到消息的回调方法就是类A调用类B中的某个方法b，然后类B又在某个时候反过来调用类A中的某个方法a
websocket.onmessage = function(event) {
	setMessage(event.data);
	
}

//连接关闭的回调方法
 websocket.onclose = function() {
// 		setMessage("close");
 	}


//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口。
window.onbeforeunload = function() {
	websocket.close();
}

//将消息显示在网页上
function setMessage(text) {
	
	$("#mes").html("在线人数："+ text );
	
}

//关闭连接
function closeWebSocket() {
	websocket.close();
}






$().ready(function(){
	
	
	$(".yi").next().hide();
	$(".yi").click(function(){
		$(this).next().slideToggle(800);
		
		
		
	})
	
	
})



</script>
</head>
<body>
<div id="content"> 
 <div id="top"><h3>欢迎 ${username}！进入员工管理系统   </h3> 
   <span id="mes"></span>
   <div id="mesNum">网站共有:${applicationScope.num}</div>
 
 
 
 
 </div>
<div id="main">
<div id="left">

<div class="yi">员工管理</div>
<ul class="er">
<li><a href="emp"target="right">员工管理</a></li>
<li><a href="emp?type=showadd"  target="right">员工添加</a></li>

</ul>
<div  class="yi">部门管理</div>
<ul class="er">
<li><a href="dep" target="right">部门管理</a></li>
<li><a href="dep?type=showadd" target="right">部门添加</a></li>

</ul>

<div class="yi">项目管理</div>
<ul class="er">
<li><a href="pro" target="right">项目管理</a></li>
<li><a href="pro?type=showadd" target="right">项目添加</a></li>

</ul>
<div class="yi">绩效管理</div>
<ul class="er">

<li><a href="score?type=see" target="right">绩效查看</a></li>
<li><a href="score" target="right">绩效修改</a></li>


</ul>

</div>
<iframe id="right" name="right" src="emp" frameborder="0" scrolling="no"></iframe>
</div>

<div id="bottom"></div>


</div>

</body>
</html>