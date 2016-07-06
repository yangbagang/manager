package com.ybg.rp.manager.base

import grails.test.mixin.*
import spock.lang.*

@TestFor(GoodsBaseInfoController)
@Mock(GoodsBaseInfo)
class GoodsBaseInfoControllerSpec extends Specification {

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
            !model.goodsBaseInfoList
            model.goodsBaseInfoCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.goodsBaseInfo!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def goodsBaseInfo = new GoodsBaseInfo()
            goodsBaseInfo.validate()
            controller.save(goodsBaseInfo)

        then:"The create view is rendered again with the correct model"
            model.goodsBaseInfo!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            goodsBaseInfo = new GoodsBaseInfo(params)

            controller.save(goodsBaseInfo)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/goodsBaseInfo/show/1'
            controller.flash.message != null
            GoodsBaseInfo.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def goodsBaseInfo = new GoodsBaseInfo(params)
            controller.show(goodsBaseInfo)

        then:"A model is populated containing the domain instance"
            model.goodsBaseInfo == goodsBaseInfo
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def goodsBaseInfo = new GoodsBaseInfo(params)
            controller.edit(goodsBaseInfo)

        then:"A model is populated containing the domain instance"
            model.goodsBaseInfo == goodsBaseInfo
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/goodsBaseInfo/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def goodsBaseInfo = new GoodsBaseInfo()
            goodsBaseInfo.validate()
            controller.update(goodsBaseInfo)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.goodsBaseInfo == goodsBaseInfo

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            goodsBaseInfo = new GoodsBaseInfo(params).save(flush: true)
            controller.update(goodsBaseInfo)

        then:"A redirect is issued to the show action"
            goodsBaseInfo != null
            response.redirectedUrl == "/goodsBaseInfo/show/$goodsBaseInfo.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/goodsBaseInfo/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def goodsBaseInfo = new GoodsBaseInfo(params).save(flush: true)

        then:"It exists"
            GoodsBaseInfo.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(goodsBaseInfo)

        then:"The instance is deleted"
            GoodsBaseInfo.count() == 0
            response.redirectedUrl == '/goodsBaseInfo/index'
            flash.message != null
    }
}
