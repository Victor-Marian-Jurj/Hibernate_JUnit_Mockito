package com.practice.dao;

import com.practice.entities.Person;
import com.practice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonDaoTest {
    @InjectMocks
    private PersonDao personDao;
    @Mock
    private Session session;
    @Mock
    private Transaction transaction;

    @BeforeAll
    public void setUp() {
        personDao = new PersonDao();
        session = mock(Session.class);
        transaction = mock(Transaction.class);

        when(session.beginTransaction()).thenReturn(transaction);

        Mockito mockito = null;
        mockito.mockStatic(HibernateUtil.class);
        when(HibernateUtil.getSession()).thenReturn(session);
    }
//    @AfterEach
//    void tearDown() {
//        Mockito.reset(HibernateUtil.class);
//    }

    @Test
    public void testAddPerson() {

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");

        doNothing().when(session).persist(person);
        personDao.add(person);

        verify(session).persist(person);
        verify(transaction).commit();
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