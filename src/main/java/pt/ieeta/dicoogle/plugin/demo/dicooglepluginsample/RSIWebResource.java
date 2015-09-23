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

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/** Example of a Restlet-based server resource plugin.
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class RSIWebResource  extends ServerResource  {
    
    @Get
    public Representation test(){
        StringRepresentation sr = new StringRepresentation("{\"name\":\"rsi\"}");

        sr.setMediaType(MediaType.APPLICATION_JSON);

        return sr;
    }
    
    // You can handle all crud operations. More information in the Restlet documentation.
    
    @Override
    public String toString(){
        return "rsi-test";
    }

}
