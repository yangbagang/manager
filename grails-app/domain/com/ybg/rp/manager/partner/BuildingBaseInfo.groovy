package com.ybg.rp.manager.partner

import com.ybg.rp.manager.system.SystemUser
import grails.databinding.BindingFormat

class BuildingBaseInfo {

    static belongsTo = [partner: PartnerBaseInfo]

    static constraints = {
    }

    String name
    String province
    String city
    String county
    String address
    @BindingFormat("yyyy-MM-dd")
    Date buildDate
    Double areaNum//建筑面积
    Integer floorNum
    Integer peopleNum
    String descriptions
    @BindingFormat("yyyy-MM-dd")
    Date signTime
    @BindingFormat("yyyy-MM-dd HH:mm:ss")
    Date uploadTime
    String company//物业公司
    SystemUser admin
    @BindingFormat("yyyy-MM-dd HH:mm:ss")
    Date auditTime
    Short auditStatus

    transient String partnerName

    String getPartnerName() {
        return partner?.shortName
    }
}
