package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private static File file = new File("D:\\Uni_work\\DS\\assignment4\\assignment4\\data_access\\src\\main\\resources\\hibernate.cfg.xml");
    private static final SessionFactory sessionFactory = new Configuration().configure(file).buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
