package com.ybg.rp.manager.system

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SystemUserController {

    static allowedMethods = [save: "POST", delete: "DELETE"]

    def index() {
        //render html for ajax
    }

    def list() {
        def data = SystemUser.list(params)
        def count = SystemUser.count()

        def result = new AjaxPagingVo()
        result.data = data
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = count
        result.recordsFiltered = count
        render result as JSON
    }

    def show(SystemUser systemUser) {
        render systemUser as JSON
    }

    @Transactional
    def save(SystemUser systemUser) {
        def result = [:]
        if (systemUser == null) {
            result.success = false
            result.msg = "systemUser is null."
            render result as JSON
            return
        }

        //TODO 将创建者及修改者改为当前用户
        if (!systemUser.id) {
            def now = new Date()
            systemUser.createTime = now
            systemUser.updateTime = now
            systemUser.createUser = ""
            systemUser.updateUser = ""
        } else {
            systemUser.updateTime = new Date()
            systemUser.updateUser = ""
        }

        if ("1" == params.enableAccount) {
            systemUser.enabled = true
        } else {
            systemUser.enabled = false
        }

        if (systemUser.hasErrors()) {
            systemUser.errors.each {
                println it
            }
            transactionStatus.setRollbackOnly()
            result.success = false
            result.msg = systemUser.errors
            render result as JSON
            return
        }

        systemUser.save flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def delete(SystemUser systemUser) {
        def result = [:]
        if (systemUser == null) {
            result.success = false
            result.msg = "systemUser is null."
            render result as JSON
            return
        }

        systemUser.delete flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

}
