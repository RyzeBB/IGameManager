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
                        {field:'price',title:'价格',width:40},
                        {field:'coupon',title:'优惠卷',width:40},
                        {field:'addr',title:'收货地址',width:220},
                        {field:'create_time',title:'下单时间',width:100},
                        {field:'state',title:'状态',width:60,formatter:function(value,rec,index){  
                        	desc =null;
                        	if(value==0){
                        		desc="等待发货"+index
                        	}
                       	 return desc;   
                        } }
                    ]],
              detailFormatter:function(index,row){
                  return '<div style="padding:2px"><table class="ddv"></table></div>';
              },
              onExpandRow: function(index,row){
                  var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
                  	console.info(_json);
                  	console.info(index);
                	console.info(_json[index].items);
                  ddv.datagrid({
                      data:_json[index].items,
                      fitColumns:true,
                      singleSelect:true,
                      rownumbers:true,
                      loadMsg:'加载中，请稍候',
                      height:'auto',
// 					  rowspan:false,
                      columns:[[
                          {field:'backup',title:'商品',width:200,formatter:function(value,rec){  
                         	 return value.name;   
                          } },
                          {field:'img',title:'图片',width:200,height:200,formatter:function(value,rec){  
                          	 return '<img src="../'+value+'">';   
                           } },
                          {field:'sku',title:'sku',width:100,align:'right'},
                          {field:'price',title:'价格',width:100,align:'right'},
                          {field:'num',title:'数量',width:100,align:'right'}
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