package com.ybg.rp.manager.themeStore

import com.ybg.rp.manager.base.GoodsBaseInfo
import com.ybg.rp.manager.base.GoodsLabelInfo
import com.ybg.rp.manager.base.GoodsTypeInfo
import com.ybg.rp.manager.base.GoodsTypeLabel
import com.ybg.rp.manager.base.GoodsTypeTwo
import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON
import grails.gorm.DetachedCriteria

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ThemeStoreGoodsInfoController {

    static allowedMethods = [save: "POST", delete: "DELETE"]

    def index() {
        //render html for ajax
    }

    def list(Long themeStoreId) {
        if (themeStoreId == null) {
            themeStoreId = 1
        }
        def c = ThemeStoreGoodsInfo.createCriteria()
        def result = c.list(params) {
            themeStore {
                eq("id", themeStoreId)
            }
        }

        def vo = new AjaxPagingVo()
        vo.data = result.resultList
        vo.draw = Integer.valueOf(params.draw)
        vo.error = ""
        vo.success = true
        vo.recordsTotal = result.totalCount
        vo.recordsFiltered = result.totalCount
        render vo as JSON
    }

    def show(ThemeStoreGoodsInfo themeStoreGoodsInfo) {
        render themeStoreGoodsInfo as JSON
    }

    @Transactional
    def save(ThemeStoreGoodsInfo themeStoreGoodsInfo) {
        def result = [:]
        if (themeStoreGoodsInfo == null) {
            result.success = false
            result.msg = "themeStoreGoodsInfo is null."
            render result as JSON
            return
        }

        themeStoreGoodsInfo.save flush:true

        if (themeStoreGoodsInfo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            result.success = false
            result.msg = themeStoreGoodsInfo.errors
            render result as JSON
            return
        }

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def delete(ThemeStoreGoodsInfo themeStoreGoodsInfo) {
        def result = [:]
        if (themeStoreGoodsInfo == null) {
            result.success = false
            result.msg = "themeStoreGoodsInfo is null."
            render result as JSON
            return
        }

        themeStoreGoodsInfo.delete flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def importGoods(Long themeStoreId) {
        println "themeStoreId=${params.themeStoreId}"
        def result = [:]

        try {
            def themeStore = ThemeStoreBaseInfo.get(themeStoreId)
            if (themeStore && params.goodsIds) {
                for (String goodsId in params.goodsIds) {
                    def g = ThemeStoreGoodsInfo.findAllByThemeStoreAndBaseId(themeStore, Long.valueOf(goodsId))
                    if (g?.size() > 0) {
                        continue
                    } else {
                        //create a instance
                        def themeStoreGoodsInfo = new ThemeStoreGoodsInfo()
                        def goods = GoodsBaseInfo.get(Long.valueOf(goodsId))
                        themeStoreGoodsInfo.themeStore = themeStore
                        themeStoreGoodsInfo.baseId = goods.id
                        themeStoreGoodsInfo.name = goods.name
                        themeStoreGoodsInfo.brand = goods.brand
                        themeStoreGoodsInfo.specifications = goods.specifications
                        themeStoreGoodsInfo.basePrice = goods.basePrice
                        themeStoreGoodsInfo.realPrice = goods.basePrice
                        themeStoreGoodsInfo.picId = goods.picId
                        themeStoreGoodsInfo.letter = goods.letter
                        themeStoreGoodsInfo.status = 1 as Short
                        themeStoreGoodsInfo.px = 1//to be modify
                        themeStoreGoodsInfo.save flush: true
                        //copy labels
                        def labels = GoodsLabelInfo.findAllByGoodsBaseInfo(goods)*.goodsLabel
                        for (GoodsTypeLabel label : labels) {
                            ThemeStoreGoodsLabel.createInstance(themeStoreGoodsInfo, label)
                        }
                        //copy types
                        def types = GoodsTypeInfo.findAllByGoodsBaseInfo(goods)*.goodsTypeTwo
                        for (GoodsTypeTwo typeTwo : types) {
                            ThemeStoreGoodsTypeTwo.createInstance(themeStoreGoodsInfo, typeTwo)
                            ThemeStoreGoodsTypeOne.createInstance(themeStoreGoodsInfo, typeTwo.typeOne)
                        }
                    }
                }
            }
        } catch (Exception e) {
        }

        result.success = true
        result.msg = ""
        render result as JSON
    }
}
