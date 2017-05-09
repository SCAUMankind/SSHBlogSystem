package com.blog.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.blog.common.Constant;
import com.blog.service.UserInfoService;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport{
	private File file;
	//文件名称  
    private String fileFileName;
    //文件类型  
    private String fileContentType;  
    //注意：文件名称和文件类型的名称前缀必须相同， 
    private UserInfoService userInfoService;
    public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
    public UserInfoService getUserInfoService() {
		return userInfoService;
	}
    public void setFile(File file) {
		this.file = file;
	}
    public File getFile() {
		return file;
	}
    public String getFileFileName() {
		return fileFileName;
	}
    public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
    public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
    public String getFileContentType() {
		return fileContentType;
	}
    public String execute() throws Exception { 
    	HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		Integer userId=(Integer)session.getAttribute("userId");
		System.out.println(ServletActionContext.getServletContext().getRealPath(""));
    	System.out.println(ServletActionContext.getServletContext().getRealPath(Constant.UPLOAD_DIR));
    	 //获取需要上传文件的文件路径  
    	System.out.println("fileFileName:"+fileFileName+"\nfileContentType:"+fileContentType);
        File uploadFile=new File(ServletActionContext.getServletContext().getRealPath(Constant.UPLOAD_DIR));  
        //System.out.println(ServletActionContext.getServletContext().getRealPath(Constant.UPLOAD_DIR));
        //判断文件是否上传，如果上传的话将会创建该目录  
		//File uploadFile=new File("WebContent\\uploadImage");
        
        if(!uploadFile.exists()){  
            uploadFile.mkdir(); //创建该目录  
        }  
        //第一种文件上传的方法  
        //声明文件输入流，为输入流指定文件路径  
        if(file!=null){
        long time = System.currentTimeMillis();  
        FileInputStream input=new FileInputStream(file);  
        //获取输出流，获取文件的文件地址及名称  
        FileOutputStream out=new FileOutputStream(uploadFile + "\\" +time+fileFileName);  
        System.out.println(uploadFile + "\\" +time+fileFileName);
        try{  
            byte[] b=new byte[1024];//每次写入的大小  
            int i=0;  
            while((i=input.read(b))>0){  
                out.write(b,0,i);  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            input.close();  
            out.close();  
        } 
        	userInfoService.updateUserImage(userId, Constant.UPLOAD_DIR + "\\" +fileFileName);	
        }
        return "success";  
    	/*System.out.println("1111");
    	HttpServletRequest request = ServletActionContext.getRequest(); 
    	InputStream in=request.getInputStream();
    	String dir = ServletActionContext.getRequest().getSession().getServletContext().getRealPath(UPLOADDIR);
        System.out.println(dir);  
        File fileLocation = new File(dir);  
        File uploadFile = new File(dir, "1");  
        if(!fileLocation.exists()){  
            boolean isCreated  = fileLocation.mkdir();  
            if(!isCreated) {  
                //目标上传目录创建失败,可做其他处理,例如抛出自定义异常等,一般应该不会出现这种情况。  
                return "fail";  
            }  
        } 
        OutputStream out = new FileOutputStream(uploadFile);   
        byte[] buffer = new byte[1024 * 1024];   
        int length;   
        System.out.println("START");
        while ((length = in.read(buffer)) > 0) {   
            out.write(buffer, 0, length); 
            System.out.println("length"+length+"buffer"+buffer);
        }   
        System.out.println("END");
        in.close();   
        out.close(); 
        return "success";   */
    }   

    //执行上传功能   
}
