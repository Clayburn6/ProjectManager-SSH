package com.pgb.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pgb.entity.User;
import com.pgb.service.ProjectService;
import com.pgb.utils.Result;

@Controller
public class ProjectController {
	@Autowired
	ProjectService projectService;
	@RequestMapping(value="/newProject",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String addProject(String title, String description, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if (username == null || username.equals("")) {
			return JSONObject.toJSONString(new Result(-1, "登录失效，请重新登录"));
		}
		else {
			if (projectService.addProject(title, description, username)) {
				return JSONObject.toJSONString(new Result(1, "添加成功"));
			}
			else {
				return JSONObject.toJSONString(new Result(-1, "添加失败, 您指定的项目负责人不存在"));
			}
		}
	}
	
	@RequestMapping(value="/editProject",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String editProject(String title, String description, String username) {
		if (projectService.updateProject(title, description, username)) {
			return JSONObject.toJSONString(new Result(1, "更新成功"));
		}
		else {
			return JSONObject.toJSONString(new Result(-1, "更新失败"));
		}
	}
	
	@RequestMapping(value="/deleteProject",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String editProject(String projectTitle) {
		if (projectService.deleteProject(projectTitle)) {
			return JSONObject.toJSONString(new Result(1, "删除成功"));
		}
		else {
			return JSONObject.toJSONString(new Result(-1, "删除失败"));
		}
	}
	
	@RequestMapping(value="/showProject",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showProject(String projectTitle) {
		Map<String, String> map = projectService.showProject(projectTitle);
		return JSONObject.toJSONString(map);
	}
}
