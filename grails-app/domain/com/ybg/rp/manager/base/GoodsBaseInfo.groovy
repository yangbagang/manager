package com.ybg.rp.manager.base

class GoodsBaseInfo {

    static constraints = {
        name()
        brand()
        specifications()
        basePrice()
        picId nullable: true
    }

    String name
    String brand
    String specifications
    Float basePrice
    String picId
}
