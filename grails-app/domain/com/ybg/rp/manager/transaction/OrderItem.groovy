package com.ybg.rp.manager.transaction

import com.ybg.rp.manager.themeStore.ThemeStoreGoodsInfo

class OrderItem {

    static belongsTo = [order: OrderInfo]

    static constraints = {
    }

    String openid
    ThemeStoreGoodsInfo goodsInfo
    Integer num
}
