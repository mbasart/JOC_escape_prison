package API.services;

import API.implement.*;
import API.interfaces.*;

import API.model.Perk;
import io.swagger.annotations.Api;

import javax.ws.rs.Path;

@Api(value = "/perk", description = "Endpoint to Perk Service")
@Path("/")
public class PerkService {
    private IPerk iPerk;

    public PerkService(){
        this.iPerk = PerkImpl.getInstance();

    }
}
