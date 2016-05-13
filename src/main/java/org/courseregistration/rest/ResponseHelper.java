package org.courseregistration.rest;

import javax.ws.rs.core.CacheControl;

public class ResponseHelper {

    public static CacheControl getCacheControl() {
        CacheControl cc = new CacheControl();
        cc.setMaxAge(300);
        cc.setPrivate(true);
        cc.setNoStore(true);
        return cc;
    }
    public static CacheControl get() {
        CacheControl cc = new CacheControl();
        cc.setMaxAge(300);
        cc.setPrivate(true);
        cc.setNoStore(true);
        return cc;
    }
}
