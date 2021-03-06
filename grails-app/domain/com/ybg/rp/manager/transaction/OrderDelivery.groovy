package com.ybg.rp.manager.transaction

class OrderDelivery {

    static belongsTo = [order: OrderInfo]

    static constraints = {
    }

    Date receiverName
    String province
    String city
    String area
    String address
    String phoneNo
    String message
}
