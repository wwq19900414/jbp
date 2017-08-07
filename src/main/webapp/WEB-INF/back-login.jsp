<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
    <head>
    	<title>JBP用户行为数据接口后台管理登陆</title>
    </head>
    <body>
    <form action="admin.action" method="post">
    <table>
	    <tr>
		    <td>后台管理员操作码</td>
		    <td><input type="password" name="operateCode"></td>
	    </tr>
	    <tr>
	    	<td colspan="2"><input type="submit" value="进入管理界面"></td>
	    </tr>
    </table>
    </form>
    
    </body>
</html>