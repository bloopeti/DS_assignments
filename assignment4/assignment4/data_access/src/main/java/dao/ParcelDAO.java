package dao;

import model.Parcel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ParcelDAO {
    public static Parcel createParcel(Parcel parcel) {
        int parcelId = -1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            parcelId = (Integer) session.save(parcel);
            parcel.setId(parcelId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
        return parcel;
    }

    public static Parcel readParcelById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List parcels = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Parcel WHERE id = :id");
            query.setParameter("id", id);
            parcels = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        if (parcels != null && !parcels.isEmpty())
            return (Parcel) parcels.get(0);
        else
            return null;
    }

    public static List<Parcel> readAllParcels() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List parcels = null;
        try {
            transaction = session.beginTransaction();
            parcels = session.createQuery("FROM Parcel").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        }
        if (parcels != null && !parcels.isEmpty())
            return parcels;
        else
            return null;
    }

    public static void updateParcel(Parcel parcel) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(parcel);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteParcel(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Parcel parcel = new Parcel();
        try {
            transaction = session.beginTransaction();
            parcel = readParcelById(id);
            session.delete(parcel);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }
}
