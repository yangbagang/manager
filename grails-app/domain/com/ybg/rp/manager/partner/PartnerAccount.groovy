package com.ybg.rp.manager.partner

import grails.databinding.BindingFormat

class PartnerAccount {

    static belongsTo = [partner: PartnerBaseInfo]

    static constraints = {
    }

    Float totalMoney
    Float useableMoney
    Float usedMoney
    @BindingFormat("yyyy-MM-dd HH:mm:ss")
    Date updateTime
}
