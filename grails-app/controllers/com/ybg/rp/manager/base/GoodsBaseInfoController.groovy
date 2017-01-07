package com.ybg.rp.manager.base

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GoodsBaseInfoController {

    static allowedMethods = [save: "POST", delete: "DELETE"]

    def index() {
        //render html for ajax
    }

    def list() {
        def data = GoodsBaseInfo.list(params)
        def count = GoodsBaseInfo.count()

        def result = new AjaxPagingVo()
        result.data = data
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = count
        result.recordsFiltered = count
        render result as JSON
    }

    def show(GoodsBaseInfo goodsBaseInfo) {
        render goodsBaseInfo as JSON
    }

    @Transactional
    def save(GoodsBaseInfo goodsBaseInfo) {
        def result = [:]
        if (goodsBaseInfo == null) {
            result.success = false
            result.msg = "goodsBaseInfo is null."
            render result as JSON
            return
        }

        if (goodsBaseInfo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            result.success = false
            result.msg = goodsBaseInfo.errors
            render result as JSON
            return
        }

        goodsBaseInfo.save flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def delete(GoodsBaseInfo goodsBaseInfo) {
        def result = [:]
        if (goodsBaseInfo == null) {
            result.success = false
            result.msg = "goodsBaseInfo is null."
            render result as JSON
            return
        }

        goodsBaseInfo.delete flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def savePic(Long goodsId, String picId) {
        def result = [:]
        def goodsBaseInfo = GoodsBaseInfo.get(goodsId)
        if (goodsBaseInfo == null) {
            result.success = false
            result.msg = "goodsBaseInfo is null."
            render result as JSON
            return
        }

        goodsBaseInfo.picId = picId
        goodsBaseInfo.save flush: true

        result.success = true
        result.msg = ""
        render result as JSON
    }
}
