package com.ybg.rp.manager.partner

import com.ybg.rp.manager.system.SystemUser

class PartnerBaseInfo {

    static constraints = {
        admin nullable: true
        auditTime nullable: true
    }

    String companyName
    String companyCode
    Date bornDate
    Double registMoney
    String phoneNum
    String province
    String city
    String county
    String address
    String contactName
    String contactPhone
    String contactMail
    Date createTime
    Short status
    String bankName
    String accountName
    String accountNum
    SystemUser admin
    Date auditTime
    Long pid = 0
    Short type = 0
}
