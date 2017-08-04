package com.pgb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pgb.service.Pj_userService;
import com.pgb.utils.Result;

@Controller
public class Pj_userController {
	@Autowired
	private Pj_userService pj_userService;
	
	@RequestMapping(value="/addUserToProject",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String addUserToProject(String username, String projectTitle) {
		if (pj_userService.addUserToProject(username, projectTitle)) {
			return JSONObject.toJSONString(new Result(1, "添加成功成功"));
		}
		else {
			return JSONObject.toJSONString(new Result(-1, "用户或项目不存在"));
		}
	}
}
