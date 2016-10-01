package com.jjweb.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.jjweb.model.Nongcun;
import com.jjweb.model.NongcunDAO;
import com.jjweb.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class NongcunAction extends ActionSupport{
	
	private Nongcun nongcun;
	private List<Nongcun> listNongcun;
	@Resource
	private NongcunDAO nongcunDAO;
	private User user;
	private File myFile;
	private String myFileFileName;
	
	
	
	@Action(value="nongcun_showAll",results={
			@Result(name="success",location = "/WEB-INF/content/business_nongcun_showAll.jsp")})
	public String nongcun_showAll(){
		listNongcun=nongcunDAO.findAll();
		return SUCCESS;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	@Action(value="nongcun_upload",results={
			@Result(name="success",location = "/WEB-INF/content/business_nongcun_showAll.jsp")})
	public String nongcun_upload(){
        try {
        	//基于myFile创建一个文件输入流  
			InputStream is = new FileInputStream(myFile);
			// 设置上传文件目录  
	        String uploadPath = ServletActionContext.getServletContext()  
	                .getRealPath("/upload");  
	     // 设置目标文件  
	        File toFile = new File(uploadPath, this.getMyFileFileName()); 
	     // 创建一个输出流  
	        OutputStream os = new FileOutputStream(toFile);  
	      //设置缓存  
	        byte[] buffer = new byte[1024];  
	  
	        int length = 0;  
	      //读取myFile文件输出到toFile文件中  
	        while ((length = is.read(buffer)) > 0) {  
	            os.write(buffer, 0, length);  
	        }  
	        System.out.println("上传用户"+user.getName());  
	        System.out.println("上传文件名"+myFileFileName);  
	       // System.out.println("上传文件类型"+myFileContentType);  
	        //关闭输入流  
	        is.close();  
	          
	        //关闭输出流  
	        os.close();  
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block8520459
			e.printStackTrace();
		} 
		return SUCCESS;
	}

	public Nongcun getNongcun() {
		return nongcun;
	}

	public void setNongcun(Nongcun nongcun) {
		this.nongcun = nongcun;
	}

	public List<Nongcun> getListNongcun() {
		return listNongcun;
	}

	public void setListNongcun(List<Nongcun> listNongcun) {
		this.listNongcun = listNongcun;
	}

	public NongcunDAO getNongcunDAO() {
		return nongcunDAO;
	}

	public void setNongcunDAO(NongcunDAO nongcunDAO) {
		this.nongcunDAO = nongcunDAO;
	}
}
