package com.ybg.rp.manager.analysis

import com.ybg.rp.manager.base.GoodsBaseInfo
import com.ybg.rp.manager.base.GoodsTypeInfo
import com.ybg.rp.manager.themeStore.ThemeStoreOfPartner
import com.ybg.rp.manager.transaction.OrderDetail
import com.ybg.rp.manager.transaction.TransactionInfo
import grails.transaction.Transactional

@Transactional
class DataAnalysisService {

    def analysis(Date from, Date to) {
        //查询数据
        def c = OrderDetail.createCriteria()
        def result = c.list {
            order {
                and {
                    if (from) {
                        gt("completeTime", from)//大于开始时间
                    }
                    if (to) {
                        lt("completeTime", to)//小于结束时间
                    }
                    eq("payStatus", 1 as Short)//己付款
                    eq("isCancel", 0 as Short)//未取消
                }
            }
        }
        //循环处理数据
        for (OrderDetail detail: result) {
            if (DataAnalysis.findByDetailId(detail.id)) {
                //己经计算过了,不再重复计算。
                continue
            }
            //准备数据
            def order = detail.order
            def tran = TransactionInfo.findByOrderNo(order.orderNo)
            def machine = order.vendMachine
            def store = machine.themeStore
            def partner = ThemeStoreOfPartner.findByBaseInfo(store).partner
            def storeGoods = detail.goods.goods
            def goods = GoodsBaseInfo.get(storeGoods.baseId)
            def typeTwo = GoodsTypeInfo.findByGoodsBaseInfo(goods).goodsTypeTwo
            //实例化
            def dataAnalysis = new DataAnalysis()
            dataAnalysis.detailId = detail.id
            //主题店数据
            dataAnalysis.themeStoreId = store.id
            dataAnalysis.themeStoreName = store.name
            //经营的合作伙伴
            dataAnalysis.partnerId = partner.id
            dataAnalysis.partnerName = partner.shortName
            //机器
            dataAnalysis.machineId = machine.id
            dataAnalysis.machineCode = machine.machineCode
            //订单
            dataAnalysis.orderId = order.id
            dataAnalysis.orderNo = order.orderNo
            //商品
            dataAnalysis.goodsName = detail.goodsName
            dataAnalysis.baseGoodsId = storeGoods.baseId
            dataAnalysis.themeStoreGoodsId = storeGoods.id
            dataAnalysis.goodsPic = detail.goodsPic
            dataAnalysis.goodsSpec = detail.goodsSpec
            dataAnalysis.typeOneId = typeTwo?.typeOne?.id
            dataAnalysis.typeTwoId = typeTwo?.id
            dataAnalysis.typeOneName = typeTwo?.typeOne?.name
            dataAnalysis.typeTwoName = typeTwo?.name
            //轨道
            dataAnalysis.orbitalNo = detail.orbitalNo
            //数量
            dataAnalysis.goodsNum = detail.goodsNum
            //价格
            dataAnalysis.buyPrice = detail.buyPrice
            //支付方式
            dataAnalysis.payWay = order.payWay
            //订单来源
            dataAnalysis.orderWay = order.orderWay
            dataAnalysis.completeTime = order.completeTime
            //初始日历类
            Calendar cal = Calendar.getInstance()
            cal.time = order.completeTime
            //计算时间
            dataAnalysis.completeYear = cal.get(Calendar.YEAR)
            dataAnalysis.completeMonth = cal.get(Calendar.MONTH) + 1
            dataAnalysis.completeDay = cal.get(Calendar.DAY_OF_MONTH)
            dataAnalysis.completeHour = cal.get(Calendar.HOUR_OF_DAY)
            dataAnalysis.dayInWeek = cal.get(Calendar.DAY_OF_WEEK)
            dataAnalysis.weekInYear = cal.get(Calendar.WEEK_OF_YEAR)
            dataAnalysis.payAccount = tran.payAccount
            //插入记录
            dataAnalysis.save flush: true
        }
    }
}
