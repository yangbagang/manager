package com.ybg.rp.manager.themeStore

import com.ybg.rp.manager.partner.BuildingBaseInfo

class ThemeStoreBaseInfo {

    static belongsTo = [building: BuildingBaseInfo]

    static constraints = {

    }

    String name
    Short status
    Date createTime
    Date openTime
    Double longitude
    Double latitude
    String position
    String province
    String city
    String county
}
