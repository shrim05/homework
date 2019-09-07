package kr.or.ddit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.CommandType;

@WebServlet("/FileSearchService")
public class FileSearchServlet extends HttpServlet {
	private ServletContext application;
	private String view = "/02/fileSearch.jsp";
	private String srcUri = "/";
	private String targetUri = "/";
	private File srcFilefolder;
	private File targetFilefolder;
	private String srcParentPath;
	private String targetParentPath;
	String targetPath;
	String srcPath;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		application = getServletContext();
		String src = request.getParameter("src");
		String target = request.getParameter("target");
	
		if(src!=null) {
			 if(src.equals("upper")) {
				 	srcPath = application.getRealPath("srcUri");
				 	srcFilefolder = new File(srcPath).getParentFile();
				 	System.out.println(srcFilefolder);
				}else {
					srcUri = src;
					srcPath = application.getRealPath(srcUri);
					srcFilefolder = new File(srcPath);
				}
		}else {
			srcPath = application.getRealPath(srcUri);
			srcFilefolder = new File(srcPath);
		}
		if(target!=null) {
			if(target.equals("upper")) {
				targetPath = application.getRealPath("targetUri");
				targetFilefolder = new File(targetPath).getParentFile(); 
			}else {
				targetUri = target;
				targetPath = application.getRealPath(targetUri);
				targetFilefolder = new File(targetPath);
			}
		}else {
			targetPath = application.getRealPath(targetUri);
			targetFilefolder = new File(targetPath);
		}
		
		System.out.println(srcFilefolder.getPath());
		String[] srcFiles = srcFilefolder.list(); 
		
		String[] targetFiles = targetFilefolder.list();
		request.setAttribute("srcParentPath", srcParentPath);
		request.setAttribute("targetParentPath", targetParentPath);
		request.setAttribute("srcUri",srcUri);
		request.setAttribute("targetUri",targetUri);
		request.setAttribute("srcFiles", srcFiles);
		request.setAttribute("targetFiles", targetFiles);
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String src = request.getParameter("src");
		String target =  request.getParameter("target");
		String srcFile = request.getParameter("srcFile");
		String command = request.getParameter("command");
		int status =200;
		if(StringUtils.isBlank(srcFile) || StringUtils.isBlank(command)||StringUtils.isBlank(src)
				||StringUtils.isBlank(target)) {
			status = HttpServletResponse.SC_BAD_REQUEST;
			response.sendError(status);
		}else {
			String srcFolder = application.getRealPath(src);
			File srcFile2 = new File(srcFolder, srcFile);
			if(!srcFile2.exists()) {
				status = HttpServletResponse.SC_NOT_FOUND;
				response.sendError(status);
			}
			try {
				CommandType commandType = CommandType.valueOf(command);
				if(status==200) {
					File targetFile2 = new File(application.getRealPath(target));
					commandType.commandProcess(srcFile2, targetFile2);
				}
			}catch(IllegalArgumentException e) {
				status = HttpServletResponse.SC_BAD_REQUEST;
				response.sendError(status);
			}
			if(status==200) {
				doGet(request,response);
			}else {
				response.sendError(status);
			}
		}
		
	}
}
