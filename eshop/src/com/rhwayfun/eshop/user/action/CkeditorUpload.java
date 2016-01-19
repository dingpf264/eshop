package com.rhwayfun.eshop.user.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CkeditorUpload extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public File getUpload() {
		return upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	@Override
	public String execute() throws Exception {
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String callback = ServletActionContext.getRequest().getParameter("CKEditorFuncNum");
		String expandName = "";
		if(uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg")){
			expandName = ".jpg";
		}else if(uploadContentType.equals("image/png") || uploadContentType.equals("image/x-png")){
			expandName = ".png";
		}else if(uploadContentType.equals("image/gif")){
			expandName = ".gif";
		}else if(uploadContentType.equals("image/bmp")){
			expandName = ".bmp";
		}else {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + 
					",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png）');");
			out.println("</script>");
			return null;
		}
		if(upload.length() > 600 * 1024){
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + 
					",''," + "'文件大小不得大于600k');");
			out.println("</script>");
			return null;
		}
		//上传图片
		InputStream is = new FileInputStream(upload);
		String uploadpath = ServletActionContext.getServletContext().getRealPath("/products");
		System.out.println("上传路径：" + uploadpath);
		String fileName = java.util.UUID.randomUUID().toString();
		
		fileName += expandName;
		File toFile = new File(uploadpath,fileName);
		OutputStream os = new FileOutputStream(toFile);
		byte[] buffer = new byte[1024];
		int length = 0;
		while((length = is.read(buffer)) > 0){
			os.write(buffer, 0, length);
		}
		is.close();
		os.close();
		
		//返回“图像”选项卡并显示图片
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + 
				",'" + "products/" + fileName + "','')");
		out.println("</script>");
		return null;
	}
}
