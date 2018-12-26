package com.ec.lab;

import javax.ejb.Remote;

@Remote
public interface SBSingletonRemote {
     public String trainModel(String inputFilePath, String outputFilePath);
}