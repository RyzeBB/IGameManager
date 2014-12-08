<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺优惠卷</title>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css">
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

<script type="text/javascript">
	$(function() {
		$("#pg").datagrid({  
            loadMsg:'数据加载中....',  
            width:900,  
            url:"list",  
            nowrap: true,  
            striped: true,  
            collapsible:true,  
            remoteSort: false,  
            pagination:false,  
            rownumbers:true,  
            columns:[[ 
				{field:'ck',checkbox:true},
				{title:'编号',field:'id',width:80,hidden: true}, 
				{title:'名称',field:'name',width:100}, 
				{title:'所需积分',field:'score',width:60}, 
				{title:'兑换金额',field:'rmb',width:60}, 
				{title:'开始时间',field:'start_time',width:140}, 
				{title:'结束时间',field:'end_time',width:140}, 
				{title:'描述',field:'descrip',width:230}, 
//                 {field:'opt',title:'操作',width:100,align:'center',   
//                     formatter:function(value,rec){  
//                     	 return '<a href="javascrpt:void()" onclick="editUser()">编辑</a> ';   
//                     }  
//                 }  
            ]],  
              
            toolbar:['-', {text:"增加",iconCls:"icon-edit",handler:function(){
            	addCoupon();
            }},'-', {text:"修改",iconCls:"icon-edit",handler:function(){
            	editUser();
            }}]}
		)});
	
	var url;
	function editUser(){
        var rows = $('#pg').datagrid('getSelections');
        if(rows.length<=0)  
        {  
            $.messager.alert('警告','您没有选择','error');  
        }  
        else if(rows.length>1)  
        {  
        	 $.messager.alert('警告','您选择了多个','error');  
        }else{
       		row= rows[0]
        	//console.info(row);
            $('#dlg').dialog('open').dialog('setTitle','修改优惠卷');
            $('#fm').form('load',row);
            url = 'modify';
        }
    }
	
	function addCoupon(){
        var rows = $('#pg').datagrid('getRows');
        if(rows.length >0)  
        {  
            $.messager.alert('警告','您已创建优惠卷','error');  
        }else{
            $('#dlg').dialog('open').dialog('setTitle','编辑优惠卷');
            url = 'add';
        }
    }
	
	function saveUser(){
        $('#fm').form('submit',{
            url: url,
            onSubmit: function(){
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
                    $('#pg').datagrid('reload');    // reload the user data
                }
            }
        });
    }
	
</script>
</head>
<body>
	<!-- 用户列表 -->
	<div id="pg"></div>
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:320px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
		        <form id="fm" method="post" novalidate>
		            <div class="fitem">
		                <label>优惠卷名称:</label>
		                <input name="id" type="hidden">
		                <input name="name" class="easyui-validatebox textbox" required="true">
		            </div>
		            <div class="fitem">
		                <label>所需积分:</label>
		                <input name="score" class="easyui-validatebox textbox" required="true">
		            </div>
		            <div class="fitem">
		                <label>兑换金额:</label>
		                <input name="rmb" class="easyui-validatebox textbox" data-options="required:true">
		            </div>
		            <div class="fitem">
		                <label>开始时间:</label>
		                <input name="start_time" class="easyui-datetimebox" required="true">
		            </div>
		            <div class="fitem">
		                <label>结束时间:</label>
		                <input name="end_time" class="easyui-datetimebox" required="true">
		            </div>
		            <div class="fitem">
		                <label>描述:</label>
		                <input name="descrip" class="easyui-validatebox textbox" required="true">
		            </div>
		        </form>
		    </div>
		    <div id="dlg-buttons">
		        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
		    </div>
</body>
</html>