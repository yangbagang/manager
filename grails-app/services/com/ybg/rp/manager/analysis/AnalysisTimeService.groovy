package com.ybg.rp.manager.analysis

import grails.transaction.Transactional

@Transactional
class AnalysisTimeService {

    static DATA_ANALYSIS_KEY = "DATA_ANALYSIS_KEY"

    @Transactional(readOnly = true)
    def getLastTime(String key) {
        AnalysisTime.findByName(key)?.lastTime
    }

    def updateLastTime(String key, Date lastTime) {
        def analysisTime = AnalysisTime.findByName(key)

        if (!analysisTime) {
            analysisTime = new AnalysisTime()
            analysisTime.name = key
        }

        analysisTime.lastTime = lastTime
        analysisTime.save flush: true
    }
}
