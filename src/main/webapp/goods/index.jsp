<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">
<title>My starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/themes/icon.css">

<!-- Bootstrap styles -->
<!-- <link rel="stylesheet" href="css/bootstrap.min.css"> -->
<!-- Generic page styles -->
<link rel="stylesheet" href="css/style.css">
<!-- blueimp Gallery styles -->
<link rel="stylesheet" href="css/blueimp-gallery.min.css">
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="css/jquery.fileupload.css">
<link rel="stylesheet" href="css/jquery.fileupload-ui.css">
<!-- CSS adjustments for browsers with JavaScript disabled -->
<noscript>
	<link rel="stylesheet" href="css/jquery.fileupload-noscript.css">
</noscript>
<noscript>
	<link rel="stylesheet" href="css/jquery.fileupload-ui-noscript.css">
</noscript>
<style type="text/css">
table {
	font-size: 12px;
}
a {text-decoration: none} 


ul li div img{
   max-height:600px; max-width:600px; 
   width: expression(this.width > 600 && this.width > this.height ? 600 : auto); 
   height: expression(this.height > 600 ? 600 : auto); 
}

ul[class="vertical-line"] li div img{
    max-height:148px; max-width:148px; 
	width: expression(this.width > 148 && this.width > this.height ? 148 : auto); 
	height: expression(this.height > 148 ? 148 : auto); 
}

</style>
<script type="text/javascript" src="js/jquery.min.js"></script>

<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="js/jquery.ui.widget.js"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script src="js/tmpl.min.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="js/load-image.all.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="js/canvas-to-blob.min.js"></script>
<!-- Bootstrap JS is not required, but included for the responsive demo navigation -->
<!-- <script src="js/bootstrap.min.js"></script> -->
<!-- blueimp Gallery script -->
<script src="js/jquery.blueimp-gallery.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="js/jquery.fileupload-process.js"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="js/jquery.fileupload-image.js"></script>
<!-- The File Upload audio preview plugin -->
<script src="js/jquery.fileupload-audio.js"></script>
<!-- The File Upload video preview plugin -->
<script src="js/jquery.fileupload-video.js"></script>
<!-- The File Upload validation plugin -->
<script src="js/jquery.fileupload-validate.js"></script>
<!-- The File Upload user interface plugin -->
<script src="js/jquery.fileupload-ui.js"></script>

