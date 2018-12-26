package com.ec.lab;

import javax.ejb.Local;

@Local
public interface SBStatelessLocal {
    public String trainData(String input, String output);
}