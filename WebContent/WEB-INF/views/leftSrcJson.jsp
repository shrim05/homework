<%@page import="kr.or.ddit.servlet03.FileWrapper"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
List<FileWrapper> leftFiles = (List<FileWrapper>)request.getAttribute("leftFiles");
%>
[
<%
for(int i=0; i<leftFiles.size();i++){
%>
{
	"id" : "<%=leftFiles.get(i).getId()%>",
	"class" : "<%=leftFiles.get(i).getResource().isDirectory()?"dir":"file"%>",
	"displayName" : "<%=leftFiles.get(i).getDisplayname() %>"
}
<%
if(i<leftFiles.size()-1){
out.print(',');
}
}%>
]