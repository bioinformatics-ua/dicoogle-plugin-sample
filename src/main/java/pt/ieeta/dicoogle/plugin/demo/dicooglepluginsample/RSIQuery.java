
package pt.ieeta.dicoogle.plugin.demo.dicooglepluginsample;

import java.util.ArrayList;
import pt.ua.dicoogle.sdk.QueryInterface;
import pt.ua.dicoogle.sdk.datastructs.SearchResult;
import pt.ua.dicoogle.sdk.settings.ConfigurationHolder;

/**
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class RSIQuery implements QueryInterface {

    
    private MemoryDICOMDB memoryDicomDB = null;
    
    public RSIQuery(MemoryDICOMDB memoryDicomDB) 
    {
        this.memoryDicomDB = memoryDicomDB;
    }
    
    
    @Override
    public Iterable<SearchResult> query(String query, Object... parameters) {
        return new ArrayList<SearchResult>();
    }

    @Override
    public String getName() {
        return "RSIQUery";
    }

    @Override
    public boolean enable() {
        return true;
    }

    @Override
    public boolean disable() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setSettings(ConfigurationHolder settings) {
        
    }

    @Override
    public ConfigurationHolder getSettings() {
        return null;
    }

}
