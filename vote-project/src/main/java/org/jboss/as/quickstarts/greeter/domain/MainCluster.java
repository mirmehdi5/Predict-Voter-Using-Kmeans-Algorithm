package org.jboss.as.quickstarts.greeter.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @brief It is a Serializable class used to map the clusters in the database
 * @author MIR
 *
 */
public class MainCluster implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<ClusterModel> objct;
	
	public MainCluster() {
        super();
    }
	
	//Getters
	
	public final List<ClusterModel> getData() {
        return objct;
    }
	
	//Setters
	 
	 public final void setData(List<ClusterModel> objct) {
		 this.objct = objct;
	 }
}
