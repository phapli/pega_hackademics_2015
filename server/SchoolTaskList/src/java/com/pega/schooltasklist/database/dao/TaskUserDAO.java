package com.pega.schooltasklist.database.dao;

import com.pega.schooltasklist.database.object.Taskuser;
import com.pega.schooltasklist.database.object.User;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;

public class TaskUserDAO {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TaskUserDAO.class);
    private static TaskUserDAO instance = null;
    private final SessionFactory sessionFactory = SessionFactoryUtil
            .getSessionFactory();

    private Session openSession() {
        return sessionFactory.openSession();

    }

    private void closeSession(Session session) {

        session.close();
    }

    private TaskUserDAO() {
    }

    public static TaskUserDAO getInstance() {
        if (instance == null) {
            instance = new TaskUserDAO();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public List<Taskuser> getAllTask(String ID) {

        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Taskuser.class)
                    .add(Restrictions.eq("user.id", ID))
                    .add(Restrictions.eq("done", false))
                    .add(Restrictions.eq("active", true));

            List<Taskuser> taskusers = criteria.list();
            transaction.commit();
            if (taskusers.size() > 0) {
                return taskusers;
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

    public long save(Taskuser taskuser) {

        Session session = openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            long res = (Long) session.save(taskuser);
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

    public void update(Taskuser taskuser) {

        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(taskuser);
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
