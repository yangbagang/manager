package com.ybg.rp.manager.base

class GoodsLabelInfo {

    static belongsTo = [goodsBaseInfo: GoodsBaseInfo, goodsLabel: GoodsTypeLabel]

    static constraints = {
    }
}
