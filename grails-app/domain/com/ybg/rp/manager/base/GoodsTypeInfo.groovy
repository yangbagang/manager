package com.ybg.rp.manager.base

class GoodsTypeInfo {

    static belongsTo = [goodsBaseInfo: GoodsBaseInfo, goodsTypeTwo: GoodsTypeTwo]

    static constraints = {
    }

    transient String goodsName
    transient String typeOneName
    transient String typeTwoName

    String getTypeOneName() {
        goodsTypeTwo?.typeOne?.name
    }

    String getTypeTwoName() {
        goodsTypeTwo?.name
    }

    String getGoodsName() {
        goodsBaseInfo?.name
    }
}
