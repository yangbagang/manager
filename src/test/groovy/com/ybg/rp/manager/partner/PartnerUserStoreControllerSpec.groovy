package com.ybg.rp.manager.partner

import grails.test.mixin.*
import spock.lang.*

@TestFor(PartnerUserStoreController)
@Mock(PartnerUserStore)
class PartnerUserStoreControllerSpec extends Specification {

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
            !model.partnerUserStoreList
            model.partnerUserStoreCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.partnerUserStore!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def partnerUserStore = new PartnerUserStore()
            partnerUserStore.validate()
            controller.save(partnerUserStore)

        then:"The create view is rendered again with the correct model"
            model.partnerUserStore!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            partnerUserStore = new PartnerUserStore(params)

            controller.save(partnerUserStore)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/partnerUserStore/show/1'
            controller.flash.message != null
            PartnerUserStore.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def partnerUserStore = new PartnerUserStore(params)
            controller.show(partnerUserStore)

        then:"A model is populated containing the domain instance"
            model.partnerUserStore == partnerUserStore
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def partnerUserStore = new PartnerUserStore(params)
            controller.edit(partnerUserStore)

        then:"A model is populated containing the domain instance"
            model.partnerUserStore == partnerUserStore
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/partnerUserStore/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def partnerUserStore = new PartnerUserStore()
            partnerUserStore.validate()
            controller.update(partnerUserStore)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.partnerUserStore == partnerUserStore

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            partnerUserStore = new PartnerUserStore(params).save(flush: true)
            controller.update(partnerUserStore)

        then:"A redirect is issued to the show action"
            partnerUserStore != null
            response.redirectedUrl == "/partnerUserStore/show/$partnerUserStore.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/partnerUserStore/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def partnerUserStore = new PartnerUserStore(params).save(flush: true)

        then:"It exists"
            PartnerUserStore.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(partnerUserStore)

        then:"The instance is deleted"
            PartnerUserStore.count() == 0
            response.redirectedUrl == '/partnerUserStore/index'
            flash.message != null
    }
}
