package com.ybg.rp.manager.marketing

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CouponController {

    static allowedMethods = [save: "POST", delete: "POST"]

    def index() {
        //render html for ajax
    }

    def list() {
        def data = Coupon.list(params)
        def count = Coupon.count()

        def result = new AjaxPagingVo()
        result.data = data
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = count
        result.recordsFiltered = count
        render result as JSON
    }

    def show(Coupon coupon) {
        render coupon as JSON
    }

    @Transactional
    def save(Coupon coupon) {
        def result = [:]
        if (coupon == null) {
            result.success = false
            result.msg = "coupon is null."
            render result as JSON
            return
        }

        coupon.save flush:true

        if (coupon.hasErrors()) {
            transactionStatus.setRollbackOnly()
            result.success = false
            result.msg = coupon.errors
            render result as JSON
            return
        }

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def delete(Coupon coupon) {
        def result = [:]
        if (coupon == null) {
            result.success = false
            result.msg = "coupon is null."
            render result as JSON
            return
        }

        coupon.delete flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def createWithPrefix(String prefix, int begin, int end, int length, int type, Float discount, Float minMoney, Float yhMoney) {
        for (int i = begin; i <= end; i++) {
            def coupon = new Coupon()
            coupon.code = formatCode(prefix, i, length)
            coupon.type = type
            coupon.discount = discount
            coupon.minMoney = minMoney
            coupon.yhMoney = yhMoney
            coupon.flag = 1 as Short
            coupon.save(flush: true)
        }

        def result = [:]
        result.success = true
        result.msg = ""
        render result as JSON
    }

    private String formatCode(String prefix, int value, int length) {
        String v = "${value}"
        String zero = ""
        int l = length - v.length()
        if (l > 0) {
            for (int i = 0; i < l; i++) {
                zero += "0"
            }
        }
        prefix + zero + v
    }
}
