package com.ybg.rp.manager.utils

import com.gexin.rp.sdk.base.IQueryResult
import com.gexin.rp.sdk.http.IGtPush

/**
 * Created by yangbagang on 2016/9/22.
 */
class GetuiUtil {

    static appId = "4JCYuVTzz37viY08wQyu81"
    static appkey = "dYYkFFqDmc9cAStDjaopc"
    static master = "VSu5GEYc2P7GnVfaWhhAq1"
    static secret = "FdN8HjlrvH8oyCfIfODfp8"
    static host = "http://sdk.open.api.igexin.com/apiex.htm"

    static boolean getUserStatus(String clientId) {
        if (!clientId) {
            return false
        }
        IGtPush push = new IGtPush(host, appkey, master)
        IQueryResult abc = push.getClientIdStatus(appId, clientId)
        Map map = abc.getResponse()
        map && map.result && map.result == "Online"
    }

    static void main(String[] args) {
        println getUserStatus("2e06b79042d81336c941c49553735aaa")
    }
}
