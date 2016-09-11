package by.pvt.module3.service;

import by.pvt.module3.hibernate.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Created by v on 06.09.2016.
 */
public class BaseService {
    private Integer recordsPerPage;
    static final Logger log = LogManager.getRootLogger();

    public BaseService() {
        recordsPerPage = 3;
    }

    public Integer getRecordsPerPage() {
        return recordsPerPage;
    }
}
