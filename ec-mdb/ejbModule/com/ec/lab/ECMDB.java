package com.ec.lab;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "testQueue", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue/test"),
})
public class ECMDB implements MessageListener {
    @EJB
    SBStateless sbsl;

    public void onMessage(Message message) {
        try {
            String mstr = ((TextMessage) message).getText();
            System.out.println("Message received: "+ mstr);
            String args[] = mstr.split(" ");
            System.out.println("Input: "+ args[0]);
            System.out.println("Output: "+ args[1]);
           System.out.println("EJB invoking: "+ sbsl.trainData(args[0], args[1]));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}