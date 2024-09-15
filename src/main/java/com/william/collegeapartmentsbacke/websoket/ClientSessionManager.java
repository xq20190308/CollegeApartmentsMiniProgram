package com.william.collegeapartmentsbacke.websoket;

import com.william.collegeapartmentsbacke.pojo.entity.websocket.ClientSessionBean;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientSessionManager {
    public static ConcurrentHashMap<String, ClientSessionBean> CLIENT_POOL;
    private static AtomicInteger clientIdMaker;
    static {
        CLIENT_POOL = new ConcurrentHashMap<>();
        clientIdMaker = new AtomicInteger(0);
    }

    public static Integer getClientIdMaker() {
        return clientIdMaker.getAndIncrement();
    }


    public static void addClientSessionBean(String userId, ClientSessionBean clientSessionBean) {
        //添加session
        CLIENT_POOL.put(userId, clientSessionBean);
    }

    public static ClientSessionBean removeClientSessionBean(String userId) {
        return CLIENT_POOL.remove(userId);
    }

    public static void removeAndCloseClientSession(String userId) {
        CLIENT_POOL.remove(userId);
    }

    public static ClientSessionBean getClientSessionBean(String userId) {
        return CLIENT_POOL.get(userId);
    }

}
