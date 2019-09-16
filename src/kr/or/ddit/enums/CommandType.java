package kr.or.ddit.enums;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;



public enum CommandType {
	COPY((srcFiles, targetFiles)->{
		File target =  new File(targetFiles, srcFiles.getName());
		FileUtils.copyFile(srcFiles, target);
	}), MOVE((srcFiles, targetFiles)->{
		File target = new File(targetFiles, srcFiles.getName());
		FileUtils.moveFile(srcFiles, target);
	}), DELETE((srcFiles, targetFiles)->{
		FileUtils.forceDelete(srcFiles);
	});
	
	public static interface CommandProcessor {
		public  void process(File srcFiles, File targetFiles) throws IOException;
		
	}
	private CommandProcessor processor;
	
	private CommandType(CommandProcessor processor) {
		this.processor = processor;
	}
	
	public void commandProcess(File srcFiles, File targetFiles) throws IOException{
		processor.process(srcFiles, targetFiles);
	}
	
}
