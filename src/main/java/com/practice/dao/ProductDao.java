package com.practice.dao;

import com.practice.entities.Product;
import com.practice.util.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public void addProduct(Product product) {

        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            session.persist(product);
            tx.commit();
        } catch (HibernateException exception) {
            tx.rollback();
        }
    }

    public List<Product> getAllProducts() {
        try (Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery("from Product as p");
//              query.setMaxResults(2);
//              query.setFirstResult(3);
//              return query.getResultList();
            return (List<Product>) query.getResultList();
        } catch (HibernateException exception) {
            return new ArrayList<>();
        }

    }

    ///////////////////////////////
    public List<Product> getProductByQuantity(int quantity) {
        try (Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery("from Product as p where p.quatity = :quatity");
            query.setParameter("quatity", quantity);
//        return query.getResultList();
            return (List<Product>) query.getResultList();
        } catch (HibernateException exception) {
            return new ArrayList<>();
        }
    }

    public List<Product> getProductByPrice(String price) {
        try (Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery("from Product as p where p.price = :price");
            query.setParameter("price", price);
            return (List<Product>) query.getResultList();
        } catch (HibernateException exception) {
            return new ArrayList<>();
        }
    }

    public List<Product> getAllProductsHibernateStyle() {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
            Root<Product> productRoot= criteriaQuery.from(Product.class);
            org.hibernate.query.Query<Product> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (HibernateException exception) {
            return null;
        }
    }

    public List<Product> getByPriceHibernateStyle(int price) {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
            Root<Product> carRoot = criteriaQuery.from(Product.class);
            criteriaQuery.select(carRoot).where(criteriaBuilder.equal(carRoot.get("price"), price));
            org.hibernate.query.Query<Product> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (HibernateException exception) {
            return new ArrayList<>();
        }
    }

    public boolean deleteProductByBarCode(String barCode) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            Product p = session.find(Product.class, barCode);
            session.remove(p);
            tx.commit();
        } catch (HibernateException exception) {
            return false;
        }
        return true;
    }

    public boolean updateProduct(String barCode, Product product) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            Product p = session.find(Product.class, barCode);
            p.setProductName(product.getProductName());
            p.setProductType(product.getProductType());
            session.merge(p);
            tx.commit();
        } catch (HibernateException exception) {
            return false;
        }
        return true;
    }


    public boolean updateProductPrice(String barCode, int newPrice) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            Product p = session.find(Product.class, barCode);
            p.setPrice(newPrice);
            session.merge(p);
            tx.commit();
        } catch (HibernateException exception) {
            return false;
        }
        return true;
    }


}
