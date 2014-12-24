<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="../js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../js/themes/icon.css">

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/datagrid-detailview.js"></script>
<script type="text/javascript" src="../js/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<table id="dg" style="width:1000px;height:600px"
            title="订单详情"
            singleSelect="true" fitColumns="true">
    </table>
    
    <div id="dlg" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
		        <form id="fm" method="post" novalidate>
		         <input name="id" type="hidden">
				<table cellpadding="5">
	                <tr>
	                    <td>订单状态:</td>
	                    <td><select class="easyui-combobox" name="state" style="width:200px;" data-options="required:true,editable:false">
					        <option value="0">等待发货</option>
					        <option value="1">订单关闭</option>
					        <option value="2">支付成功,等待发货</option>
					        <option value="3">退款中</option>
					        <option value="4">退款成功</option>
					        <option value="5">等待确认收货</option>
					        <option value="6">交易成功</option>
					    </select></td>
	                </tr>
	                <tr>
	                    <td>备注信息:</td>
	                    <td><input class="easyui-textbox" missingMessage="该输入项为必须输入项" type="text" name="state"
						data-options="multiline:true,required:true,validType:'length[1,50]',invalidMessage:'1-50个字符！'"
						 style="height: 80px; width: 200px"></input></td></input></td>
	                </tr>
	            </table>
		        </form>
		    </div>
		    <div id="dlg-buttons">
		        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
		    </div>
    <script type="text/javascript">
        $(function(){
        	var _json;
        	 $.post("order", function(_data){
        		 _json = eval('('+_data+')'); 
//         		 $('#dg').datagrid({
//             		 data:_json
//             	 });
              $('#dg').datagrid({
              view: detailview,
              data:_json,
              columns:[[
                        {field:'id',title:'订单号',width:80},
                        {field:'price',title:'总价格',width:40},
                        {field:'coupon',title:'优惠卷',width:40},
                        {field:'addr',title:'收货地址',width:220},
                        {field:'create_time',title:'下单时间',width:100},
                        {field:'descrip',title:'备注',width:100,hidden: true},
                        {field:'state',title:'状态',width:100,formatter:function(value,rec,index){  
                        	desc =null;
                        	_descrip=_json[index].descrip;
                        	if(value==0){
                        		desc="等待发货";
                        	}else if(value==1){
                        		desc="订单关闭";
	                        }else if(value==2){
	                    		desc="支付成功,等待发货";
	                        }else if(value==3){
	                    		desc="退款中";
	                        }else if(value==4){
	                    		desc="退款成功";
	                        }else if(value==5){
	                        	desc="等待确认收货";
	                        }else if(value==6){
	                    		desc="交易成功";
	                    	}else{
	                    		desc="异常状态";
	                    	}
                        	if(_descrip){
                        		desc = '<div id="dcrip" class="easyui-panel easyui-tooltip" title="'+_descrip+'" style="border:1px solid #F60;width:100px;padding:5px">'+desc+'</div>'
                        	}
                       	 return desc;   
                        } }
                    ]],
               toolbar:[{text:"修改订单状态",iconCls:"icon-edit",handler:function(){
            	   var rows=$("#dg").datagrid('getSelections');  
                   
                   if(rows.length<=0)  
                   {  
                       $.messager.alert('警告','您没有选择','error');  
                   }else if(rows.length==1){
                	   $('#dlg').dialog('open').dialog('setTitle','修改订单状态');
                   		$('#fm').form('load',rows[0]);
                   } else{
                	   $.messager.alert('警告','您选择了多个','error');  
                   }
                   
            	  
               }}],
              detailFormatter:function(index,row){
                  return '<div style="padding:2px"><table class="ddv"></table></div>';
              },
              onExpandRow: function(index,row){
                  var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
                  ddv.datagrid({
                      data:_json[index].items,
                      fitColumns:true,
                      singleSelect:true,
                      rownumbers:true,
                      loadMsg:'加载中，请稍候',
                      height:'auto',
// 					  rowspan:false,
                      columns:[[
                          {field:'backup',title:'商品',width:120,align:'center',formatter:function(value,rec){  
                         	 return value.name;   
                          } },
                          {field:'img',title:'图片',width:200,align:'center',formatter:function(value,rec){  
                          	 return '<img src="../'+value+'">';   
                           } },
                          {field:'sku',title:'sku',width:100,align:'center'},
                          {field:'price',title:'价格',width:100,align:'center'},
                          {field:'num',title:'购买数量',width:100,align:'center'}
                      ]],
                      onResize:function(){
                          $('#dg').datagrid('fixDetailRowHeight',index);
                      },
                      onLoadSuccess:function(){
                          setTimeout(function(){
                              $('#dg').datagrid('fixDetailRowHeight',index);
                          },0);
                      }
                  });
                  $('#dg').datagrid('fixDetailRowHeight',index);
              }
          });
        	 });

        });
    </script>
</body>
</html>