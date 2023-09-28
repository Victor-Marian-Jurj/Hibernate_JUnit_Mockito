package com.practice.dao;

import com.practice.util.HibernateUtil;
import org.hibernate.Session;

public class HibernateUtilSessionProvider implements SessionProvider {

    @Override
    public Session getSession() {
        return HibernateUtil.getSession();
    }
}
