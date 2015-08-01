package com.pega.schooltasklist.database.dao;

import com.pega.schooltasklist.database.object.Groupuser;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;

public class GroupUserDAO {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GroupUserDAO.class);
    private static GroupUserDAO instance = null;
    private final SessionFactory sessionFactory = SessionFactoryUtil
            .getSessionFactory();

    private Session openSession() {
        return sessionFactory.openSession();

    }

    private void closeSession(Session session) {

        session.close();
    }

    private GroupUserDAO() {
    }

    public static GroupUserDAO getInstance() {
        if (instance == null) {
            instance = new GroupUserDAO();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public List<Groupuser> getGroupMember(String ID) {

        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Groupuser.class)
                    .add(Restrictions.eq("group.id", ID))
                    .add(Restrictions.eq("active", true));

            List<Groupuser> groupusers = criteria.list();
            transaction.commit();
            if (groupusers.size() > 0) {
                return groupusers;
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

    public long save(Groupuser groupuser) {

        Session session = openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            long res = (Long) session.save(groupuser);
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

    public void update(Groupuser groupuser) {

        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(groupuser);
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
