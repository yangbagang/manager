package com.ybg.rp.manager.base

class BaseAreaInfo {

    static constraints = {
        level()
        areaNo()
        pid()
        name()
    }

    Short level;
    String areaNo;
    Integer pid;
    String name;
}
