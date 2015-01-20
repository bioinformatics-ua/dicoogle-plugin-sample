package pt.ieeta.dicoogle.plugin.demo.dicooglepluginsample;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;
import org.dcm4che2.io.StopTagInputHandler;
import pt.ua.dicoogle.sdk.IndexerInterface;
import pt.ua.dicoogle.sdk.StorageInputStream;
import pt.ua.dicoogle.sdk.datastructs.Report;
import pt.ua.dicoogle.sdk.settings.ConfigurationHolder;
import pt.ua.dicoogle.sdk.task.ProgressCallable;
import pt.ua.dicoogle.sdk.task.Task;

/**
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class RSIIndex implements IndexerInterface{
    
    
    private MemoryDICOMDB memoryDicomDB = null;
    
    
    public RSIIndex(MemoryDICOMDB memoryDicomDB) 
    {
        this.memoryDicomDB = memoryDicomDB;
    }
    
    private Report indexURI(StorageInputStream storage) throws IOException
    {
        BufferedInputStream bufferedStream = null;
        DicomInputStream dicomStream = null;
        try (InputStream fileStream = storage.getInputStream()) {
            bufferedStream = new BufferedInputStream(fileStream);
            dicomStream = new DicomInputStream(bufferedStream);
            //dicomStream.setFileSize(file.length());
            dicomStream.setHandler(new StopTagInputHandler(Tag.PixelData));
            DicomObject dicomObject = dicomStream.readDicomObject();
            
            String SOPInstanceUID = dicomObject.getString(Tag.SOPInstanceUID);
            String PatientName = dicomObject.getString(Tag.PatientName);
            System.err.println("SOP Instance UID: "+SOPInstanceUID);
            System.err.println("PatientName: "+PatientName);

        
        }
        catch(Exception e )
        {
            System.err.println("indexURI: Do whatever you want.");
        }
        finally{
            if (bufferedStream!=null)
                bufferedStream.close();
        }
            
        // Do whatever you want
        // System.out.println("Size: "+ storage.getSize()); // Works with new SDK (1.0)
        return new Report();
    }
    
    @Override
    public Task<Report> index(final StorageInputStream file) {
        
        
        return new Task<>(
                new ProgressCallable<Report>() {
                    private float progress = 0.0f;

                    @Override
                    public Report call() throws Exception {
                        
                        Report r = null;
                        try
                        {
                            r = indexURI(file);
                            
                        }
                        catch (Exception e)
                        {
                            System.err.println("ProgressCallable: Do whatever you want.");
                        }
                        
                        progress = 1.0f;

                        return r;
                    }

                    @Override
                    public float getProgress() {
                        return progress;
                    }
                });
        
    }

    @Override
    public Task<Report> index(final Iterable<StorageInputStream> files) {
        
        return new Task<>(
                new ProgressCallable<Report>() {
                    private float progress = 0.0f;

                    @Override
                    public Report call() throws Exception {
                        
                        Report r = new Report();
                        try
                        {
                            for (StorageInputStream f : files)
                            {
                                r = indexURI(f);
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            System.err.println("ProgressCallable (iterator): Do whatever you want.");
                        }
                        
                        progress = 1.0f;
                        
                        // Wait for it, solve the problem. :D 
                        // This is not async (for real)
                        return r;
                    }

                    @Override
                    public float getProgress() {
                        return progress;
                    }
                });
        
    }

    @Override
    public boolean unindex(URI uri) {
        // Not implemented
        return false;
        
    }

    @Override
    public String getName() {
        return "RSIStorageTest";
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
        // FAKE value, implement by yourself. 
        return true;
    }

    @Override
    public void setSettings(ConfigurationHolder settings) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConfigurationHolder getSettings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
