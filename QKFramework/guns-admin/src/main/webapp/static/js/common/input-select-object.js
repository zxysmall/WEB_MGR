/**
 * 输入框，下拉动态数据封装
 */
var tergetSelectId;
(function() {
	var InputSelect = function (_id,dataUrl){  
		tergetSelectId = _id;
		this.dataUrl = dataUrl;
		this.data = {};
		this.onChange = null;
	};
	
	InputSelect.prototype = {
		/**
		 * 初始化select
		 */
		init : function() {
			 var ajax = new $ax(Feng.ctxPath + this.dataUrl, function (data) {
	            var dataList = data.dataList;  
	            $("#"+tergetSelectId).empty();  
	            if(dataList && dataList.length != 0){  
	                for(var i=0; i<dataList.length; i++){  
	                    var option="<option value=\""+dataList[i].key+"\"";  
	                    if(dataList[i].selected){  
	                        option += " selected=\"selected\" "; //默认选中  
	                    }  
	                    option += ">"+dataList[i].value+"</option>";  //动态添加数据  
	                    $("#"+tergetSelectId).append(option);  
	                }  
	            }  
			 });
			 ajax.setData(this.data);
			 ajax.start();
			 if(null != this.onChange){
				 this.onChange();
			 }
		},
		
		set : function (key, value) {
			if (typeof key == "object") {
				for (var i in key) {
					if (typeof i == "function")
						continue;
					this.data[i] = key[i];
				}
			} else {
				this.data[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
			}
			return this;
		},
		
		bindOnChange : function(func){
			this.onChange = func;
		}
	};
	
	window.InputSelect = InputSelect;
	
} ());