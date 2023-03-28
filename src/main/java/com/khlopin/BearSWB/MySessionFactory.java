package com.khlopin.BearSWB;

import com.khlopin.BearSWB.entity.Chat;
import com.khlopin.BearSWB.entity.Message;
import com.khlopin.BearSWB.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySessionFactory {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Message.class);
            configuration.addAnnotatedClass(Chat.class);
            configuration.configure();
            return configuration.buildSessionFactory();
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
