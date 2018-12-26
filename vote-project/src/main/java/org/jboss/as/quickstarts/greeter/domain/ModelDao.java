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

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @brief This interface consists of all the model related operations.
 * @author MIR
 *
 */
public interface ModelDao {
   
	/**
	 * @brief This function trains the data using Kmeans algorithm and creates clusters.
	 * @param input
	 * @param Output
	 * @return
	 */
	String trainModel(String input, String Output);
	
	/**
	 * @brief This function saves the cluster in the database.
	 * @param modelfilepath
	 * @param modelClassName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	String saveModel(String modelfilepath, String modelClassName)throws FileNotFoundException, IOException;
	
	/**
	 * @brief This function predicts which party would the voter support.
	 * @param input1
	 * @param input2
	 * @param input3
	 * @param input4
	 * @param input5
	 * @param input6
	 * @param input7
	 * @param input8
	 * @param input9
	 * @param input10
	 * @param input11
	 * @param input12
	 * @param input13
	 * @param input14
	 * @param input15
	 * @param input16
	 * @param Model
	 * @return
	 */
	String predictModel(Double[] input, String Model);
	
	/**
	 * @brief This function calculates the accuracy of the algorithm used.
	 * @param inputpath
	 * @param ModelClassName
	 * @return
	 * @throws IOException
	 */
	String calcAccuracy(String inputpath, String ModelClassName) throws IOException;
}
