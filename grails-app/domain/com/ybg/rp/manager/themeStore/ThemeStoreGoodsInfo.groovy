package com.ybg.rp.manager.themeStore

class ThemeStoreGoodsInfo {

    static belongsTo = [themeStore: ThemeStoreBaseInfo]

    static constraints = {
        picId nullable: true
        px nullable: true
    }

    Long baseId
    String name
    String brand
    String specifications
    Float basePrice
    Float realPrice
    String picId
    Short status
    Integer px
    String letter
    Short yhEnable = 0//是否可以有优惠
}
