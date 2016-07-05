import com.ybg.rp.manager.objectMarshaller.system.SystemUserRoleObjectMarshaller
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->
        JSON.registerObjectMarshaller(Date) {
            return it?.format("yyyy-MM-dd HH:mm:ss")
        }
        JSON.registerObjectMarshaller(new SystemUserRoleObjectMarshaller(), 9999)
    }
    def destroy = {
    }
}
