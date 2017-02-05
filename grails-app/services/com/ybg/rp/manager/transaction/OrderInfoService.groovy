package com.ybg.rp.manager.transaction

import com.ybg.rp.manager.device.VendLayerTrackGoods
import grails.transaction.Transactional

import java.text.SimpleDateFormat

@Transactional
class OrderInfoService {

    def createOrder(Long vendLayerId, Short payWay) {
        createOrderInfo(vendLayerId, payWay)
    }

    private createOrderInfo(Long vendLayerId, Short payWay) {
        def vendLayerTrackGoods = VendLayerTrackGoods.get(vendLayerId)
        if (vendLayerTrackGoods) {
            if (vendLayerTrackGoods.currentInventory > 0) {
                //创建订单
                def orderInfo = new OrderInfo()
                orderInfo.vendMachine = vendLayerTrackGoods.vendMachine
                orderInfo.orderNo = createOrderNo()
                orderInfo.payStatus = 1 as Short//己支付
                orderInfo.orderWay = 0 as Short//线下购买
                orderInfo.deliveryStatus = 1 as Short//己出货
                orderInfo.payWay = payWay
                orderInfo.createTime = new Date()
                orderInfo.confirmTime = new Date()
                orderInfo.completeTime = new Date()
                orderInfo.orderMoney = vendLayerTrackGoods.goods.realPrice
                orderInfo.totalMoney = vendLayerTrackGoods.goods.realPrice
                orderInfo.realMoney = vendLayerTrackGoods.goods.realPrice
                orderInfo.youHuiJuan = orderInfo.totalMoney - orderInfo.realMoney
                orderInfo.transNo = ""
                orderInfo.save flush: true

                //创建订单详情
                def orderDetail = new OrderDetail()
                orderDetail.order = orderInfo
                orderDetail.goods = vendLayerTrackGoods
                orderDetail.goodsNum = 1
                orderDetail.goodsPrice = vendLayerTrackGoods.goods.realPrice
                orderDetail.discount = 0
                orderDetail.buyPrice = vendLayerTrackGoods.goods.realPrice
                orderDetail.goodsName = vendLayerTrackGoods.goods.name
                orderDetail.goodsSpec = vendLayerTrackGoods.goods.specifications
                orderDetail.goodsPic = vendLayerTrackGoods.goods.picId
                orderDetail.orbitalNo = vendLayerTrackGoods.orbitalNo
                orderDetail.status = 3 as Short
                orderDetail.errorStatus = 0 as Short
                orderDetail.refundPrice = 0
                orderDetail.save flush: true
            }
        }
    }

    static String createOrderNo() {
        def sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS")
        sdf.format(new Date())
    }
}
