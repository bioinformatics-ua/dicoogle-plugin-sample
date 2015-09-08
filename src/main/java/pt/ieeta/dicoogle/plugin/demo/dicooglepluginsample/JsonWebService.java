
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
