package com.ybg.rp.manager.base

class GoodsTypeInfo {

    static belongsTo = [goodsBaseInfo: GoodsBaseInfo, goodsTypeOne: GoodsTypeOne]

    static constraints = {
    }

}
