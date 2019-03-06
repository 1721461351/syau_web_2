<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<link rel="icon" href="./favicon.ico" type="image/x-icon">
<style type="text/css">
	#div{
		margin-top: 200px;
		font-size: 16px;
		font-weight: normal;
		background-color: #ECF4FF;
		height: 35px;
		width: 100%;
	}
	#div img{
		width: 18px;
		height: 18px;
		padding-top: 7px;
	}
	#div a{
		text-decoration: none;
	}
</style>
</head>
<body>
	<div id="div" align="center">
		<img alt="完成" src="images/exit.png">
		已经完善成功，<span id="second" style="color: blue;">3</span>秒后跳转首页，如果不跳转请    <a href="student.jsp">点击这里</a>
	</div>
</body>
<script type="text/javascript">
	var time=3;
	var timer;
	timer=setInterval(
			function(){
				var second=document.getElementById("second");
				if(time>=1){
					second.innerHTML=time;
					time--;
				}else{
					clearInterval(timer);
					location.href="student.jsp";
				}
				
	}, 1000);
</script>
</html>