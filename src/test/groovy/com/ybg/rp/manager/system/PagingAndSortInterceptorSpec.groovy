package com.ybg.rp.manager.system


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PagingAndSortInterceptor)
class PagingAndSortInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test pagingAndSort interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"pagingAndSort")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