<!-- The XDomainRequest Transport is included for cross-domain file deletion for IE 8 and IE 9 -->
<!--[if (gte IE 8)&(lt IE 10)]>
<script src="js/cors/jquery.xdr-transport.js"></script>
<![endif]-->
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
	$.fn.serializeObject = function() {
		var serializeObj = {};
		var array = this.serializeArray();
		//console.info("array == " + array);
		//console.info(JSON.stringify(array));
		$(array).each(
				function() {
					if (serializeObj[this.name]) {
// 						console.info(this.name + "==="
// 								+ $.isArray(serializeObj[this.name]));
						if ($.isArray(serializeObj[this.name])) {
							serializeObj[this.name].push(this.value);
						} else {
							serializeObj[this.name] = [
									serializeObj[this.name], this.value ];
						}
					} else {
						serializeObj[this.name] = this.value;
					}
				});
		return serializeObj;
	};

	function submitForm() {
		tag = $('#fbean').form('validate');
		if (tag) {
			$('#fbean').submit();
			// 			jsonuserinfo = $("#ff").serializeObject();
			// 			var jsonuserinfo = $('#ff').serializeObject();
			// 			$.ajaxSetup({
			// 				contentType : 'application/json;charset=utf-8'
			// 			});
			// 			$.ajax({
			// 				type : "post",
			// 				url : $("#ff").attr("action"),
			// 				data : JSON.stringify(jsonuserinfo),
			// // 				data : jsonuserinfo,
			// 				dataType : "json",
			// 				contentType : "application/json;charset=utf-8",
			// 				success : function(msg) {
			// 					alert("success")
			// 				},
			// 				error : function(XMLHttpRequest, textStatus, errorThrown) {
			// 					alert("失败");
			// 				}
			// 			});
		} else {
			return;
		}
	}

	function picLoad() {
		alert("图片加载完成");
	}
	function po_sucess(cdata) {
		alert(cdata);
	}
	function clearForm() {
		$('#fbean').form('clear');
	}

	function addAttr() {
		content = "<tr><td>"
				+ "<input name=\"params\" class=\"easyui-textbox\" missingMessage=\"该输入项不能为空\" required data-options=\"prompt:'例如: 品牌:coco',validType:'length[2,6]' \" style=\"width:120px;height:25px\">"
				+ "</td></tr>";
		var add_attr_c = $(content).appendTo("#attr_set");
		$.parser.parse(add_attr_c);
	}
	var img_zone = "window_pic";
	var img_name = "titlePic";
	function openPic(param,name) {
		img_zone = param;
		img_name = name;
		$("#pic_select").show();
		var e = window.event || arguments[0];
		var y = e.pageY || e.clientY + document.documentElement.scrollTop;
		y = y+20;
		$("#pic_select").css({
			'top' : y + 'px'
		});
	}
	function closePic() {
		$("#pic_select").hide();
	}
	
	function choose_img(imgurl) {
		flag = false;
			$("#"+img_zone+" ul li")
					.each(
							function(i, o) {
								if ($(o).children("div").html().trim() == "") {
									var content = "<img src= '"+imgurl+"'><input type='hidden' name='"+img_name+"' value='"+imgurl+"'>";
									content = content+"<div style='width: 20px; height:20px;float:left; position:absolute; left:2px; top:2px;' align='center'><a href='javascript:void(0)' title='移除' onclick='removePic(this)'><font color='red' size='3'><b>X</b></font></a></div>";
									$(o).children("div").html(content);
									flag = true;
									return false;
								}
							});
			if(!flag){
				alert("图片选择超过上限");
			}
// 		} else {
// 			$("#window_pic ul li")
// 					.each(
// 							function(i, o) {
// 								var input_ = $(o).children("div").children(
// 										"input");
// 								if (typeof (input_) != "undefined"
// 										&& input_.val() == imgurl) {
// 									if (i >= 4) {
// 										$(o).children("div").html("");
// 									} else {
// 										$(o).children("div").html("");
// 										$("#window_pic ul li")
// 												.each(
// 														function(j, oj) {
// 															if (j >= i && j < 4) {
// 																var next_content = $(
// 																		oj)
// 																		.next()
// 																		.children(
// 																				"div")
// 																		.html();
// 																if (next_content != "") {
// 																	$(oj)
// 																			.children(
// 																					"div")
// 																			.html(
// 																					next_content);
// 																	$(oj)
// 																			.next()
// 																			.children(
// 																					"div")
// 																			.html(
// 																					"");
// 																} else {
// 																	return false;
// 																}
// 															}
// 														});
// 										return false;
// 									}
// 								}
// 							});
// 		}
	}
	function removePic(item) {
		$(item).parent().parent().html("");
	}
	
