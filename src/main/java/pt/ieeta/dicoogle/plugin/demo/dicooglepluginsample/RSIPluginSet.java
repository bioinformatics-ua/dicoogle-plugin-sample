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

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ua.dicoogle.sdk.IndexerInterface;
import pt.ua.dicoogle.sdk.JettyPluginInterface;
import pt.ua.dicoogle.sdk.PluginSet;
import pt.ua.dicoogle.sdk.QueryInterface;
import pt.ua.dicoogle.sdk.StorageInterface;
import pt.ua.dicoogle.sdk.settings.ConfigurationHolder;

/** The main plugin set.
 * 
 * This is the entry point for all plugins.
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 * @author Eduardo Pinho <eduardopinho@ua.pt>
 */
@PluginImplementation
public class RSIPluginSet implements PluginSet {
    // use slf4j for logging purposes
    private static final Logger logger = LoggerFactory.getLogger(RSIPluginSet.class);
    
    // We will list each of our plugins as an attribute to the plugin set
    private final RSIIndexer indexer;
    private final RSIQuery query;
    private final RSIWebResource web;
    private final RSIJettyPlugin jettyWeb;
    private final RSIStorage storage;
    
    // Additional resources may be added here.
    private final MemoryDICOMDB memoryDicomDB = new MemoryDICOMDB();
    private ConfigurationHolder settings;
    
    public RSIPluginSet() throws IOException {
        logger.info("Initializing RSI Plugin Set");

        // construct all plugins here
        this.indexer = new RSIIndexer(memoryDicomDB);
        this.jettyWeb = new RSIJettyPlugin();
        this.query = new RSIQuery(memoryDicomDB);
        this.storage = new RSIStorage();
        this.web = new RSIWebResource();
        
        logger.info("RSI Plugin Set is ready");
    }
    

    @Override
    public Collection<IndexerInterface> getIndexPlugins() {
        /* Since we only have one indexer, we can return a singleton. For more than
         * one plugin, Arrays.asList(...) is a nice alternative. If there are no
         * plugins of this type in the set, simply return an empty set or list.
         *
         * This cast in the argument is needed (only in versions prior to Java 8),
         * otherwise the return type cannot be resolved properly.
         */
        return Collections.singleton((IndexerInterface) this.indexer);
    }

    @Override
    public Collection<QueryInterface> getQueryPlugins() {
        return Collections.singleton((QueryInterface) this.query);
    }
    
    /** This method is used to retrieve a name for identifying the plugin set. Keep it as a constant value.
     * 
     * @return a unique name for the plugin set
     */
    @Override
    public String getName() {
        return "dicoogle-plugin-sample";
    }

    @Override
    public Collection<ServerResource> getRestPlugins() {
        return Collections.singleton((ServerResource) this.web);
    }

    @Override
    public Collection<JettyPluginInterface> getJettyPlugins() {
        return Collections.singleton((JettyPluginInterface) this.jettyWeb);
    }

    @Override
    public void shutdown() {
        logger.info("Plugin sample will shutdown");
    }

    @Override
    public Collection<StorageInterface> getStoragePlugins() {
        return Collections.singleton((StorageInterface) this.storage);
    }

    @Override
    public void setSettings(ConfigurationHolder xmlSettings) {
        this.settings = xmlSettings;
    }

    @Override
    public ConfigurationHolder getSettings() {
        return this.settings;
    }

}