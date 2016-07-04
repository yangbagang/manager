package com.ybg.rp.manager.base

class GoodsTypeInfo {

    static belongsTo = [goodsBaseInfo: GoodsBaseInfo, goodsTypeTwo: GoodsTypeTwo]

    static constraints = {
    }

}
