package com.khlopin.BearSWB.services.repositories;

import com.khlopin.BearSWB.MySessionFactory;
import com.khlopin.BearSWB.entity.User;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
@Transactional
public class UserRepository {


    public void addUserInDB(User user) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
    }

    public User getUserFromDbByID(long id) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        }
    }

    public User getUserFromDbByLogin(String login) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.login = :loginName", User.class);
            query.setParameter("loginName", login);
            return query.getSingleResult();
        } catch (RuntimeException e) {
            return null;
        }
    }

    public List<User> getAllUsers() {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    public  void updateUser(User user) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
    }


}
