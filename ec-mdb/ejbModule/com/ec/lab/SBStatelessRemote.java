package com.ec.lab;

import javax.ejb.Remote;

@Remote
public interface SBStatelessRemote {
     public String trainData(String input, String output);
}