package dao;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO {
    public User createUser(User user) {
        int userId = -1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            userId = (Integer) session.save(user);
            user.setId(userId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
        return user;
    }



    public static User readUserById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List users = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE id = :id");
            query.setParameter("id", id);
            users = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        if (users != null && !users.isEmpty())
            return (User) users.get(0);
        else
            return null;
    }

    public static User readUserByUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List users = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE username = :username");
            query.setParameter("username", username);
            users = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        if (users != null && !users.isEmpty())
            return (User) users.get(0);
        else
            return null;
    }


    public static void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteUser(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        User user = new User();
        try {
            transaction = session.beginTransaction();
            user = readUserById(id);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }
}
