/**
 * 重组treegrid的数据
 * @param treeId 要绑定的TreeGrid的id
 * @param parentColumn 数据中关联的父级对象的名称
 * @param data 数据集
 * @param ind 自定义的参数，可以为null  比如：iconCls属性对应数据库img值，可以有多个，中间用逗号隔开例如："iconCls-img,iconCls-img"
 * @returns {Array}
 */
function formatTreeGrid(treeId,parentColumn,data,ind){
	var opts = $(treeId).treegrid('getColumnFields');
	var treeData = new Array();
	for(var i in data){
		if(data[i][parentColumn]==null){
			var temp = {};
			for(var a in opts){
				var val = "";
				try{
					val = eval("data[i]['"+opts[a].replace(/\./g,"']['")+"']");
				}catch(e){
					val = "";
				}
				temp[opts[a]] = val;
			}
			if(ind!=null && ind!=""){
				var ind_ = ind.split(',');
				for(var k=0;k<ind_.length;k++){
					var val = "";
					try{
						val = eval("data[i]['"+ind_[k].split('-')[1].replace(/\./g,"']['")+"']");
					}catch(e){
						val = "";
					}
					temp[ind_[k].split('-')[0]] = val;
				}
			}
			temp['children'] = [];
			eval("var tree_grid_"+ data[i]['id'] +"="+JSON.stringify(temp).toString());
			treeData.push(eval("tree_grid_"+ data[i]['id']));
		}else{
			if(eval("var tree_grid_"+ data[i][parentColumn]['id'])==undefined){
				 var temp = {};
				 for(var a in opts){
					 var val = "";
					 try{
						val = eval("data[i]['"+opts[a].replace(/\./g,"']['")+"']");
					 }catch(e){
						val = "";
					 }
					 temp[opts[a]] = val;
				}
				temp['children'] = [];
				if(ind!=null && ind!=""){
					var ind_ = ind.split(',');
					for(var k=0;k<ind_.length;k++){
						var val = "";
						try{
							val = eval("data[i]['"+ind_[k].split('-')[1].replace(/\./g,"']['")+"']");
						}catch(e){
							val = "";
						}
						temp[ind_[k].split('-')[0]] = val;
					}
				}
				eval("var tree_grid_"+ data[i]['id'] +"="+JSON.stringify(temp).toString());
				eval("tree_grid_"+ data[i][parentColumn]['id']+".children.push(tree_grid_"+ data[i]['id']+")");  
			}
		}
		
	}
	
	return treeData;
}
function formatTree(parentColumn,data,ind){
	var treeData = new Array();
	for(var i in data){
		if(data[i][parentColumn]==null){
			var temp = {
				id:data[i]['id'],
				text:data[i]['text'],
				children:[]
			};
			if(ind!=null && ind!=""){
				var ind_ = ind.split(',');
				for(var k=0;k<ind_.length;k++){
					temp[ind_[k].split('-')[0]] = data[i][ind_[k].split('-')[1]];
				}
			}
			eval("var tree_"+ data[i]['id'] +"="+JSON.stringify(temp).toString());
			treeData.push(eval("tree_"+ data[i]['id']));
		}else{
			if(eval("var tree_"+ data[i][parentColumn]['id'])==undefined){
				var temp = {
					id:data[i]['id'],
					text:data[i]['text'],
					children:[]
				};
				if(ind!=null && ind!=""){
					var ind_ = ind.split(',');
					for(var k=0;k<ind_.length;k++){
						temp[ind_[k].split('-')[0]] = data[i][ind_[k].split('-')[1]];
					}
				}
				eval("var tree_"+ data[i]['id'] +"="+JSON.stringify(temp).toString());
				eval("tree_"+ data[i][parentColumn]['id']+".children.push(tree_"+ data[i]['id']+")");  
			}
		}
	}
	return $.parseJSON(JSON.stringify(treeData).toString());
}