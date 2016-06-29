package com.ybg.rp.manager.transaction

import com.ybg.rp.manager.device.VendMachineInfo

class OrderInfo {

    static belongsTo = [vendMachine: VendMachineInfo]

    static constraints = {
    }

    String orderNo
    /** 作废标识 */
    Short isCancel //0:未取消 1:手动取消 2:超时取消
    /** 付款状态  */
    Short payStatus    //0:未付款 1:已付款
    /** 订单渠道 */
    Short orderWay     //0:线下 1:微信 2:WEB 3:APP
    /** 发货状态 */
    Short deliveryStatus;   //0:未发货 1:出货成功 2:出货失败
    /** 支付方式 */
    Short payWay   //0:园区一卡通 1:支付宝 2:微信支付 3:在线账户
    /** 取货方式 */
    Short getWay  //0:默认 未选 1:自己取 2:别人配送
    Date receiverName
    String province
    String city
    String area
    String address
    String phoneNo
    String message
    String transNo
    Float orderMoney
    Float peiSongMoney
    Float totalMoney
    Float youHuiJuan
    Float userScore
    Float realMoney
    Date createTime
    Date confirmTime
    Date completeTime
    Date quHuoTime
    Date peiSongTime
    String quHuoCode
}
