
package pt.ieeta.dicoogle.plugin.demo.dicooglepluginsample;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class RSIWebService  extends ServerResource  {
    
    
    @Get
    public Representation test(){
        StringRepresentation sr = new StringRepresentation("{\"name\":\"rsi\"}");

        sr.setMediaType(MediaType.APPLICATION_JSON);

        return sr;
    }
    
    // You can handle all crud operations. Please, read the restlet documentation
    
    @Override
    public String toString(){return "rsidemo";}

}
