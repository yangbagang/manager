<%=packageName ? "package ${packageName}" : ''%>

import com.ybg.rp.manager.vo.AjaxPagingVo
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ${className}Controller {

    static allowedMethods = [save: "POST", delete: "DELETE"]

    def index() {
        //render html for ajax
    }

    def list() {
        def data = ${className}.list(params)
        def count = ${className}.count()

        def result = new AjaxPagingVo()
        result.data = data
        result.draw = Integer.valueOf(params.draw)
        result.error = ""
        result.success = true
        result.recordsTotal = count
        result.recordsFiltered = count
        render result as JSON
    }

    def show(${className} ${propertyName}) {
        render ${propertyName} as JSON
    }

    @Transactional
    def save(${className} ${propertyName}) {
        def result = [:]
        if (${propertyName} == null) {
            result.success = false
            result.msg = "${propertyName} is null."
            render result as JSON
            return
        }

        if (${propertyName}.hasErrors()) {
            transactionStatus.setRollbackOnly()
            result.success = false
            result.msg = ${propertyName}.errors
            render result as JSON
            return
        }

        ${propertyName}.save flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

    @Transactional
    def delete(${className} ${propertyName}) {
        def result = [:]
        if (${propertyName} == null) {
            result.success = false
            result.msg = "${propertyName} is null."
            render result as JSON
            return
        }

        ${propertyName}.delete flush:true

        result.success = true
        result.msg = ""
        render result as JSON
    }

}
