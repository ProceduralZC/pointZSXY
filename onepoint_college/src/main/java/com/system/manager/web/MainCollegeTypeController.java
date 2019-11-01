package com.system.manager.web;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.system.core.config.SysConfig;
import com.system.manager.entity.DefineData;
import com.system.manager.entity.MainCollegeType;
import com.system.manager.entity.Photo;
import com.system.manager.entity.SysVersion;
import com.system.manager.entity.UniqId;
import com.system.manager.service.MainCollegeTypeService;
import com.system.manager.service.impl.LocalFileServiceImpl;
import com.system.manager.service.impl.PhotoServiceImpl;
import com.system.manager.util.BarCodeService;
import com.system.manager.web.model.MainCollegeTypeModel;
import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;
//首页课程主类型
@RestController
@RequestMapping(value="/system")
public class MainCollegeTypeController extends BaseController{
	
	@Autowired
	private MainCollegeTypeService mainCollegeTypeService;
	@Autowired
	private SysUsersService sysUsersService;
	@Autowired
	private LocalFileServiceImpl fileService;
	@Autowired
	private PhotoServiceImpl photoServiceImpl;
	@Autowired
	private BarCodeService barCodeService;
	@Autowired
	private SysConfig sysConfig;
	
	/**
	 * 添加知识类型
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/addCollegetype" ,method = {RequestMethod.POST})
	public String addCollegeType(@ModelAttribute  MainCollegeTypeModel model,HttpServletRequest request
		,@RequestParam() String imageurl,HttpServletResponse response){
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
        model.setSysUsers(sysUsers);
        model.setCollegeimage(imageurl);
      //生成图片
		Integer barCodeImg = barCodeService.createBarCode(imageurl);
		model.setCollegeImgId(barCodeImg);
        
        Integer id = mainCollegeTypeService.addCollegeType(model,sysUsers);
		return super.message(id+"","添加成功","success");
	}
	
	
	/**
	 * 上传图片
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/uploadFile" )
	public String uploadFile(HttpServletRequest request
		,@RequestParam(value="collegeimage",required=false) MultipartFile collegeimage,HttpServletResponse response){
		String msg = "添加成功";
		System.out.println("uploadFile in---------------");
		String fullPathName=""; //上传的文件路径名
		Photo photo = null;
		try {
			if(collegeimage.getSize() > 2097152){
				msg="文件大小不能超过2M"; 
				throw new Exception("文件大小不能超过2M");
			}
			if(collegeimage.getSize() > 0){
				String filename = collegeimage.getOriginalFilename();
				String fileExt = filename.substring(filename.lastIndexOf(".")+1).toLowerCase();
				if(!Arrays.<String>asList("gif,jpg,jpeg,png,bmp".split(",")).contains(fileExt)){
					 msg = "上传文件扩展名是不允许的扩展名。\n只允许gif,jpg,jpeg,png,bmp格式。";
					 throw new Exception("上传文件扩展名是不允许的扩展名。\n只允许gif,jpg,jpeg,png,bmp格式。");
				}
				fullPathName = fileService.executeStoreFile("MAINTYPE", collegeimage);//保存指定目录下，MAINTYPE为子文件夹
				photo = new Photo();
				photo.setPid(UniqId.getInstance().getUniqID());
//				photo.setPfkid(pid);
				photo.setPurl(fullPathName);
//				String fileName = DefineData.FILE_LOCAL_DIR + fullPathName;
				String fileName = sysConfig.getFileLocalDir() + fullPathName;
				File files = new File(fileName);
				BufferedImage bi2 = ImageIO.read(files);
				photo.setPheight(bi2.getHeight() + "");
				photo.setPwidth(bi2.getWidth() + "");
//				photoServiceImpl.executeSavePhoto(photo);
				
				System.out.println("fullPathName===---------------"+fullPathName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return super.message(fullPathName+"","添加成功","success");
	}
	
	/**
	 * 查询所有知识类型
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllCollegetype", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainCollegeType> findAll(@ModelAttribute MainCollegeTypeModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return mainCollegeTypeService.pageList(pageable,model);
	}
	
	
	/**
	 * 通过条件查询知识类型
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllCollegetypeSearch", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<MainCollegeType> findCollegeInfoSearch(@ModelAttribute MainCollegeTypeModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = getPageable(request);
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
		SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
		
//		if(null!=sysUsers.getWorkshop()){
//			model.setWorkshop(sysUsers.getWorkshop().getId());
//		}
//		if(null!=sysUsers.getTeam()){
//			model.setTeam(sysUsers.getTeam().getId());
//		}
		if(null != model.getCollegetypecode()){
			model.setCollegetype(model.getCollegetypecode());
		}
		
		Page<MainCollegeType> page= mainCollegeTypeService.findByPage(model, pageable);
		
		return page;
	}
	
	/**
	 * 通过条件课件类型查询一类信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllCollegetypeBytype", method = {RequestMethod.GET,RequestMethod.POST})
	public List<MainCollegeType> findWorkshopInfoSearch(@ModelAttribute MainCollegeTypeModel model
			,@RequestParam() String collegetypecode,HttpServletRequest request, HttpServletResponse response) {
		if(null != model.getCollegetypecode()){
			model.setCollegetypecode(collegetypecode);
		}
		List<MainCollegeType> list = mainCollegeTypeService.findByPageType(model,collegetypecode);
		
		return list;
	}
	
	@RequestMapping(value = "/getAllCollegetype/{id}", method = {RequestMethod.GET })
	public MainCollegeType getCollegetype(@PathVariable Integer id) {
		return mainCollegeTypeService.getCollege(id);
	}
	
}
