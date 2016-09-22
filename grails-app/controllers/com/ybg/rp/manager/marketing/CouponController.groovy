package com.ybg.rp.manager.marketing

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON
import pl.touk.excel.export.WebXlsxExporter

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CouponController {

    static allowedMethods = [save: "POST", delete: "POST"]

    def index() {
        //render html for ajax
    }

    def list() {
        def name = params.name ?: ""
        def c = Coupon.createCriteria()
        def data = c.list(params) {
            like("code", "%"+name+"%")
        }

        def result = new AjaxPagingVo()
        result.data = data
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = data.totalCount
        result.recordsFiltered = data.totalCount
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
        prefix + zero + v + getRandomInt()
    }

    private String getRandomInt() {
        def random = new Random()
        def num1 = random.nextInt(10)
        def num2 = random.nextInt(10)
        return "${num1}${num2}"
    }

    def exportExcel() {
        //只导出有效的部分
        def coupons = Coupon.findAllByFlag(1 as Short)
        def headers = ['编号', '类型', '折扣', '最小金额', '优惠金额', '是否有效']
        def withProperties = ['code', 'humanType', 'discount', 'minMoney', 'yhMoney', 'humanFlag']

        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(coupons, withProperties)
            save(response.outputStream)
        }
    }

}
