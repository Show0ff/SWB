package com.khlopin.BearSWB.services;


import com.khlopin.BearSWB.MySessionFactory;
import com.khlopin.BearSWB.entity.Message;
import com.khlopin.BearSWB.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ChatRepository {


    public static List<Message> getMessageList() {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Message> fromMessage = session.createQuery("from Message", Message.class);
            return fromMessage.list();
        }
    }


    public static void addMessageInDB(Message message) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(message);
            transaction.commit();
        }
    }


    public static void addUserInDB(User user) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
    }


    public static User findUserFromDbByID(long id) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        }
    }



    public static User findUserFromDbByLogin(String login) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.login = :loginName", User.class);
            query.setParameter("loginName",login);
            return query.getSingleResult();
        } catch (RuntimeException e) {
            return null;
        }
    }

}
