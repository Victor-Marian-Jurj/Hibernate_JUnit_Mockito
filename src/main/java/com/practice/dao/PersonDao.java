package com.practice.dao;

import com.practice.entities.Person;
import com.practice.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PersonDao {

    public void add(Person person) {

        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(person);
            tx.commit();
        } catch (HibernateException exception) {
            tx.rollback();
        } finally {
            session.close();
        }
    }
}
