package com.practice.dao;

import com.practice.entities.Person;
import com.practice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class PersonDaoTest {

    private PersonDao personDao;
    private SessionProvider sessionProvider;
    private Session session;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        sessionProvider = mock(SessionProvider.class);
        session = mock(Session.class);
        transaction = mock(Transaction.class);

        personDao = new PersonDao(sessionProvider);

        when(sessionProvider.getSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    void testAddPerson() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");

        personDao.add(person);

        verify(session).persist(person);
        verify(transaction).commit();
        verify(session).close();
    }


    @Test
    void getAll() {

        List<Person> personList = new ArrayList<>();
        personList.add(new Person());
        personList.add(new Person());

        Query query = mock(Query.class);
        when(session.createQuery("from Person as p")).thenReturn(query);
        when(query.getResultList()).thenReturn(personList);

        List<Person> result = personDao.getAll();

        verify(session).createQuery("from Person as p");
        verify(query).getResultList();

        assertEquals(personList, result);

    }

    @Test
    void deletePerson() {
        String cnp = "1111";

        Person person = new Person();
        person.setCnp(cnp);

        when(session.find(Person.class, cnp)).thenReturn(person);
        boolean result = personDao.deletePerson(cnp);

        verify(session).remove(person);
        verify(transaction).commit();

        assertTrue(result);

    }

    @Test
    void updatePerson() {
        String cnp = "1111";

        Person person = new Person();
        person.setCnp(cnp);
        person.setAddress("Banatului");
        person.setLastName("John");
        person.setFirstName("Doe");

        when(session.find(Person.class, cnp)).thenReturn(person);
        boolean result = personDao.updatePerson(cnp, person);

        verify(session).merge(person);
        verify(transaction).commit();

        assertTrue(result);
    }
}