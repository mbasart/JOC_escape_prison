package API.services;


import API.implement.*;
import API.interfaces.*;
import io.swagger.annotations.*;

import javax.ws.rs.*;

@Api(value = "/game", description = "Endpoint to Game Service")
@Path("/")
public class GameService {

    private IGame iGame;

    public GameService(){
        this.iGame = GameImpl.getInstance();

    }
}
