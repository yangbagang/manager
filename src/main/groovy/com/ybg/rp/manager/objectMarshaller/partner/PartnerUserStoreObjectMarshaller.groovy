package com.ybg.rp.manager.objectMarshaller.partner

import com.ybg.rp.manager.partner.PartnerUserAuthority
import com.ybg.rp.manager.partner.PartnerUserStore
import grails.converters.JSON
import org.grails.web.converters.exceptions.ConverterException
import org.grails.web.converters.marshaller.ObjectMarshaller
import org.grails.web.json.JSONWriter

/**
 * Created by yangbagang on 16/9/10.
 */
class PartnerUserStoreObjectMarshaller implements ObjectMarshaller<JSON> {

    @Override
    boolean supports(Object object) {
        return object instanceof PartnerUserStore
    }

    @Override
    void marshalObject(Object object, JSON converter) throws ConverterException {
        JSONWriter writer = converter.getWriter()
        writer.object()
        writer.key('partnerName').value(object?.user?.parnterBaseInfo?.shortName)
                .key("userName").value(object?.user?.realName)
                .key('storeName').value(object?.store?.name)
                .key('userId').value(object.user?.id)
                .key('storeId').value(object.store?.id)
        writer.endObject()
    }

}
