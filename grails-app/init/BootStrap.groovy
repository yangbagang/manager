import com.ybg.rp.manager.objectMarshaller.partner.PartnerUserAuthorityObjectMarshaller
import com.ybg.rp.manager.objectMarshaller.partner.PartnerUserStoreObjectMarshaller
import com.ybg.rp.manager.objectMarshaller.system.SystemUserRoleObjectMarshaller
import com.ybg.rp.manager.objectMarshaller.themeStore.ThemeStoreOfPartnerHistoryObjectMarshaller
import com.ybg.rp.manager.system.SystemUser
import grails.converters.JSON

class BootStrap {

    def systemUserService

    def init = { servletContext ->
        JSON.registerObjectMarshaller(Date) {
            return it?.format("yyyy-MM-dd HH:mm:ss")
        }
        JSON.registerObjectMarshaller(new SystemUserRoleObjectMarshaller(), 9999)
        JSON.registerObjectMarshaller(new PartnerUserAuthorityObjectMarshaller(), 9999)
        JSON.registerObjectMarshaller(new ThemeStoreOfPartnerHistoryObjectMarshaller(), 9999)
        JSON.registerObjectMarshaller(new PartnerUserStoreObjectMarshaller(), 9999)
        systemUserService.initSystemUser()
    }
    def destroy = {
    }
}
