package pt.ieeta.dicoogle.plugin.demo.dicooglepluginsample;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class MemoryDICOMDB {
    
    private List<String> sopInstanceUIDs = null;
    public MemoryDICOMDB ()
    {
        sopInstanceUIDs = new ArrayList<>();
    }
    
    public void add(String sop)
    {
        sopInstanceUIDs.add(sop);
    }
    public void remove(String sop)
    {
        sopInstanceUIDs.remove(sop);
    }
    

}
