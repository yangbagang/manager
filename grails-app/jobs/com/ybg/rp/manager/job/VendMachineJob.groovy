package com.ybg.rp.manager.job

import com.ybg.rp.manager.device.VendMachineInfo
import com.ybg.rp.manager.utils.GetuiUtil

/**
 * Created by yangbagang on 2016/9/22.
 */
class VendMachineJob {

    static delay = 1000l * 60 * 10//延迟执行,10分钟后开始执行任务。
    static repeatInterval = 1000l * 60 * 10 //执行频率,10分钟执行一次。

    static triggers = {
        simple name: 'vendMachineTrigger', startDelay: delay, repeatInterval: repeatInterval
    }

    def execute() {
        //获得设备列表
        def machines = VendMachineInfo.findAllByIsReal(1 as Short)
        for (VendMachineInfo machineInfo: machines) {
            if (GetuiUtil.getUserStatus(machineInfo.clientId)) {
                machineInfo.onlineStatus = 1 as Short
            } else {
                machineInfo.onlineStatus = 0 as Short
            }
            machineInfo.reportTime = new Date()
            machineInfo.save(flush: true)
        }
    }
}
