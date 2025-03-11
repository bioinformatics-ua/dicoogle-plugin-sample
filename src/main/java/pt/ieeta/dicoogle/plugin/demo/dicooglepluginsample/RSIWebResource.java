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

import org.json.JSONException;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Example of a Restlet-based server resource plugin.
 *
 * @author Luís A. Bastião Silva - <bastiao@bmd-software.com>
 */
public class RSIWebResource  extends ServerResource  {
    private static final Logger logger = LoggerFactory.getLogger(RSIWebResource.class);

    @Get
    public Representation test(){
        StringRepresentation sr = new StringRepresentation("{\"name\":\"dicoogle-plugin-sample\"}");

        sr.setMediaType(MediaType.APPLICATION_JSON);

        return sr;
    }

    @Post("json")
    public JsonRepresentation testJson(JsonRepresentation userJson){

        try {
            org.json.JSONObject userObj = userJson.getJsonObject();
            logger.info("id = {} ; name = {}", userObj.getInt("id"), userObj.getString("name"));

            JsonRepresentation sr = new JsonRepresentation("{\"operation\":\"OK\"}");
            sr.setMediaType(MediaType.APPLICATION_JSON);
            return sr;
    
        } catch (JSONException e) {
            logger.error("Could not retrieve JSON object", e);
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST);

            JsonRepresentation sr = new JsonRepresentation("{\"operation\":\"failed\"}");
            sr.setMediaType(MediaType.APPLICATION_JSON);
            return sr;
        }
    }

    // You can handle all crud operations. More information in the Restlet documentation.
    
    @Override
    public String toString(){
        return "dicoogle-test";
    }

}
