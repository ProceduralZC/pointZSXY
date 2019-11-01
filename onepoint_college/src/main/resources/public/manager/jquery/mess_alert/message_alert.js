var js = document.scripts;
var jsPath;
for(var i = js.length; i > 0; i--) {
	if (js[i - 1].src.indexOf("message_alert.js") > -1) {
		jsPath = js[i - 1].src.substring(0, js[i - 1].src.lastIndexOf("/") + 1);
	}
}
document.write("<link id='mess_css' rel='stylesheet' type='text/css' href='"+jsPath+"/message_alert.css' />");

function createMessage(){
	$('.messager_tip').remove();
	$('.messager').remove();
	$(document.body).append('<div class="messager_tip"></div>');
	var messHtml = "<div class='messager'>"+
						"<div class='title'>"+
							"<span></span>"+
							"<a href='javascript:void(0);' title='关闭' onclick='closeMessage();'>&times;</a>"+
						"</div>"+
						"<div class='body'><span></span></div>"+
						"<div class='button'>"+
							"<a class='left_button' href='javascript:void(0);' onclick='okMessage();'>确定</a>"+
						"</div>"+
					"</div>";
	$(document.body).append(messHtml);
}
function closeMessage(){
	$(".messager").addClass("messager_remove");
	window.setTimeout(function(){
		$('.messager_tip').remove();
		$('.messager').remove();
	},300);
}
//弹出框 type={waring,info,error,question}
var myFunction;
function messager_alert(title,text,type,func){
	myFunction = func;
	if(type!=null && typeof type=="function"){
		myFunction = type;
	}
	createMessage();
	$('.messager .title span').text(title);
	if(type!=null && typeof type!="function"){
		$('.messager .title span').addClass(type);
	}else{
		$('.messager .title span').addClass("waring");
	}
	$('.messager .body span').html(text);
}
function messager_confirm(title,text,type,func){
	myFunction = func;
	if(type!=null && typeof type=="function"){
		myFunction = type;
	}
	createMessage();
	$('.messager .title span').text(title);
	if(type!=null && typeof type!="function"){
		$('.messager .title span').addClass(type);
	}else{
		$('.messager .title span').addClass("waring");
	}
	$('.messager .body span').html(text);
	$('.messager .button').append("<a href='javascript:void(0);' onclick='closeMessage();'>取消</a>");
	
}
function okMessage(){
	$('.messager_tip').remove();
	$('.messager').remove();
	if(myFunction!=null && typeof myFunction == 'function'){
		myFunction();
	}
}







