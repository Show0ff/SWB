package com.khlopin.BearSWB.services.repositories;

import com.khlopin.BearSWB.MySessionFactory;
import com.khlopin.BearSWB.entity.Post;
import com.khlopin.BearSWB.entity.User;
import com.khlopin.BearSWB.entity.Wall;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
@Transactional
public class UserPageRepository {


    public  Wall getWallByLogin(String login) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.login = :loginName", User.class);
            query.setParameter("loginName", login);
            User user = query.getSingleResult();
            return user.getWall();
        } catch (NoResultException e) {
            return null;
        }
    }

    public  List<Post> getPostsOfUserByLogin(String login) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.login = :loginName", User.class);
            query.setParameter("loginName", login);
            User user = query.getSingleResult();
            return user.getWall().getPostList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public  void addPostInUserWall(String login, Post post) {
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
}
