package com.ybg.rp.manager.partner

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PartnerUserOperationLogController {

    static allowedMethods = [save: "POST", delete: "DELETE"]

    def index() {
        //render html for ajax
    }

    def list() {
        def data = PartnerUserOperationLog.list(params)
        def count = PartnerUserOperationLog.count()

        def result = new AjaxPagingVo()
        result.data = data
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = count
        result.recordsFiltered = count
        render result as JSON
    }

    def show(PartnerUserOperationLog partnerUserOperationLog) {
        render partnerUserOperationLog as JSON
    }

    @Transactional
    def save(PartnerUserOperationLog partnerUserOperationLog) {
        def result = [:]
        if (partnerUserOperationLog == null) {
            result.success = false
            result.msg = "partnerUserOperationLog is null."
            render result as JSON
            return
        }

        partnerUserOperationLog.save flush:true

        if (partnerUserOperationLog.hasErrors()) {
            transactionStatus.setRollbackOnly()
            result.success = false
            result.msg = partnerUserOperationLog.errors
            render result as JSON
            return
        }

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def delete(PartnerUserOperationLog partnerUserOperationLog) {
        def result = [:]
        if (partnerUserOperationLog == null) {
            result.success = false
            result.msg = "partnerUserOperationLog is null."
            render result as JSON
            return
        }

        partnerUserOperationLog.delete flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

}
