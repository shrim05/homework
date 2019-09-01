<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
Calendar cal = Calendar.getInstance();
String yearParam = request.getParameter("year");
String mthParam = request.getParameter("mth"); 

int year = cal.get(Calendar.YEAR);
int mthForCal = cal.get(Calendar.MONTH);
int date = cal.get(Calendar.DATE);
int mth = cal.get(Calendar.MONTH)+1;
if(yearParam != null || mthParam != null) {
	year = Integer.parseInt(yearParam);
	mth =  Integer.parseInt(mthParam);
	if(year>=1 && year<=9999 && mth>=1 && mth<=12){
		mthForCal = mth-1;
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.MONTH,mthForCal);
		cal.set(Calendar.DATE,1);
	}else{
		response.sendError(500,"올바른 날짜를 입력하세요");
	}
}
int mthTotalDays = cal.getActualMaximum(Calendar.DATE);
int mthStartDay = cal.get(Calendar.DAY_OF_WEEK);
int cellIndex = 0;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
       window.onload = function(){
        let clock = document.getElementById('clock');
        let today = document.getElementById('date');
        setInterval(() => {
            let currentDate = new Date();  
            let year = currentDate.getFullYear();
            let mth = currentDate.getMonth();
            let date = currentDate.getDate();
            let day = currentDate.getDay();
            let week = ['일','월','화','수','목','금','토',];
            let hours = currentDate.getHours();
            let min = currentDate.getMinutes();
            let sec = currentDate.getSeconds();
            today.innerHTML = year +'년 ' +mth+'월 '+date+'일 '+week[day]+'요일';
            clock.innerHTML = hours +'시 '+ min+'분 ' + sec+'초';
        }, 1000);
    }
    
    </script>
<style>
table {
	border-collapse: collapse;
	border: 1px solid black;
	width: 50%;
}

tr, td {
	width : 30px;
	border: 1px solid black;
	text-align: center;
}
.blank{
	background-color: gray;
}
body{
	background-color: #454545;
	color:white;
}
#clock,#date{
	color:#429CE3;
}
#sec_cal{
	
}
a:link {text-decoration: none; color: white;}
a:visited {text-decoration: none; color: white;}
a:active {text-decoration: none; color: white;}
a:hover {text-decoration: underline; color: red;}
</style>
</head>

<body>
	<header id="sec_header">
	<div>현재시간 : </div>
	<div id="date"></div>
	<div id="clock"></div>
	
	<div>선택한 날짜: <%=year+"년 "+mth+"월" %></div>
	</header>
	<nav id="sec_nav">
	<form method="get">
		<input type="text" name="year" placeholder="연도입력"> <input
			type="text" name="mth" placeholder="월입력"> <input
			type="submit">
	</form>
	<%if(mth>1) {%>
	<a href="<%=request.getRequestURL()%>?year=<%=year%>&mth=<%=mth-1%>"><<<&nbsp</a>
	<%} %>
	<%if(mth<12){ %>
	<a href="<%=request.getRequestURL()%>?year=<%=year%>&mth=<%=mth+1%>">&nbsp>>></a>
	<%} %>
	</nav>
	<section id="sec_cal">
	<table>
		<tr>
			<td>일</td>
			<td>월</td>
			<td>화</td>
			<td>수</td>
			<td>목</td>
			<td>금</td>
			<td>토</td>
		</tr>
		<tr>
			<%
              	for(int i = 1 ; i < mthStartDay;i++){
              %>
			<td class="blank"></td>
			<%	cellIndex++;
              	}
              %>
			<%
              	for(int i=1;i<=mthTotalDays; i++){
              %>
			<td><%=i%></td>
			<%		cellIndex++;
              		if(cellIndex==7){
              %>
		</tr>
		<%				if(i<=mthTotalDays){
              %>
		<tr>
			<%		
							cellIndex=0;
			            }
              		}
              	}
			if(cellIndex!=0){
			for(int i=cellIndex ; i<7;i++){
			%>
					<td class="blank"></td>
			<%		
				}
			}
             %>
		</tr>

	</table>

	</section>
	<section id="sec_todo"></section>
</body>

</html>