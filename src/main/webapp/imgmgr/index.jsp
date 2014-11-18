<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片选择</title>
<link rel="stylesheet" type="text/css"
	href="js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/themes/icon.css">
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div class="easyui-tabs" style="width: 850px; height: 550px">
		<div title="选择图片" style="padding: 10px">
			<div id="pic_content"
				style="width: 800px; height: 335px; border: 1px solid #8CC1ED;">
				<ul>
					<li>
						<div
							style="width: 150px; height: 150px; border: 1px solid #0099CC; margin-top: 10px;"></div>
					</li>
					<li>
						<div
							style="width: 150px; height: 150px; border: 1px solid #0099CC; margin-top: 10px;"></div>

					</li>
					<li>
						<div
							style="width: 150px; height: 150px; border: 1px solid #0099CC; margin-top: 10px;"></div>
					</li>
					<li>
						<div
							style="width: 150px; height: 150px; border: 1px solid #0099CC; margin-top: 10px;"></div>
					</li>
					<li>
						<div
							style="width: 150px; height: 150px; border: 1px solid #0099CC; margin-top: 10px;"></div>
					</li>
					<li>
						<div
							style="width: 150px; height: 150px; border: 1px solid #0099CC; margin-top: 10px;"></div>
					</li>
					<li>
						<div
							style="width: 150px; height: 150px; border: 1px solid #0099CC; margin-top: 10px;"></div>

					</li>
					<li>
						<div
							style="width: 150px; height: 150px; border: 1px solid #0099CC; margin-top: 10px;"></div>
					</li>
					<li>
						<div
							style="width: 150px; height: 150px; border: 1px solid #0099CC; margin-top: 10px;"></div>
					</li>
					<li>
						<div
							style="width: 150px; height: 150px; border: 1px solid #0099CC; margin-top: 10px;"></div>
					</li>
				</ul>
			</div>
			<div id="pp" class="easyui-pagination"></div>
		</div>
		<div title="上传图片" style="padding: 10px">ddddddd</div>
	</div>
	<script type="text/javascript">
		$('#pp').pagination({
			total : 100,
			layout : [ 'first', 'prev', 'next', 'last', 'refresh' ],
			showPageList : false,
			showRefresh : false,
			displayMsg : '',
			onSelectPage : function(pageNumber, pageSize) {
				$.get("upload?pageNumber="+pageNumber+"&pageSize="+pageSize, function(data){
				 var json = eval('('+data+')');    
					console.info(json);
					console.info(json.files);
					 $.each(json.files, function (index, item) {  
		                 //循环获取数据    
		                 img_url = item.thumbnailUrl;
		                 img_content = "<img src='"+item.thumbnailUrl+"'style='width:148px;height:148px'>"
		                 $("#pic_content ul li:eq("+index+")").children("div").html(img_content);
						console.info(JSON.stringify(item));
// 		                 $("#list").html($("#list").html() + "<br>" + name + " - " + idnumber + " - " + sex + "<br/>");  
		             });  
					//$("#pic_content").html("HELLO");
				});
			}
		});
		
	</script>
</body>
</html>