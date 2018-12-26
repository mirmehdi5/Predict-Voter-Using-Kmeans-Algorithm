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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @brief This class is implements the ModelDao interface. This class is used for all the data related operations.
 * @author MIR
 *
 */
public class ManagedBeanModelDao implements ModelDao  {
	
    @Inject
    private EntityManager entityManager;

    @Inject
    private UserTransaction utx;
    
    private static final Logger logger = LoggerFactory.getLogger(ManagedBeanModelDao.class.getName());  

    /**
     * @brief This function executes the ECJMSProducer.jar file which in turn invokes a Message driven bean which again calls an EJB to create clusters using Kmeans algorithm.
     */
	public String trainModel(String Inputfilepath, String Outputfilepath){		
		String result = "";
		try {
			String hdfsPath = "hdfs://localhost:9000";
			
			Configuration conf = new Configuration();
			conf.set("fs.defaultFS", hdfsPath);

			//get FileSystem
			FileSystem fs = FileSystem.get(conf);
			
			//read a file
			Path path = new Path(Inputfilepath);
			if (!fs.exists(path)) {
				result = "File" + " does not exists";
			}
			else {
				ProcessBuilder pb = new ProcessBuilder("java", "-jar", "C:/Users/MIR/Documents/Term 1/Enterprize Computing/Project/ECJMSProducer.jar",Inputfilepath,Outputfilepath);		
				pb.start();
				
				logger.info("The user has trained the data!"); 
				result = "Training Started! It may take upto 8 minutes to train the model.";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	/**
	 * @brief This function saves the cluster in the database.
	 */
	public String saveModel(String modelFilepath, String modelClassname) throws FileNotFoundException, IOException{
		String hdfsPath = "hdfs://localhost:9000";
		String result = "";
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", hdfsPath);
		//get FileSystem
		FileSystem fs = FileSystem.get(conf);		
		//read a file
		Path path = new Path(modelFilepath);
		try{
			if (!fs.exists(path)) {
				result = "File does not exists";
				return result;
			}
			else {
				MainCluster objCluster = readDataFromHadoop(conf,path,"t");
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
	 			ObjectOutput out = null;
	    		try {
	    		  out = new ObjectOutputStream(bos);   
	    		  out.writeObject(objCluster);
	    		  out.flush(); 
	    		} finally {
	    		  try {
	    		    bos.close();
	    		  } catch (IOException ex) {
	    		    // ignore close exception
	    		  }
	    		}				
	 			Model objModel = new Model();
	 			objModel.setName("test");
	 			objModel.setClassname(modelClassname);
	 			objModel.setObject(bos.toByteArray());
	 			objModel.setDate(new Timestamp(System.currentTimeMillis()));
				try {
					utx.begin();
					entityManager.persist(objModel);  
				} catch (NotSupportedException | SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
	                try {
						utx.commit();
					} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
							| HeuristicRollbackException | SystemException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }				 
				logger.info("The user has saved the data in the database!"); 
				result ="Data is trained Successfully and is stored in the database";
			}
		}catch(Exception ex){
			result = "Please check the input";
		}
		
		return result;
	}
	
	/**
	 * @brief This function has the logic to the predict which party a particular voter would support using the clusters in the database.
	 */
	public String predictModel(Double[] input, String Modelname){
		String result ="";
		 try{
	            Model objClus = new Model();	            
	            Query query = entityManager.createQuery("select u from Model u where u.classname = :classname");
	    		query.setParameter("classname", Modelname);
	    		objClus = (Model) query.getSingleResult();			 
	    		MainCluster objHouse = new MainCluster();	    		
	    		ByteArrayInputStream in = new ByteArrayInputStream(objClus.getObject());
	    	    ObjectInputStream is = new ObjectInputStream(in);
	    	    objHouse = (MainCluster) is.readObject();	        	
	        	int[] a_arr = new int[16];	        	
	        	for(int val : a_arr){
	        		val = 0;
	        	}
	        	
	        	double[][] dis = new double[16][objHouse.getData().size()];
	        	for(int i=0;i<16;i++){
	        		for(ClusterModel objModel : objHouse.getData()){
		        		dis[i][a_arr[i]] = Math.abs(input[i] - Double.parseDouble(objModel.getData()[i]));
		        		a_arr[i]++;
		        	}
	        	}
	        		        	
	        	int counter[] = new int[objHouse.getData().size()];	        	
	        	for(int coun : counter){
	        		coun = 0;
	        	}	        	
	        	
	        	for(int len=0; len<dis.length;len++){
	        		for(int ind=0; ind<dis[len].length-1;ind++){
		        		if(dis[len][ind]>0 && dis[len][ind+1]>0){
		        			if(dis[len][ind] < dis[len][ind+1]){
		            			counter[ind]++;
		            		}else{
		            			counter[ind+1]++;
		            		}
		        		}
		        	}
	        	}
	        	
	        	int min = counter[0];
	        	int near = 0;	        	
	        	for(int ind=1;ind<dis[0].length;ind++){
	        		if(min < counter[ind]){
	        			min = counter[ind];
	        			near = ind;
	        		}
	        	}
	        	
	        	int perc = counter[0];
	        	if(counter[0] < counter[1]){
	        		perc = counter[1];
	        	}
	        	
	        	double calc = (((double)perc)/16.0)*100;	    	    
	    	    String value =  "The canditate has "+ calc +"% chances of voting the " + objHouse.getData().get(near).getData()[16]+" party";	    	    
	    		result =  value;

	        }catch(Exception ex){
	        	if(ex.toString().contains("No entity found for query")){
	        		result = "Please enter a valid class name";
	        	}else{
	        		result = ex.toString();
	        	}	
	        }
		 return result;
	}
	

	/**
	 * @brief This function is used to calculate the accuracy of the algorithm.
	 */
	public String calcAccuracy(String inputpath, String ModelClassName) throws IOException{		
		logger.info("Calculating the accuracy of the algorithm!"); 
		String acc = "";
		try{
			String hdfsPath = "hdfs://localhost:9000";
			Configuration conf = new Configuration();
			conf.set("fs.defaultFS", hdfsPath);
			//get FileSystem
			FileSystem fs = FileSystem.get(conf);			
			//read a file
			Path path = new Path(inputpath);
			if (!fs.exists(path)) 
				acc = "File " + " does not exists";
			
			else {				
				MainCluster objCluster = readDataFromHadoop(conf,path,"n");
				int len = objCluster.getData().size();				
				int rep_count = 0;		
				int dem_count = 0;				
				int dem_crt = 0;
				int rep_crt = 0;				
				for(int i=0;i<len;i++){
					
						Double[] doub_array =new Double[objCluster.getData().get(i).getData().length];
						for(int len_val=0; len_val < doub_array.length-1; len_val++)
							doub_array[len_val] = Double.parseDouble(objCluster.getData().get(i).getData()[len_val]);
						
						String pred_result = predictModel(doub_array, ModelClassName);
						
						if(pred_result.contains("demo") && objCluster.getData().get(i).getData()[objCluster.getData().get(i).getData().length-1].contains("demo"))
							dem_crt++;
						
						if(pred_result.contains("republ") && objCluster.getData().get(i).getData()[objCluster.getData().get(i).getData().length-1].contains("republ"))
							rep_crt++;
															
						if(objCluster.getData().get(i).getData()[objCluster.getData().get(i).getData().length-1].contains("republ"))
							rep_count++;
						else
							dem_count++;
					
				}				
				double rep_accuracy = ((double)rep_crt)/((double)rep_count) * 100;
				double dem_accuracy = ((double)dem_crt)/((double)dem_count) * 100;				
				double final_accuracy = (rep_accuracy + dem_accuracy)/2;				
				acc = "This algorithm is approximately "+final_accuracy+"% accurate.";				
				logger.info(acc);				
			}
		}catch(Exception ex){
			acc = ex.toString();
		}		
		return acc;
	}
	

	public MainCluster readDataFromHadoop(Configuration conf,Path path, String condition) throws FileNotFoundException, IOException{
		//get FileSystem
		FileSystem fs = FileSystem.get(conf);
		FSDataInputStream in = fs.open(path);
		byte[] b = new byte[1024];
		int numBytes = 0;
		String s = new String();
		while ((numBytes = in.read(b)) > 0) {
			s += new String(b);
		}
		
		String[] data = s.split("\\"+condition+"");			
		List<String> objList = new ArrayList<String>();
		for(String value : data){
			if(value.contains("\n")){
				String[] res = value.split("\n");
				if(!res[1].isEmpty())
					objList.add(res[1]);
			}
			else{
				objList.add(value);
			}				
		}
		
		MainCluster objCluster = new MainCluster();
		List<ClusterModel> list_Model = new ArrayList<ClusterModel>();			
		for(String value : objList){
			if(!value.isEmpty() && !value.equals("") && !value.equals(null) && value.contains(",")){
				String[] res = value.split(",");
				ClusterModel objModel = new ClusterModel();
				String[] data_list = new String[res.length];
				for(int i = 0; i < res.length;i++){
					data_list[i] = res[i];
				}					
				objModel.setData(data_list);					
				list_Model.add(objModel);
			}	
		}
		objCluster.setData(list_Model);
		return objCluster;
	}
}
