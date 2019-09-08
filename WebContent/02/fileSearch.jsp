<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/fileSearch.jsp</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
   li {
            margin-top: 10px;
            margin-right: 10px;
            padding: 5px 0px 5px 5px;
        }

        li.dir {
            cursor: pointer;
        }

        ul {
            border: 2px solid black;
            width: 20%;
            height: 500px;
            overflow: auto;
        }

        ul#srcArea {
            float: left;
        }

        form#commandForm {
            float: left;
            width: 50%;
            height: 500px;
            display: table-cell;
            vertical-align: middle;
            text-align: center;
        }
        ul#targetArea {
            float: right;
        }
        .active {
            border: 2px solid blue;
        }
</style>
</head>
<body>
<form id="explorerForm">
	<% 
	String srcUri = (String)request.getAttribute("srcUri"); 
	String targetUri = (String)request.getAttribute("targetUri");
	%>
        <input type="hidden" name="src" id="src" value="<%=srcUri %>" readonly="" >
        <input type="hidden" name="target" id="target" value="<%=targetUri %>" readonly="" >
    </form>
    <ul id="srcArea">
    <%	
    	if(!srcUri.equals("/")){
    	%>
	        <li class="dir" id="upper">..</li>
		<%    		
    	}
		List<String> srcFiles = (List<String>) request.getAttribute("srcFiles");
		if(srcFiles!=null){
			for(String tmp : srcFiles){
				if(tmp.contains(".")){			
		%>
		<li class="file" id=<%=tmp.substring(tmp.lastIndexOf("/")+1,tmp.length()) %>><%=tmp.substring(tmp.lastIndexOf("/")+1,tmp.length()) %></li>
        	<%}else{ %>
        <li class="dir" id=<%=tmp%>><%=tmp.substring(tmp.lastIndexOf("/")+1,tmp.length())%></li>
        
	<%    		}
			}
		}%>
    </ul>
    <form action="?" id="commandForm" method="post">
        <p>
            <input type="hidden" name="src" id="src" value="<%=srcUri %>" readonly="">
            <input type="hidden" name="target" id="target" value="<%=targetUri %>" readonly="" >
        </p>
        <p>
            선택한 파일 : <input type="text" name="srcFile" id="srcFile" value="" required="">
        </p>
        <p>
            <label><input type="radio" name="command" value="COPY" required="">복사</label>
            <label><input type="radio" name="command" value="MOVE" required="">이동</label>
            <label><input type="radio" name="command" value="DELETE" required="">삭제</label>
            <input type="submit" value="명령 수행">
        </p>
    </form>
    <ul id="targetArea"> 
    <%	
    	if(!targetUri.equals("/")){
    	%>
	        <li class="dir" id="upper">..</li>
		<%    		
    	}
    	List<String> targetFiles = (List<String>) request.getAttribute("targetFiles");
    	if(targetFiles!=null){
    		for(String tmp:targetFiles){
    			if(tmp.contains(".")){			
		%>
		<li class="file" id=<%=tmp.substring(tmp.lastIndexOf("/")+1,tmp.length())%>><%=tmp.substring(tmp.lastIndexOf("/")+1,tmp.length()) %></li>
       		 <%}else{ %>
        <li class="dir" id=<%=tmp%>><%=tmp.substring(tmp.lastIndexOf("/")+1,tmp.length())%></li>
        
		<% 
       			}
			}
		}
		%>
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
        $("ul").on("dblclick", "li.dir", function () {
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