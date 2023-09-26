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


    public List<Person> getByLastName(String lastName) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from Person as p where p.lastName = :lastName");
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    public List<Person> getByFirstNameAndLastName(String firstName, String lastName) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from Person as p where p.firstName = :firstName and p.lastName = :lastName");
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }
}
