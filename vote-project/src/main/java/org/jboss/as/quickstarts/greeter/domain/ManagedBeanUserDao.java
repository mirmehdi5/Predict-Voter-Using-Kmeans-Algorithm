/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.greeter.domain;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * @brief This class has all the user related operations
 * @author MIR
 *
 */
public class ManagedBeanUserDao implements UserDao {

    @Inject
    private EntityManager entityManager;

    @Inject
    private UserTransaction utx;

    /**
     * @brief This function is used to check whether the is present in the database
     */
    public User getForUsername(String username) {
        try {
            User user;
            try {
                utx.begin();
                Query query = entityManager.createQuery("select u from User u where u.username = :username");
                query.setParameter("username", username);
                user = (User) query.getSingleResult();
            } catch (NoResultException e) {
                user = null;
            }
            utx.commit();
            return user;
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }
    
    /**
     * @brief This function is used to change the role of the user in the database.
     */
    public void changeUserRole(User user) {
        try {
            try {
            	 utx.begin();
            	 Query query = entityManager.createQuery("update User r set r.role = :role where r.username= :username");
            	 query.setParameter("username", user.getUsername());
            	 query.setParameter("role", user.getRole());
            	 query.executeUpdate();        
            } finally {
                utx.commit();
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (SystemException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }
    }
}
