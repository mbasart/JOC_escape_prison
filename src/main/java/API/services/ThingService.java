package API.services;

import API.implement.*;
import API.interfaces.*;

import API.model.Thing;
import io.swagger.annotations.Api;

import javax.ws.rs.Path;

@Api(value = "/thing", description = "Endpoint to Thing Service")
@Path("/")
public class ThingService {

    private IThing iThing;

    public ThingService(){
        this.iThing = ThingImpl.getInstance();

    }
}
