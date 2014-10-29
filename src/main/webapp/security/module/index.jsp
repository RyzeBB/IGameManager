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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="js/themes/color.css">
<!--     <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css"> -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

</head>
<body>
    <table id="tg" class="easyui-treegrid" title="资源模块" style="width:950px;height:450px"
            data-options="
                iconCls: 'icon-ok',
                rownumbers: true,
                animate: true,
                collapsible: false,
                fitColumns: true,
                url: 'module.action',
                method: 'post',
                idField: 'id',
                treeField: 'name',
                onContextMenu: onContextMenu,
                onAfterEdit:onAfterEdit
            ">
        <thead>
            <tr>
           		<th data-options="field:'id',width:30">id</th>
                <th data-options="field:'name',width:120,editor:'text'">模块名称</th>
                <th data-options="field:'sn',width:60,editor:'text'">模块编号</th>
                <th data-options="field:'url',width:140,editor:'text'">模块地址</th>
                <th data-options="field:'orderNo',width:80,editor:'text'">排序</th>
                <th data-options="field:'menustate',editor:'text'">是否显示在主菜单</th>
            </tr>
        </thead>
    </table>
    <div id="mm" class="easyui-menu" style="width:120px;">
        <div onclick="append()" data-options="iconCls:'icon-add'">添加</div>
        <div onclick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
        <div onclick="save()" data-options="iconCls:'icon-save'">保存</div>
        <div onclick="cancel()" data-options="iconCls:'icon-cancel'">取消编辑</div>
        <div onclick="removeIt()" data-options="iconCls:'icon-remove'">Remove</div>
        <div class="menu-sep"></div>
        <div onclick="collapse()">Collapse</div>
        <div onclick="expand()">Expand</div>
    </div>
    <script type="text/javascript">
        function onContextMenu(e,row){
            e.preventDefault();
            $(this).treegrid('select', row.id);
            $('#mm').menu('show',{
                left: e.pageX,
                top: e.pageY
            });
        }
        
       	function onAfterEdit (rowIndex, changes) {
            //endEdit该方法触发此事件
            console.info(changes);
        }
       	
        var idIndex = 100;
        function append(){
            idIndex++;
            var d1 = new Date();
            var d2 = new Date();
            d2.setMonth(d2.getMonth()+1);
            var node = $('#tg').treegrid('getSelected');
            $('#tg').treegrid('append',{
                parent: node.id,
                data: [{
                    id: idIndex,
                    name: 'New Task'+idIndex,
                    persons: parseInt(Math.random()*10),
                    begin: $.fn.datebox.defaults.formatter(d1),
                    end: $.fn.datebox.defaults.formatter(d2),
                    progress: parseInt(Math.random()*100)
                }]
            })
        }
        function removeIt(){
            var node = $('#tg').treegrid('getSelected');
            if (node){
                $('#tg').treegrid('remove', node.id);
            }
        }
        function collapse(){
            var node = $('#tg').treegrid('getSelected');
            if (node){
                $('#tg').treegrid('collapse', node.id);
            }
        }
        function expand(){
            var node = $('#tg').treegrid('getSelected');
            if (node){
                $('#tg').treegrid('expand', node.id);
            }
        }
        
        var editingId;
        function edit(){
            if (editingId != undefined){
                $('#tg').treegrid('select', editingId);
                return;
            }
            var row = $('#tg').treegrid('getSelected');
            if (row){
                editingId = row.id
                $('#tg').treegrid('beginEdit', editingId);
            }
        }
        function save(){
            if (editingId != undefined){
                var t = $('#tg');
                t.treegrid('endEdit', editingId);
                editingId = undefined;
                var persons = 0;
                var rows = t.treegrid('getChildren');
                for(var i=0; i<rows.length; i++){
                    var p = parseInt(rows[i].persons);
                    if (!isNaN(p)){
                        persons += p;
                    }
                }
//                 var frow = t.treegrid('getFooterRows')[0];
//                 frow.persons = persons;
//                 t.treegrid('reloadFooter');
            }
        }
        function cancel(){
            if (editingId != undefined){
                $('#tg').treegrid('cancelEdit', editingId);
                editingId = undefined;
            }
        }
    </script>
</body>
</html>