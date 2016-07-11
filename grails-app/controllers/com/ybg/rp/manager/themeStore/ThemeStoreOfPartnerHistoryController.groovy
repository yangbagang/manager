package com.ybg.rp.manager.themeStore

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ThemeStoreOfPartnerHistoryController {

    def index() {
        //render html for ajax
    }

    def list() {
        def data = ThemeStoreOfPartnerHistory.list(params)
        def count = ThemeStoreOfPartnerHistory.count()

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
