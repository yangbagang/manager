package com.ybg.rp.manager.transaction

import com.ybg.rp.manager.themeStore.ThemeStoreGoodsInfo

class ShoppingCart {

    static constraints = {
    }

    //微信购物车
    String openid
    ThemeStoreGoodsInfo goodsInfo
    Integer num
}
