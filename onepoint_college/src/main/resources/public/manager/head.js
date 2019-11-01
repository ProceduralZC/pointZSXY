var theme = "customize";
var pathName=window.document.location.pathname;  
var contextPath=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 

document.write("<link id=\"themes\" rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/manager/jquery/themes/"+theme+"/easyui.css\" />");
document.write("<link id=\"themes\" rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/manager/jquery/themes/"+theme+"/icon.css\" />");
document.write("<link id=\"themes\" rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/manager/jquery/themes/"+theme+"/icons.css\" />");
document.write("<script type=\"text/javascript\" src=\""+contextPath+"/manager/jquery/jquery-1.8.0.min.js\"></script>");
document.write("<script type=\"text/javascript\" src=\""+contextPath+"/manager/jquery/jquery.easyui.min.js\"></script>");
document.write("<script type=\"text/javascript\" src=\""+contextPath+"/manager/jquery/easyui-lang-zh_CN.js\"></script>");
document.write("<script type=\"text/javascript\" src=\""+contextPath+"/manager/jquery/treegrid-dnd.js\"></script>");
document.write("<script type=\"text/javascript\" src=\""+contextPath+"/manager/jquery/ajaxfileupload.js\"></script>");

document.write("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/manager/css/"+theme+"/css.css\" />");
document.write("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/manager/css/"+theme+"/login.css\" />");
document.write("<link rel=\"stylesheet\" type=\"text/css\" href=\""+contextPath+"/manager/css/"+theme+"/form.css\" />");

document.write("<script type=\"text/javascript\" src=\""+contextPath+"/manager/script/myScript.js\"></script>");
document.write("<script type=\"text/javascript\" src=\""+contextPath+"/manager/script/myPlugin.js\"></script>");

document.write("<script type=\"text/javascript\" src=\""+contextPath+"/manager/script/calendar/WdatePicker.js\"></script>");
document.write("<script type=\"text/javascript\" src=\""+contextPath+"/manager/jquery/mess_alert/message_alert.js\"></script>");

document.write("<link rel=\"shortcut Icon\" href=\""+contextPath+"/manager/images/logo.ico\" type=\"image/x-icon\" />");
document.write("<link rel=\"bookmark \" href=\""+contextPath+"/manager/images/logo.ico\"  type=\"image/x-icon\" />");