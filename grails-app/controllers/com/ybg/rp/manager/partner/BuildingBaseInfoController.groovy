package com.ybg.rp.manager.partner

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BuildingBaseInfoController {

    static allowedMethods = [save: "POST", delete: "DELETE"]

    def index() {
        //render html for ajax
    }

    def list() {
        def data = BuildingBaseInfo.list(params)
        def count = BuildingBaseInfo.count()

        def result = new AjaxPagingVo()
        result.data = data
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = count
        result.recordsFiltered = count
        render result as JSON
    }

    def show(BuildingBaseInfo buildingBaseInfo) {
        render buildingBaseInfo as JSON
    }

    @Transactional
    def save(BuildingBaseInfo buildingBaseInfo) {
        def result = [:]
        if (buildingBaseInfo == null) {
            result.success = false
            result.msg = "buildingBaseInfo is null."
            render result as JSON
            return
        }

        if (buildingBaseInfo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            result.success = false
            result.msg = buildingBaseInfo.errors
            render result as JSON
            return
        }

        buildingBaseInfo.save flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def delete(BuildingBaseInfo buildingBaseInfo) {
        def result = [:]
        if (buildingBaseInfo == null) {
            result.success = false
            result.msg = "buildingBaseInfo is null."
            render result as JSON
            return
        }

        buildingBaseInfo.delete flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

}
