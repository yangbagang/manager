package com.ybg.rp.manager.system

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SystemUserRoleController {

    static allowedMethods = [save: "POST", delete: "DELETE"]

    def systemUserRoleService

    def index() {
        //render html for ajax
    }

    def list() {
        def data = SystemUserRole.list(params)
        def count = SystemUserRole.count()

        def result = new AjaxPagingVo()
        result.data = data
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = count
        result.recordsFiltered = count
        render result as JSON
    }

    @Transactional
    def addUserRole(Long adminId) {
        def result = [:]

        systemUserRoleService.addUserRole(adminId, params.roleList)

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def delete(Long adminId, Long roleId) {
        def result = [:]
        def systemUserRole = SystemUserRole.get(adminId, roleId)
        if (systemUserRole == null) {
            result.success = false
            result.msg = "systemUserRole is null."
            render result as JSON
            return
        }

        systemUserRole.delete flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

}
