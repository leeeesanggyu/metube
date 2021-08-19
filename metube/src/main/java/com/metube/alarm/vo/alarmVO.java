package com.metube.alarm.vo;

public class alarmVO {

	private int pk;
	private int post_pk;
	private int p_user_pk;
	private int c_user_pk;
	private String cmd;
	private String create_at;
	private String name;
	private String title;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public int getPost_pk() {
		return post_pk;
	}
	public void setPost_pk(int post_pk) {
		this.post_pk = post_pk;
	}
	public int getP_user_pk() {
		return p_user_pk;
	}
	public void setP_user_pk(int p_user_pk) {
		this.p_user_pk = p_user_pk;
	}
	public int getC_user_pk() {
		return c_user_pk;
	}
	public void setC_user_pk(int c_user_pk) {
		this.c_user_pk = c_user_pk;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getCreate_at() {
		return create_at;
	}
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
}
