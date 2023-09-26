package com.practice.dao;

import com.practice.entities.Person;
import com.practice.util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

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

    public List<Person> getAll() {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from Person as p");
//        query.setMaxResults(2); //fetch
//        query.setFirstResult(3); //offset
//        return query.getResultList();
        List<Person> people = query.getResultList();
        return people;
    }


}
