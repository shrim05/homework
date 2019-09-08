package kr.or.ddit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	private final String VIEW_PATH = "/02/fileSearch.jsp";
	private String srcUri;
	private String targetUri;
	private String topUri;
	private String topPath;
	private File srcFileFolder;
	private File targetFileFolder;
	private String targetPath;
	private String srcPath;
	private List<String> srcFiles;
	private List<String> targetFiles;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		srcUri = "/";
		targetUri = "/";
		srcPath = application.getRealPath(srcUri);
		targetPath = application.getRealPath(targetUri);
		srcFileFolder = new File(srcPath);
		targetFileFolder = new File(targetPath);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		topUri = application.getContextPath();
		topPath = application.getResource("/").toString();
		String src = request.getParameter("src");
		String target = request.getParameter("target");
		srcFiles = new ArrayList<String>();
		targetFiles = new ArrayList<>();
		if(src != null) {
			if(src.equals("upper")) {
				srcFileFolder = srcFileFolder.getParentFile();
				if(srcFileFolder.toURI().toString().equals(topPath)) {
				srcUri="/";
				}
			}else if(StringUtils.isNotBlank(src)) {
				srcUri = src;
				srcPath = application.getRealPath(srcUri);
				srcFileFolder = new File(srcPath);
			}
		}
		File[] srcList = srcFileFolder.listFiles();
		for(File temp :  srcList) {
			String tempUri = temp.toURI().toString();
			if(temp.isDirectory()) {
				tempUri = tempUri.substring(tempUri.lastIndexOf(topUri)+topUri.length(),tempUri.length()-1);
			}else if(temp.isFile()) {
				tempUri = tempUri.substring(tempUri.lastIndexOf(topUri)+topUri.length(),tempUri.length());
			}
			srcFiles.add(tempUri);
		}
		
		if(target != null) {
			if(target.equals("upper")) {
				targetFileFolder = targetFileFolder.getParentFile();
				if(targetFileFolder.toURI().toString().equals(topPath)) {
					targetUri="/";
				}
			}else if(StringUtils.isNotBlank(target)) {
				targetUri =target;
				targetPath = application.getRealPath(targetUri);
				targetFileFolder = new File(targetPath);
			}
		}
		File[] targetList = targetFileFolder.listFiles();
		for(File temp :  targetList) {
			String tempUri = temp.toURI().toString();
			if(temp.isDirectory()) {
				tempUri = tempUri.substring(tempUri.lastIndexOf(topUri)+topUri.length(),tempUri.length()-1);
			}else if(temp.isFile()) {
				tempUri = tempUri.substring(tempUri.lastIndexOf(topUri)+topUri.length(),tempUri.length());
			}
			targetFiles.add(tempUri);
		}
		request.setAttribute("srcUri",srcUri);
		request.setAttribute("targetUri",targetUri);
		request.setAttribute("srcFiles", srcFiles);
		request.setAttribute("targetFiles", targetFiles);
		request.getRequestDispatcher(VIEW_PATH).forward(request, response);
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
