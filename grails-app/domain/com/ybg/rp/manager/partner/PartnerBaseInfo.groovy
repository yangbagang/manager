package com.ybg.rp.manager.partner

import com.ybg.rp.manager.system.SystemUser

class PartnerBaseInfo {

    static constraints = {
        companyName()
        companyCode()
        bornDate()
        registMoney()
        phoneNum()
        province()
        city()
        county()
        address()
        contactName()
        contactPhone()
        contactMail()
        createTime()
        status()
        bankName()
        accountName()
        accountNum()
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
}
