package com.ybg.rp.manager.base

class GoodsTypeTwo {

    static belongsTo = [typeOne: GoodsTypeOne]

    static constraints = {
        name()
        status()
    }

    String name
    Short status
}
