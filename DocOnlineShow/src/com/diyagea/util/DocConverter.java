package com.diyagea.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * doc docx格式转换
 */
public class DocConverter {
	private static final int environment = 1;// 环境 1：windows 2:linux
	private String outputPath = "";// 输入路径 ，如果不设置就输出在默认的位置
	private String dirName;
	private File pdfFile;
	private File swfFile;
	private File docFile;
	
	public DocConverter(String fileString) {
		init(fileString);
	}

	/**
	 * 重新设置file
	 * 
	 * @param fileString
	 */
	public void setFile(String fileString) {
		init(fileString);
	}

	/**
	 * 初始化
	 * 
	 * @param filePath
	 */
	private void init(String filePath) {
		dirName = filePath.substring(0, filePath.lastIndexOf("/"));  
		docFile = new File(filePath);  
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1,filePath.lastIndexOf("."));  
		dirName = dirName + "/" + fileName;
		//获得文件后缀名
		String suffixName = filePath.substring(filePath.lastIndexOf("."));
		// 判断上传的文件是否是TXT文件  
		if (suffixName.equalsIgnoreCase(".txt")) {  
			FormatTxt.toUTF(docFile);
		    pdfFile = new File(dirName + ".pdf"); // 用于处理PDF文档  
		} else if (ImgToPDF.isImage(docFile)) {  
			pdfFile = new File(dirName + ".pdf");  
			ImgToPDF.img2PDF(filePath, pdfFile.getAbsolutePath());
		} else {  
		    pdfFile = new File(dirName + ".pdf");  
		}  
		
		swfFile = new File(dirName + ".swf");  
        
	}
	
	/**
	 * 转换主方法
	 */
	@SuppressWarnings("unused")
	public boolean conver() {

		if (swfFile.exists()) {
			System.out.println("****swf转换器开始工作，该文件已经转换为swf****");
			return true;
		}

		if (environment == 1) {
			System.out.println("****swf转换器开始工作，当前设置运行环境windows****");
		} else {
			System.out.println("****swf转换器开始工作，当前设置运行环境linux****");
		}
		try {
			DocToPDF.doc2pdf(docFile, pdfFile);
			PdfToSwf.pdf2swf(pdfFile, swfFile, environment);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (swfFile.exists()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 返回Swf文件路径
	 * 
	 * @param s
	 */
	public String getswfPath() {
		if (swfFile.exists()) {
			String tempString = swfFile.getPath();
			tempString = tempString.replaceAll("\\\\", "/");
			return tempString;
		} else {
			return "";
		}

	}
	/**
	 * 设置输出路径
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
		if (!outputPath.equals("")) {
			String realName = dirName.substring(dirName.lastIndexOf("/"),
					dirName.lastIndexOf("."));
			if (outputPath.charAt(outputPath.length()) == '/') {
				swfFile = new File(outputPath + realName + ".swf");
			} else {
				swfFile = new File(outputPath + realName + ".swf");
			}
		}
	}

}

