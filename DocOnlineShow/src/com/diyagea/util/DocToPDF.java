package com.diyagea.util;

import java.io.File;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * 文档转换为PDF
 * @author DIYAGEA
 *
 */
public class DocToPDF {

	/**
	 * 转为PDF
	 * 
	 * @param file
	 */
	public static void doc2pdf(File docFile, File pdfFile) throws Exception {
		if (docFile.exists()) {
			if (!pdfFile.exists()) {
				//连接OpenOffice服务
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
				try {
					//获取连接
					connection.connect();
					DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
					//开始转化DOC-TO-PDF
					converter.convert(docFile, pdfFile);
					System.out.println("****pdf转换成功，PDF输出：" + pdfFile.getPath()+ "****");
				} catch (java.net.ConnectException e) {
					e.printStackTrace();
					System.out.println("****swf转换器异常，openoffice服务未启动！****");
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					e.printStackTrace();
					System.out.println("****swf转换器异常，读取转换文件失败****");
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					//关闭连接
					connection.disconnect();
				}
			} else {
				System.out.println("****已经转换为pdf，不需要再进行转化****");
			}
		} else {
			System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");
		}
	}
}
