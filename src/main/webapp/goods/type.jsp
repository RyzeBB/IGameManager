<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品分类</title>
<link rel="stylesheet" type="text/css"	href="../js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<!-- blueimp Gallery styles -->
<link rel="stylesheet" href="../css/blueimp-gallery.min.css">
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="../css/jquery.fileupload.css">
<link rel="stylesheet" href="../css/jquery.fileupload-ui.css">
<!-- CSS adjustments for browsers with JavaScript disabled -->
<noscript>
	<link rel="stylesheet" href="../css/jquery.fileupload-noscript.css">
</noscript>
<noscript>
	<link rel="stylesheet" href="../css/jquery.fileupload-ui-noscript.css">
</noscript>
<style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/locale/easyui-lang-zh_CN.js"></script>

<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="../js/jquery.ui.widget.js"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script src="../js/tmpl.min.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="../js/load-image.all.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="../js/canvas-to-blob.min.js"></script>
<!-- Bootstrap JS is not required, but included for the responsive demo navigation -->
<!-- <script src="js/bootstrap.min.js"></script> -->
<!-- blueimp Gallery script -->
<script src="../js/jquery.blueimp-gallery.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="../js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="../js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="../js/jquery.fileupload-process.js"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="../js/jquery.fileupload-image.js"></script>
<!-- The File Upload audio preview plugin -->
<script src="../js/jquery.fileupload-audio.js"></script>
<!-- The File Upload video preview plugin -->
<script src="../js/jquery.fileupload-video.js"></script>
<!-- The File Upload validation plugin -->
<script src="../js/jquery.fileupload-validate.js"></script>
<!-- The File Upload user interface plugin -->
<script src="../js/jquery.fileupload-ui.js"></script>

