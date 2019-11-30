package com.system.phone.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.core.jsonpacket.JsonResponse;
import com.system.manager.entity.DefineData;
import com.system.manager.entity.MessageSys;
import com.system.manager.service.MessageService;
import com.system.manager.web.model.MessageSysModel;
import com.system.user.web.base.BaseController;
//添加消息
@RestController
@RequestMapping(value="/ms")
public class PMessageController extends BaseController{
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * 分页查询消息记录
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryAllPhoneMessagepage/{start}/{size}",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResponse queryAllPhoneCoursePathpage(@PathVariable Integer start,@PathVariable Integer size,
			@ModelAttribute MessageSysModel model,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jsonResponse = super.sucessResponse();
		List<Map<String,Object>> items= new ArrayList<Map<String,Object>>();
		Pageable pageable = new PageRequest(start-1, size);
		try {
			Page<Map<String,Object>> page = messageService.findByPagePhone(model, pageable);
			items =page.getContent();
			jsonResponse.setItems(items);
			return jsonResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return super.failureResponse();
		}
	}
	
	/**
	 * 删除信息
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/deleteMessage" ,method = {RequestMethod.POST})
	public String deleteClaimRecord(@RequestParam  String ids
			,HttpServletRequest request,HttpServletResponse response){
		messageService.delete(ids);
		return super.message("","操作成功!","success");
	}
	/**
	 * 通过id查询详情
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/getMessageById/{id}" ,method = {RequestMethod.GET})
	public MessageSys  getMaterialsById(@PathVariable Integer id,HttpServletRequest request){
		
		return messageService.get(id);
	}
	
	/**
	 * 查看网页
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/getMessageWeb/{id}" ,method = {RequestMethod.GET})
	public String  getMessageById(@PathVariable Integer id,HttpServletRequest request
			,HttpServletResponse response){
		try{
			MessageSys msg = messageService.get(id);
			
		    //用于存储html字符串  
		    StringBuilder stringHtml = new StringBuilder();  
		    PrintStream printStream = null;
		    String fileurl = null;
		    try{  
		       //创建根目录
		       fileurl = DefineData.FILE_LOCAL_DIR+"html";
			   File rootDir = new File(fileurl);
			   if (!rootDir.exists()) {
				 rootDir.mkdirs();
			   }
				//打开文件  
		       printStream = new PrintStream(new FileOutputStream(fileurl+"/message"+id+".html"));
		    }catch(FileNotFoundException e){  
		       e.printStackTrace();  
		    }  
		    
		    String saveFilepath = fileurl+"/message"+id+".html";
		    File rootDirifsave = new File(saveFilepath);
//		    if (!rootDirifsave.exists()) {
			    //输入HTML文件内容  
			    stringHtml.append("<html><head>");  
			    stringHtml.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");  
			    stringHtml.append("<title>一点知识学院</title>");
			    stringHtml.append("<h1 style=\"width:100%;text-align:center;color:#333333\">"+msg.getMsgtitle()+"</h1>");
			    stringHtml.append("<style>");
			    stringHtml.append(".box{font-size:25px}");
//			    stringHtml.append(".box .f20{ font-size:20px}");
			    stringHtml.append("</style>");
			    stringHtml.append("</head>");  
			    stringHtml.append("<body>");  
			    stringHtml.append("<div class=\"box\">"+msg.getMsgcontent()+"</div>");  
			    stringHtml.append("</body></html>");  
			    try{  
			     //将HTML文件内容写入文件中  
			     printStream.println(stringHtml.toString());  
			    }catch (Exception e) {  
			          
			        e.printStackTrace();  
			    }
//		    }
		    
		    File html_file = new File(saveFilepath);
            FileInputStream inputStream = new FileInputStream(html_file);  
            ServletOutputStream out;
            // 3.通过response获取ServletOutputStream对象(out)  
            out = response.getOutputStream();  
  
            int b = 0;  
            byte[] buffer = new byte[1024];  
            while ((b = inputStream.read(buffer)) != -1) {  
                // 4.写到输出流(out)中  
                out.write(buffer, 0, b);  
            }  
            inputStream.close();  
            out.flush();  
            out.close(); 
			
		}catch(Exception e){
//			logger.info("queryCustDescById------------Exception------------------");
		}
		return "";
	}
	
}
