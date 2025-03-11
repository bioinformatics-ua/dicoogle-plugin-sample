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

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ua.dicoogle.sdk.QueryInterface;
import pt.ua.dicoogle.sdk.datastructs.SearchResult;
import pt.ua.dicoogle.sdk.settings.ConfigurationHolder;

/** Example of a query provider.
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class RSIQuery implements QueryInterface {
    private static final Logger logger = LoggerFactory.getLogger(RSIQuery.class);

    private boolean enabled;
    private ConfigurationHolder settings;
    private final MemoryDICOMDB memoryDicomDB;
    
    public RSIQuery(MemoryDICOMDB memoryDicomDB) {
        this.memoryDicomDB = memoryDicomDB;
        this.enabled = true;
    }
    
    private SearchResult generateSearchResult()
    {
        
        HashMap<String, Object> map = new HashMap<>();
        map.put("PatientID",UUID.randomUUID().toString() );
        map.put("PatientName",UUID.randomUUID().toString() );
        map.put("SOPInstanceUID",UUID.randomUUID().toString() );
        map.put("SeriesInstanceUID",UUID.randomUUID().toString() );
        map.put("StudyInstanceUID",UUID.randomUUID().toString() );
        map.put("Modality","CT");
        map.put("StudyDate","20150120");
        map.put("SeriesDate","20150120");
        
        SearchResult r = new SearchResult(
                URI.create("file://" + UUID.randomUUID().toString() ), 1, map);
        return r;
    }
    
    
    @Override
    public Iterable<SearchResult> query(String query, Object... parameters) {
        
        List<SearchResult> results =  new ArrayList<>();
        results.add(generateSearchResult());
        results.add(generateSearchResult());
        results.add(generateSearchResult());
        results.add(generateSearchResult());
        results.add(generateSearchResult());
        
        return results;
    }

    @Override
    public String getName() {
        return "dicoogle-plugin-sample";
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
