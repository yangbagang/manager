package com.ybg.rp.manager.system

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SystemLogController {

    def index() {
        //render html for ajax
    }

    def list() {
        def data = SystemLog.list(params)
        def count = SystemLog.count()

        def result = new AjaxPagingVo()
        result.data = data
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = count
        result.recordsFiltered = count
        render result as JSON
    }

    def show(SystemLog systemLog) {
        render systemLog as JSON
    }

}
