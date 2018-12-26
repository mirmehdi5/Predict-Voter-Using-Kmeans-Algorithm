package org.jboss.as.quickstarts.greeter.domain;

import java.io.Serializable;

/**
 * @brief It is a Serializable class used to map the clusters in the database
 * @author MIR
 *
 */
public class ClusterModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String[] data;
	
	public ClusterModel() {
        super();
    }
	
	//Getters
	
	public final String[] getData() {
        return data;
    }
	
	//Setters
	 
	 public final void setData(String[] data) {
		 this.data = data;
	 }
}
