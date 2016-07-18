package com.ybg.rp.manager.partner

class PartnerUserLoginRecord {

    static constraints = {
    }

    PartnerUserInfo userInfo
    Date loginTime
    String loginDevice
    String loginIp
    String token
}
