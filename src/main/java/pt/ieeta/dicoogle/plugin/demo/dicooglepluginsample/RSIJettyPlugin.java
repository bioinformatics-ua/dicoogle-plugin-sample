/**
 * Copyright (C) 2014  Universidade de Aveiro, DETI/IEETA, Bioinformatics Group - http://bioinformatics.ua.pt/
 *
 * This file is part of Dicoogle/dicoogle-plugin-sample.
 *
 * Dicoogle/dicoogle-plugin-sample is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dicoogle/dicoogle-plugin-sample is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Dicoogle.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.ieeta.dicoogle.plugin.demo.dicooglepluginsample;

import java.net.URL;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ua.dicoogle.sdk.JettyPluginInterface;
import pt.ua.dicoogle.sdk.core.DicooglePlatformInterface;
import pt.ua.dicoogle.sdk.core.PlatformCommunicatorInterface;
import pt.ua.dicoogle.sdk.settings.ConfigurationHolder;

/** Example of a Jetty Servlet plugin.
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class RSIJettyPlugin implements JettyPluginInterface, PlatformCommunicatorInterface {
    private static final Logger logger = LoggerFactory.getLogger(RSIJettyPlugin.class);
    
    private boolean enabled;
    private ConfigurationHolder settings;
    private DicooglePlatformInterface platform;
    private final RSIJettyWebService webService;
    
    public RSIJettyPlugin() {
        this.webService = new RSIJettyWebService();
        this.enabled = true;
    }

    @Override
    public void setPlatformProxy(DicooglePlatformInterface pi) {
        this.platform = pi;
        // since web service is not a plugin interface, the platform interface must be provided manually
        this.webService.setPlatformProxy(pi);
    }

    @Override
    public String getName() {
        return "RSI";
    }

    @Override
    public boolean enable() {
        this.enabled = true;
        return true;
    }

    @Override
    public boolean disable() {
        this.enabled = false;
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setSettings(ConfigurationHolder settings) {
        this.settings = settings;
        // use settings here
    }

    @Override
    public ConfigurationHolder getSettings() {
        return settings;
    }


    @Override
    public HandlerList getJettyHandlers() {

        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/sample");
        handler.addServlet(new ServletHolder(this.webService), "/hello");
        
        /* TODO: Change here if you want 
         * During the development stage you can point to a directory in your machine,
         * such as: file:///Users/bastiao/myHtml5Files
         */
        URL url = RSIJettyPlugin.class.getResource("/WEBAPP");
        logger.debug("Retrieving web app from \"{}\"", url);
        String directoryToServeAssets = url.toString();
        
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

}
