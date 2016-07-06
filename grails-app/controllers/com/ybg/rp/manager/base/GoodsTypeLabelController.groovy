package com.ybg.rp.manager.base

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GoodsTypeLabelController {

    static allowedMethods = [save: "POST", delete: "DELETE"]

    def index() {
        //render html for ajax
    }

    def list() {
        def data = GoodsTypeLabel.list(params)
        def count = GoodsTypeLabel.count()

        def result = new AjaxPagingVo()
        result.data = data
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = count
        result.recordsFiltered = count
        render result as JSON
    }

    def show(GoodsTypeLabel goodsTypeLabel) {
        render goodsTypeLabel as JSON
    }

    @Transactional
    def save(GoodsTypeLabel goodsTypeLabel) {
        def result = [:]
        if (goodsTypeLabel == null) {
            result.success = false
            result.msg = "goodsTypeLabel is null."
            render result as JSON
            return
        }

        if (goodsTypeLabel.hasErrors()) {
            transactionStatus.setRollbackOnly()
            result.success = false
            result.msg = goodsTypeLabel.errors
            render result as JSON
            return
        }

        goodsTypeLabel.save flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def delete(GoodsTypeLabel goodsTypeLabel) {
        def result = [:]
        if (goodsTypeLabel == null) {
            result.success = false
            result.msg = "goodsTypeLabel is null."
            render result as JSON
            return
        }

        goodsTypeLabel.delete flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

}
