package com.ybg.rp.manager.device

class VendLayerTrackInfo {

    static belongsTo = [vendMachine: VendMachineInfo]

    static constraints = {
    }

    /**第几层（层数）*/
    String layer;

    /** 轨道数量*/
    Integer orbitalNum;

    /**创建时间*/
    Date createTime

    /**更新时间*/
    Date updateTime
}
