package com.diyagea.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * 读取不同编码的TXT文件
 * @author DIYAGEA
 *
 */
public class FormatTxt {

	//定义四种TXT编码
	private final static String GBK = "GBK";
	private final static String UTF16 = "UTF-16BE";
	private final static String UNICODE = "Unicode";
	private final static String UTF8 = "UTF-8";
	
	/**
	 * 根据传入的File，改为编码UTF8
	 * @param f
	 */
	public static void toUTF(File f){
		InputStreamReader isr = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		
		try {
             isr = new InputStreamReader(new FileInputStream(f),getFileCharset(f));
             br = new BufferedReader(isr);
             
             StringBuffer sb = new StringBuffer();   
             String temp = null;   
             while((temp = br.readLine()) != null){   
                 sb.append(temp);
                 sb.append("\r\n");
             }   
             System.out.println(sb.toString());
             
             fos = new FileOutputStream(f); 
             osw = new OutputStreamWriter(fos, UTF8); 
             osw.write(sb.toString()); 
             osw.flush(); 
             
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }catch (IOException e) {
             e.printStackTrace();
         }finally{
        	 try {
				isr.close();
				br.close();
				fos.close();
				osw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	 
         }
	}
	
	private static String getFileCharset(File fileName) {
		String code = null;
		try {
			BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
			int p = (bin.read() << 8) + bin.read();

			switch (p) {
			case 0xefbb:
				code = UTF8;
				break;
			case 0xfffe:
				code = UNICODE;
				break;
			case 0xfeff:
				code = UTF16;
				break;
			default:
				code = GBK;
			}
			return code;
		} catch (Exception e) {

		}
		return code;
	}
}
