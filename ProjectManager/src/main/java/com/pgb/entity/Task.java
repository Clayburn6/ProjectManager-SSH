package com.pgb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

@Entity
@Table(name="task")
@Lazy(value=false)
public class Task {
	private int id;
	private String title;
	private String detail = "这是一个任务";
	private Date deadline = new Date(System.currentTimeMillis() + 24*3600*1000);
	private Date createTime = new Date(System.currentTimeMillis());
	private int priority = 5;
	private int status = 50;
	private int alive = 1;
	
	private Project project;
	public Task() {}
	public Task(int id, String title, String detail, Date deadline, int priority, int status, int alive) {
		super();
		this.id = id;
		this.title = title;
		this.detail = detail;
		this.deadline = deadline;
		this.priority = priority;
		this.status = status;
		this.alive = alive;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAlive() {
		return alive;
	}
	public void setAlive(int alive) {
		this.alive = alive;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="projectid", nullable=false)
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	
}

