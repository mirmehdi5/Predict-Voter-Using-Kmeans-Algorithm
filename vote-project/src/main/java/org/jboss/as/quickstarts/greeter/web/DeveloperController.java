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

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.as.quickstarts.greeter.domain.ModelDao;

/**
 * 
 * @author MIR
 * @class Developer Controller
 * @brief This controller used for training the data and saving it in the database.It is also used for calculating the accuracy of the trained clusters.
 *
 */
@Named
@RequestScoped
public class DeveloperController {
	
	@Inject
    private ModelDao modelDao;

    private String inputfilepath;
    
    private String modelclassname;

    private String outputfilepath;
    
    private String modelfilepath;
    
    private String greeting;

    /**
     * @brief This functions calls the trainModel function to create the clusters using Kmeans algorithm
     */
    public void trainmodel() {
    	String result = modelDao.trainModel(getInputfilepath(),getOutputfilepath());
    	greeting = result;
    }
    
    /**
     * @brief This function calls the saveModel function in the bean to save the clusters in the database.
     */
    public void savemodel() {
    	String result;
		try {
			result = modelDao.saveModel(getModelfilepath(), getModelclassname());
			greeting = result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    /**
     * @brief This function calls the calcAccuracy in the bean to calculate the accuracy of the algorithm.
     */
	public void calcacc() {	
    	String result;
		try {
			result = modelDao.calcAccuracy(getModelfilepath(), getModelclassname());
			greeting = result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

	/**
	 * @brief This function gets the input file path in HDFS from the developer_page.xhtml.
	 * @return the input file path in string format.
	 */
	public String getInputfilepath() {
        return inputfilepath;
    }

	/**
	 * This function sets the input file path to the developer_page.xhtml.
	 * @param inputfilepath
	 */
    public void setInputfilepath(String inputfilepath) {
        this.inputfilepath = inputfilepath;
    }
    
    /**
	 * @brief This function gets the model file path in HDFS from the developer_page.xhtml.
	 * @return the model file path in string format.
	 */
    public String getModelfilepath() {
        return modelfilepath;
    }

    /**
	 * This function sets the model file path to the developer_page.xhtml.
	 * @param modelfilepath
	 */
    public void setModelfilepath(String modelfilepath) {
        this.modelfilepath = modelfilepath;
    }
    
    /**
	 * @brief This function gets the output file path in HDFS from the developer_page.xhtml.
	 * @return the output file path in string format.
	 */
    public String getOutputfilepath() {
        return outputfilepath;
    }

    /**
	 * This function sets the output file path to the developer_page.xhtml.
	 * @param outputfilepath
	 */
    public void setOutputfilepath(String outputfilepath) {
        this.outputfilepath = outputfilepath;
    }
    
    /**
	 * @brief This function gets the model class name in the database from the developer_page.xhtml.
	 * @return the model file path in string format.
	 */
    public String getModelclassname() {
        return modelclassname;
    }

    /**
	 * This function sets the model class name to be saved in the database to the developer_page.xhtml.
	 * @param modelclassname
	 */
    public void setModelclassname(String modelclassname) {
        this.modelclassname = modelclassname;
    }

    /**
	 * @brief This function gets the greeting attribute from the developer_page.XHTML page.
	 */
    public String getGreeting() {
        return greeting;
    }

}
