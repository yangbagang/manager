package com.ybg.rp.manager.themeStore

import com.ybg.rp.manager.base.GoodsTypeOne
import com.ybg.rp.manager.base.GoodsTypeTwo

class ThemeStoreGoodsInfo {

    static belongsTo = [themeStore: ThemeStoreBaseInfo, typeOne: GoodsTypeOne, typeTwo: GoodsTypeTwo]

    static constraints = {
    }

    String name
    String brand
    String specifications
    Float basePrice
    Float realPrice
    String picId
    Short status
    Integer px
    String labels
}
