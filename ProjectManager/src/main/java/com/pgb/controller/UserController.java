package com.pgb.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pgb.entity.User;
import com.pgb.service.UserService;
import com.pgb.utils.Md5;
import com.pgb.utils.Result;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@RequestMapping(value="/register",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String register(MultipartFile file, HttpServletRequest request, User user) {
		String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^(.+?)\\.(png|jpg|gif|PNG|JPG|GIF)$")) {
        	return JSONObject.toJSONString(new Result(-1, "您上传的不是一张图片！"));
        }
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        user.setIssupper(0);
        user.setHeadphoto(request.getContextPath()+"/upload/"+fileName);
        
        boolean tag = userService.register(user);
        if (tag) {
        	return JSONObject.toJSONString(new Result(1, "注册成功"));
        }
        else {
        	return JSONObject.toJSONString(new Result(-1, "注册失败，用户名已存在或内容填写不完整"));
        }
        
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String login(String username, String password, HttpServletRequest request) {
		boolean tag = userService.login(username, password);
		if (tag) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			return JSONObject.toJSONString(new Result(1, "登录成功"));
			
		}
		else {
			return JSONObject.toJSONString(new Result(-1, "用户名不存在或密码错误"));
		}
	}
	
	@RequestMapping(value="/showAllUsers",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showAllUsers() {
		
		List<User> list = userService.getAllUser();
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		for (User user : list) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", user.getUsername());
			map.put("email", user.getUsername());
			map.put("phone", user.getPhone());
			resultList.add(map);
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(resultList);
		return jsonArray.toJSONString();
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showDetail(String username) {
		User user = userService.showDetail(username);
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", user.getUsername());
		map.put("email", user.getEmail());
		map.put("phone", user.getPhone());
		map.put("headphoto", user.getHeadphoto());
		return JSONObject.toJSONString(map);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String updateUser(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, User user)  {
		if (file != null) {
			String path = request.getSession().getServletContext().getRealPath("upload");
	        String fileName = file.getOriginalFilename();
	        if (!fileName.matches("^(.+?)\\.(png|jpg|gif|PNG|JPG|GIF)$")) {
	        	return JSONObject.toJSONString(new Result(-1, "您上传的不是一张图片！"));
	        }
	        File targetFile = new File(path, fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	  
	        //保存  
	        try {  
	            file.transferTo(targetFile);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }
	        System.out.println(fileName);
	        
	        user.setHeadphoto(request.getContextPath()+"/upload/"+fileName);
		}
		if (userService.update(user)) {
			return JSONObject.toJSONString(new Result(1, "更新成功"));
		}
		else {
			return JSONObject.toJSONString(new Result(-1, "更新失败"));
		}
	}
	
	@RequestMapping(value="/changeWordBySuper",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String SuperCgpw(String username, String newpasswd) {
		boolean tag = userService.changepw(username, newpasswd);
		if (tag) {
			return JSONObject.toJSONString(new Result(1, "更新成功"));
		}
		else {
			return JSONObject.toJSONString(new Result(-1, "更新失败"));
		}
	}
	
	@RequestMapping(value="/changeWordByUser",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String UserCgpw(String username, String newpasswd, String oldpasswd) {
		boolean tag = userService.changepw(username, newpasswd, oldpasswd);
		if (tag) {
			return JSONObject.toJSONString(new Result(1, "更新成功"));
		}
		else {
			return JSONObject.toJSONString(new Result(-1, "更新失败"));
		}
	}
}
