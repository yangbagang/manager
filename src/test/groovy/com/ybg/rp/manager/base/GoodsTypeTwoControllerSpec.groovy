package com.ybg.rp.manager.base

import grails.test.mixin.*
import spock.lang.*

@TestFor(GoodsTypeTwoController)
@Mock(GoodsTypeTwo)
class GoodsTypeTwoControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.goodsTypeTwoList
            model.goodsTypeTwoCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.goodsTypeTwo!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def goodsTypeTwo = new GoodsTypeTwo()
            goodsTypeTwo.validate()
            controller.save(goodsTypeTwo)

        then:"The create view is rendered again with the correct model"
            model.goodsTypeTwo!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            goodsTypeTwo = new GoodsTypeTwo(params)

            controller.save(goodsTypeTwo)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/goodsTypeTwo/show/1'
            controller.flash.message != null
            GoodsTypeTwo.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def goodsTypeTwo = new GoodsTypeTwo(params)
            controller.show(goodsTypeTwo)

        then:"A model is populated containing the domain instance"
            model.goodsTypeTwo == goodsTypeTwo
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def goodsTypeTwo = new GoodsTypeTwo(params)
            controller.edit(goodsTypeTwo)

        then:"A model is populated containing the domain instance"
            model.goodsTypeTwo == goodsTypeTwo
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/goodsTypeTwo/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def goodsTypeTwo = new GoodsTypeTwo()
            goodsTypeTwo.validate()
            controller.update(goodsTypeTwo)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.goodsTypeTwo == goodsTypeTwo

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            goodsTypeTwo = new GoodsTypeTwo(params).save(flush: true)
            controller.update(goodsTypeTwo)

        then:"A redirect is issued to the show action"
            goodsTypeTwo != null
            response.redirectedUrl == "/goodsTypeTwo/show/$goodsTypeTwo.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/goodsTypeTwo/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def goodsTypeTwo = new GoodsTypeTwo(params).save(flush: true)

        then:"It exists"
            GoodsTypeTwo.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(goodsTypeTwo)

        then:"The instance is deleted"
            GoodsTypeTwo.count() == 0
            response.redirectedUrl == '/goodsTypeTwo/index'
            flash.message != null
    }
}
