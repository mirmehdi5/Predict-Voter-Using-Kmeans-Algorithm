package com.ec.lab;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/**
*
* @author Mir Mohammed Mehdi
* Email : mehd8970@mylaurier.ca
*/
@Path("/")
@RequestScoped
public class StatsRS {  

    @GET
    @Path("/getjson")
    @Produces({ "application/json" })
    public String predictJSon() {
    	String result = "";
    	try{
    		
    		result = "{\"key\":\"An election is an event in which one or more people make a choice, selecting representatives. Elections "+
    					"are held by many different kinds of organizations, such as corporations, non-profit organizations and "+
    					"governments. In this website, the user is requested to grade a political party's performance based on "+
    					"various parameters such as healthcare, education etc. This data is used to predict as to which party the user "+
    					"would support in the election, using K-Means algorithm. This would help the political party in determining their "+
    					"position in the voter's mind, self-analyze and proceed with a suitable campaign so that they could try to change "+
    					"the outcome to their favour to the maximum possible extent. \"}";	
    	}catch(Exception ex){
    	}
    	return result;
    }
      
}