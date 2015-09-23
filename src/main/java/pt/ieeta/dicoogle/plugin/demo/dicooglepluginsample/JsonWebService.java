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
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class JsonWebService extends ServerResource  {
    
    
    @Get
    public Representation testJson(){
        StringRepresentation sr = new StringRepresentation("{\"name\":\"rsi\"}");

        sr.setMediaType(MediaType.APPLICATION_JSON);

        return sr;
    }


    @Post("json")
    public JsonRepresentation testJson(JsonRepresentation userJson){

        org.json.JSONObject userObj = null;
        try {
            userObj = userJson.getJsonObject();
            System.out.println(userObj.getInt("id"));
            System.out.println(userObj.getString("name"));
            //return userObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        
        JsonRepresentation sr = new JsonRepresentation("{\"name\":\"rsi\"}");

        sr.setMediaType(MediaType.APPLICATION_JSON);
        
        return sr;
    }
    
    // You can handle all crud operations. Please, read the restlet documentation
    
    
    
    @Override
    public String toString(){return "jsonDemo";}

}
