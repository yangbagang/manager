package com.ybg.rp.manager.partner

class BuildingFileInfo {

    static belongsTo = [building: BuildingBaseInfo]

    static constraints = {
    }

    String fileId
}
