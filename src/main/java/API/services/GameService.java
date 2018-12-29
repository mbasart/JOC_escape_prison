package API.services;


import API.implement.*;
import API.interfaces.*;
import API.model.Enemies;
import API.model.Game;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/game", description = "Endpoint to Game Service")
@Path("/")
public class GameService {

    private IGame iGame;

    public GameService(){
        this.iGame = GameImpl.getInstance();

    }

    @GET
    @ApiOperation(value = "Get game", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Game.class),
            @ApiResponse(code = 404, message = "Cannot find game")
    })
    @Path("/getGame/{gameId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGame(@PathParam("gameId") int gameId) {
        Game game =  iGame.getGame(gameId);
        if (game == null) return Response.status(404).build();
        else return Response.status(201).entity(game).build();
    }



    @POST
    @ApiOperation(value = "create a new Game", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Game.class),
    })
    @Path("/addGame/{isCompleted}/{gameLength}/{healthPoints}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEnemies(@PathParam("isCompleted") int isCompleted,@PathParam("gameLength") int gameLength,@PathParam("healthPoints") int healthPoints){
        this.iGame.addGame(isCompleted, gameLength, healthPoints);
        return Response.status(201).build();
    }
}
