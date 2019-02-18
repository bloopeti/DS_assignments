package dao;

import model.City;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityDAO {
    public static City createCity(City city) {
        int cityId = -1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            cityId = (Integer) session.save(city);
            city.setId(cityId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
        return city;
    }

    public static City readCityById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List citys = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM City WHERE id = :id");
            query.setParameter("id", id);
            citys = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        if (citys != null && !citys.isEmpty())
            return (City) citys.get(0);
        else
            return null;
    }

    public static List<City> readAllCitys() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List citys = null;
        try {
            transaction = session.beginTransaction();
            citys = session.createQuery("FROM City").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        }
        if (citys != null && !citys.isEmpty())
            return citys;
        else
            return null;
    }

    public static void updateCity(City city) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteCity(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        City city = new City();
        try {
            transaction = session.beginTransaction();
            city = readCityById(id);
            session.delete(city);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }
}
