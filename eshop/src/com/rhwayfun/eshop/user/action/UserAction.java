package com.rhwayfun.eshop.user.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.rhwayfun.eshop.user.entity.User;
import com.rhwayfun.eshop.user.entity.Userdetail;
import com.rhwayfun.eshop.user.service.IUserService;
import com.rhwayfun.eshop.utils.MailUitls;
import com.rhwayfun.eshop.utils.State;
import com.rhwayfun.eshop.utils.UUIDUtils;
import com.rhwayfun.eshop.utils.VerifyCode;

public class UserAction{

	private static final long serialVersionUID = 1L;
	
	private IUserService userService;
	
	private User user;
	private Userdetail userdetail;
	private String username;
	private String code;
	private String verifycode;
	private String rePassword;
	
	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Userdetail getUserdetail() {
		return userdetail;
	}

	public void setUserdetail(Userdetail userdetail) {
		this.userdetail = userdetail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**************以下是action执行的方法**************/
	
	/**
	 * 登录页面跳转
	 */
	public String loginPage() throws Exception{
		return "loginPage";
	}
	
	/**
	 * 登录实现
	 */
	public String login() throws Exception{
		Map request = (Map) ActionContext.getContext().get("request");
		//检查验证码
		String verify = (String) ServletActionContext.getRequest().getSession().getAttribute("VerifyCode");
		if(!verifycode.equalsIgnoreCase(verify)){
			request.put("msg", "验证码错误");
			return "login_error";
		}
		User user1 = new User(user.getUsername(), user.getPassword());
		User existUser = userService.login(user1);
		if(existUser != null){
			ActionContext.getContext().getSession().put("user", existUser);
			return "login_success";
		}
		return "login_error";
	}
	
	/**
	 * 注销
	 */
	public String logOut() throws Exception{
		ServletActionContext.getRequest().getSession().invalidate();
		return "loginPage";
	}
	
	/**
	 * 注册页面跳转
	 */
	public String registerPage() throws Exception{
		return "registerPage";
	}

	/**
	 * 异步检查用户名是否有效
	 */
	public String validateUsername() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		User us = userService.validateUsername(username);
		System.out.println(us);
		if(us == null){
			out.print("<font color='green'>恭喜！用户名可用</font>");
		}else{
			out.print("<font color='red'>抱歉！用户名已被注册</font>");
		}
		return "none";
	}
	
	/**
	 * 注册实现
	 */
	public String register() throws Exception{
		Map request = (Map) ActionContext.getContext().get("request");
		//检查验证码
		String verify = (String) ServletActionContext.getRequest().getSession().getAttribute("VerifyCode");
		if(!verifycode.equalsIgnoreCase(verify)){
			request.put("msg", "验证码错误");
			return "register_error";
		}else{
			//首先需要设置用户的状态
			User user1 = new User();
			
			user1.setUsername(user.getUsername());
			user1.setPassword(user.getPassword());
			Userdetail userdetail1 = new Userdetail();
			userdetail1.setGender(userdetail.getGender());
			userdetail1.setTruename(userdetail.getTruename());
			userdetail1.setBirthday(userdetail.getBirthday());
			userdetail1.setEmail(userdetail.getEmail());
			userdetail1.setAddress(userdetail.getAddress());
			userdetail1.setPhone(userdetail.getPhone());
			
			userdetail1.setState(State.IN_ACTIVE);
			userdetail1.setCode(UUIDUtils.getUUID() + UUIDUtils.getUUID());
			userdetail1.setUser(user1);
			user1.setUserdetail(userdetail1);
			
			userService.save(user1);
			//发送激活邮件
			System.out.println(userdetail1.getEmail());
			
			MailUitls.sendMail(userdetail1.getEmail(), userdetail1.getCode());
			
			request.put("msg", "恭喜！注册成功，请快去您注册的邮箱" + userdetail1.getEmail() + "激活吧O(∩_∩)O哈！");
			return "register_success";
		}
	}
	
	/**
	 * 点击注册邮件进行激活
	 */
	public String active() throws Exception{
		Map request = (Map) ActionContext.getContext().get("request");
		User user1 = userService.findUserByCode(code);
		if(user1 != null){
			//激活成功
			Userdetail userdetail1 = user1.getUserdetail();
			userdetail1.setState(State.ACTIVE);
			userdetail1.setCode(null);
			user1.setUserdetail(userdetail1);
			userService.update(user1);
			request.put("msg", "您刚才注册账号已经激活成功！速速登录享受极致购物体验吧\\(^o^)/~");
		}else{
			request.put("msg", "激活失败！");
		}
		return "msg";
	}
	
	/**
	 * 生成验证码
	 */
	public String verifycode() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		VerifyCode.getVerifyCode(response);
		return "none";
	}
};
