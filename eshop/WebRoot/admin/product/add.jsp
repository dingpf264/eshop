<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
		<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
		<script src="${pageContext.request.contextPath}/ckeditor/samples/js/sample.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/ckeditor/samples/css/samples.css">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
		<script type="text/javascript">
				function findCs() {
					var cid = document.getElementById("cSelect").value;
					//创建xmlhttp对象
					var xmlhttp = null;
					if(window.XMLHttpRequest){
						xmlhttp = new XMLHttpRequest();
					}else if(window.ActiveXObject){
						xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
					}
					var url = "${pageContext.request.contextPath}/adminProduct_findCs.action?cid=" + cid + "&time=" + new Date().getTime();
					xmlhttp.open("GET", url,true);
					xmlhttp.onreadystatechange = function(){
						if (xmlhttp.readyState == 4) {
							if (xmlhttp.status == 200) {
								var data = xmlhttp.responseText;
								var csSelect = document.getElementById("csSelect");
								csSelect.options.length = 0;
								var tmp = data.split("|");
								for (var i = 0; i <= tmp.length; i++) {
									var csid = tmp[i].substring(0,tmp[i].lastIndexOf(":"));
									var csname = tmp[i].substring(tmp[i].lastIndexOf(":") + 1);
									var op = new Option(csname, csid);
									csSelect.options.add(op);
									//csSelect.append("<option value='"+ csid +"'>"+ csname +"</option>");
								};
							};
						};
					};
					xmlhttp.send(null);
				}
		</script>
	</HEAD>
	<body id="main">
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/adminProduct_save.action?currentPage=1" method="post" enctype="multipart/form-data">
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong><STRONG>添加商品</STRONG>
						</strong>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品名称：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<input type="text" name="pname" class="bg"/>
					</td>
					
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						所属二级分类：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<select id="cSelect" onchange="findCs(this)">
							<option>--请选择一级分类--</option>
							<s:set value="#request.cList" id="cList"></s:set>
							<s:iterator value="#cList" id="c1">
								<option value="<s:property value="#c1.cid"/>"><s:property value="#c1.cname"/></option>
							</s:iterator>
						</select>
						<select name="categorysecond.csid" id="csSelect">
							<%-- <s:iterator value="#request.csList" id="c">
								<option value="<s:property value="#c.csid"/>"><s:property value="#c.csname"/></option>
							</s:iterator> --%>
							<option>--请选择二级分类--</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						市场价：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<input type="text" name="marketPrice" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商城价：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<input type="text" name="shopPrice" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						是否热门：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<select name="isHot">
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品图片：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<input type="file" name="pic" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品介绍：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<textarea rows="20" cols="30" name="pdesc" id="editor1"></textarea>
						<script>
					        CKEDITOR.replace('editor1');
					    </script>
					</td>
				</tr>
				
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
							&#30830;&#23450;
						</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
	<script type="text/javascript">
		  function fileUpload() {
		    var files = ['file3'];  //将上传三个文件 ID 分别为file2,file2,file3
		    $.ajaxFileUpload( {
		      url : 'fileUpload.action',     //用于文件上传的服务器端请求地址  
		      secureuri : false,            //一般设置为false  
		      fileElementId : files,        //文件上传的id属性  <input type="file" id="file" name="file" />  
		      dataType : 'json',            //返回值类型 一般设置为json  
		      success : function(data, status) {
		       var fileNames = data.fileFileName; //返回的文件名 
		       var filePaths = data.filePath;     //返回的文件地址 
		       for(var i=0;i<data.fileFileName.length;i++){
		    	  $("#down").append("<img src='readImage.action?path=" + filePaths[i] + "' width='60px' height='50px'/>");
		        };
		      }
		    });
		  }
		</script>
</HTML>