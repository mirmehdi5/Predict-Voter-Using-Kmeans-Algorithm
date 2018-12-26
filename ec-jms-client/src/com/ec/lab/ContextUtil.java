package com.ec.lab;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ContextUtil {
    public static Context getInitialContext() throws NamingException {
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.jboss.naming.remote.client.InitialContextFactory");
        props.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming");
        props.setProperty(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        props.put(Context.SECURITY_PRINCIPAL, "quickstartUser");
        props.put(Context.SECURITY_CREDENTIALS, "quickstartPwd1!");
        props.put("jboss.naming.client.ejb.context", true);
        Context context = new InitialContext(props);
        return context;
    }
}