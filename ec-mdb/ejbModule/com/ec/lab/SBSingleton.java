package com.ec.lab;

import java.io.IOException;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Session Bean implementation class SBSingleton
 */
@Singleton
@LocalBean
public class SBSingleton implements SBSingletonRemote, SBSingletonLocal {

    /**
     * Default constructor. 
     */
    public SBSingleton() {
    }

    @Override
    public String trainModel(String inputFilePath, String outputFilePath) {
    	System.out.println("Build the Model");
		try {
			ProcessBuilder pb = new ProcessBuilder("C:/enterprise/hadoop-2.7.1/bin/hadoop.cmd", "jar", "C:/Users/MIR/Documents/Term 1/Enterprize Computing/Project/kmeans-hd.jar","com.ec.lab.KmeansMR", inputFilePath, outputFilePath);		
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Training Started... It may take upto 8 minutes to train the data";
    }
}