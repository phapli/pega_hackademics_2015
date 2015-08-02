package com.pega.schooltasklist.database.dao;

import com.pega.schooltasklist.database.object.User;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;

public class UserDAO {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);
    private static UserDAO instance = null;
    private final SessionFactory sessionFactory = SessionFactoryUtil
            .getSessionFactory();

    private Session openSession() {
        return sessionFactory.openSession();

    }

    private void closeSession(Session session) {

        session.close();
    }

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public User login(String ID, String pass) {
        ID = ID.toLowerCase();
        Session session = openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class)
                    .add(Restrictions.eq("id", ID).ignoreCase())
                    .add(Restrictions.eq("password", pass))
                    .add(Restrictions.eq("active", true));

            List<User> users = criteria.list();
            transaction.commit();
            if (users.size() > 0) {
                return users.get(0);
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
    public User getUser(String ID) {

        Session session = openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class)
                    .add(Restrictions.eq("id", ID))
                    .add(Restrictions.eq("active", true));

            List<User> users = criteria.list();
            transaction.commit();
            if (users.size() > 0) {
                return users.get(0);
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
    public List<User> getAllChild(String ID) {

        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class)
                    .add(Restrictions.eq("user.id", ID))
                    .add(Restrictions.eq("active", true));

            List<User> users = criteria.list();
            transaction.commit();
            if (users.size() > 0) {
                return users;
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
    public List<User> queryUser() {
        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class)
                    .add(Restrictions.eq("active", true));

            List<User> users = criteria.list();
            transaction.commit();
            if (users.size() > 0) {
                return users;
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

    public long save(User user) {

        Session session = openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            long res = (Long) session.save(user);
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

    public void update(User user) {

        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(user);
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
