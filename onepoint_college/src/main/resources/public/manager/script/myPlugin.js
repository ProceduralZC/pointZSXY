/* ==========================弹出框部分========================================== */
function openWindow(url){
	var win = '<div id="windowDiv" style="background:#ffffff;width:100%;height:auto;position: absolute;top:0px;left:0px;">';
	win += '<iframe style="width:100%;height:'+($(document).height())+'px;" scrolling="auto" frameborder="0" src="'+url+'" />';
	win += '</div>';
	$(document.body).append(win); 
}
function closeWindow(){
	$("#windowDiv",window.parent.document).remove();
}

/* ==========================表单自动填充值========================================== */
function FormView(formId,formUrl){ 
	this.formId = formId; 
	this.formUrl = formUrl;
	this.doLoad = function(callback){
		$.ajax({
			type : 'GET',
			url : formUrl,
			data : {},
			dataType : "json",
			success : function(data) {
				loadForm(formId,data);
				if(typeof callback == 'function'){
					callback(data);
				}
			},
			error : function(response) {
				alert("出错了，请联系管理员!");
			}
		});
	}
}
function loadForm(form,data){
	$.parser.parse($(form));
	for(var i in data){
		var valT=null;
		if(data[i]!=null){
			valT = data[i].toString();
		}else{
			continue;
		}
		var tagName = $("*[name="+i+"]").prop("tagName");
		if(tagName != undefined){
			if(tagName.toUpperCase()=='INPUT'){
				$(form).find("*[name="+i+"]").each(function(){
					if($(this).attr('type')!=undefined && $(this).attr('type').toUpperCase()=='RADIO'){
						if($(this).val() == valT){
							$(this).attr('checked',true);
						}
					}else if($(this).attr('type')!=undefined && $(this).attr('type').toUpperCase()=='CHECKBOX'){
						if(valT!=null){
							var vals = valT.split(',');
							for(i=0;i<vals.length;i++){
								if($(this).val() == vals[i]){
									$(this).attr('checked',true);
								}
							}
							//$(this).attr('checked',true);
						}
					}else{
						$(this).val(valT);
					}
				});  
			}else if(tagName.toUpperCase()=='SELECT' || 
					tagName.toUpperCase()=='TEXTAREA' || tagName.toUpperCase()=='SELECT'){
				$(form).find("*[name="+i+"]").val(valT);
			}else{
				$(form).find("*[name="+i+"]").text(valT);
			}
		}
		var target = $("#"+i).attr('class');
		if(target != undefined){
			if(target.indexOf('easyui-numberbox')>=0){
				$("#"+i).numberbox('setValue',valT);
			}
			if(target.indexOf('easyui-combobox')>=0){
				$("#"+i).combobox('setValue',valT);
			}
			if(target.indexOf('easyui-numberspinner')>=0){
				$("#"+i).numberspinner('setValue',valT);
			}
		}
		
	}
}