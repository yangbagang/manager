package com.ybg.rp.manager.base

class GoodsBaseInfo {

    static constraints = {
        picId nullable: true
    }

    String name
    String brand
    String specifications
    Float basePrice
    String picId
}
