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

@WebServlet("/FileSearchService")
public class FileSearchServlet extends HttpServlet {
	private ServletContext application;
	private Map<String,Map<String,String>> srcMap;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		String srcUri = "/";
		String srcPath = application.getRealPath(srcUri);
		application.setAttribute("srcPath", srcPath);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String srcPath = (String)application.getAttribute("srcPath");
		srcMap = new LinkedHashMap<>();
		Files.walk(Paths.get(srcPath)).forEach(filePath -> {
			if (Files.isRegularFile(filePath)) {
				String fileList = filePath.getFileName().toString();
				String folderList = filePath.subpath(9, 10).toString();
				String srcFiles = filePath.toString();
				Map<String, String> fileMap = new HashMap<>();
				fileMap.put(folderList, fileList);
				srcMap.put(srcFiles, fileMap);
			}
		});
		request.setAttribute("srcData",srcMap);
		String view = "/02/fileSearch.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
