package com.ybg.rp.manager.job

import com.ybg.rp.manager.analysis.AnalysisTimeService

class DataAnalysisJobJob {

    def analysisTimeService

    def dataAnalysisService

    static delay = 1000l * 60 * 5//延迟执行,5分钟后开始执行任务。
    static repeatInterval = 1000l * 60 * 10 //执行频率,10分钟执行一次。

    static triggers = {
      simple name: 'dataAnalysisTrigger', startDelay: delay, repeatInterval: repeatInterval
    }

    def execute() {
        //开始时间
        def lastTime = analysisTimeService.getLastTime(AnalysisTimeService.DATA_ANALYSIS_KEY)
        //结束时间
        def now = new Date()
        //开始分析
        dataAnalysisService.analysis(lastTime, now)
        //更新时间
        analysisTimeService.updateLastTime(AnalysisTimeService.DATA_ANALYSIS_KEY, now)
    }
}
