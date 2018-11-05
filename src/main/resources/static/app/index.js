require.config({
	paths:{
		vendors:'vendors',
		
		filters:'filters',
		css:'vendors/r/css',
		text:'vendors/r/text',
		jquery:'vendors/jquery/jquery-1.11.1.min',
		"bootstrap": 'vendors/bootstrap/js/bootstrap.min',
		vue:"vendors/vue/vue.min",
		"vue-router": "vendors/vue-router/vue-router",
		"elements":"https://unpkg.com/element-ui/lib/index",
		components:"components",
		lodash:"vendors/lodash/lodash.min",
		highcharts:"vendors/highcharts/highcharts",

		datetimepicker:'vendors/bootstrap-datetimepicker/bootstrap-datetimepicker',
		ztree:'vendors/ztree/jquery.ztree.all.min',
	    "bootstrap-dialog":"vendors/bootstrap-dialog/bootstrap-dialog.min",
	    'bootstrap-table':'vendors/bootstrap-table/bootstrap-table.min',
	    'bootstrap-validator': 'vendors/bootstrap-validator/bootstrapValidator.min',
	    "config": 'js/config'
	}
});
require(["vue-router","js/hierarchy","filters/filter"],
		function(VueRouter,Hierarchy,filter){
	Vue.config.devtools = true;
	Vue.use(VueRouter);
	
	var toComponent = function(item){
        var componentPath = item.component,
        COMPONENT_TYPE = "100",
        IFRAME_TYPE    = "200",
        WINDOW_TYPE    = "300";
        if (item.component && item.type === COMPONENT_TYPE) {
            return {
                component: function (resolve) {
                    require([componentPath], resolve);
                }
            };
        } else if (item.component && item.type === IFRAME_TYPE) {
            return {
                component: {
                    template: "<div style='overflow: hidden;height:100%;'><iframe width='100%' height='100%' frameBorder='0' src='" + componentPath + "'></iframe></div>"
                }
            };
        } else if(item.component && item.type === WINDOW_TYPE){
            return {
                component: {
                    template: componentPath
                }
            };
        } else {
            return {
                component: {
                    template: "<div>Unknown type or not set component property</div>"
                }
            };
        };
    };
    
    $.getJSON("app/config.json").then(function (json) {
	    var route;
	    if (json) {
	        $.each(json,function(i,v){
				v.component = toComponent(v).component;
	        });
	        var hierarchyArray = Hierarchy.toHierarchy(json);
	    }
	    return hierarchyArray;
	}).then(function (routeMap) {
        if (!routeMap) {
            console.log("routeMap is undefined");
            return;
        }
        var router = new VueRouter({
            routes:routeMap
        });
        new Vue({
    		el: '#app',
    		template: '<router-view></router-view>',
    		router:router
      	});
    })
    
});