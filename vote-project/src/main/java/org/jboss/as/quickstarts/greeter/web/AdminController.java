package org.jboss.as.quickstarts.greeter.web;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.as.quickstarts.greeter.domain.User;
import org.jboss.as.quickstarts.greeter.domain.UserDao;

/**
 * 
 * @author MIR
 * @class AdminController
 * @brief This controller is used change role of the users present in the database.
 *
 */
@Named
@RequestScoped
public class AdminController {
	@Named
    @Produces
    @RequestScoped
    private User newUser = new User();

    @Inject
    private UserDao userDao;
    
    private String name;
    
    private int role;

    private String greeting;
    
    /**
	 * @brief This function calls the getForUsername and changeUserRole to change the role of the users in the database.
	 */
    public void change() {
    	User user = userDao.getForUsername(name);
    	if (user != null) {   	
    		user.setRole(getRole());
    		userDao.changeUserRole(user);
    		greeting = " Role Updated to " +user.getRole();
    	} else {
            greeting = "No such user exists! Please Enter a valid username";
        }
    }
    
	/**
	 * @brief This function gets the name attribute from the admin_page.XHTML page.
	 */
    public String getName() {
        return name;
    }

	/**
	 * @brief This function sets the name attribute to the admin_page.XHTML page.
	 */
    public void setName(String name) {
        this.name = name;
    }
    
	/**
	 * @brief This function gets the role attribute from the admin_page.XHTML page.
	 */
    public int getRole() {
        return role;
    }
	
    /**
     * @brief This function sets the role attribute to the admin_page.XHTML page.
	 */
    public void setRole(int role) {
        this.role = role;
    }

    /**
	 * @brief This function gets the greeting attribute from the admin_page.XHTML page.
	 */
    public String getGreeting() {
        return greeting;
    }
}
