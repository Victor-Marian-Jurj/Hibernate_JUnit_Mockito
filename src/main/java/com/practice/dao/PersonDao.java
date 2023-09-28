package com.practice.dao;

import com.practice.entities.Person;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


public class PersonDao {

    private final SessionProvider sessionProvider;


    public PersonDao() {
        sessionProvider = new HibernateUtilSessionProvider();
    }

    public PersonDao(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public void add(Person person) {

        Session session = sessionProvider.getSession();
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
        Session session = sessionProvider.getSession();
        Query query = session.createQuery("from Person as p");
//        query.setMaxResults(2); //fetch
//        query.setFirstResult(3); //offset
//        return query.getResultList();
        List<Person> people = query.getResultList();
        return people;
    }


    public List<Person> getByLastName(String lastName) {
        Session session = sessionProvider.getSession();
        Query query = session.createQuery("from Person as p where p.lastName = :lastName");
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    public List<Person> getByFirstNameAndLastName(String firstName, String lastName) {
        Session session = sessionProvider.getSession();
        Query query = session.createQuery("from Person as p where p.firstName = :firstName and p.lastName = :lastName");
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    public List<Person> getAllHibernateStyle() {
        try {
            try (Session session = sessionProvider.getSession()) {
                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
                Root<Person> personRoot = criteriaQuery.from(Person.class);
                org.hibernate.query.Query<Person> query = session.createQuery((criteriaQuery));
                return query.getResultList();
            }
        } catch (HibernateException exception) {
            return null;
        }
    }

    public List<Person> getByCnpHibernateStyle(String cnp) {
        try {
            try (Session session = sessionProvider.getSession()) {
                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
                Root<Person> personRoot = criteriaQuery.from(Person.class);
                criteriaQuery.select(personRoot).where(criteriaBuilder.like(personRoot.get("cnp"), cnp));
                org.hibernate.query.Query<Person> query = session.createQuery(criteriaQuery);
                return query.getResultList();
            }
        } catch (HibernateException exception) {
            return new ArrayList<>();
        }
    }

    public boolean deletePerson(String cnp) {
        try {
            try(Session session = sessionProvider.getSession()) {
                Transaction tx = session.beginTransaction();
                Person p = session.find(Person.class, cnp);
                session.remove(p);
                tx.commit();
            }
        } catch(HibernateException exception){
            return false;
        }
        return true;
    }

    public boolean updatePerson(String cnp, Person person) {
        try {
            try (Session session = sessionProvider.getSession()) {
                Transaction tx = session.beginTransaction();
                Person p = session.find(Person.class, cnp);
                p.setAddress(person.getAddress());
                p.setFirstName(person.getFirstName());
                p.setLastName(person.getLastName());
                session.merge(p);
                tx.commit();
            }
        } catch (HibernateException exception) {
            return false;
        }
        return true;
    }

    public boolean updateAddress(String cnp, String newAddress) {
        try {
            try (Session session = sessionProvider.getSession()) {
                Transaction tx = session.beginTransaction();
                Person p = session.find(Person.class, cnp);
                p.setAddress(newAddress);
                session.merge(p);
                tx.commit();
            }
        } catch (HibernateException exception) {
            return false;
        }
        return true;
    }
}
