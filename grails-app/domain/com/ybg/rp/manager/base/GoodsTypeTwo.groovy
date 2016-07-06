package com.ybg.rp.manager.base

class GoodsTypeTwo {

    static belongsTo = [typeOne: GoodsTypeOne]

    static constraints = {

    }

    String name
    Short status

    transient String parentName

    String getParentName() {
        return this.typeOne?.name
    }
}
