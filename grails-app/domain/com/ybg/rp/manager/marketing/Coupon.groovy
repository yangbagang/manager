package com.ybg.rp.manager.marketing

class Coupon {

    static constraints = {
        code(unique: true)
    }

    String code//编号
    Integer type//类型1满减，满100减20；2折扣，7折；
    Float discount = 0//折扣，具体几折。
    Float minMoney = 0//起点金额，即从多少金额起可以使用。
    Float yhMoney = 0//具体减多少
    Short flag//是否有效

}
