package com.ybg.rp.manager.partner

class PartnerUserAuthority {

    static belongsTo = [user: PartnerUserInfo, authority: PartnerAuthorityInfo]

    static constraints = {
    }
}
