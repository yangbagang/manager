package com.ybg.rp.manager.themeStore

import com.ybg.rp.manager.base.GoodsTypeOne

class ThemeStoreGoodsTypeOne {

    static belongsTo = [goodsInfo: ThemeStoreGoodsInfo, typeOne: GoodsTypeOne]

    static constraints = {
    }

    static ThemeStoreGoodsTypeOne createInstance(ThemeStoreGoodsInfo goodsInfo, GoodsTypeOne typeOne) {
        def instance = new ThemeStoreGoodsTypeOne(goodsInfo: goodsInfo, typeOne: typeOne)
        instance.save flush: true
        instance
    }
}
