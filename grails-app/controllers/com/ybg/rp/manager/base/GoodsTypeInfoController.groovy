package com.ybg.rp.manager.base

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GoodsTypeInfoController {

    static allowedMethods = [save: "POST", delete: "DELETE"]

    def index() {
        //render html for ajax
    }

    def list(Long goodsId) {
        def data = GoodsTypeInfo.findAllByGoodsBaseInfo(GoodsBaseInfo.get(goodsId))
        def count = data.size()

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
    def save(Long goodsId, Long typeTwoId) {
        def result = [:]
        def goods = GoodsBaseInfo.get(goodsId)
        def typeTwo = GoodsTypeTwo.get(typeTwoId)
        def goodsTypeInfo = GoodsTypeInfo.findAllByGoodsBaseInfoAndGoodsTypeTwo(goods, typeTwo)
        if(!goodsTypeInfo) {
            goodsTypeInfo = new GoodsTypeInfo()
            goodsTypeInfo.goodsBaseInfo = goods
            goodsTypeInfo.goodsTypeTwo = typeTwo
            goodsTypeInfo.save flush:true
        }

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def delete(GoodsTypeInfo goodsTypeInfo) {
        def result = [:]
        if (goodsTypeInfo == null) {
            result.success = false
            result.msg = "goodsTypeInfo is null."
            render result as JSON
            return
        }

        goodsTypeInfo.delete flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

}
