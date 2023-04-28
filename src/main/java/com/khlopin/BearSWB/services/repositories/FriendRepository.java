package com.khlopin.BearSWB.services.repositories;

import com.khlopin.BearSWB.MySessionFactory;
import com.khlopin.BearSWB.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FriendRepository {

    public void addFriend(String loginOfOwner, String loginOfAddingUser) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<User> queryOwner = session.createQuery("FROM User WHERE login = :login", User.class);
            queryOwner.setParameter("login", loginOfOwner);
            User owner = queryOwner.getSingleResult();

            Query<User> queryFriend = session.createQuery("FROM User WHERE login = :login", User.class);
            queryFriend.setParameter("login", loginOfAddingUser);
            User friend = queryFriend.getSingleResult();

            owner.getFriendList().add(friend);

            session.update(owner);

            transaction.commit();
        }
    }

    public List<User> getFriendListByUserLogin(String login) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("SELECT u FROM User u JOIN u.friendList f WHERE u.login = :loginName", User.class);
            query.setParameter("loginName", login);
            return query.list().get(0).getFriendList();
        } catch (RuntimeException e) {
            return null;
        }
    }

}
