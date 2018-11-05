define(["sortable"], function(Sortable){
    return {
        bind: function(options){
            options = options || {}

            var sortable = new Sortable(this.el, options)

            if (this.arg && !this.vm.sortable) {
                this.vm.sortable = {}
            }

            //  Throw an error if the given ID is not unique
            if (this.arg && this.vm.sortable[this.arg]) {
                console.warn('[vue-sortable] cannot set already defined sortable id: \'' + this.arg + '\'')
            } else if( this.arg ) {
                this.vm.sortable[this.arg] = sortable
            }
        }
    }
})