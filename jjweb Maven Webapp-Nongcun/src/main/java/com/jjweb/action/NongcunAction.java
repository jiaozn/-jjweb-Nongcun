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
        	//����myFile����һ���ļ�������  
			InputStream is = new FileInputStream(myFile);
			// �����ϴ��ļ�Ŀ¼  
	        String uploadPath = ServletActionContext.getServletContext()  
	                .getRealPath("/upload");  
	     // ����Ŀ���ļ�  
	        File toFile = new File(uploadPath, this.getMyFileFileName()); 
	     // ����һ�������  
	        OutputStream os = new FileOutputStream(toFile);  
	      //���û���  
	        byte[] buffer = new byte[1024];  
	  
	        int length = 0;  
	      //��ȡmyFile�ļ������toFile�ļ���  
	        while ((length = is.read(buffer)) > 0) {  
	            os.write(buffer, 0, length);  
	        }  
	        System.out.println("�ϴ��û�"+user.getName());  
	        System.out.println("�ϴ��ļ���"+myFileFileName);  
	       // System.out.println("�ϴ��ļ�����"+myFileContentType);  
	        //�ر�������  
	        is.close();  
	          
	        //�ر������  
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
