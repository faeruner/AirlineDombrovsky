package by.pvt.module3.service;

import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.entity.MemberType;
import by.pvt.module3.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v on 07.09.2016.
 */
public class ServiceMemberType extends BaseService implements CommonService<MemberType> {
    public MemberType getById(Integer id)
    {
        CommonDAO<MemberType> dao = new CommonDAO<MemberType>(MemberType.class, HibernateUtil.getSesson());
        return dao.getById(id);
    }

    public void delete(Integer id)
    {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<MemberType> dao = new CommonDAO<MemberType>(MemberType.class, session);
            dao.delete(id);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public void add(MemberType air)
    {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<MemberType> dao = new CommonDAO<MemberType>(MemberType.class, session);
            dao.add(air);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public void update(MemberType air)
    {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<MemberType> dao = new CommonDAO<MemberType>(MemberType.class, session);
            dao.update(air);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public List<MemberType> getPage(Integer num_page)
    {
        Session session = HibernateUtil.getSesson();
        Query query = session.createQuery("from by.pvt.module3.entity.MemberType order by id");
        if(num_page == null)
            num_page = 1;

        query.setFirstResult((num_page-1)*getRecordsPerPage());
        query.setMaxResults(getRecordsPerPage());
        List<MemberType> list = query.list();

        return list;
    }

    public List<MemberType> getAll() {
        Session session = HibernateUtil.getSesson();
        CommonDAO<MemberType> dao = new CommonDAO<MemberType>(MemberType.class, session);
        return dao.getAll();
    }

    public List<Integer> getPagesNums()
    {
        Session session = HibernateUtil.getSesson();
        Query queryCount = session.createQuery("select count(*) from by.pvt.module3.entity.MemberType");
        Long count = (Long) queryCount.uniqueResult();

        List<Integer> listPages = new ArrayList<Integer>();
        for(int i=1; count>0; i++) {
            listPages.add(i);
            count -= getRecordsPerPage();
        }

        return listPages;
    }
}
