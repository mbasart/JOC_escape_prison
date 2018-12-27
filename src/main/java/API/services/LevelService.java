package API.services;

import API.implement.*;
import API.interfaces.*;

import API.model.Level;
import io.swagger.annotations.Api;

import javax.ws.rs.Path;

@Api(value = "/level", description = "Endpoint to Level Service")
@Path("/")
public class LevelService {

    private ILevel iLevel;

    public LevelService(){
        this.iLevel = LevelImpl.getInstance();

    }
}
