package com.ec.lab;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

/**
 * Session Bean implementation class SBStateless
 */
@Stateless
@LocalBean
public class SBStateless implements SBStatelessRemote, SBStatelessLocal {
    private static final Logger LOGGER = Logger.getLogger(SBStateless.class);
    
    @EJB
    private SBSingletonLocal sbsgt;

    /**
     * Default constructor. 
     */
    public SBStateless() {
    }
    
    @Override
    public String trainData(String input, String output) {
        return sbsgt.trainModel(input, output);
    }

}