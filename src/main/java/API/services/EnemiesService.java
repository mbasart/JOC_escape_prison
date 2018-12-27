package API.services;


import API.implement.*;
import API.interfaces.*;

import io.swagger.annotations.Api;

import javax.ws.rs.Path;

@Api(value = "/enemies", description = "Endpoint to Enemies Service")
@Path("/")
public class EnemiesService {

    private IEnemies iEnemies;

    public EnemiesService(){
        this.iEnemies = EnemiesImpl.getInstance();

    }
}
