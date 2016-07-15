package com.diyagea.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 图片转化为PDF
 * @author DIYAGEA
 *
 */
public class ImgToPDF {
	
	/**
	 * 判断文件类型是否是图片
	 * @param imageFile
	 * @return
	 */
	public static boolean isImage(File imageFile) {
		//如果文件不存在，返回false
		if (!imageFile.exists()) { 
            return false; 
        } 
		//图片缓存buffer
        BufferedImage img = null; 
        try { 
        	//读入内存
            img = ImageIO.read(imageFile); 
            //根据图片的大小判断
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) { 
                return false; 
            } 
            return true; 
        } catch (Exception e) { 
            return false; 
        } finally { 
        	//回收Img
            img = null; 
        } 
	}
	
	/**
	 * 图片转换为PDF
	 * @param imagePath
	 * @param pdfPath
	 */
	public static void img2PDF(String imagePath, String pdfPath){
		Document doc = null;
		try {
			//创建文件输入流
			BufferedImage img = ImageIO.read(new File(imagePath));
			FileOutputStream fos = new FileOutputStream(pdfPath);
			//创建PDF文档对象
			doc = new Document(null, 0, 0, 0, 0);
			doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
			//获取图片对象
			Image image = Image.getInstance(imagePath);
			//获得PDF输出流
			PdfWriter.getInstance(doc, fos);
			doc.open();
			//写入内容
			doc.add(image);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (BadElementException e2) {
			e2.printStackTrace();
		} catch (DocumentException e3) {
			e3.printStackTrace();
		}finally {
			//关闭PDF输出流
			doc.close();
		}
	}
	
}

