package com.ybg.rp.manager.base

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GoodsTypeTwoController {

    static allowedMethods = [save: "POST", delete: "DELETE"]

    def index() {
        //render html for ajax
    }

    def list() {
        def data = GoodsTypeTwo.list(params)
        def count = GoodsTypeTwo.count()

        def result = new AjaxPagingVo()
        result.data = data
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = count
        result.recordsFiltered = count
        render result as JSON
    }

    def show(GoodsTypeTwo goodsTypeTwo) {
        render goodsTypeTwo as JSON
    }

    @Transactional
    def save(GoodsTypeTwo goodsTypeTwo) {
        def result = [:]
        if (goodsTypeTwo == null) {
            result.success = false
            result.msg = "goodsTypeTwo is null."
            render result as JSON
            return
        }

        if (goodsTypeTwo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            result.success = false
            result.msg = goodsTypeTwo.errors
            render result as JSON
            return
        }

        goodsTypeTwo.save flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def delete(GoodsTypeTwo goodsTypeTwo) {
        def result = [:]
        if (goodsTypeTwo == null) {
            result.success = false
            result.msg = "goodsTypeTwo is null."
            render result as JSON
            return
        }

        goodsTypeTwo.delete flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

}
