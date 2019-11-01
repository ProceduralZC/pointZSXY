//皮肤 customize,default,themes1
var theme = "customize";
//项目路径
var pathName=window.document.location.pathname;  
var path=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 

function progress(){
	$.messager.progress({
		title:'请等待',
		msg:'正在加载...'
	});
}

function loadingDiv(){
	if($('#loading1').html()==undefined){
		var height = $(document).height();
		var load = "<div id='loading1' style='display:none;position:absolute;left:0;top:0;width:100%;height:"+height+"px;background:#E8E8E8;opacity:0.5;filter:alpha(opacity=50);z-index:10000;'></div>";
		load += "<div id='loading2' class='panel-loading' style='display:none;position:fixed;top:38%;left:41%;border:2px #95B8E7 solid;width:160px;font-size: 14px;z-index:10001;background:url("+path+"/manager/jquery/themes/"+theme+"/images/loading-1.gif) no-repeat 10px 8px #FFFFFF;'>正在处理，请稍等...</div>";
		$(document.body).append(load);
	}
	$('#loading1').show();
	$('#loading2').show();
}
function loadingClose(){
	$('#loading1').hide();
	$('#loading2').hide();
}
$(document).ajaxStart(function(){
	//if($('#list_data').html()==undefined && $('#loginBut').html()==undefined){
		loadingDiv();
	//}
});
$(document).ajaxComplete(function(event, jqXHR, options){
	loadingClose();
});

//查询拼接参数
function searchGo(url,searchId,dataGrid){
	var str = '{';
	var inputs = $(searchId).find('input');
	var checkName = "";
	inputs.each(function(){
		if($(this).attr('type')=='checkbox' || $(this).attr('type')=='radio'){
			if(checkName != $(this).attr('name')){
				var val = "";
	   			$(searchId).find("input[name='"+$(this).attr('name')+"']:checked").each(function(){    
	   				val += $.trim($(this).val())+",";    
	  			}); 
	  			if(val!=""){
    				val = val.substr(0,val.length-1);
    			}
    			str += '"'+$(this).attr('name')+'":"'+val+'",';
  			}
  			checkName = $(this).attr('name');
		}else{
			str += '"'+$(this).attr('name')+'":"'+$.trim($(this).val())+'",';
		}
	});
	var selects = $(searchId).find('select');
	selects.each(function(){
		str += '"'+$(this).attr('name')+'":"'+$.trim($(this).val())+'",';
	});
	if(str.charAt(str.length-1)==","){
		str = str.substr(0,str.length-1);
	}
	str += '}';
	var dataset =  $.parseJSON(str);//查询的json
	$(dataGrid).datagrid({
		pageNumber:1,
		queryParams:dataset,
		url:url
	});
}
//清空查询条件
function clearGo(url,searchId,dataGrid){
	$(searchId).find('select').each(function(){
		$(this).find('option').eq(0).attr('selected', 'true');
	});
	$(searchId).find('input').each(function(){
		if($(this).attr('type')=='checkbox' || $(this).attr('type')=='radio'){
			$(this).removeAttr("checked");
		}else{
			if($(this).attr('type')!='hidden'){
				$(this).val('');
			}
		}
	});
	searchGo(url,searchId,dataGrid);
}

//查询拼接参数
function updateSearchGo(url,searchId,dataGrid){
	var str = '{';
	var inputs = $(searchId).find('input');
	var checkName = "";
	inputs.each(function(){
		if($(this).attr('type')=='checkbox' || $(this).attr('type')=='radio'){
			if(checkName != $(this).attr('name')){
				var val = "";
	   			$(searchId).find("input[name='"+$(this).attr('name')+"']:checked").each(function(){    
	   				val += $.trim($(this).val())+",";    
	  			}); 
	  			if(val!=""){
    				val = val.substr(0,val.length-1);
    			}
    			str += '"'+$(this).attr('name')+'":"'+val+'",';
  			}
  			checkName = $(this).attr('name');
		}else{
			str += '"'+$(this).attr('name')+'":"'+$.trim($(this).val())+'",';
		}
	});
	var selects = $(searchId).find('select');
	selects.each(function(){
		str += '"'+$(this).attr('name')+'":"'+$.trim($(this).val())+'",';
	});
	if(str.charAt(str.length-1)==","){
		str = str.substr(0,str.length-1);
	}
	str += '}';
	var dataset =  $.parseJSON(str);//查询的json
	$(dataGrid).datagrid({
		queryParams:dataset,
		url:url
	});
}

