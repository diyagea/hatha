package com.diyagea.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.diyagea.dao.HathaDAO;
import com.diyagea.pojo.Hatha;
import com.diyagea.util.DealWithExcel;


public class HathaTest {

	
	public static void main(String[] args) throws IOException {
		File f = new File("D:/项目开发要求.xlsx");
		
		/*List<Hatha> l = DealWithExcel.readXlsx(f);
		System.out.println(l);*/
		
		/*Thread t = new Thread(new DealThread(f));
		t.start();*/
		
		HathaDAO dao = new HathaDAO();
		/*List<Hatha> ls = dao.findByPage(0, 5);
		System.out.println(ls.size() + " || " +ls);*/
		System.out.println(dao.getCount());
	}
	
	
}
