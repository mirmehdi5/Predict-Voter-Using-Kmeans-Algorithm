package com.ec.lab;

import javax.ejb.Local;

@Local
public interface SBSingletonLocal {
    public String trainModel(String inputFilePath, String outputFilePath);
}