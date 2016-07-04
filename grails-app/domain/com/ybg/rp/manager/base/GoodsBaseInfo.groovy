package com.ybg.rp.manager.base

class GoodsBaseInfo {

    static hasMany = [labels: GoodsLabelInfo, types: GoodsTypeInfo]

    static constraints = {
        picId nullable: true
    }

    String name
    String brand
    String specifications
    Float basePrice
    String picId

    String toString() {
        return "${brand}-${name}-${specifications}"
    }
}
