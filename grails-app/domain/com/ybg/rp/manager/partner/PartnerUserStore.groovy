package com.ybg.rp.manager.partner

import com.ybg.rp.manager.themeStore.ThemeStoreBaseInfo
import grails.gorm.DetachedCriteria

class PartnerUserStore {

    static belongsTo = [user: PartnerUserInfo, store: ThemeStoreBaseInfo]

    static constraints = {
    }

    static PartnerUserStore get(long userId, long storeId) {
        criteriaFor(userId, storeId).get()
    }

    static boolean exists(long userId, long storeId) {
        criteriaFor(userId, storeId).count()
    }

    private static DetachedCriteria criteriaFor(long userId, long storeId) {
        PartnerUserStore.where {
            user == PartnerUserInfo.load(userId) &&
                    store == ThemeStoreBaseInfo.load(storeId)
        }
    }

    static PartnerUserStore create(PartnerUserInfo user, ThemeStoreBaseInfo store) {
        def instance = new PartnerUserStore(user: user, store: store)
        instance.save()
        instance
    }

    static PartnerUserStore create(Long userId, Long storeId) {
        def user = PartnerUserInfo.get(userId)
        def store = ThemeStoreBaseInfo.get(storeId)
        if (user && store) {
            def instance = new PartnerUserStore(user: user, store: store)
            instance.save(flush: true)
            instance
        }
    }
}
