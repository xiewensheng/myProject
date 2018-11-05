define(["text!./index.html","css!./index.css"],function(tpl){
	return {
		template: tpl,
        data: function(){
        	return {//        		定义变量
        		
			};
        },
        mounted : function(){//        	页面渲染之前触发
        	
        },
        methods:{//        	方法
			//退出登录
			logout: function (event) {
                event.preventDefault();
                window.location.replace("./login.html");
			},
            clickMenu:function(event,item){
				
			}
        }
	};
});