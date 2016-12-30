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
    Integer dayOfWeek = 0//星期几有效。0表示不限
    Integer dayOfMonth = 0//每月几号有效。0表示不限
    Integer maxCount = Integer.MAX_VALUE//最大可用次数。
    Short flag//是否有效

    transient String humanType
    transient String humanFlag

    String getHumanType() {
        type == 1 ? "满减" : "折扣"
    }

    String getHumanFlag() {
        def valid = 1 as Short
        flag == valid ? "有效" : "无效"
    }
}
