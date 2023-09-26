package com.practice;

import com.practice.dao.PersonDao;
import com.practice.entities.Person;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Person person = new Person("0505", "Adrian", "Cristoiu", "Str. Bucovat", "0744589985", "adi.cris@gmail.com");


        PersonDao personDao = new PersonDao();

//        personDao.add(person);
//        System.out.println(personDao.getAll());
//        System.out.println(personDao.getByLastName("Stefan"));
//        System.out.println(personDao.getByFirstNameAndLastName("Vasile", "Eusebiu"));
          System.out.println(personDao.getAllHibernateStyle());


    }
}