// 	function 
	var rowCount = 0;//属性行数
	function addRow(id) {
		$("#attr_button").linkbutton("enable"); 
		tr_c = $("#" + id + " tr:last");
		td_length = tr_c.children('td').length;
		// 		content = "<tr>" + $("#" + id + " tr:last").html() + "</tr>";
		content = "<tr>";
		temp1 = "<td>SKU-"+rowCount+"<input type='hidden' name='mulVal[0].values' value='SKU-"+rowCount+"'></td>";
		temp = "<td><input type=\"text\"  name='mulVal["+rowCount+"].values' class=\"easyui-textbox\" missingMessage=\"该输入项不能为空\" required data-options=\"prompt:'请输入值...'\" style=\"width:70px\"></td>";
		temp4= "<td><div id='window_pic_"+rowCount +"' style='padding: 10px;'><ul class='vertical-line'><li><div style='width: 150px; height: 150px; border: 1px solid #0099CC;position:relative'></div></li><li><div style='width: 150px; height: 150px; border: 1px solid #0099CC;position:relative'></div></li></ul></div>"+
				"<div align='center' style='padding-top: 150px'><button type='button' class='easyui-linkbutton' data-options=\"iconCls:'icon-add'\" onclick=\"openPic('window_pic_"+rowCount+"','mulVal["+rowCount+"].img')\">添加图片</button></div>";
		rowCount++;
		for (i = 0; i < td_length; i++) {
			if(i==0){
				content = content + temp1;
			}else if(i==4){
				content = content + temp4;
			}else{
				content = content + temp;
			}
		}
		content = content + "</tr>"
		// 		alert(content);
		n_node = $(content).appendTo($("#" + id));
		$.parser.parse(n_node);
	};
	var colCount = 2;
	function addRol(id) {
		colCount++;
		title_n = "<td width='30'><input type='text' name='mulName' class=\"easyui-textbox\" missingMessage=\"该输入项不能为空\" required data-options=\"prompt:'请输入值...'\" style=\"width:50px\"></td>"

		$("#" + id + " tr")
				.each(
						function(i, ot) {
							if (i == 0) {
								add_attr_c = $(title_n).appendTo(ot);
								$.parser.parse(add_attr_c);
							} else {
								title_v = "<td width='30'><input type='text' name='mulVal["
										+ (i - 1)
										+ "].values' class=\"easyui-textbox\" missingMessage=\"该输入项不能为空\" required data-options=\"prompt:'请输入值...'\" style=\"width:50px\"></td>"
								add_attr_c = $(title_v).appendTo(ot);
								$.parser.parse(add_attr_c);
							}
						});
	}
</script>

</head>

