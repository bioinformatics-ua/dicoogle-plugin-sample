package pt.ieeta.dicoogle.plugin.demo.dicooglepluginsample;

import java.io.IOException;
import java.net.URI;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.io.DicomInputStream;
import pt.ua.dicoogle.sdk.StorageInputStream;
import pt.ua.dicoogle.sdk.StorageInterface;
import pt.ua.dicoogle.sdk.settings.ConfigurationHolder;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dcm4che2.io.DicomOutputStream;

/**
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class RSIStorage implements StorageInterface {
    Map<String, ByteArrayOutputStream> mem = new HashMap<String, ByteArrayOutputStream>();
    
    @Override
    public String getScheme() {
        return "mem://";
    }

    @Override
    public boolean handles(URI location) {
        if (location.toString().contains("mem://"))
            return true;
        return false;
    }

    @Override
    public Iterable<StorageInputStream> at(final URI location) {
        Iterable<StorageInputStream> c = new Iterable<StorageInputStream>() {

            @Override
            public Iterator<StorageInputStream> iterator() {
                Collection c2 = new ArrayList<StorageInputStream>();
                StorageInputStream s = new StorageInputStream() {

                    @Override
                    public URI getURI() {
                        return location;
                    }

                    @Override
                    public InputStream getInputStream() throws IOException {
                        ByteArrayOutputStream bos = mem.get(location);
                        ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
                        return bin;
                    }

                    @Override
                    public long getSize() throws IOException {
                        return 0 ;
                    }
                };
                c2.add(s);
                return c2.iterator();
            }
        };
        return c;
    }

    @Override
    public URI store(DicomObject dicomObject, Object... objects) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DicomOutputStream dos = new DicomOutputStream(bos);
        try {
            dos.writeDicomFile(dicomObject);
        } catch (IOException ex) {
            Logger.getLogger(RSIStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        bos.toByteArray();
        URI uri = null;
        try {
            uri = new URI("mem://" + UUID.randomUUID().toString());
        } catch (URISyntaxException ex) {
            Logger.getLogger(RSIStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        mem.put(uri.toString(), bos);
        
        
        return uri;
    }

    @Override
    public URI store(DicomInputStream inputStream, Object... objects) throws IOException {

        DicomObject obj = inputStream.readDicomObject();

        return store(obj);
    }

    @Override
    public void remove(URI location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        return "memoryStorage";
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConfigurationHolder getSettings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
