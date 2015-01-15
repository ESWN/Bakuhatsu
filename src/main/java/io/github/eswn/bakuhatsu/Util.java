package io.github.eswn.bakuhatsu;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public final class Util{

    private static final Logger LOG = Logger.getLogger(Util.class.getName());
    private static ResourceBundle config;

    static{
        config = ResourceBundle.getBundle("io.github.eswn.bakuhatsu.Config");
    }

    private Util(){
    }

    public static String getConfig(String key){
        String value = System.getProperty(key);
        if (value != null)
            return value;
        try{
            return config.getString(key);
        }catch (MissingResourceException ex){
            return null;
        }
    }
}
