<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"	href="../js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css">

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	$(function() {
		$("#test").datagrid({  
            loadMsg:'数据加载中....',  
            width:900,  
            height:530,  
            url:"../goods/list",  
            nowrap: false,  
            striped: true,  
            collapsible:true,  
            remoteSort: false,  
            pagination:true,  
//             rownumbers:true,  
            frozenColumns:[[  
                
            ]],  
            columns:[[ 
                {title:'基本信息',colspan:7,width:600},  
                {field:'opt',title:'操作',width:100,align:'center', rowspan:2,  
                    formatter:function(value,rec){  
                    	 return '<a href="../goods/listone/'+rec.id+'" >编辑</a> ';   
                    }  
                }  
            ],[  
				{field:'ck',checkbox:true}, 
				{title:'编号',field:'id',width:80,hidden: true}, 
                {field:'titlePic',title:'图片',width:160, align:"center", 
                    formatter:function(value,rec){  
                  	 	 return '<img src="../'+rec.titlePic[0]+'">';    
                  }},  
                {field:'name',title:'名称',width:120}, 
                {field:'saleCount',title:'已出售数量',width:120},  
                {field:'price',title:'价格',width:120},  
                {field:'stock',title:'库存',width:120}  
            ]],  
              
            toolbar:['-', {text:"删除",iconCls:"icon-remove",handler:function(){  
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
//                 	console.info("==="+JSON.stringify(ids));
//                     $.messager.alert('警告','不支持批量删除','error');  
//                 }  
//                 else  
//                 {  
                    $.messager.confirm('确定','您确定要删除吗',function(t){  
                        if(t)  
                        {  
                              
                            $.ajax({  
                                url : '../goods/delete/',  
                                data :JSON.stringify(ids),
                                contentType: "application/json",
                                dataType : 'json',  
                                method: 'post',
                                success : function(r) {  
                                    if (r.success) {  
                                    	 $("#test").datagrid('acceptChanges');  
                                        $.messager.show({  
                                            msg : r.msg,  
                                            title : '成功'  
                                        });  
                                        editRow = undefined;  
                                        $("#test").datagrid('reload');  
                                    } else {  
                                        /*datagrid.datagrid('rejectChanges');*/  
                                         $("#test").datagrid('beginEdit', editRow);  
                                        $.messager.alert('错误', r.msg, 'error');  
                                    }  
                                    $("#test").datagrid('unselectAll');  
                                }  
                            });  
                          
                        }  
                    })  
                }  
                  
                  
            }}]  
        });  
        var p = $('#test').datagrid('getPager');  
        if (p){  
            $(p).pagination({  
            	 showPageList: false,
//                 onBeforeRefresh:function(){  
//                     alert('before refresh');  
//                 }  
            });  
        }  
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

</script>
</head>
<body>
	<!-- 用户列表 -->
			<div id="test"></div>
</body>
</html>