package com.diyagea.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.diyagea.dao.HathaDAO;
import com.diyagea.pojo.Hatha;
import com.diyagea.util.DealWithExcel;

public class DealThread implements Runnable {

	List<Hatha> hathas;
	DealWithExcel dwe;
	HathaDAO dao;
	
	File f;
	
	public DealThread(File file) {
		f = file;
		dwe = new DealWithExcel();
		dao = new HathaDAO();
	}

	public void run() {
		Date d = new Date();
		long startTime = d.getTime();
		try {
			System.out.println("开始解析文件");
			hathas = dwe.readXlsx(f);
		} catch (IOException e) {
			System.out.println("文件解析失败");
			e.printStackTrace();
		}
		d = new Date();
		long endDealTime = d.getTime();
		System.out.println("解析Excel时间为：" + (endDealTime - startTime));
		for(Hatha h : hathas){
			dao.addHatha(h);
		}
		d = new Date();
		long endInsert = d.getTime();
		System.out.println("导入完毕");
		System.out.println("导入时间为:" + (endInsert - endDealTime));
	}

}
