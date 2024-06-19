package com.william.collegeapartmentsbacke.websoket;

import com.william.collegeapartmentsbacke.pojo.entity.SessionBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSessionMannager {
    public static ConcurrentHashMap<String, SessionBean> SESSION_POOL = new ConcurrentHashMap<>();


    public static void addSessionBean(String keyuserid,SessionBean sessionBean) {
        //添加session
        SESSION_POOL.put(keyuserid, sessionBean);
    }

    public static SessionBean removeSessionBean(String keyuserid) {
        return SESSION_POOL.remove(keyuserid);
    }

    public static void removeAndClose(String keyuserid) {
        SESSION_POOL.remove(keyuserid);
    }

    public static SessionBean getSessionBean(String keyuserid) {
        return SESSION_POOL.get(keyuserid);
    }

}
