package com.ybg.rp.manager.system

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SystemLogController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

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
        respond systemLog
    }

    def create() {
        respond new SystemLog(params)
    }

    @Transactional
    def save(SystemLog systemLog) {
        if (systemLog == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemLog.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemLog.errors, view:'create'
            return
        }

        systemLog.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemLog.label', default: 'SystemLog'), systemLog.id])
                redirect systemLog
            }
            '*' { respond systemLog, [status: CREATED] }
        }
    }

    def edit(SystemLog systemLog) {
        respond systemLog
    }

    @Transactional
    def update(SystemLog systemLog) {
        if (systemLog == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemLog.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemLog.errors, view:'edit'
            return
        }

        systemLog.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemLog.label', default: 'SystemLog'), systemLog.id])
                redirect systemLog
            }
            '*'{ respond systemLog, [status: OK] }
        }
    }

    @Transactional
    def delete(SystemLog systemLog) {

        if (systemLog == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        systemLog.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemLog.label', default: 'SystemLog'), systemLog.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemLog.label', default: 'SystemLog'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
