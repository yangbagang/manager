package com.ybg.rp.manager.partner

import com.ybg.rp.manager.system.SystemUser

class BuildingBaseInfo {

    static belongsTo = [partner: PartnerBaseInfo]

    static constraints = {
    }

    String name
    String province
    String city
    String county
    String address
    Date buildDate
    Double areaNum
    Integer floorNum
    Integer peopleNum
    String descriptions
    Date signTime
    Date uploadTime
    String company
    SystemUser admin
    Date auditTime
    Short auditStatus
}
