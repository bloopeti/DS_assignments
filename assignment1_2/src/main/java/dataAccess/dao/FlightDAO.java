package dataAccess.dao;

import dataAccess.model.Flight;
import org.hibernate.*;

import java.util.List;

public class FlightDAO {


    public static Flight createFlight(Flight flight) {
        int flightId = -1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            flightId = (Integer) session.save(flight);
            flight.setId(flightId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
        return flight;
    }

    public static Flight readFlightById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List flights = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Flight WHERE id = :id");
            query.setParameter("id", id);
            flights = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        if (flights != null && !flights.isEmpty())
            return (Flight) flights.get(0);
        else
            return null;
    }

    public static List<Flight> readAllFlights() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List flights = null;
        try {
            transaction = session.beginTransaction();
            flights = session.createQuery("FROM Flight").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        }
        if (flights != null && !flights.isEmpty())
            return flights;
        else
            return null;
    }

    public static void updateFlight(Flight flight) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(flight);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteFlight(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Flight flight = new Flight();
        try {
            transaction = session.beginTransaction();
            flight = readFlightById(id);
            session.delete(flight);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }
}
