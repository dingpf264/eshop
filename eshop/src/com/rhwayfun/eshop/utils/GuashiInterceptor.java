package com.rhwayfun.eshop.utils;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class GuashiInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		/*UserAction userAction = (UserAction) invocation.getAction();
		//获取用户名
		String username = userAction.getUser().getUsername();
		//获取所有的挂失信息
		java.util.List<Guashi> guashi_list = userService.getReportLossUsers();
		for (Guashi guashi : guashi_list) {
			if(guashi.getUser().getUsername().equals(username)){
				//说明该账号已经被挂失了，不能再登陆
				Map request = (Map) invocation.getInvocationContext().get("request");
				request.put("msg", "对不起，您的账号已被挂失！");
				return Action.LOGIN;
			}
		}*/
		return invocation.invoke();
	}

}
