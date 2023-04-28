package com.khlopin.BearSWB.services.repositories;


import com.khlopin.BearSWB.MySessionFactory;
import com.khlopin.BearSWB.entity.*;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

@Transactional
public class ChatRepository {


    public List<MessageForAll> getMessageList() {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<MessageForAll> fromMessage = session.createQuery("from MessageForAll", MessageForAll.class);
            return fromMessage.list();
        }
    }


    public void addMessageInDB(MessageForAll message) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(message);
            transaction.commit();
        }
    }

}
