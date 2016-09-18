package by.pvt.module3.servlet;

import by.pvt.module3.service.common.ServiceUtil;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;


public class RequestListener implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent event) {
        ServiceUtil.closeSession();
    }

    public void requestInitialized(ServletRequestEvent event) {
    }

}
