package com.ybg.rp.manager.base

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BaseAreaInfoController {

    def index() {
        //render html for ajax
    }

    def list() {
        def data = BaseAreaInfo.list(params)
        def count = BaseAreaInfo.count()

        def result = new AjaxPagingVo()
        result.data = data
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = count
        result.recordsFiltered = count
        render result as JSON
    }

    def listArea(Short level, String pname) {
        if (pname == "") {
            def result = BaseAreaInfo.findAllByLevel(level)
            render result as JSON
        } else {
            def parent = BaseAreaInfo.findByLevelAndName(level - 1, pname)
            def result = BaseAreaInfo.findAllByPid(parent?.id?.intValue())
            render result as JSON
        }
    }
}
