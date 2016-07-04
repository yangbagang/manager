package com.ybg.rp.manager.themeStore

import com.ybg.rp.manager.partner.PartnerBaseInfo
import com.ybg.rp.manager.system.SystemUser

class ThemeStoreOfPartner {

    static belongsTo = [baseInfo: ThemeStoreBaseInfo, partner: PartnerBaseInfo]

    static constraints = {

    }

    Float sale
    Date fromDate
    Date toDate
    SystemUser lastUpdateUser
    Date lastUpdateTime

}
