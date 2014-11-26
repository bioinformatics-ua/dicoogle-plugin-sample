/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.ieeta.dicoogle.plugin.demo.dicooglepluginsample;


import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

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
		
                System.out.println("PluginController was set:"+pi);
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

	public HandlerList getJettyHandlers() {
		// TODO Auto-generated method stub
		
		ServletContextHandler handler = new ServletContextHandler();
		handler.setContextPath("/sample");
		handler.addServlet(new ServletHolder(new RSIJettyWebService()), "/hello");

		
		HandlerList l = new HandlerList();
		l.addHandler(handler);
		
		return l;
	}

	public DicooglePlatformInterface getPluginController() {
		return pluginController;
	}


}
