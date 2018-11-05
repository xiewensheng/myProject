/**
 * @author chenchu
 * @description check the component should be deactivated or not
 */
define(["views/hierarchy"], function(Hierarchy) {
    return {
        route: {
            canDeactivate: function(transition){
            	var route = JSON.parse(sessionStorage.getItem("route"))
                var fromLink = transition.from.path.split("/").slice(-1)[0]
                var toLink = transition.to.path.split("/").slice(-1)[0]
                
                var toRoute = Hierarchy.depthOrderTraverse(route, function(item){
                    return item.link.substr(1) === toLink
                })
                if(toRoute.children){
                    var links = toRoute.children.map(function(item){return item.link.substr(1)})
                    if(~links.indexOf(fromLink)){
                        console.log("deactivate fail", transition.from.path, transition.to.path)
                        transition.abort()
                    }else{
                        console.log("deactivate success", transition.from.path, transition.to.path)
                        transition.next()
                    }
                }else{
                    console.log("deactivate success for without children", transition.from.path, transition.to.path)
                    transition.next()
                }
            }
        }
    }
})