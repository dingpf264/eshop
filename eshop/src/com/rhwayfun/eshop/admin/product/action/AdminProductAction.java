package com.rhwayfun.eshop.admin.product.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.rhwayfun.eshop.admin.categorysecond.service.ICategorySecondService;
import com.rhwayfun.eshop.admin.product.service.IAdminProductService;
import com.rhwayfun.eshop.category.entity.Category;
import com.rhwayfun.eshop.category.entity.Categorysecond;
import com.rhwayfun.eshop.category.service.ICategoryService;
import com.rhwayfun.eshop.product.entity.Product;
import com.rhwayfun.eshop.utils.PageBean;

public class AdminProductAction implements ModelDriven<Product>{
	
	private Product product = new Product();
	
	@Override
	public Product getModel() {
		return product;
	}
	
	private int currentPage = 1;
	private File pic;
	private String picFileName;
	private File[] file; // 文件
	private String[] fileFileName; // 文件名
	private String[] filePath; // 文件路径
	private InputStream inputStream;
	private int cid;
	private String data;
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getCid(){
		return cid;
	}
	
	public void setCid(int cid){
		this.cid = cid;
	}

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String[] getFilePath() {
		return filePath;
	}

	public void setFilePath(String[] filePath) {
		this.filePath = filePath;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	private IAdminProductService adminProductService;
	private ICategorySecondService categorySecondService;
	private ICategoryService categoryService;
	
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCategorySecondService(
			ICategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setAdminProductService(IAdminProductService adminProductService) {
		this.adminProductService = adminProductService;
	}

	/*******************以下是action的实现***********************/
	
	public String findAllByPage() throws Exception{
		PageBean<Product> pList = adminProductService.findAllByPage(currentPage);
		//放入值栈中
		ActionContext.getContext().getValueStack().set("pList", pList);
		return "findAllByPage";
	}

	public String addPage() throws Exception{
		//获取所有的一级分类实现联动效果
		List<Category> cList = categoryService.findAll();
		//获取所有的二级分类
		List<Categorysecond> csList = categorySecondService.findAll();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("csList", csList);
		request.put("cList", cList);
		return "addPage";
	}
	
	public String save() throws Exception{
		product.setPdate(new Timestamp(new Date().getTime()));
		if(pic != null){
			//获取上传图片的物理路径
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/products");
			//创建一个文件，该文件是包保存的磁盘地址
			File diskFile = new File(realPath + "//" + picFileName);
			FileUtils.copyFile(pic, diskFile);
			//设置商品的图片
			product.setImage("products/" + picFileName);
		}
		adminProductService.save(product);
		PageBean<Product> pList = adminProductService.findAllByPage(currentPage);
		//放入值栈中
		ActionContext.getContext().getValueStack().set("pList", pList);
		return "save";
	}
	
	/**
	 * 文件上传
	 */
	public String fileUpload() {
		String path = ServletActionContext.getServletContext().getRealPath(
				"/products");
		File file = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists()) {
			file.mkdir();
		}
		try {
			if (this.file != null) {
				File f[] = this.getFile();
				filePath = new String[f.length];
				for (int i = 0; i < f.length; i++) {
					String fileName = java.util.UUID.randomUUID().toString(); // 采用时间+UUID的方式随即命名
					String name = fileName
							+ fileFileName[i].substring(fileFileName[i]
									.lastIndexOf(".")); // 保存在硬盘中的文件名
					//FileUtils.copyFile(f[i], new File(path +"//" + name));
					FileInputStream inputStream = new FileInputStream(f[i]);
					FileOutputStream outputStream = new FileOutputStream(path
							+ "\\" + name);
					byte[] buf = new byte[1024];
					int length = 0;
					while ((length = inputStream.read(buf)) != -1) {
						outputStream.write(buf, 0, length);
					}
					inputStream.close();
					outputStream.flush();
					outputStream.close();
					// 文件保存的完整路径
					// 如：D:\tomcat6\webapps\struts_ajaxfileupload\\upload\a0be14a1-f99e-4239-b54c-b37c3083134a.png
					filePath[i] = path + "\\" + name;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * 读取图片readImage
	 */
	public String readImage() {
		System.out.println(ServletActionContext.getRequest()
					.getParameter("path"));
		try {
			inputStream = new FileInputStream(new File(ServletActionContext.getRequest()
					.getParameter("path")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String edit() throws Exception{
		//根据pid获取商品的信息
		product = adminProductService.findByPid(product.getPid());
		List<Categorysecond> csList = categorySecondService.findAll();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("csList", csList);
		return "edit";
	}
	
	public String update() throws Exception{
		product.setPdate(new Timestamp(new Date().getTime()));
		if(pic != null){
			//获取上传图片的物理路径
			String delPath = ServletActionContext.getServletContext()
					.getRealPath("/") + product.getImage();
			//同时删除原来的商品图片
			File originFile= new File(delPath);
			originFile.delete();
			//获取上传图片的物理路径
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/products");
			//创建一个文件，该文件是包保存的磁盘地址
			File diskFile = new File(realPath + "//" + picFileName);
			FileUtils.copyFile(pic, diskFile);
			//设置商品新的图片
			product.setImage("products/" + picFileName);
		}
		adminProductService.update(product);
		return "update";
	}
	
	public String delete() throws Exception{
		product = adminProductService.findByPid(product.getPid());
		//获取上传图片的物理路径
		String realPath = ServletActionContext.getServletContext()
				.getRealPath("/") + product.getImage();
		File diskFile = new File(realPath);
		diskFile.delete();
		adminProductService.delete(product);
		return "delete";
	}
	
	//findPageProductsByName
	public String findPageProductsByName() throws Exception{
		PageBean<Product> products = adminProductService.findPageProductsByName(product.getPname(),currentPage);
		ActionContext.getContext().getValueStack().set("pList", products);
		return "findPageProductsByName";
	}

	//findCs
	public String findCs() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		List<Categorysecond> csList = categorySecondService.findCs(cid);
		System.out.println("list:" + csList);
		StringBuilder sb = new StringBuilder();
		for (Categorysecond cs : csList) {
			sb.append(cs.getCsid() + ":" + cs.getCsname());
			sb.append("|");
		}
		data = sb.toString().substring(0,sb.toString().lastIndexOf("|"));
		System.out.println("data:" + data);
		ServletActionContext.getResponse().getWriter().print(data);
		
		/*out.println("document.getElementById('csList').length=" + csList.size());
		out.println("document.getElementById('csList').selectedIndex=0");
		for(int i = 0 ; i < csList.size(); i++){
			Categorysecond cs = csList.get(i);
			out.println("document.getElementById('csSelect').options[" + i +"].value=" + cs.getCsid() 
				+ "");
			out.println("document.getElementById('csSelect').options[" + i +"].text=" + cs.getCsname() 
				+ "");
		}	*/

		return null;
	}
}
