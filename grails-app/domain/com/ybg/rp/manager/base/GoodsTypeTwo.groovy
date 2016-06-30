package com.ybg.rp.manager.base

class GoodsTypeTwo {

    static belongsTo = [typeOne: GoodsTypeOne]

    static constraints = {

    }

    String name
    Short status
}
