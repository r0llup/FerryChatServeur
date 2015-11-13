/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Sh1fT
 */

public final class PropertiesLauncher {
    protected Properties properties;

    /**
     * Creates new instance PropertiesLauncher
     * @param filename 
     */
    public PropertiesLauncher(String filename) {
        this.setProperties(new Properties());
        this.loadProperties(filename);
    }

    public Properties getProperties() {
        return this.properties;
    }

    protected void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * Load the properties file
     * @param filename 
     */
    protected void loadProperties(String filename) {
        try {
            this.getProperties().load(ClassLoader.getSystemResourceAsStream(filename));
        } catch(IOException ex) {
            System.out.println("Aïe Aïe Aïe !\n" + ex.getLocalizedMessage());
            System.exit(1);
        }
    }
}