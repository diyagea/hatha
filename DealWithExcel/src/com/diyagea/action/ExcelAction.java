package com.diyagea.action;

import java.io.File;
import java.util.List;

import com.diyagea.dao.HathaDAO;
import com.diyagea.pojo.Hatha;
import com.diyagea.service.DealThread;
import com.opensymphony.xwork2.ActionSupport;

public class ExcelAction extends ActionSupport{

	private File upload; // 上传文件
	private String uploadContentType; // 上传文件类型
	private String uploadFileName; // 上传文件名

	private HathaDAO dao = new HathaDAO();
	
	private int curPage;
	
	private List<Hatha> hathas;
	
	public String dealWithExcel() throws Exception {
		Thread t = new Thread(new DealThread(upload));
		t.start();
		
		return SUCCESS;
	}
	
	public String findByPage() throws Exception {
		int everyPage = 5;
		int totalCount = dao.getCount();
		int totalPage = 0;
		if(totalCount % everyPage == 0) {
			totalPage = totalCount / everyPage;
		} else {
			totalPage = totalCount / everyPage + 1;
		}
		
		if(curPage <= 0){
			curPage = 1;
		}
		if(curPage > totalPage){
			curPage = totalPage;
		}
		
		int index = (curPage-1)*everyPage;
		
		hathas = dao.findByPage(index, everyPage);
		
		return SUCCESS;
	}
	

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public List<Hatha> getHathas() {
		return hathas;
	}

	public void setHathas(List<Hatha> hathas) {
		this.hathas = hathas;
	}

	

}
