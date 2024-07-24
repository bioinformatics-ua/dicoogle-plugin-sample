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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.io.DicomInputStream;
import pt.ua.dicoogle.sdk.StorageInputStream;
import pt.ua.dicoogle.sdk.StorageInterface;
import pt.ua.dicoogle.sdk.settings.ConfigurationHolder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.dcm4che2.io.DicomOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Example of a storage plugin.
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class RSIStorage implements StorageInterface {
    private static final Logger logger = LoggerFactory.getLogger(RSIStorage.class);

    private final Map<String, byte[]> mem = new HashMap<>();
    private boolean enabled = true;
    private ConfigurationHolder settings;
    
    @Override
    public String getScheme() {
        return "mem";
    }

    @Override
    public boolean handles(URI location) {
        if (location.toString().contains("mem://"))
            return true;
        return false;
    }

    @Override
    public Iterable<StorageInputStream> at(final URI location, Object... objects) {
        Iterable<StorageInputStream> c = new Iterable<StorageInputStream>() {

            @Override
            public Iterator<StorageInputStream> iterator() {
                StorageInputStream s = new StorageInputStream() {

                    @Override
                    public URI getURI() {
                        return location;
                    }

                    @Override
                    public InputStream getInputStream() throws IOException {
                        byte[] data = mem.get(location.toString());
                        ByteArrayInputStream bin = new ByteArrayInputStream(data);
                        return bin;
                    }

                    @Override
                    public long getSize() throws IOException {
                        return mem.get(location.toString()).length;
                    }
                };
                return Collections.singletonList(s).iterator();
            }
        };
        return c;
    }

    @Override
    public URI store(DicomObject dicomObject, Object... objects) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (DicomOutputStream dos = new DicomOutputStream(bos)) {
            dos.writeDicomFile(dicomObject);
        } catch (IOException ex) {
            logger.warn("Failed to store object", ex);
        }
        URI uri = URI.create("mem://" + UUID.randomUUID().toString());
        mem.put(uri.toString(), bos.toByteArray());

        return uri;
    }

    @Override
    public URI store(DicomInputStream inputStream, Object... objects) throws IOException {
        DicomObject obj = inputStream.readDicomObject();
        return store(obj);
    }

    @Override
    public void remove(URI location) {
        this.mem.remove(location.toString());
    }

    @Override
    public String getName() {
        return "memoryStorage";
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
        return this.settings;
    }

}
