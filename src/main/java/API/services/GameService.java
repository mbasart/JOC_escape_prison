package API.services;


import API.implement.*;
import API.interfaces.*;
import API.model.Game;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/game", description = "Endpoint to Game Service")
@Path("/")
public class GameService {

    private Manager manager;

    public GameService(){
        this.manager = ManagerImpl.getInstance();

    }
/*
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
    @Path("/addGame/{isCompleted}/{gameLength}/{healthPoints}/{gameName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEnemies(@PathParam("isCompleted") int isCompleted,@PathParam("gameLength") int gameLength,@PathParam("healthPoints") int healthPoints,@PathParam("gameName") String gameName){
        this.iGame.addGame(isCompleted, gameLength, healthPoints, gameName);
        return Response.status(201).build();
    }*/

    //A partir d'aqui coses pel joc
    @PUT
    @ApiOperation(value = "create a new Game", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 1, message = "Successful"),
            @ApiResponse(code = 2, message = "Error")
    })
    @Path("/newGame/{userName}/{gameName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newGame(@PathParam("userName") String userName, @PathParam("gameName") String gameName){
        int value = this.manager.newGame(userName,gameName);
        if(value == 1)
            return Response.status(1).build();
        else
            return Response.status(2).build();
    }
}