<body>

	<div style="padding: 10px 60px 20px 20px">

		<form id="fbean" method="POST" action="goods/createGoods">
			<table cellpadding="5">
				<tr>
					<td width="80">请选择分类:</td>
					<td><input class="easyui-combobox" name="type" 
						data-options="required:true,editable:false,
						url:'goods/type',
                    	method:'get',
                    	valueField: 'id',
                    	textField: 'name'"/>
					</td>
				</tr>
				<tr>
					<td>产品标题:</td>
					<td><input class="easyui-validatebox textbox" type="text"
						name="name" required validType="length[5,10]" 
						invalidMessage="5-10个字符！" style="height: 25px; width: 300px"></input></td>
				</tr>
				<tr>
					<td>商品简介:</td>
					<td><input class="easyui-textbox" missingMessage="该输入项为必须输入项" type="text" name="introduce"
						data-options="multiline:true,required:true,validType:'length[5,50]',invalidMessage:'5-50个字符！'"
						 style="height: 80px; width: 300px"></input></td>
				</tr>
				<tr>
					<td>商品价格:</td>
					<td><input class="easyui-numberbox" precision="2" required
						type="text" name="price" style="height: 25px; width: 100px">/元</td>
				</tr>
				<tr>
					<td>商品折扣:</td>
					<td><input class="easyui-numberbox" min="1" max="100" precision="0" required
						type="text" name="offer" style="height: 25px; width: 50px"
						value="100">&nbsp;&nbsp;商品折扣(单位%，例如20，表示折扣20%)</td>
				</tr>
				<tr>
					<td>商品库存:</td>
					<td><input class="easyui-numberbox" min="1" max="100000" precision="0" required
						type="text" name="stock" style="height: 25px; width: 50px"></td>
				</tr>
				<tr>
					<td width="80">显示风格:</td>
					<td><input class="easyui-combobox" name="disStyle"
						data-options=" valueField: 'label',textField: 'value',
        data: [{
            label: '1',
            value: '横排',
            selected:true
        },{
            label: '2',
            value: '竖排'
        }]" />

					</td>
				</tr>
				<tr>
					<td>快递方式:</td>
					<td><input class="easyui-validatebox textbox" required
						type="text" name="shippingType" validType="length[1,10]"
						invalidMessage="1-10个字符！！" style="height: 25px; width: 60px"></td>
				</tr>
				<tr>
					<td>快递费用:</td>
					<td><input class="easyui-numberbox" precision="0" required
						type="text" name="shippingCost" style="height: 25px; width: 60px"></td>
				</tr>
				<tr>
					<td>联系方式:</td>
					<td><input class="easyui-validatebox textbox" required
						type="text" name="serviecType" validType="length[1,10]"
						invalidMessage="1-10个字符！！" style="height: 25px; width: 60px" value="QQ"></td>
				</tr>
				<tr>
					<td>联系号码:</td>
					<td><input class="easyui-validatebox textbox" required
						type="text" name="serviecTel" validType="length[1,15]"
						invalidMessage="1-15个字符！！" style="height: 25px; width: 60px"></td>
				</tr>
				<tr>
					<td>发货地址:</td>
					<td><input class="easyui-validatebox textbox" required
						type="text" name="address" validType="length[10,50]"
						invalidMessage="10-50个字符！！" style="height: 25px; width: 400px"></td>
				</tr>
				<tr>
					<td valign="top">宝贝属性:</td>
					<td><div class="easyui-panel"
							style="background: #F7F7F7; width: 830px; height: 300px; padding: 10px;">
							<table id="attr_set">
								<tr>
									<td><input name="params"
										class="easyui-validatebox textbox" required
										data-options="prompt:'请输入属性值...'"
										style="width: 120px; height: 25px" value="品牌:"></td>
								</tr>
								<tr>
									<td><input name="params"
										class="easyui-validatebox textbox" required
										data-options="prompt:'请输入属性值...'"
										style="width: 120px; height: 25px" value="工艺:"></td>
								</tr>
								<tr>
									<td><input name="params"
										class="easyui-validatebox textbox" required
										data-options="prompt:'请输入属性值...'"
										style="width: 120px; height: 25px" value="风格:"></td>
								</tr>
							</table>
							<br> <a href="#" class="easyui-linkbutton"
								data-options="iconCls:'icon-add'"
								onclick="addAttr();return false;">添加基本属性</a> <br>
						</div></td>
				</tr>

				<tr>
					<td valign="top">橱窗图片:</td>
					<td>
						<div class="easyui-panel"
							style="background: #F7F7F7; width: 830px; height: 280px;">
							<div id="window_pic" style="padding: 10px;">
								<ul class="vertical-line">
									<li>
										<div
											style="width: 150px; height: 150px; border: 1px solid #0099CC;position:relative"></div>
									</li>
									<li>
										<div
											style="width: 150px; height: 150px; border: 1px solid #0099CC;position:relative"></div>

									</li>
									<li>
										<div
											style="width: 150px; height: 150px; border: 1px solid #0099CC;position:relative"></div>
									</li>
									<li>
										<div
											style="width: 150px; height: 150px; border: 1px solid #0099CC;position:relative"></div>
									</li>
									<li>
										<div
											style="width: 150px; height: 150px; border: 1px solid #0099CC;position:relative"></div>
									</li>
								</ul>
							</div>
							<div align="center" style="padding-top: 150px">
								<button type="button" class="easyui-linkbutton"
									data-options="iconCls:'icon-add'" onclick="openPic('window_pic','titlePic')">添加图片</button>
							</div>
						</div>
					</td>
				</tr>

				<tr>
					<td valign="top">产品详情:</td>
					<td>
						<div id="p" class="easyui-panel"
							style="background: #F7F7F7; width: 830px; height:500px; padding: 10px;">
							<button type="button"  class="easyui-linkbutton" onclick="addRow('m_table')">添加多属性</button>
							<button id="attr_button" type="button" class="easyui-linkbutton" onclick="addRol('m_table')"  data-options="iconCls:'icon-add',disabled:true">添加列</button>
							<table id="m_table" border="1px" bordercolor="#0099CC" cellspacing="0px" bordercolor="#000000" style="border-collapse:collapse">
								<tr>
									<td width="80">SKU<input type="hidden" name="mulName"
										value="sku"></td>
									<td width="80">数量<input type="hidden" name="mulName"
										value="num"></td>
									<td width="80">价格<input type="hidden" name="mulName"
										value="price"></td>
									<td width="80">折扣<input type="hidden" name="mulName"
										value="offer"></td>
									<td width="350" align="center">图片</td>
								</tr>
								<!-- <tr>
									<td height="200"><input type="text" name='mulVal[0].values'
										class="easyui-textbox" missingMessage="该输入项不能为空" required
										data-options="prompt:'请输入值...'" style="width: 70px"></td>
									<td><input type="text" name='mulVal[0].values'
										class="easyui-textbox" missingMessage="该输入项不能为空" required
										data-options="prompt:'请输入值...'" style="width: 70px"></td>
									<td><input type="text" name='mulVal[0].values'
										class="easyui-textbox" missingMessage="该输入项不能为空" required
										data-options="prompt:'请输入值...'" style="width: 70px"></td>
									<td><input type="text" name='mulVal[0].values'
										class="easyui-textbox" missingMessage="该输入项不能为空" required
										data-options="prompt:'请输入值...'" style="width: 70px"></td>
									<td>
										<div id="attr_pic_0" style="padding: 10px;">
											<ul>
												<li>
													<div
														style="width: 150px; height: 150px; border: 1px solid #0099CC;"></div>
												</li>
												<li>
													<div
														style="width: 150px; height: 150px; border: 1px solid #0099CC;"></div>
			
												</li>
											</ul>
										</div>
										<div align="center" style="padding-top: 150px">
											<button type="button" class="easyui-linkbutton"
												data-options="iconCls:'icon-add'" onclick="openPic('attr_pic_0','mulVal[0].img')">添加图片</button>
												
										</div>
									</td>
								</tr> -->
							</table>

						</div>
					</td>
				</tr>

				<tr>
					<td valign="top">细节信息:</td>
					<td>
						<div id="pic_other" class="easyui-panel"
							style="background: #F7F7F7; width: 850px; height: 600px; padding: 10px;">
				<ul>
					<li>
						<div
							style="margin-top: 10px;position:relative;width:auto"></div>
					</li>
					<li>
						<div
							style="margin-top: 10px;position:relative;float:none;"></div>

					</li>
					<li>
						<div
							style="margin-top: 10px;position:relative;float:none;"></div>
					</li>
					<li>
						<div
							style="margin-top: 10px;position:relative;float:none;"></div>
					</li>
					<li>
						<div
							style="margin-top: 10px;position:relative;float:none;"></div>
					</li>
					<li>
						<div
							style="margin-top: 10px;position:relative;float:none;"></div>
					</li>
					<li>
						<div
							style="margin-top: 10px;position:relative;float:none;"></div>

					</li>
				</ul>
				<div align="center">
				<button type="button" class="easyui-linkbutton"
									data-options="iconCls:'icon-add'" onclick="openPic('pic_other','detailePic')">添加图片</button>
									</div>
				</div>
							
					</td>
				</tr>

			</table>
			<div style="text-align: center; padding: 5px">
				<input type="submit" value="提交">
			</div>
		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="submitForm()">增加商品</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="clearForm()">清除</a>
		</div>
	</div>
	
	<div id="pic_select" style="width:890px;height:500px;float:left;position:absolute;left:100px;top:10px;">
	<div class="easyui-tabs" style="width:850px;" data-options="tools:'#tab-tools'">
		<div title="选择图片" style="padding: 10px" onLoad="picLoad()">
			<div id="pic_content"
				style="width: 800px; height: 335px; border: 1px solid #8CC1ED;">
				<ul class="vertical-line">
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
		<div title="上传图片" style="padding: 10px">
			<div id="pic_select_div" style="width: 800px; height: 335px; border: 1px solid #8CC1ED;">
			<div class="container">
				<!-- The file upload form used as target for the file upload widget -->
				<form id="fileupload" action="upload" method="POST"
					enctype="multipart/form-data">
					<!-- Redirect browsers with JavaScript disabled to the origin page -->
					<noscript>
						<input type="hidden" name="redirect"
							value="https://blueimp.github.io/jQuery-File-Upload/">
					</noscript>
					<!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
					<div class="row fileupload-buttonbar">
						<div class="col-lg-7">
							<!-- The fileinput-button span is used to style the file input field as button -->
							<span class="btn btn-success fileinput-button"><a
								href="javascript:void(0)" class="easyui-linkbutton"
								data-options="iconCls:'icon-add'" style="width: 80px">添加文件</a> <input
								type="file" name="files[]" multiple=""> </span>
							<button type="submit" class="easyui-linkbutton start"
								data-options="iconCls:'icon-save'">开始上传</button>
							<button type="reset" class="easyui-linkbutton cancel"
								data-options="iconCls:'icon-remove'">取消上传</button>
							<button type="button" class="easyui-linkbutton delete"
								data-options="iconCls:'icon-cut'">删除选中项</button>
							<input type="checkbox" class="toggle">
							<!-- The global file processing state -->
							<span class="fileupload-process"></span>
						</div>
						<!-- The global progress state -->
						<div class="col-lg-5 fileupload-progress fade">
							<div class="progress progress-striped active" role="progressbar"
								aria-valuemin="0" aria-valuemax="100">
								<div class="progress-bar progress-bar-success"
									style="width: 0%;"></div>
							</div>
							<!-- The extended global progress state -->
							<div class="progress-extended">&nbsp;</div>
						</div>
					</div>
					<!-- The table listing the files available for upload/download -->
					<table role="presentation" class="table table-striped">
						<tbody class="files"></tbody>
					</table>
					<br>
				</form>
			</div>
			<!-- The blueimp Gallery widget -->
			<div id="blueimp-gallery"
				class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
				<div class="slides"></div>
				<h3 class="title"></h3>
				<a class="prev">‹</a> <a class="next">›</a> <a class="close">×</a> <a
					class="play-pause"></a>
				<ol class="indicator"></ol>
			</div>
			<!-- The template to display files available for upload -->
			<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}

    <tr class="template-upload fade">
        <td style="width:200px">
            <span class="preview"></span>
        </td>
        <td style="width:100px">
            <strong class="error text-danger"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
        </td>
        <td>
	{% if (file.error || !o.options.autoUpload) { %}  
       <button class="start" disabled=true>上传</button> 
    {% } else { %}  
		<button class="start">上传</button>
 	{% } %}  
            {% if (!i && !o.options.autoUpload) { %}
 				
            {% } %}
			
            {% if (!i) { %}
                <button class="easyui-linkbutton cancel" data-options="iconCls:'icon-remove'">取消</button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
			<!-- The template to display files available for download -->
			<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td style="width:200px">
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </td>
        <td style="width:100px">
            {% if (file.error) { %}
                <div><span class="label label-danger">错误</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>删除</span>
                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>取消</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
		</div>
		</div>
	</div>
	<div id="tab-tools">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="closePic()"></a>
    </div>
	</div>
	<script type="text/javascript">
		$('#pp').pagination({
			total : 100,
			layout : [ 'first', 'prev', 'next', 'last', 'refresh' ],
			showPageList : false,
			showRefresh : false,
			displayMsg : '',
			onSelectPage : function(pageNumber, pageSize) {
				console.info(pageNumber + " -- "+pageSize)
				$.get("upload?pageNumber="+pageNumber+"&pageSize="+pageSize, function(data){
				   json = eval('('+data+')');    
					//console.info(json);
					//console.info(json.files);
					 $.each($("#pic_content ul li"), function (index, item) { 
						 $(item).children("div").html("");
					 });
					 $('#pp').pagination('refresh',{	// change options and refresh pager bar information
							total: json.count
						});
					 $.each(json.files, function (index, item) {  
		                 //循环获取数据    
		                 img_url = item.thumbnailUrl;
		                 img_content = "<img src='"+item.thumbnailUrl+"'  class='divImg1' onclick=choose_img('"+item.thumbnailUrl+"')>"
		                 $("#pic_content ul li:eq("+index+")").children("div").html(img_content);
						//console.info(JSON.stringify(item));
// 		                 $("#list").html($("#list").html() + "<br>" + name + " - " + idnumber + " - " + sex + "<br/>");  
		             });  
					//$("#pic_content").html("HELLO");
				});
			}
		});
		
	</script>
	<script src="js/main.js"></script>
</body>
</html>
