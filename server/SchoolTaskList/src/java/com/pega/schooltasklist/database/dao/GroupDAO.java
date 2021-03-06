package com.pega.schooltasklist.database.dao;

import com.pega.schooltasklist.database.object.Schoolgroup;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;

public class GroupDAO {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GroupDAO.class);
    private static GroupDAO instance = null;
    private final SessionFactory sessionFactory = SessionFactoryUtil
            .getSessionFactory();

    private Session openSession() {
        return sessionFactory.openSession();

    }

    private void closeSession(Session session) {

        session.close();
    }

    private GroupDAO() {
    }

    public static GroupDAO getInstance() {
        if (instance == null) {
            instance = new GroupDAO();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public Schoolgroup getGroup(long ID) {

        Session session = openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Schoolgroup.class)
                    .add(Restrictions.eq("id", ID))
                    .add(Restrictions.eq("active", true));

            List<Schoolgroup> groups = criteria.list();
            transaction.commit();
            if (groups.size() > 0) {
                return groups.get(0);
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("", e);
        } finally {
            closeSession(session);
        }
        return null;
    }

    public long save(Schoolgroup group) {

        Session session = openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            long res = (Long) session.save(group);
            transaction.commit();

            return res;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("", e);
            return -1;
        } finally {
            closeSession(session);
        }

    }

    public void update(Schoolgroup group) {

        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(group);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("", e);
        } finally {
            closeSession(session);
        }
    }

}
