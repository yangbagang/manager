package com.ybg.rp.manager.partner

class PartnerAuthorityInfo {

    static constraints = {
        authority()
        name()
        mem()
    }

    String authority
    String name
    String mem
}
