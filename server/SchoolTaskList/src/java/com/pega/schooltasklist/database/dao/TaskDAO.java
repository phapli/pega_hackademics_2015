package com.pega.schooltasklist.database.dao;

import com.pega.schooltasklist.database.object.Task;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;

public class TaskDAO {
    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TaskDAO.class);
    private static TaskDAO instance = null;
    private final SessionFactory sessionFactory = SessionFactoryUtil
            .getSessionFactory();
    
    private Session openSession() {
        return sessionFactory.openSession();
        
    }
    
    private void closeSession(Session session) {
        
        session.close();
    }
    
    private TaskDAO() {
    }
    
    public static TaskDAO getInstance() {
        if (instance == null) {
            instance = new TaskDAO();
        }
        return instance;
    }
    
    @SuppressWarnings("unchecked")
    public Task getTask(long ID) {
        
        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Task.class)
                    .add(Restrictions.eq("id", ID))
                    .add(Restrictions.eq("active", true));
            
            List<Task> tasks = criteria.list();
            transaction.commit();
            if (tasks.size() > 0) {
                return tasks.get(0);
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
    public List<Task> getGroupTasks(String ID) {
        
        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Task.class)
                    .add(Restrictions.eq("group.id", ID))
                    .add(Restrictions.eq("active", true));
            
            List<Task> tasks = criteria.list();
            transaction.commit();
            if (tasks.size() > 0) {
                return tasks;
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
    
    public long save(Task task) {
        
        Session session = openSession();
        
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            long res = (Long) session.save(task);
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
    
    public void update(Task task) {
        
        Session session = openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(task);
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
