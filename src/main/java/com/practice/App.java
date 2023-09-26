package com.practice;

import com.practice.dao.PersonDao;
import com.practice.dao.ProductDao;
import com.practice.entities.Person;
import com.practice.entities.Product;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Person person = new Person("1111", "Panait", "Valeriu", "Str. Banatului", "0744589985", "adi.cris@gmail.com", null);

        PersonDao personDao = new PersonDao();

//        personDao.add(person);
//        System.out.println(personDao.getAll());
//        System.out.println(personDao.getByLastName("Stefan"));
//        System.out.println(personDao.getByFirstNameAndLastName("Vasile", "Eusebiu"));
//        System.out.println(personDao.getAllHibernateStyle());
//        System.out.println(personDao.getByCnpHibernateStyle("1111"));
//        personDao.updatePerson("0505", person);
//        personDao.updateAddress("0505", "Arhiepiscopiei");
//        personDao.deletePerson("0505");

//        Product product = new Product("0505", "Iphone 15", "Electronice", 1, 6500, person);

        Product product = new Product("0606","Iphone 16", "Electronice", 1, 8500, person);

        ProductDao productDao = new ProductDao();

//        productDao.addProduct(product);

//        System.out.println(productDao.getAllProducts());

//                System.out.println(productDao.getProductByPrice("4300"));

//                System.out.println(productDao.getProductByQuantity(1));
//
//                System.out.println(productDao.getAllProductsHibernateStyle());
//
//                System.out.println(productDao.updateProduct("0404", product));
//
//                System.out.println(productDao.updateProductPrice("0404", 9999));
//
//        System.out.println(productDao.deleteProductByBarCode("0404"));



    }
}
