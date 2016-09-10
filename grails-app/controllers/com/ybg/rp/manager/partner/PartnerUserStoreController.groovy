package com.ybg.rp.manager.partner

import com.ybg.rp.manager.themeStore.ThemeStoreOfPartner
import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PartnerUserStoreController {

    static allowedMethods = [save: "POST", delete: "POST"]

    def index() {
        //render html for ajax
    }

    def list() {
        def c = PartnerUserStore.createCriteria()
        def name = params.name ?: ""
        def data = c.list(params) {
            or {
                user {
                    like("realName", "%"+name+"%")
                }
                store {
                    like("name", "%"+name+"%")
                }
            }
        }

        def result = new AjaxPagingVo()
        result.data = data
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = data.totalCount
        result.recordsFiltered = data.size()
        render result as JSON
    }

    def show(PartnerUserStore partnerUserStore) {
        render partnerUserStore as JSON
    }

    @Transactional
    def addUserStore(Long userId) {
        def result = [:]

        if (userId && params.storeList) {
            for(String storeId in params.storeList) {
                if(!PartnerUserStore.exists(userId, Long.valueOf(storeId))) {
                    PartnerUserStore.create(userId, Long.valueOf(storeId))
                }
            }
        }

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def delete(Long userId, Long storeId) {
        println "userId=${userId}, storeId=${storeId}"
        def result = [:]
        def partnerUserStore = PartnerUserStore.get(userId, storeId)
        if (partnerUserStore == null) {
            result.success = false
            result.msg = "partnerUserStore is null."
            render result as JSON
            return
        }

        partnerUserStore.delete flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

    def listStores(Long userId) {
        def stores = []
        def user = PartnerUserInfo.get(userId)
        if (user && user.parnterBaseInfo) {
            stores = ThemeStoreOfPartner.findAllByPartner(user.parnterBaseInfo)*.baseInfo
        }
        render stores as JSON
    }
}
