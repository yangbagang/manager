package com.ybg.rp.manager.device

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class VendMachineInfoErrorInfoController {

    def index() {
        //render html for ajax
    }

    def list() {
        def data = VendMachineInfoErrorInfo.list(params)
        def count = VendMachineInfoErrorInfo.count()

        def result = new AjaxPagingVo()
        result.data = data
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = count
        result.recordsFiltered = count
        render result as JSON
    }

}
