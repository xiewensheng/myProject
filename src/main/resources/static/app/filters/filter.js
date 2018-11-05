define([], function(){
	//十六进制颜色值域RGB格式颜色值之间的相互转换

	//-------------------------------------
	//十六进制颜色值的正则表达式
	var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
	/*RGB颜色转换为16进制*/
	String.prototype.colorHex = function(){
		var that = this;
		if(/^(rgb|RGB)/.test(that)){
			var aColor = that.replace(/(?:\(|\)|rgb|RGB)*/g,"").split(",");
			var strHex = "#";
			for(var i=0; i<aColor.length; i++){
				var hex = Number(aColor[i]).toString(16);
				if(hex === "0"){
					hex += hex;	
				}
				strHex += hex;
			}
			if(strHex.length !== 7){
				strHex = that;	
			}
			return strHex;
		}else if(reg.test(that)){
			var aNum = that.replace(/#/,"").split("");
			if(aNum.length === 6){
				return that;	
			}else if(aNum.length === 3){
				var numHex = "#";
				for(var i=0; i<aNum.length; i+=1){
					numHex += (aNum[i]+aNum[i]);
				}
				return numHex;
			}
		}else{
			return that;	
		}
	};

	//-------------------------------------------------

	/*16进制颜色转为RGB格式*/
	String.prototype.colorRgb = function(){
		var sColor = this.toLowerCase();
		if(sColor && reg.test(sColor)){
			if(sColor.length === 4){
				var sColorNew = "#";
				for(var i=1; i<4; i+=1){
					sColorNew += sColor.slice(i,i+1).concat(sColor.slice(i,i+1));	
				}
				sColor = sColorNew;
			}
			//处理六位的颜色值
			var sColorChange = [];
			for(var i=1; i<7; i+=2){
				sColorChange.push(parseInt("0x"+sColor.slice(i,i+2)));	
			}
			return "RGB(" + sColorChange.join(",") + ")";
		}else{
			return sColor;	
		}
	};
    return {
        percentage: function(ratio, dijit){
            return (ratio * 100).toFixed(dijit) + "%"
        },
        tofixed: function(num, dijit){
            return Number(num).toFixed(dijit)
        },
        format: function(date, fmt){
            date = date instanceof Date ? date : new Date(date)
            var o = {
                "m+": date.getMonth() + 1, //月份 
                "d+": date.getDate(), //日 
                "h+": date.getHours(), //小时 
                "i+": date.getMinutes(), //分 
                "s+": date.getSeconds(), //秒 
                "q+": Math.floor((date.getMonth() + 3) / 3), //季度 
                "S": date.getMilliseconds() //毫秒 
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1,(date.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1,(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }
    }
})