package com.pgb.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pgb.entity.Task;
import com.pgb.service.TaskService;
import com.pgb.utils.Result;

@Controller
public class TaskController {
	@Autowired
	TaskService taskService;
	
	@RequestMapping(value="/editTask",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String editTask(String title, String detail, String priority, String status, String deadline, String projectTitle) {
		int pri = 0;
		int sta = 0;
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(deadline);
			pri = Integer.parseInt(priority);
			sta = Integer.parseInt(status);
		}
		catch (Exception e) {
			return JSONObject.toJSONString(new Result(-1, "更新失败"));
		}
		Task task = new Task();
		task.setTitle(title);
		task.setDetail(detail);
		task.setPriority(pri);
		task.setStatus(sta);
		task.setDeadline(date);
		if (taskService.editTask(task, projectTitle)) {
			return JSONObject.toJSONString(new Result(1, "更新成功"));
		}
		else {
			return JSONObject.toJSONString(new Result(-1, "更新失败"));
		}
	}
	
	@RequestMapping(value="/searchTask",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String search(String taskTitle, String projectTitle) {
		List<Task> list = taskService.searchTask(taskTitle, projectTitle);
		if (list.isEmpty() || list == null) {
			return JSONObject.toJSONString(new Result(1, "添加失败"));
		}
		else {
			List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
			for (Task task : list) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("title", task.getTitle());
				map.put("detail", task.getDetail());
				map.put("priority", task.getPriority() == 10 ? "紧急" : "普通");
				map.put("status", task.getStatus() == 100 ? "已完成" : "活动中");
				map.put("createtime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(task.getCreateTime()));
				map.put("deadline", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(task.getDeadline()));
				map.put("project", task.getProject().getTitle());
				resultList.add(map);
			}
			JSONArray jsonArray = new JSONArray();
			jsonArray.addAll(resultList);
			return jsonArray.toJSONString();
		}
	}
	@RequestMapping(value="/deleteTask",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String delete(String taskTitle, String projectTitle) {
		if (taskService.deletTask(taskTitle, projectTitle)) {
			return JSONObject.toJSONString(new Result(1, "删除成功"));
		}
		else {
			return JSONObject.toJSONString(new Result(-1, "删除失败"));
		}
	}
	
	@RequestMapping(value="/addTask",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String add(String taskTitle, String projectTitle) {
		if (taskService.addTask(taskTitle, projectTitle)) {
			return JSONObject.toJSONString(new Result(1, "添加成功"));
		}
		else {
			return JSONObject.toJSONString(new Result(-1, "添加失败"));
		}
	}
}
