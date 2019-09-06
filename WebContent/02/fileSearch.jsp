<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/fileSearch.jsp</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">

</style>
</head>
<body>
<form id="explorerForm">
        좌패널 위치 : <input type="text" name="src" id="src" value="" readonly>
        우패널 위치 : <input type="text" name="target" id="target" value="" readonly>
    </form>
    <ul id="srcArea">
    <%
		Map<String,Map<String,String>> files = (Map)request.getAttribute("srcData");
    	if(files!=null){
			for(Map.Entry<String, Map<String, String>> tmp : files.entrySet()){
				String realPath = tmp.getKey();
				Map<String,String> fileMap = tmp.getValue();
				for(Map.Entry<String,String> tmp2: fileMap.entrySet()){
					String key = tmp2.getKey();
				%>
<%--         <li class="dir" id=<%=key%>><%=key%></li> --%>
        
		</ul>
		<%
				}
    		}
    	}%>
    </ul>
    <form action="?" id="commandForm" method="post">
        <p>
            좌패널 위치 : <input type="text" name="src" id="src" value="" readonly>
            우패널 위치 : <input type="text" name="target" id="target" value="" readonly>
        </p>
        <p>
            선택한 파일 : <input type="text" name="srcFile" id="srcFile" value="" required readonly>
        </p>
        <p>
            <label><input type="radio" name="command" value="COPY" required="">복사</label>
            <label><input type="radio" name="command" value="MOVE" required="">이동</label>
            <label><input type="radio" name="command" value="DELETE" required="">삭제</label>
            <input type="submit" value="명령 수행">
        </p>
    </form>
    <ul id="targetArea">
<%--         <li class="dir" id=<%= %>><%= %></li> --%>
    </ul>
    <script type="text/javascript">
        var explorerForm = $("#explorerForm");
        var commandForm = $("#commandForm");
        var src = $("#src");
        var target = $("#target");
        var srcFile = $("#srcFile");
        $("ul#srcArea>li.file").on("click", function () {
            var file = $(this).prop("id");
            $(this).siblings("li").removeClass("active");
            $(this).toggleClass("active");
            if ($(this).hasClass("active")) {
                srcFile.val(file);
            } else {
                srcFile.val("");
            }
        });
        $("ul").on("dblclick", "li", function () {
            var thisId = $(this).prop("id");
            var parentId = $(this).parent().prop("id");
            if (parentId == "srcArea") {
                src.val(thisId);
            } else if (parentId == "targetArea") {
                target.val(thisId);
            }
            explorerForm.submit();
        });
    </script>

</body>
</html>