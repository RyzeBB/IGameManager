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

<!-- Bootstrap styles -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Generic page styles -->
<link rel="stylesheet" href="css/style.css">
<!-- blueimp Gallery styles -->
<link rel="stylesheet" href="css/blueimp-gallery.min.css">
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="css/jquery.fileupload.css">
<link rel="stylesheet" href="css/jquery.fileupload-ui.css">
<!-- CSS adjustments for browsers with JavaScript disabled -->
<noscript><link rel="stylesheet" href="css/jquery.fileupload-noscript.css"></noscript>
<noscript><link rel="stylesheet" href="css/jquery.fileupload-ui-noscript.css"></noscript>
<style type="text/css">
table {
	font-size: 12px;
}
</style>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>


<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="js/jquery.ui.widget.js"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script src="js/tmpl.min.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="js/load-image.all.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="js/canvas-to-blob.min.js"></script>
<!-- Bootstrap JS is not required, but included for the responsive demo navigation -->
<script src="js/bootstrap.min.js"></script>
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
	
	function openPic() {
		$("#pic_select").panel("open");
		var e = window.event || arguments[0];
		var y = e.pageY || e.clientY + document.documentElement.scrollTop;
		$("#pic_select_div").css({'top': y + 'px'});  
	}
	
	function choose_img(imgurl,c_o){
// 		var win_pic = $("input[name='win_pic']");
//         for(var i=0;i<win_pic.length;i++){
//         	if(win_pic[i].value==null){
//         		var content = "<img src= '"+imgurl+"' style='width:148px;height:148px'>";
//         		$(content).addTo(win_pic.parent());
//         	}
//         }
		if(c_o.checked){
	        $("#window_pic ul li").each(function(i, o){
	        	if( $(o).children("div").html().trim()==""){
					var content = "<img src= '"+imgurl+"' style='width:148px;height:148px'><input type='hidden' name='winpic' value='"+imgurl+"'>";
					 $(o).children("div").html(content);
					 return false;
	        	} else {
	        		if(i>=4){
	        			alert("最多上传五张图");
	        			c_o.checked = false;
	        		}
	        	}
	        });
		}else{
			$("#window_pic ul li").each(function(i, o){
				var input_ = $(o).children("div").children("input");
	        	if(typeof(input_)!="undefined" && input_.val() == imgurl){
	        		if(i>=4){
	        			$(o).children("div").html("");
	        		}else{
	        			$(o).children("div").html("");
	        			$("#window_pic ul li").each(function(j, oj){
	        				if(j>=i && j<4){
	        					var next_content = $(oj).next().children("div").html();
	        					if(next_content != ""){
	        						$(oj).children("div").html(next_content);
	        						$(oj).next().children("div").html("");
	 		 					 }else{
	 		 						return false;
	 		 					 }
	        				}
	        			});
// 	        				
// 	        				alert(next_content);
// 	        				
// 	        			}
	        			 return false;
	        		}
	        	}
        });
		}
	}
	
	//初始化文件上传功能模块
	//$('#fileupload').fileupload(); 
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
						<div class="easyui-panel" style="background: #F7F7F7; width: 800px; height: 300px; padding: 10px;">
							<div id="window_pic">
							<ul>
								<li>
									<div style="width:150px;height:150px;border:1px solid #0099CC;"></div>
								</li>
								<li>
									<div style="width:150px;height:150px;border:1px solid #0099CC;"></div>
									
								</li>
								<li>
									<div style="width:150px;height:150px;border:1px solid #0099CC;"></div>
								</li>
								<li>
									<div style="width:150px;height:150px;border:1px solid #0099CC;"></div>
								</li>
								<li>
									<div style="width:150px;height:150px;border:1px solid #0099CC;"></div>
								</li>
							</ul>
						</div>
						<div align="center" style="padding-top: 200px">
								<button class="btn btn-primary start" type="button" onclick="openPic()">添加图片</button>
							</div>
						</div>
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
		</div></div>
		<div id="pic_select_div" style="float:left;position:absolute;left:100px;top:10px;">
		<div id="pic_select" class="easyui-pane">
			<div class="container">
    <!-- The file upload form used as target for the file upload widget -->
    <form id="fileupload" action="upload" method="POST" enctype="multipart/form-data">
        <!-- Redirect browsers with JavaScript disabled to the origin page -->
        <noscript><input type="hidden" name="redirect" value="https://blueimp.github.io/jQuery-File-Upload/"></noscript>
        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
        <div class="row fileupload-buttonbar">
            <div class="col-lg-7">
                <!-- The fileinput-button span is used to style the file input field as button -->
                <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>Add files...</span>
                    <input type="file" name="files[]" multiple="">
                </span>
                <button type="submit" class="btn btn-primary start">
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start upload</span>
                </button>
                <button type="reset" class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel upload</span>
                </button>
                <button type="button" class="btn btn-danger delete">
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" class="toggle">
                <!-- The global file processing state -->
                <span class="fileupload-process"></span>
            </div>
            <!-- The global progress state -->
            <div class="col-lg-5 fileupload-progress fade">
            	<div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                    <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                </div>
                <!-- The extended global progress state -->
                <div class="progress-extended">&nbsp;</div>
            </div>
        </div>
        <!-- The table listing the files available for upload/download -->
        <table role="presentation" class="table table-striped"><tbody class="files"></tbody></table>
    <br>
    </form>
</div>
<!-- The blueimp Gallery widget -->
<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
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
            <input type="checkbox" disabled="disabled">
            <strong class="error text-danger"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="btn btn-primary start" disabled>
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start</span>
                </button>
            {% } %}
            {% if (!i) { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
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
                {% if (file.url) { %}
                    <input type="checkbox" onclick="choose_img('{%=file.url%}',this)">
                {% } else { %}
                    <input type="checkbox" disabled="disabled">
                {% } %}
            {% if (file.error) { %}
                <div><span class="label label-danger">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>											
</div>
</div>
	

	
	<!-- The main application script -->
<script src="js/main.js"></script>
</body>
</html>
