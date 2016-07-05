package com.ybg.rp.manager.system

import grails.transaction.Transactional

@Transactional
class SystemUserRoleService {

    def addUserRole(Long adminId, String[] roleIds) {
        if (adminId && roleIds) {
            for (String roleId in roleIds) {
                if (!SystemUserRole.exists(adminId, Long.valueOf(roleId))) {
                    SystemUserRole.create(adminId, Long.valueOf(roleId))
                }
            }
        }
    }
}
