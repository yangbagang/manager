package com.ybg.rp.manager.themeStore

import com.ybg.rp.manager.partner.PartnerBaseInfo
import com.ybg.rp.manager.system.SystemUser

class ThemeStoreOfPartnerHistory {

    static belongsTo = [baseInfo: ThemeStoreBaseInfo, partner: PartnerBaseInfo]

    static constraints = {

    }

    Float scale
    SystemUser lastUpdateUser
    Date lastUpdateTime
}
