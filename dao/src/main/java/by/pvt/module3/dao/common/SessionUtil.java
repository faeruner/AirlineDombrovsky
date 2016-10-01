package by.pvt.module3.dao.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

public class SessionUtil {
    protected static final Logger log = LogManager.getLogger(SessionUtil.class);
    private static SessionFactory sessionFactory;
    private static String driverClass;
    public final static String SQLITE_JDBC = "org.sqlite.JDBC";

    private static ThreadLocal<Session> localSession = new ThreadLocal<Session>();

    public static Session getSesson() {
        Session s = localSession.get();
        // Open a new Session, if this thread has none yet
        if (s == null) {
            s = getSessionFactory().openSession();
            // Store it in the ThreadLocal variable
            localSession.set(s);
        }
        return s;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
//        if (SessionUtil.sessionFactory == null)
            SessionUtil.sessionFactory = sessionFactory;
    }

    private static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Boolean driverUsed(String name) {
        if (driverClass == null) {
            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
            LocalSessionFactoryBean bean = (LocalSessionFactoryBean) context.getBean("&sessionFactory");
            Configuration configuration = bean.getConfiguration();
            driverClass = configuration.getProperty("hibernate.connection.driver_class");
        }
        return driverClass.equals(name);
    }

    public static void closeSession() {
        Session s = localSession.get();
        if (s != null)
            s.close();
        localSession.set(null);
    }

    public static void rollback(Transaction tx) {
        try {
            if (tx != null) {
                tx.rollback();
            }
        } catch (HibernateException ignored) {
            log.error("Couldn't rollback Transaction", ignored);
        }
    }

    public static String getEntityByClass(Class clazz) {
        AbstractEntityPersister aep = (AbstractEntityPersister) getSessionFactory().getClassMetadata(clazz);
        return aep.getEntityName();
    }
}
