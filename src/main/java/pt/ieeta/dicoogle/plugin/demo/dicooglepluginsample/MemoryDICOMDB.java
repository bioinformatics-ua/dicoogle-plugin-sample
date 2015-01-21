package pt.ieeta.dicoogle.plugin.demo.dicooglepluginsample;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class MemoryDICOMDB {
    
    private List<String> patientNames = null;
    private List<String> studies = null;
    private List<String> series = null;
    private List<String> sopInstanceUIDs = null;
    
    public MemoryDICOMDB ()
    {
        sopInstanceUIDs = new ArrayList<>();
    }
    
    public void add(String patient, String study, String serie, String sop)
    {
        sopInstanceUIDs.add(sop);
    }
    public void remove(String sop)
    {
        sopInstanceUIDs.remove(sop);
    }
    

}
