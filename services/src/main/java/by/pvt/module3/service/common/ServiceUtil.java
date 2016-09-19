package by.pvt.module3.service.common;

import by.pvt.module3.dao.common.SessionUtil;

public class ServiceUtil {
    public static void closeSession() {
        SessionUtil.closeSession();
    }
}
