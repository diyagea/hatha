package com.diyagea.action;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.diyagea.service.ConvertService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DocConvertAction extends ActionSupport {
	private File upload; // 上传文件
	private String uploadContentType; // 上传文件类型
	private String uploadFileName; // 上传文件名

	private String filePath;
	private String message;
	private String swfPath;
	private String errorMsg;


	public String receiveFile() throws Exception {

		String savePath = ServletActionContext.getServletContext().getRealPath("/upload");
        System.out.println("realpath: "+savePath);
        if (upload == null) {
        	errorMsg = "请上传正确的文件";
        	return INPUT;
        }
           
        File saveFile = new File(new File(savePath), uploadFileName);
        if (!saveFile.getParentFile().exists())
            saveFile.getParentFile().mkdirs();
        FileUtils.copyFile(upload, saveFile);
        
		// *************处理文件**********************
        String converfilename = saveFile.getPath().replaceAll("\\\\", "/");
		System.out.println(converfilename);
		ConvertService conService = new ConvertService(converfilename);
		// 启动处理线程
		new Thread(conService).start();;

	/*	// swf绝对路径
		String absPath = conService.getswfPath();
		System.out.println(" swf-abs-path : " + absPath);
		// 相对路径
		String swfPath = "upload" + absPath.substring(absPath.lastIndexOf("/"));
		System.out.println(" swf-path : " + swfPath);
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("swfpath", swfPath);
*/
		swfPath =  "upload/" + uploadFileName.substring(0, uploadFileName.lastIndexOf(".")) + ".swf";
		System.out.println(swfPath);
		ActionContext.getContext().getSession().put("swfPath", swfPath);
		String extName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
		message = "上传的文件:" + uploadFileName + "___ 文件类型:" + extName;

		return SUCCESS;
	}

	public String showDocs() throws Exception {
		return SUCCESS;
	}

	// ---------------get | set------------------------

	public String getSwfPath() {
		return swfPath;
	}

	public void setSwfPath(String swfPath) {
		this.swfPath = swfPath;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
