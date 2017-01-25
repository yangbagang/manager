package com.ybg.rp.manager.device

import com.ybg.rp.manager.themeStore.ThemeStoreBaseInfo
import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

class VendLayerTrackGoodsController {

    def listGoods(Long storeId) {
        def c = VendLayerTrackGoods.createCriteria()
        def store = ThemeStoreBaseInfo.get(storeId)
        def goods = c.list(max: params.length, offset: params.start) {
            vendMachine {
                eq("themeStore", store)
            }
            gt("currentInventory", 0)
            order("orbitalNo", "asc")
        }

        def result = new AjaxPagingVo()
        result.data = goods
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = goods.totalCount
        result.recordsFiltered = goods.totalCount
        render result as JSON
    }
}
