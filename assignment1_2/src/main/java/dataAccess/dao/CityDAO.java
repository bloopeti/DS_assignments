package dataAccess.dao;

import dataAccess.model.City;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityDAO {
    public static City readCityById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List cities = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM City WHERE id = :id");
            query.setParameter("id", id);
            cities = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        if (cities != null && !cities.isEmpty())
            return (City) cities.get(0);
        else
            return null;
    }
}
