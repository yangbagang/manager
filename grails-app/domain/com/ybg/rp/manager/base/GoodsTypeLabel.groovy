package com.ybg.rp.manager.base

class GoodsTypeLabel {

    static hasMany = [goodsInfos: GoodsLabelInfo]

    static constraints = {

    }

    String label
    Short status

}
