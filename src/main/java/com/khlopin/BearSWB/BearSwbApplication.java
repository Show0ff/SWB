package com.khlopin.BearSWB;


import com.khlopin.BearSWB.entity.Chat;
import com.khlopin.BearSWB.entity.Message;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;


@SpringBootApplication
public class BearSwbApplication {


	public static void main(String[] args) {
		try (Session session = MySessionFactory.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(Chat.builder().usersOfChat(new ArrayList<>()).build());
			transaction.commit();
		}

		SpringApplication.run(BearSwbApplication.class, args);
	}

}