<script type="text/javascript">
	$(function() {
		$("#test").datagrid({  
            loadMsg:'数据加载中....',  
            width:900,  
            height:530,  
            url:"../goods/type",  
            method:'get',
            nowrap: false,  
            striped: true,  
            collapsible:true,  
            remoteSort: false,  
            pagination:false,  
            rownumbers:false,  
            columns:[[ 
                {field:'ck',checkbox:true},
                {title:'编号',field:'id',width:80,hidden: true},
                {field:'name',title:'名称',width:120}, 
                {field:'pic_url',title:'图片',width:300, align:"center", 
                    formatter:function(value,rec){  
                  	 	 return '<img src="../'+rec.pic_url+'">';    
                  }}
            ]],  
              
            toolbar:[{text:"增加分类",iconCls:"icon-add",handler:function(){
            	newType();
            }},'-', {text:"删除",iconCls:"icon-remove",handler:function(){  
                var rows=$("#test").datagrid('getSelections');  
               
                if(rows.length<=0)  
                {  
                    $.messager.alert('警告','您没有选择','error');  
                }  
                else if(rows.length>=1)  
                {  
                	ids = new Array();
                	for (var i = 0; i < rows.length; i++) {
                		ids.push(rows[i].id);
					}
                    $.messager.confirm('确定','您确定要删除吗',function(t){  
                        if(t)  
                        {  
                              
                            $.ajax({  
                                url : 'delType',  
                                data :JSON.stringify(ids),
                                contentType: "application/json",
                                dataType : 'json',  
                                method: 'post',
                                success : function(result) {  
                                	console.info(result)
                                    if (result.errorMsg){
                                        $.messager.show({
                                            title: 'Error',
                                            msg: result.errorMsg
                                        });
                                    } else {
                                        $('#test').datagrid('reload');    // reload the user data
                                    }
                                }   
                            });  
                          
                        }  
                    })  
                }  
                  
                  
            }},'-',{text:"修改分类",iconCls:"icon-edit",handler:function(){
            	editUser();
            }}]  
        });  
//         var p = $('#test').datagrid('getPager');  
//         if (p){  
//             $(p).pagination({  
//             	 showPageList: false,
//                 onBeforeRefresh:function(){  
//                     alert('before refresh');  
//                 }  
//             });  
//         }  
    });  
    function resize(){  
        $('#test').datagrid('resize', {  
            width:700,  
            height:400  
        });  
    }  
    function getSelected(){  
        var selected = $('#test').datagrid('getSelected');  
        if (selected){  
            alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);  
        }  
    }  
    function getSelections(){  
        var ids = [];  
        var rows = $('#test').datagrid('getSelections');  
        for(var i=0;i<rows.length;i++){  
            ids.push(rows[i].code);  
        }  
        alert(ids.join(':'));  
    }  
    function clearSelections(){  
        $('#test').datagrid('clearSelections');  
    }  
    function selectRow(){  
        $('#test').datagrid('selectRow',2);  
    }  
    function selectRecord(){  
        $('#test').datagrid('selectRecord','002');  
    }  
    function unselectRow(){  
        $('#test').datagrid('unselectRow',2);  
    }  
    function mergeCells(){  
        $('#test').datagrid('mergeCells',{  
            index:2,  
            field:'addr',  
            rowspan:2,  
            colspan:2  
        });  	
	}
    
    var url;
    function newType(){
       $('#dlg').dialog('open').dialog('setTitle','新增分类');
       $('#fm').form('clear');
       $('#_pic').html("");
       url = 'save';
    }
    function editUser(){
        var rows = $('#test').datagrid('getSelections');
        if(rows.length<=0)  
        {  
            $.messager.alert('警告','您没有选择','error');  
        }  
        else if(rows.length>1)  
        {  
        	 $.messager.alert('警告','您选择了多个','error');  
        }else{
       		row= rows[0]
        	console.info(row);
            $('#dlg').dialog('open').dialog('setTitle','修改分类');
            $('#fm').form('load',row);
            $('#_pic').html("<img src='../"+row.pic_url+"'>");
            url = 'update';
        }
    }
    function saveUser(){
        $('#fm').form('submit',{
            url: url,
            onSubmit: function(){
            	if($('_pic').html==null){
            		 $.messager.show({    // show error message
                         title: 'Error',
                         msg: "图片为空，不能提交"
                     });
            		return;
            	}
                return $(this).form('validate');
            },
            success: function(result){
                var result = eval('('+result+')');
                if (result.errorMsg){
                    $.messager.show({
                        title: 'Error',
                        msg: result.errorMsg
                    });
                } else {
                    $('#dlg').dialog('close');        // close the dialog
                    $('#test').datagrid('reload');    // reload the user data
                }
            }
        });
    }
    function destroyUser(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
                if (r){
                    $.post('destroy_user.php',{id:row.id},function(result){
                        if (result.success){
                            $('#dg').datagrid('reload');    // reload the user data
                        } else {
                            $.messager.show({    // show error message
                                title: 'Error',
                                msg: result.errorMsg
                            });
                        }
                    },'json');
                }
            });
        }
    }

</script>
</head>
<body>
	<!-- 用户列表 -->
			<div id="test"></div>
			<div id="dlg" class="easyui-dialog" style="width:400px;height:320px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
		        <form id="fm" method="post" novalidate>
		            <div class="fitem">
		                <label>分类名称:</label>
		                <input name="id" type="hidden">
		                <input name="name" class="easyui-validatebox textbox" required="true">
		                <input id="pic_url" name="pic_url" type="hidden">
		            </div>
		            <div class="fitem">
		                <label>图片预览:</label>
		                <button type="button" onclick="$('#pic_select').dialog('open')">选择图片</button>
		                <br> <br>
		                <div id="_pic"></div>
		            </div>
		        </form>
		    </div>
		    <div id="dlg-buttons">
		        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
		    </div>
		    
