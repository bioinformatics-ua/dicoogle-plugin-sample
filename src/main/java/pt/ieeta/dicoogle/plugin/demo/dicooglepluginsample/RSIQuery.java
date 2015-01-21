
package pt.ieeta.dicoogle.plugin.demo.dicooglepluginsample;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private SearchResult generateSearchResult()
    {
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("PatientID",UUID.randomUUID().toString() );
        map.put("PatientName",UUID.randomUUID().toString() );
        map.put("SOPInstanceUID",UUID.randomUUID().toString() );
        map.put("SeriesInstanceUID",UUID.randomUUID().toString() );
        map.put("StudyInstanceUID",UUID.randomUUID().toString() );
        map.put("Modality","CT");
        map.put("StudyDate","20150120");
        map.put("SeriesDate","20150120");
        
        SearchResult r = null;
        try {
            r = new SearchResult(new URI("file:///1"+UUID.randomUUID().toString() ), 1, map);
        } catch (URISyntaxException ex) {
            Logger.getLogger(RSIQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return r;
    }
    
    
    @Override
    public Iterable<SearchResult> query(String query, Object... parameters) {
        
        List<SearchResult> results =  new ArrayList<SearchResult>();
        results.add(generateSearchResult());
        results.add(generateSearchResult());
        results.add(generateSearchResult());
        results.add(generateSearchResult());
        results.add(generateSearchResult());
        
        
        return results;
        
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
