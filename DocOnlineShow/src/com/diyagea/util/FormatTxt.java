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
	 * 根据传入的TXT File，改为编码UTF8
	 * @param f
	 */
	public static void toUTF(File f){
		//声明输入输出流
		InputStreamReader isr = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		
		String charset = getFileCharset(f);
		//如果是UTF8，即不用转换编码
		if(UTF8.equals(charset))
			return ;
		
		try {
			//获得文件输入流
             isr = new InputStreamReader(new FileInputStream(f),charset);
             br = new BufferedReader(isr);
             //用stringbuffer缓存文件内容
             StringBuffer sb = new StringBuffer();   
             String temp = null;   
             while((temp = br.readLine()) != null){   
                 sb.append(temp);
                 sb.append("\r\n");
             }   
             //打印文件内容
//             System.out.println(sb.toString());
             
             //获得文件输出流
             fos = new FileOutputStream(f); 
             //设置编码格式，UTF-8
             osw = new OutputStreamWriter(fos, UTF8); 
             //写入内容
             osw.write(sb.toString());
             //缓存清空
             osw.flush(); 
             
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }catch (IOException e) {
             e.printStackTrace();
         }finally{
        	 try {
        		//关闭输入输出流
				isr.close();
				br.close();
				fos.close();
				osw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	 
         }
	}
	
	/**
	 * 根据TXT内容判断编码
	 * @param file
	 * @return 编码格式
	 */
	private static String getFileCharset(File file) {
		String charset = null;
		try {
			//获得文件输入流
			BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
			int p = (bin.read() << 8) + bin.read();
			//关闭输入流
			bin.close();
			//判断
			switch (p) {
			case 0xefbb:
				charset = UTF8;
				break;
			case 0xfffe:
				charset = UNICODE;
				break;
			case 0xfeff:
				charset = UTF16;
				break;
			default:
				charset = GBK;
			}
			return charset;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charset;
	}
}
