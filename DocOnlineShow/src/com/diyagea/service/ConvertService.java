package com.diyagea.service;

import java.io.File;

import com.diyagea.util.DocToPDF;
import com.diyagea.util.FormatTxt;
import com.diyagea.util.ImgToPDF;
import com.diyagea.util.PdfToSwf;

/**
 * 处理文档业务逻辑类（线程）
 * 
 * @author DIYAGEA
 *
 */
public class ConvertService implements Runnable {

	private static final int environment = 1;// 环境 1：windows 2:linux
	private File pdfFile;
	private File swfFile;
	private File docFile;

	private String filePath;

	public ConvertService(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 初始化
	 * 
	 * @param filePath
	 */
	private void init() {
		// 创建上传的文件对象
		docFile = new File(filePath);
		// 文件路径+文件名（不含文件格式）
		String dirName = filePath.substring(0, filePath.lastIndexOf("."));
		// 创建临时PDF文档，swf文件
		pdfFile = new File(dirName + ".pdf");
		swfFile = new File(dirName + ".swf");

		// 获得文件后缀名
		String suffixName = filePath.substring(filePath.lastIndexOf("."));

		// 判断上传的文件格式
		if (suffixName.equalsIgnoreCase(".txt")) {
			// 如果是TXT文件，统一转换编码为UTF8
			FormatTxt.toUTF(docFile);
		} else if (ImgToPDF.isImage(docFile)) {
			// 图片直接转为PDF
			ImgToPDF.img2PDF(filePath, pdfFile.getAbsolutePath());
		}

	}

	/**
	 * 转换主方法
	 */
	@SuppressWarnings("unused")
	public boolean convert() {
		// 判断swf文件是否存在，若存在直接返回
		if (swfFile.exists()) {
			System.out.println("****swf转换器开始工作，该文件已经转换为swf****");
			return true;
		}
		// 提示输出路径
		if (environment == 1) {
			System.out.println("****swf转换器开始工作，当前设置运行环境windows****");
		} else {
			System.out.println("****swf转换器开始工作，当前设置运行环境linux****");
		}

		try {
			//文档文件转换为PDF
			DocToPDF.doc2pdf(docFile, pdfFile);
			//PDF转换为SWF
			PdfToSwf.pdf2swf(pdfFile, swfFile, environment);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		//判断是否成功
		if (swfFile.exists()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 线程逻辑方法
	 */
	public void run() {
		// 数据初始化
		init();
		// 执行转换
		if (!convert()) {
			// 转换失败，更新状态为失败
			// TODO: DB update state to fail
			System.out.println("状态更新为失败");
		}

		// 转换成功，更新状态为成功
		// TODO: DB update state to success
		System.out.println("状态更新为成功");

	}

}
