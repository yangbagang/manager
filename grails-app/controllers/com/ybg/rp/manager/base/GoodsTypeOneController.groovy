package com.ybg.rp.manager.base

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GoodsTypeOneController {

    static allowedMethods = [save: "POST", delete: "DELETE"]

    def index() {
        //render html for ajax
    }

    def list() {
        def data = GoodsTypeOne.list(params)
        def count = GoodsTypeOne.count()

        def result = new AjaxPagingVo()
        result.data = data
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = count
        result.recordsFiltered = count
        render result as JSON
    }

    def show(GoodsTypeOne goodsTypeOne) {
        render goodsTypeOne as JSON
    }

    @Transactional
    def save(GoodsTypeOne goodsTypeOne) {
        def result = [:]
        if (goodsTypeOne == null) {
            result.success = false
            result.msg = "goodsTypeOne is null."
            render result as JSON
            return
        }

        if (goodsTypeOne.hasErrors()) {
            transactionStatus.setRollbackOnly()
            result.success = false
            result.msg = goodsTypeOne.errors
            render result as JSON
            return
        }

        goodsTypeOne.save flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def delete(GoodsTypeOne goodsTypeOne) {
        def result = [:]
        if (goodsTypeOne == null) {
            result.success = false
            result.msg = "goodsTypeOne is null."
            render result as JSON
            return
        }

        goodsTypeOne.delete flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

}
