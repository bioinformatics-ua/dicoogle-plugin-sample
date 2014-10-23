
package pt.ieeta.dicoogle.plugin.demo.dicooglepluginsample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import org.restlet.resource.ServerResource;
import pt.ua.dicoogle.sdk.GraphicalInterface;
import pt.ua.dicoogle.sdk.IndexerInterface;
import pt.ua.dicoogle.sdk.JettyPluginInterface;
import pt.ua.dicoogle.sdk.PluginSet;
import pt.ua.dicoogle.sdk.QueryInterface;
import pt.ua.dicoogle.sdk.StorageInterface;
import pt.ua.dicoogle.sdk.core.DicooglePlatformInterface;
import pt.ua.dicoogle.sdk.core.PlatformCommunicatorInterface;
import pt.ua.dicoogle.sdk.settings.ConfigurationHolder;

/**
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
@PluginImplementation
public class RSIPluginSet implements PluginSet, PlatformCommunicatorInterface{
    
    List<IndexerInterface> RSIIndexerList;
    List<QueryInterface> RSIQueryList;
    List<ServerResource> RSIwebservices;
    
    /* if you need to handle storage, you also should add it here */
    
    private MemoryDICOMDB memoryDicomDB = new MemoryDICOMDB();
    
    public RSIPluginSet() throws IOException {
        System.err.println("Initializing RSI Plugin Set");
        
        RSIIndexerList = new ArrayList<>();
        RSIQueryList = new ArrayList<>();
        RSIwebservices = new ArrayList<>();
        
        RSIwebservices.add(new RSIWebService());
        RSIIndexerList.add(new RSIIndex(memoryDicomDB));
        
        /* More plugins should be added here */ 
        
        System.err.println("Completed Init of RSI Plugin Set");
    }
    
    @Override
    public void setPlatformProxy(DicooglePlatformInterface core) {
        // You can acess the core. It may be useful to do interaction between 
        // other external plugins. 
    }
    
    
    @Override
    public List<IndexerInterface> getIndexPlugins() {
        return RSIIndexerList;
    }

   
    @Override
    public List<QueryInterface> getQueryPlugins() {
        return RSIQueryList;
    }
    
    @Override
    public String getName(){return "RSI Plugin Set";}

    @Override
    public List<ServerResource> getRestPlugins() {
        return RSIwebservices;
    }

    @Override
    public List<JettyPluginInterface> getJettyPlugins() {
        return new ArrayList() ;
    }

    @Override
    public void shutdown() {
        
    }

    @Override
    public Collection<StorageInterface> getStoragePlugins() {
        return new ArrayList() ;
    }

    @Override
    public void setSettings(ConfigurationHolder xmlSettings) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ConfigurationHolder getSettings() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<GraphicalInterface> getGraphicalPlugins() {
        return new ArrayList() ;
    }
}