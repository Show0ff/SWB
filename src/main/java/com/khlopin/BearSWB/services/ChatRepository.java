package com.khlopin.BearSWB.services;


import com.khlopin.BearSWB.MySessionFactory;
import com.khlopin.BearSWB.entity.Message;
import com.khlopin.BearSWB.entity.Post;
import com.khlopin.BearSWB.entity.User;
import com.khlopin.BearSWB.entity.Wall;
import jakarta.persistence.NoResultException;
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
            query.setParameter("loginName", login);
            return query.getSingleResult();
        } catch (RuntimeException e) {
            return null;
        }
    }

    public static List<User> getAllUsers() {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    public static void addFriend(String loginOfOwner, String loginOfAddingUser) {
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

    public static List<User> getFriendListByUserLogin(String login) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("SELECT u FROM User u JOIN u.friendList f WHERE u.login = :loginName", User.class);
            query.setParameter("loginName", login);
            return query.list().get(0).getFriendList();
         } catch (RuntimeException e) {
            return null;
        }
    }

    public static Wall getWallByLogin(String login) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.login = :loginName", User.class);
            query.setParameter("loginName", login);
            User user = query.getSingleResult();
            return user.getWall();
        } catch (NoResultException e) {
            return null;
        }
    }

    public static List<Post> getPostsOfUserByLogin(String login) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.login = :loginName", User.class);
            query.setParameter("loginName", login);
            User user = query.getSingleResult();
            return user.getWall().getPostList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public static void addPostInUserWall(String login, Post post) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.login = :loginName", User.class);
            query.setParameter("loginName", login);
            User user = query.getSingleResult();

            Wall wall = user.getWall();
            List<Post> postList = wall.getPostList();
            post.setAuthorOfPost(user);
            postList.add(post);

            session.beginTransaction();
            session.saveOrUpdate(wall);
            session.getTransaction().commit();
        }
    }


    public static void updateUser(User user) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
    }
}
