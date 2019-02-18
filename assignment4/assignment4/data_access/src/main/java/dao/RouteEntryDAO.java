package dao;

import model.RouteEntry;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RouteEntryDAO {
    public static RouteEntry createRouteEntry(RouteEntry routeEntry) {
        int routeEntryId = -1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            routeEntryId = (Integer) session.save(routeEntry);
            routeEntry.setId(routeEntryId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
        return routeEntry;
    }

    public static RouteEntry readRouteEntryById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List routeEntrys = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM RouteEntry WHERE id = :id");
            query.setParameter("id", id);
            routeEntrys = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        if (routeEntrys != null && !routeEntrys.isEmpty())
            return (RouteEntry) routeEntrys.get(0);
        else
            return null;
    }

    public static List<RouteEntry> readAllRouteEntrys() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List routeEntrys = null;
        try {
            transaction = session.beginTransaction();
            routeEntrys = session.createQuery("FROM RouteEntry").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        }
        if (routeEntrys != null && !routeEntrys.isEmpty())
            return routeEntrys;
        else
            return null;
    }

    public static void updateRouteEntry(RouteEntry routeEntry) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(routeEntry);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteRouteEntry(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        RouteEntry routeEntry = new RouteEntry();
        try {
            transaction = session.beginTransaction();
            routeEntry = readRouteEntryById(id);
            session.delete(routeEntry);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }
}
