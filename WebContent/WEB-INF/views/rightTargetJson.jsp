<%@page import="kr.or.ddit.servlet03.FileWrapper"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
List<FileWrapper> rightFiles = (List<FileWrapper>)request.getAttribute("rightFiles");
%>
[
<%
for(int i=0; i<rightFiles.size();i++){
%>
{
	"id" : "<%=rightFiles.get(i).getId()%>",
	"class" : "<%=rightFiles.get(i).getResource().isDirectory()?"dir":"file"%>",
	"displayName" : "<%=rightFiles.get(i).getDisplayname() %>"
}
<%
if(i<rightFiles.size()-1){
out.print(',');
}
}%>
]