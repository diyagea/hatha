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
			System.out.println("��ʼ�����ļ�");
			hathas = dwe.readXlsx(f);
		} catch (IOException e) {
			System.out.println("�ļ�����ʧ��");
			e.printStackTrace();
		}
		d = new Date();
		long endDealTime = d.getTime();
		System.out.println("����Excelʱ��Ϊ��" + (endDealTime - startTime));
		for(Hatha h : hathas){
			dao.addHatha(h);
		}
		d = new Date();
		long endInsert = d.getTime();
		System.out.println("�������");
		System.out.println("����ʱ��Ϊ:" + (endInsert - endDealTime));
	}

}
