package by.pvt.module3.dao.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.service.ServiceRegistry;

public class SessionUtil {

    private static SessionFactory sessionFactory;

    private static ThreadLocal<Session> localSession = new ThreadLocal<Session>() {
        @Override
        protected Session initialValue() {
            return getSessionFactory().openSession();
        }
    };

    public static Session getSesson() {
        return localSession.get();
    }

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }

    public static void closeSession() {
        Session session = localSession.get();
        if (session != null) {
            session.flush();
            session.close();
            localSession.remove();
        }
    }

    public static String getEntityByClass(Class clazz) {
        AbstractEntityPersister aep = (AbstractEntityPersister) getSessionFactory().getClassMetadata(clazz);
        return aep.getEntityName();
    }
}
