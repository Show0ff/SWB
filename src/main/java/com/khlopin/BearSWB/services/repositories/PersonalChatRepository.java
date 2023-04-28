package com.khlopin.BearSWB.services.repositories;

import com.khlopin.BearSWB.MySessionFactory;
import com.khlopin.BearSWB.entity.Chat;
import com.khlopin.BearSWB.entity.PersonalMessage;
import com.khlopin.BearSWB.entity.User;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
@Transactional
public class PersonalChatRepository {

    public  List<PersonalMessage> getPersonalChatHistory(User recipient) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            String hql = "SELECT pm FROM PersonalMessage pm " +
                    "WHERE pm.sender = :recipient OR pm.receiver = :recipient " +
                    "AND pm.receiver <> pm.sender " +
                    "ORDER BY pm.id DESC";
            return session.createQuery(hql, PersonalMessage.class)
                    .setParameter("recipient", recipient)
                    .getResultList();
        }
    }


    public  void addPersonalMessage(PersonalMessage personalMessage) {
        Transaction transaction = null;
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User sender = session.merge(personalMessage.getSender());
            User receiver = session.merge(personalMessage.getReceiver());
            personalMessage.setSender(sender);
            personalMessage.setReceiver(receiver);
            session.persist(personalMessage);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public  Chat findChatBetweenUsers(Long userId1, Long userId2) {
        String hql = "select c from Chat c join c.usersOfChat u where u.id = :userId1 intersect select c from Chat c join c.usersOfChat u where u.id = :userId2";
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            TypedQuery<Chat> query = session.createQuery(hql, Chat.class);
            query.setParameter("userId1", userId1);
            query.setParameter("userId2", userId2);
            return query.getSingleResult();
        }
    }


    public  boolean isChatExistByUserIds(Long userId1, Long userId2) {
        String hql = "select c from Chat c join c.usersOfChat u where u.id = :userId1 intersect select c from Chat c join c.usersOfChat u where u.id = :userId2";
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            TypedQuery<Chat> query = session.createQuery(hql, Chat.class);
            query.setParameter("userId1", userId1);
            query.setParameter("userId2", userId2);
            List<Chat> chats = query.getResultList();
            return !chats.isEmpty();
        }
    }

    public  List<Chat> getAllChats() {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Chat> fromChat = session.createQuery("from Chat ", Chat.class);
            return fromChat.list();
        }
    }

    public  void updateMessagesInPersonalChat(PersonalMessage personalMessage) { //TODO this method does not work. Session Exception
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Chat chat = findChatBetweenUsers(personalMessage.getSender().getId(), personalMessage.getReceiver().getId());
            chat.getMessages().add(personalMessage);
            session.update(chat);
            tx.commit();
        }
    }
}
