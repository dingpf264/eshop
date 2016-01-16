package com.rhwayfun.eshop.utils;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminLoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();
		if(session.get("adminuser") == null && session.get("seller") == null){
			return "adminLogin";
		}else{
			return invocation.invoke();
		}
	}

}
