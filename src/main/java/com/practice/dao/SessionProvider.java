package com.practice.dao;

import org.hibernate.Session;

public interface SessionProvider {
    Session getSession();
}
