/**
 * @author: chenchu
 * @description: datetimepicker directive
 */
define(["datetimepicker"], function(){
    return {
        params: ["format", "start", "end", "minView", "startView","viewSelect"],
        paramWatchers: {
            start: function(val){
                $(this.el).datetimepicker("setStartDate", new Date(val))
            },
            end: function(val){
                $(this.el).datetimepicker("setEndDate", new Date(val))
            }
        },
        bind: function(el,binding,vnode){
            var vm = vnode.context;
            var key = binding.expression;
            var params = vnode.data.attrs;
            var _minview = params.minView ? params.minView : (params.format ? (~params.format.indexOf("h") ? 0 : 2) : 2);
            $(el).datetimepicker({
                format: params.format || "yyyy-mm-dd",
                startDate: params.start ? new Date(params.start) : null,
                endDate: params.end ? new Date(params.end) : null,
                startView: params.startView ? params.startView : 2,
                viewSelect:params.viewSelect ? params.viewSelect : _minview,
                minView: _minview,
                autoclose: true,
                todayBtn: true,
                language: "zh-CN"
            }).on("changeDate", function(event) {
                vm.$set(vm,key, event.date.getTime() - 8 * 3600 * 1000)
            })
                el.readOnly = true
        },
        update: function(el,vnode){
            $(el).datetimepicker("setDate", new Date(vnode.value))
        },
        unbind: function(el){
            $(el).datetimepicker("remove")
        }
    }
})