<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>管理员登录</TITLE>
<LINK href="${pageContext.request.contextPath}/css/User_Login.css"
	type=text/css rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
</HEAD>
<BODY id=userlogin_body>
	<DIV></DIV>
	<DIV id=user_login>
		<DL>
			<DD id=user_top>
				<UL>
					<LI class=user_top_l></LI>
					<LI class=user_top_c></LI>
					<LI class=user_top_r></LI>
				</UL>
			<DD id=user_main>
				<UL>
					<LI class=user_main_l></LI>
					<form id="loginForm" action="adminUser_login.action" method="post" target="_parent">
						<LI class=user_main_c>
							<DIV class=user_main_box>
								<UL>
									<LI class=user_main_text>用户名：</LI>
									<LI class=user_main_input><INPUT class=TxtUserNameCssClass
										id=TxtUserName maxLength=30 name="adminuser.username"></LI>
								</UL>
								<UL>
									<LI class=user_main_text>密&nbsp;&nbsp;&nbsp;&nbsp;码：</LI>
									<LI class=user_main_input><INPUT class=TxtPasswordCssClass
										id=TxtPassword type=password name="adminuser.password"></LI>
								</UL>
							</DIV>
						</LI>
						<LI class=user_main_r><INPUT class=IbtnEnterCssClass
							id=IbtnEnter
							style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px"
							type=image src="${pageContext.request.contextPath}/images/user_botton.gif" name=IbtnEnter
							onclick="javascript:document.getElementById('loginForm').submit();">
						</LI>
					</form>
				</UL>
			<DD id=user_bottom>
				<UL>
					<LI class=user_bottom_l></LI>
					<LI class=user_bottom_c><SPAN style="MARGIN-TOP: 40px"></SPAN>
					</LI>
					<LI class=user_bottom_r></LI>
				</UL>
			</DD>
		</DL>
	</DIV>
	<SPAN id=ValrUserName style="DISPLAY: none; COLOR: red"></SPAN>
	<SPAN id=ValrPassword style="DISPLAY: none; COLOR: red"></SPAN>
	<SPAN id=ValrValidateCode style="DISPLAY: none; COLOR: red"></SPAN>
	<DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV>

	<DIV></DIV>

	</FORM>
</BODY>
</HTML>