<!-- 		    <div id="pic_select"  style="width:890px;height:500px;float:left;position:absolute;left:100px;top:10px;"> -->
		    <div id="pic_select" title="图片处理" class="easyui-dialog" closed="true" style="width:890px;height:500px;">
	<div class="easyui-tabs">
		<div title="选择图片" style="padding: 10px">
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
				<form id="fileupload" action="../upload" method="POST"
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
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="../{%=file.thumbnailUrl%}"></a>
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
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="../{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
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
				$.get("../upload?pageNumber="+pageNumber+"&pageSize="+pageSize, function(data){
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
		                 img_content = "<img src='../"+item.thumbnailUrl+"'  class='divImg1' onclick=choose_img('"+item.thumbnailUrl+"')>"
		                 $("#pic_content ul li:eq("+index+")").children("div").html(img_content);
						//console.info(JSON.stringify(item));
// 		                 $("#list").html($("#list").html() + "<br>" + name + " - " + idnumber + " - " + sex + "<br/>");  
		             });  
					//$("#pic_content").html("HELLO");
				});
			}
		});
		$(function () {
		    'use strict';

		    // Initialize the jQuery File Upload widget:
		    $('#fileupload').fileupload({
		        // Uncomment the following to send cross-domain cookies:
		        //xhrFields: {withCredentials: true},
//		        url: 'upload',
		    });

		    // Enable iframe cross-domain access via redirect option:
		    $('#fileupload').fileupload(
		        'option',
		        'redirect',
		        window.location.href.replace(
		            /\/[^\/]*$/,
		            '/cors/result.html?%s'
		        )
		    );

//		    if (window.location.hostname === 'blueimp.github.io') {
		        // Demo settings:
//		        $('#fileupload').fileupload('option', {
//		            url: '//jquery-file-upload.appspot.com/',
//		            // Enable image resizing, except for Android and Opera,
//		            // which actually support image resizing, but fail to
//		            // send Blob objects via XHR requests:
//		            disableImageResize: /Android(?!.*Chrome)|Opera/
//		                .test(window.navigator.userAgent),
//		            maxFileSize: 5000000,
//		            acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i
//		        });
//		        // Upload server status check for browsers with CORS support:
//		        if ($.support.cors) {
//		            $.ajax({
//		                url: '//jquery-file-upload.appspot.com/',
//		                type: 'HEAD'
//		            }).fail(function () {
//		                $('<div class="alert alert-danger"/>')
//		                    .text('Upload server currently unavailable - ' +
//		                            new Date())
//		                    .appendTo('#fileupload');
//		            });
//		        }
//		    } else {
		    	
		    	 $('#fileupload').fileupload('option', {
//		             url: '',
		             // Enable image resizing, except for Android and Opera,
		             // which actually support image resizing, but fail to
		             // send Blob objects via XHR requests:
//		             disableImageResize: /Android(?!.*Chrome)|Opera/
//		                 .test(window.navigator.userAgent),
		             maxFileSize: 5000000,
		             maxNumberOfFiles:6,
		             acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i
		         });
		        // Load existing files:
		        $('#fileupload').addClass('fileupload-processing');
//		        $.ajax({
//		            // Uncomment the following to send cross-domain cookies:
//		            //xhrFields: {withCredentials: true},
//		            url: $('#fileupload').fileupload('option', 'url'),
//		            dataType: 'json',
//		            context: $('#fileupload')[0]
//		        }).always(function () {
//		            $(this).removeClass('fileupload-processing');
//		        }).done(function (result) {
//		            $(this).fileupload('option', 'done')
//		                .call(this, $.Event('done'), {result: result});
//		        });
//		    }
		//  //定位  
//		    $('#pic_select').panel({
//		    	 title: "图片选择",
//		    	    width: 900,
//		    	    height: 400,
//		    	    iconCls: 'icon-ok',
//		    	    closable:true
//		    });
		   // $('#pic_select').hide();
		    $.get("../upload?pageNumber=1&pageSize=10", function(data){
				 var temp_json = eval('('+data+')');    
					//console.info(json);
					//console.info(json.files);
				 $('#pp').pagination('refresh',{	// change options and refresh pager bar information
						total: temp_json.count
					});
					 $.each(temp_json.files, function (index, item) {  
		                //循环获取数据    
//						 var temp_img_url = item.thumbnailUrl;
						 var temp_img_content = "<img src='../"+item.thumbnailUrl+"'  class='divImg1' onclick=choose_img('"+item.thumbnailUrl+"')>"
		                $("#pic_content ul li:eq("+index+")").children("div").html(temp_img_content);
						//console.info(JSON.stringify(item));
//		                 $("#list").html($("#list").html() + "<br>" + name + " - " + idnumber + " - " + sex + "<br/>");  
		            });  
					//$("#pic_content").html("HELLO");
				});
		});
		
		function choose_img(_url){
			$('#_pic').html("<img src='../"+_url+"'>");
			$('#pic_url').val(_url);
			$('#pic_select').dialog('close');
		}
	</script>
</body>
</html>