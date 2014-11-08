<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">

<html>
<head>
<title>My starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/themes/icon.css">
<style type="text/css">
table {
	font-size: 12px;
}
</style>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
// 	$.fn.serializeObject = function() {
// 		var o = {};
// 		var a = this.serializeArray();
// 		$.each(a, function() {
// 			if (o[this.name]) {
// 				if (!o[this.name].push) {
// 					o[this.name] = [ o[this.name] ];
// 				}
// 				o[this.name].push(this.value || '');
// 			} else {
// 				o[this.name] = this.value || '';
// 			}
// 		});
// 		return o;
// 	};

	function submitForm() {
		// 		var data = $("#ff").serializeArray(); //自动将form表单封装成json
		// 		alert(JSON.stringify(data)); 
		tag = $('#ff').form('validate');
		if (tag) {
			$('#ff').submit();
// 			var jsonuserinfo = $('#ff').serializeObject();
// 			$.ajaxSetup({
// 				contentType : 'application/json'
// 			});
// 			console.info(JSON.stringify(jsonuserinfo));
			// 			$.post($('#ff').attr('action'), jsonuserinfo, po_sucess, "json" );

// 			$.ajax({
// 				type:"post",
// 				url:$("#ff").attr("action"),
// 				data:JSON.stringify(jsonuserinfo),
// 				dataType : "json",
// 				contentType: "application/json;charset=utf-8",
// 				success : function(msg) {
// 					alert("success")
// 				},
// 				error : function(XMLHttpRequest, textStatus, errorThrown) {
// 					alert("失败");
// 				}
// 			});

			// 	        alert(JSON.stringify(jsonuserinfo)); 
		} else {
			return;
		}

		//$('#ff').form('submit');
	}

	function po_sucess(cdata) {
		alert(cdata);
	}
	function clearForm() {
		$('#ff').form('clear');
	}

	function addAttr() {
		content = "<tr><td>"
				+ "<input name=\"attrName\" class=\"easyui-textbox\" missingMessage=\"该输入项不能为空\" required data-options=\"prompt:'属性名称...' ,validType:'length[2,6]'\" style=\"width:60px;height:25px\"> : "
				+ "<input name=\"attrValue\" class=\"easyui-textbox\" missingMessage=\"该输入项不能为空\" required data-options=\"prompt:'属性值...',validType:'length[2,6]' \" style=\"width:100px;height:25px\">"
				+ "</td></tr>";
		var add_attr_c = $(content).appendTo("#attr_set");
		$.parser.parse(add_attr_c);
	}
</script>
</script>

</head>

<body>

	<div style="padding: 10px 60px 20px 20px">

		<form id="ff" method="post" action="goods/createGoods">
			<table cellpadding="5">
				<tr>
					<td width="80">请选择分类:</td>
					<td><input class="easyui-combobox" name="type"
						data-options="
        valueField: 'label',
        textField: 'value',
        groupField:'group',
        data: [{
            label: '充气娃娃',
            value: '充气娃娃',
            group: '好东西'
        },{
            label: '铁锤',
            value: '铁锤',
            group: '好东西'
        },{
            label: '男装',
            value: '男装',
            group: '衣服'
        },{
            label: '女装',
            value: '女装',
            group: '衣服'
        },{
            label: '童装',
            value: '童装',
            group: '衣服'
        },{
            label: 'lua',
            value: 'Ruby'
        }]" />

					</td>
				</tr>
				<tr>
					<td>产品标题:</td>
					<td><input class="easyui-validatebox textbox" type="text" name="name" required validType="length[5,10]" invalidMessage="5-10个字符！" style="height: 25px; width: 300px"></input></td>
				</tr>
				<tr>
					<td valign="top">宝贝属性:</td>
					<td><div class="easyui-panel" style="background: #F7F7F7; width: 800px; height: 200px; padding: 10px;">
							<table id="attr_set">
								<tr>
									<td><span>品牌:</span> <input name="attrName" type="hidden" value="品牌"> <input name="attrValue" class="easyui-validatebox textbox" required
										data-options="prompt:'请输入属性值...'" style="width: 80px; height: 25px"></td>
								</tr>
								<tr>
									<td><span>工艺:</span> <input name="attrName" type="hidden" value="工艺"> <input name="attrValue" class="easyui-validatebox textbox" required
										data-options="prompt:'请输入属性值...'" style="width: 80px; height: 25px"></td>
								</tr>
								<tr>
									<td><span>风格:</span> <input name="attrName" type="hidden" value="风格"> <input name="attrValue" class="easyui-validatebox textbox" required
										data-options="prompt:'请输入属性值...'" style="width: 80px; height: 25px"></td>
								</tr>
							</table>
							<br>
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addAttr();return false;">添加基本属性</a>
							<br>
						</div></td>
				</tr>

				<tr>
					<td valign="top">橱窗图片:</td>
					<td>
						<div id="p" class="easyui-panel" style="background: #F7F7F7; width: 800px; height: 200px; padding: 10px;"></div>
					</td>
				</tr>

				<tr>
					<td valign="top">产品详情:</td>
					<td>
						<div id="p" class="easyui-panel" style="background: #F7F7F7; width: 800px; height: 200px; padding: 10px;"></div>
					</td>
				</tr>

				<tr>
					<td valign="top">细节信息:</td>
					<td>
						<div id="p" class="easyui-panel" style="background: #F7F7F7; width: 800px; height: 200px; padding: 10px;"></div>
					</td>
				</tr>

			</table>
			<div style="text-align: center; padding: 5px">
				<input type="submit" value="提交">
			</div>
		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
		</div>
	</div>

</body>
</html>
