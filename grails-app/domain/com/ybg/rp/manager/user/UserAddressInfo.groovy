package com.ybg.rp.manager.user

class UserAddressInfo {

    static belongsTo = [userInfo: UserBaseInfo]

    static constraints = {
        name()
        province()
        city()
        area()
        address()
        isDefault()
    }

    String province
    String city
    String area
    String phoneNo
    String name
    String address
    Short isDefault
}
