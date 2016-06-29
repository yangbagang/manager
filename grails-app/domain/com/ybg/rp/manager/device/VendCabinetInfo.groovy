package com.ybg.rp.manager.device

class VendCabinetInfo {

    static belongsTo = [vendMachine: VendMachineInfo]

    static constraints = {
    }

    /**
     * 格子柜名称
     */
    String name
    /**
     * 格子门数量
     */
    Integer num
    /**
     * 状态：1：启用；0：废弃
     */
    Short status
}