//获取id
function getId(id,flag){
	var data;
	if(flag == "tree"){
		data = $(id).treegrid('getSelections');//获取选中的行
	}else if(flag == "data"){
		data = $(id).datagrid('getChecked');//获取选中的行
	}
	if(data=="" || data.length<1){
		$.messager.alert('提示','请选择!','warning');
		return "";
	}
	if(data.length>1){
		$.messager.alert('提示','只能选中一条记录!','warning');
		return "";
	}
	if(data[0]['id']){
		return data[0]['id'];
	}else{
		return data[0][0];
	}
}
//获取ids
function getIds(id,flag){
	var ids = "";
	var data;
	if(flag == "tree"){
		data = $(id).treegrid('getSelections');//获取选中的行
	}else if(flag == "data"){
		data = $(id).datagrid('getChecked');//获取选中的行
	}
	if(data=="" || data.length<1){
		$.messager.alert('提示','请选择!','warning');
		return "";
	}
	for(i in data){
		if(data[i]['id']){
			ids += data[i]['id']+",";
		}else{
			ids += data[i][0]+",";
		}
	}
	if(ids.charAt(ids.length-1)==","){
		ids = ids.substr(0,ids.length-1);
	}
	return ids;
}
function onLoadScroll(data){
	if(null!=data && 0==data.totalElements){
		$(this).prev(".datagrid-view2").children(".datagrid-body").html("<div style='width:" +  $(this).prev(".datagrid-view2").find(".datagrid-header-row").width() + "px;border:solid 0px;height:30px;text-align:center;color:gray;'>没有相关记录！</div>");
	}
}



//监听浏览器宽度变化，调整datagrid尺寸和布局
$(window).resize(function(){
	$('#list_data').datagrid('resize');
	$('#list_tree').treegrid('resize');
});
//获取参数
function getPram(parm) {
	var value = null;
	var url = location.search; //获取url中"?"符后的字串
	if (url.indexOf("?") >-1) {
		var str = url.substr(1);
		strs = str.split("&");
		for ( var i = 0; i < strs.length; i++) {
			if(strs[i].split("=")[0]==parm){
				value = strs[i].split("=")[1];
			}
		}
	}
	return $.trim(value);
}
/*
获取一系列的日期
*/
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

//验证邮箱
function  checkEmail(s){
	if(s==null || s=="undefined"){
		 return false;
	}
	s = s.replace(/\s+/g, ""); //替换所有空格！
	if( s==""){
		return false;
	}
	if (!s.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
		return false;
	}
	return true;
}

function checkMobile(s){
	if(s==null || s=="undefined"){
		 return false;
	}
	s = s.replace(/\s+/g, ""); //替换所有空格！
	if(s==""){
		return false;
	}
	
	var myreg =/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[7]{1})|(14[5,7]{1}))+\d{8})$/;
	if(!myreg.test(s)) { 
        return false; 
    } 
    return true ;
}


//是否为数字
function isDigit(s) { 
	if(s==null || s=="undefined"){
		 return false;
	}
	s = s.replace(/\s+/g, ""); //替换所有空格！
	if( s==""){
		return false;
	}
	var patrn=/^[0-9]{1,20}$/; 
	if (!patrn.test(s)){
		return false;
	}
	return true ;
}

//数字和字母
function isDigitOrLetter(s) {
	if(s==null || s=="undefined"){
		 return false;
	}
	s = s.replace(/\s+/g, ""); //替换所有空格！
	if(s==""){
		return false;
	}
	var patrn=/^[0-9a-zA-Z]+$/; 
	if (!patrn.test(s)){
		return false;
	}
	return true ;
}

//校验密码合法性
function isPassword(s) {
	if(s==null || s=="undefined"){
		 return false;
	}
	s = s.replace(/\s+/g, ""); //替换所有空格！
	if(s==""){
		return false;
	}
	var patrn=/[a-zA-Z0-9]{6,16}/; 
	if (!patrn.test(s)){
		return false;
	}
	if(s.length<6||s.length>16){
		return false;
	}
	return true ;
}

function isChinese(s){
	if(s==null || s=="undefined"){
		 return false;
	}
	s = s.replace(/\s+/g, ""); //替换所有空格！
	if( s==""){
		return false;
	}
	 if(s.length>10){
		 return false;
	 }
	 var myReg = /^[\u4e00-\u9fa5]+$/;
     if (!myReg.test(s)) {
    	 return false;
     }
     return true ;
}

Date.prototype.format = function(format) {
	var args = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter

		"S" : this.getMilliseconds()
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var i in args) {
		var n = args[i];
		if (new RegExp("(" + i + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n
					: ("00" + n).substr(("" + n).length));
	}
	return format;
};