package com.ybg.rp.manager.base

class GoodsTypeOne {

    static hasMany = [children: GoodsTypeTwo]

    static constraints = {

    }

    String name
    Short status
}
