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
package org.jboss.as.quickstarts.greeter.web;

import java.util.Base64;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;*/
import org.jboss.as.quickstarts.greeter.domain.User;
import org.jboss.as.quickstarts.greeter.domain.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author MIR
 * @class GreetController
 * @brief This is the controller for main login page
 *
 */
@Named
@RequestScoped
public class GreetController {
	
	@Inject
    private UserDao userDao;

    private String username;
    
    private int role;
    
    private String password;

    private String greeting;
    
    private static final Logger logger = LoggerFactory.getLogger(GreetController.class.getName());  

    /**
     * @brief This function calls the getForUsername for user authentication
     * @return returns the page according to the role of the user in the database.
     */
    public String greet() {
        User user = userDao.getForUsername(username);
        String encoded_password = encode(password);
        if (user != null) {
            logger.info("The user is " + user.getUsername()+ ". And the role of this user is "+user.getRole()); 
        	if(user.getPassword().equals(encoded_password)){
        		if(user.getRole() == 1){
            		return "admin_page?faces-redirect=true";
            	}
            	else if(user.getRole() == 2){
            		return "developer_page?faces-redirect=true";
            	}
            	else{
            		return "general_page?faces-redirect=true";
            	}
        	} else{
        		greeting = "Incorrect Password!!!! ";
        		return "greet";
        	}
        } else {
            logger.info("An Invalid user access"); 
            greeting = "Invalid User!!!! ";
            return "greet";
        }
    }

	/**
	 * @brief This function gets the username from the greet.xhtml page.
	 * @return username
	 */
    public String getUsername() {
        return username;
    }

    /**
	 * @brief This function sets the username to the greet.xhtml page.
	 * @param username
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    
	/**
	 * @brief This function gets the role from the greet.xhtml page.
	 * @return role
	 */
    public int getRole() {
        return role;
    }

    /**
	 * @brief This function sets the role to the greet.xhtml page.
	 * @param role
	 */
    public void setRole(int role) {
        this.role = role;
    }
    
	/**
	 * @brief This function gets the password from the greet.xhtml page.
	 * @return password
	 */
    public String getPassword() {
        return password;
    }

    /**
	 * @brief This function sets the password to the greet.xhtml page.
	 * @param password
	 */
    public void setPassword(String password) {
        this.password = password;
    }

	/**
	 * @brief This function gets the message from the greet.xhtml page.
	 * @return message
	 */
    public String getGreeting() {
        return greeting;
    }
    
	/**
	 * @brief This function is used to encode the password fed by the user
	 * @return encoded password
	 */
    public String encode(String value){
    	 Base64.Encoder encoder = Base64.getMimeEncoder();  
         String eStr = encoder.encodeToString(value.getBytes());  
         return eStr;
    }

}
