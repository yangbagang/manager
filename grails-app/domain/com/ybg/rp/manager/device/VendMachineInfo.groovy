package com.ybg.rp.manager.device

import com.ybg.rp.manager.themeStore.ThemeStoreBaseInfo

class VendMachineInfo {

    static belongsTo = [themeStore: ThemeStoreBaseInfo]

    static constraints = {
        status nullable: true
        type nullable: true
        clientId nullable: true
        onlineStatus nullable: true
        reportTime nullable: true
    }

    /**机器名称*/
    String machineName

    /**售卖机码*/
    String machineCode

    /**机器型号*/
    String machineModel

    /**删除状态*/
    Short status = 1 as Short//1 normal 0 isDelete

    /**是否有格子柜*/
    Integer type = 0

    /** 客户端ID(个推)*/
    String clientId

    /** 客户端在线状态(个推)*/
    Short onlineStatus

    /** 最后返回状态时间 */
    Date reportTime

    Short isReal = 1 //是否真实存在,对于物理机器为1,对于线上机器为0

    transient String themeStoreName

    String getThemeStoreName() {
        return themeStore?.name
    }
}
