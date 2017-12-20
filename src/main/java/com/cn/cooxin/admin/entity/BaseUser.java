package com.cn.cooxin.admin.entity;

import com.cn.cooxin.code.entity.BaseEntitys;

/**
 * 用户信息Entity
 * 
 * @author airufei
 * @version 2017-02-13
 */
public class BaseUser extends BaseEntitys {

	private static final long serialVersionUID = 1L;
	private String username; // 用户名

	private String password; // 密码

	private int age=18; // 年龄

	private String email; // 账号

	private String phone; // 手机

	private String address; // 地址

	private String qq; // QQ号

	private String wechart; // 微信号

	private int type=0; // 用户类型: 0是互联网用户，1是管理员用户
	
	private String imgCode;//验证码，只是为了接收页面数据

	public BaseUser() {

	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getImgCode() {
		return imgCode;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechart() {
		return wechart;
	}

	public void setWechart(String wechart) {
		this.wechart = wechart;
	}
}