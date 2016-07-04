package com.ybg.rp.manager.transaction

import com.ybg.rp.manager.device.VendLayerTrackGoods

class OrderDetail {

    static belongsTo = [order: OrderInfo]

    static constraints = {
    }

    VendLayerTrackGoods goods
    Integer goodsNum
    Float goodsPrice
    Float discount
    Float buyPrice
    String goodsName
    String goofsSpec
    String goodsPic
    String orbitalNo
    Integer status//0 未取货 1 申请退款 2 已退款 3取货
    Short errorStatus//异常状态:0-正常 1-异常 2-已处理  默认0
    Double refundPrice//退款金额
}