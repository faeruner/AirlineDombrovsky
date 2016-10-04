package by.pvt.module3.dao.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.persister.entity.AbstractEntityPersister;

public class SessionUtil {
    protected static final Logger log = LogManager.getLogger(SessionUtil.class);
    private static SessionFactory sessionFactory;

    private static ThreadLocal<Session> localSession = new ThreadLocal<Session>();

    public static Session getSession() {
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
            SessionUtil.sessionFactory = sessionFactory;
    }

    private static SessionFactory getSessionFactory() {
        return sessionFactory;
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

    static String getEntityByClass(Class clazz) {
        AbstractEntityPersister aep = (AbstractEntityPersister) getSessionFactory().getClassMetadata(clazz);
        return aep.getEntityName();
    }
}
