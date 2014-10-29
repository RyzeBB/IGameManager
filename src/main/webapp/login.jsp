<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台登录管理系统</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js" language="javascript" type="text/javascript"></script>
<script type="text/javascript" src="fancybox/jquery.fancybox-1.3.4.js"></script>  
<link rel="stylesheet" type="text/css" href="fancybox/jquery.fancybox-1.3.4.css" /> 
<script type="text/javascript">
// function changeAuthCode() {
// 	var num = 	new Date().getTime();
// 	var rand = Math.round(Math.random() * 10000);
// 	num = num + rand;
// 	$('#ver_code').css('visibility','visible');
// 	if ($("#vdimgck")[0]) {
// 		$("#vdimgck")[0].src = "../include/vdimgck.php?tag=" + num;
// 	}
// 	return false;	
// }
</script>
 <script type="text/javascript">
	 function someStr(){
		 var now=(new Date()).getHours();
			if(now>0&&now<=6){
				document.write("午夜好，");
			}else if(now>6&&now<=11){
				document.write("早上好，");
			}else if(now>11&&now<=14){
				document.write("中午好，");
			}else if(now>14&&now<=18){
				document.write("下午好，");
			}else{
				document.write("晚上好，");
			}
	 }
	 <c:if test="${not empty param.error}">  
	 $(document).ready(function() {
		    $.fancybox("登录失败<br /> <br/> ${SPRING_SECURITY_LAST_EXCEPTION.message}");
		});   
  </c:if>  
	     	
</script>
<style type="text/css">
<!--
body {
	background-color: #D9DDE0;
}
-->
</style></head>
<body>
<center>
<!--wrap-->
<div class="wrap">
  <!-- 头部 -->
  <div class="ln_top">
    <h1 class="ln_logo">
      <script type="text/javascript">
           	var now=(new Date()).getHours();
			if(now>0&&now<=6){
				document.write("午夜好，");
			}else if(now>6&&now<=11){
				document.write("早上好，");
			}else if(now>11&&now<=14){
				document.write("中午好，");
			}else if(now>14&&now<=18){
				document.write("下午好，");
			}else{
				document.write("晚上好，");
			}
			</script>欢迎登后台管理系统
    </h1>
    <ul class="ln_top_nav">
        <li><a href="javascript:void(0);"> 网站主页</a>/</li>
        <li><a href="javascript:void(0);">联系客服</a>/</li>
        <li><a href="javascript:void(0);" title="社区">社区</a></li>
    </ul>
  </div>
  <!-- 头部 end -->
  <!--ln_main-->
  <div class="ln_main">
    <p id="error_info" class="ln_error" style="display:none;"></p>
    <form id="form1" name="form1" method="post" action="login.action">
      <input type="hidden" name="gotopage" value=""/>
      <input type="hidden" name="dopost" value="login" />
      <input name='adminstyle' type='hidden' value='newdedecms' />
    <table border="0" cellspacing="0" cellpadding="0" class="ln_login" id="login_area">
      <tbody>
        <tr>
          <td class="in_login_de">帐号:</td>
          <td><input type="text" id="username" name="username" class="ln_text co_b6" value="请在此输入用户名/账号" onFocus="if(this.value=='请在此输入用户名/账号'){this.value='';}" onBlur="if(this.value==''){this.value='请在此输入用户名/账号';}"/>
          </td>
        </tr>
        <tr>
          <th>密码:</th>
          <td><input type="password" name="password" class="ln_text"/></td>
        </tr>
        <tr>
          <th>验证码:</th>
          <td><input type="text" maxlength="4" name="code" id="code"  
       class="input" style="width: 80px;"/> <img id="validateCode"  
       src="image.jsp" width="75" height="25" onclick="changeCode(this)"/>
            <p >看不清可点击图片切换验证码！</p>
          </td>
        </tr>
        <tr>  
                    <td align="right"><input id="_spring_security_remember_me"  
                        name="_spring_security_remember_me" type="checkbox" value="true" />  
  
                    </td>  
                    <td><label for="_spring_security_remember_me">记住我?</label>  
                    </td>  
                </tr>  
        <tr>
          <th> </th>
          <td><input type="submit"  value="" name="sm1" class="ln_btn_login" onClick="this.form.submit();" /></td>
        </tr>
        <tr>
          <th> </th>
          <td><a id="findpwd_link" href="javascript:void(0);" title="忘记密码">忘记密码</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
                                id="register_link" href="javascript:void(0);" title="免费注册">免费注册</a> </td>
        </tr>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="2">
            <div style="margin-top:25px; color:#666;"><script type="text/javascript">
           	var now=(new Date()).getHours();
			if(now>0&&now<=6){
				document.write("午夜好，");
			}else if(now>6&&now<=11){
				document.write("早上好，");
			}else if(now>11&&now<=14){
				document.write("中午好，");
			}else if(now>14&&now<=18){
				document.write("下午好，");
			}else{
				document.write("晚上好，");
			}
			</script>
          您也可以继续访问您的  <a href="javascript:void(0);">网站主页</a></div>
          </td>
        </tr>
      </tfoot>
    </table>
    </form>
  </div>
</div>
<!--wrap end-->
<!-- 底部 -->
<div class="ln_footer">
  <div class="ln_footer_content">友情连接 </div>
  <div class="ln_footer_content">Copyright ? 2011- 2011 Allen. All Rights Reserved</div>
</div>
<!-- 底部 end -->
</center>
 <script type="text/javascript">  
       function changeCode(item){
    	   $(item).hide().attr('src','image.jsp?'+ Math.floor(Math.random() * 100)).fadeIn();  
       }
    </script>  
</body>
</html>
