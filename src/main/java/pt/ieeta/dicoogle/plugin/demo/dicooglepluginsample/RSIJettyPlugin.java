/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ieeta.dicoogle.plugin.demo.dicooglepluginsample;

import java.io.File;
import java.net.URL;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import pt.ua.dicoogle.sdk.JettyPluginInterface;
import pt.ua.dicoogle.sdk.core.DicooglePlatformInterface;
import pt.ua.dicoogle.sdk.core.PlatformCommunicatorInterface;
import pt.ua.dicoogle.sdk.settings.ConfigurationHolder;

/**
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class RSIJettyPlugin implements JettyPluginInterface, PlatformCommunicatorInterface {

    private ConfigurationHolder settings;
    private DicooglePlatformInterface pluginController;

    public RSIJettyPlugin() {
        super();
        new RSIJettyWebService();
    }

    public void setPlatformProxy(DicooglePlatformInterface pi) {
        this.pluginController = pi;
        RSIJettyWebService.setPlugin(this);

        System.out.println("PluginController was set:" + pi);
    }

    public String getName() {
        return "RSIJetty";
    }

    public boolean enable() {
        // TODO Auto-generated method stub
        return true;
    }

    public boolean disable() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    public void setSettings(ConfigurationHolder settings) {
        // TODO Auto-generated method stub
        this.settings = settings;
    }

    public ConfigurationHolder getSettings() {
        // TODO Auto-generated method stub
        return settings;
    }

    private String getJarFolder() {
        
        String name = getClass().getName().replace('.', '/');
        name = getClass().getResource("/" + name + ".class").toString();
        name = name.substring(0, name.indexOf("!"));
        
        String s = "";
        for (int k = 0; k < name.length(); k++) {
            s += name.charAt(k);
            if (name.charAt(k) == ' ') {
                k += 2;
            }
        }
        return s.replace('/', File.separatorChar) + "!";
    }

    public HandlerList getJettyHandlers() {

        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/sample");
        handler.addServlet(new ServletHolder(new RSIJettyWebService()), "/hello");

        /* TODO: Change here if you want 
         * For deploymennt stage you can point for a directory in your machine,
        * such as: file:///Users/bastiao/myHtml5Files
         * */
        String directoryToServeAssets = getJarFolder() + "/WEBAPP/";
        
        final WebAppContext webpages = new WebAppContext(directoryToServeAssets, "/dashboardSample");
        webpages.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "true"); // disables directory listing
        webpages.setInitParameter("useFileMappedBuffer", "false");
        webpages.setInitParameter("cacheControl", "max-age=0, public");

        webpages.setWelcomeFiles(new String[]{"index.html"});

        HandlerList l = new HandlerList();
        l.addHandler(handler);
        l.addHandler(webpages);

        return l;
    }

    public DicooglePlatformInterface getPluginController() {
        return pluginController;
    }

}
