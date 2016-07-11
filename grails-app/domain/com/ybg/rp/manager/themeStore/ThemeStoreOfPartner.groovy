package com.ybg.rp.manager.themeStore

import com.ybg.rp.manager.partner.PartnerBaseInfo
import com.ybg.rp.manager.system.SystemUser
import grails.databinding.BindingFormat

class ThemeStoreOfPartner {

    static belongsTo = [baseInfo: ThemeStoreBaseInfo, partner: PartnerBaseInfo]

    static constraints = {
    }

    Float scale
    @BindingFormat("yyyy-MM-dd")
    Date fromDate
    @BindingFormat("yyyy-MM-dd")
    Date toDate
    SystemUser lastUpdateUser
    Date lastUpdateTime

}
