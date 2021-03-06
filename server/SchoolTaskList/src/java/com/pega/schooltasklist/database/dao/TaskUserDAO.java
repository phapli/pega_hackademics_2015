package com.pega.schooltasklist.database.dao;

import com.pega.schooltasklist.database.object.Taskuser;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
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
                    .add(Restrictions.eq("active", true))
                    .addOrder(Order.asc("createDate"));

            List<Taskuser> taskusers = criteria.list();
            transaction.commit();
            if (taskusers.size() > 0) {
                for (Taskuser t : taskusers) {
                    Hibernate.initialize(t.getTask());
                    Hibernate.initialize(t.getTask().getSchoolgroup());
                }
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

    @SuppressWarnings("unchecked")
    public Taskuser getTask(String userID, long taskID) {

        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Taskuser.class)
                    .add(Restrictions.eq("user.id", userID))
                    .add(Restrictions.eq("task.id", taskID))
                    .add(Restrictions.eq("active", true));

            List<Taskuser> taskusers = criteria.list();
            transaction.commit();
            if (taskusers.size() > 0) {
                return taskusers.get(0);
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

    @SuppressWarnings("unchecked")
    public Taskuser deleteTask(long taskID) {

        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("delete Entity where task.id = " + taskID);
            q.executeUpdate();
            transaction.commit();
            
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
