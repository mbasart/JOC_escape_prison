package API.services;


import API.implement.*;
import API.interfaces.*;
import API.model.Game;
import com.google.gson.Gson;
import io.swagger.annotations.*;
import io.swagger.jaxrs.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/game", description = "Endpoint to Game Service")
@Path("/game")
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
            @ApiResponse(code = 3, message = "Aquest nom pel game ja existeix"),
            @ApiResponse(code = 2, message = "Error")
    })
    @Path("/newGame/{userName}/{gameName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newGame(@PathParam("userName") String userName, @PathParam("gameName") String gameName){
        int value = this.manager.newGame(userName,gameName);
        if(value == 1)
            return Response.status(1).build();
        else if(value == 3)
            return Response.status(3).build();
        else
            return Response.status(2).build();
    }

    @GET
    @ApiOperation(value = "obtain a list of games", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 1, message = "Successful", response = Game.class),
            @ApiResponse(code = 2, message = "Empty"),
            @ApiResponse(code = 3,message = "Error")
    })
    @Path("/llistaGames")
    @Produces(MediaType.APPLICATION_JSON)
    public Response llistaGames (){

        List<Game> allGames = this.manager.loadAllGames();
        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(allGames) {};
        //String json = new Gson().toJson(allGames);
        try {
            if (allGames.size() > 0)
                return Response.status(1).entity(entity).build();
            else
                return Response.status(2).build();
        }catch (Exception e){
            return Response.status(3).build();
        }
    }

    @GET
    @ApiOperation(value = "obtain a list of games of a specific user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 1,message = "Succesful",response = Game.class),
            @ApiResponse(code = 2,message = "No hi ha games"),
            @ApiResponse(code = 3,message = "Error")
    })
    @Path("/gameList/{userName}")
    public Response llistaGamesUser(@PathParam("userName") String userName){
        List<Game> gamesUser = this.manager.loadGamesOfUser(userName);
        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(gamesUser) {};
        //String json = new Gson().toJson(gamesUser);
        try{
            if(gamesUser.size()>0)
                return Response.status(1).entity(entity).build();
            else
                return Response.status(2).build();
        }catch (Exception e) {
            return Response.status(3).build();
        }
    }

    @GET
    @ApiOperation(value = "obtain a game of a specific user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 1, message = "Succesful",response = Game.class),
            @ApiResponse(code = 2, message = "Username no existeix"),
            @ApiResponse(code = 3, message = "Gamename no existeix")
    })
    @Path("/getGame/{userName}/{nameGame}")
    public Response getGameOfUser(@PathParam("userName") String userName,@PathParam("nameGame") String nameGame){
        Game gameUser = this.manager.getGameOfUser(userName,nameGame);
        Boolean checkUser = this.manager.checkUser(userName);
        Boolean checkGame = this.manager.checkGameOfUser(userName,nameGame);


        if(!checkUser)
            return Response.status(2).build();
        else if(!checkGame)
            return Response.status(3).build();
        else
            return Response.status(1).entity(gameUser).build();
    }

    @PATCH
    @ApiOperation(value = "update a game of a specific user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 1,message ="Successful"),
            @ApiResponse(code = 2, message = "Partida acabada"),
            @ApiResponse(code = 3, message = "Username no existeix"),
            @ApiResponse(code = 4, message = "NameGame no existeix")
    })
    @Path("/updateGame/{userName}/{nameGame}/{gameLength}/{healthPoints}")
    public Response updateGame(@PathParam("userName") String userName, @PathParam("nameGame") String nameGame, @PathParam("gameLength") int gameLength, @PathParam("healthPoints") int healthPoints){
        int resultat = this.manager.updateGameOfUser(userName,nameGame,gameLength,healthPoints);

        if(resultat==3)
            return Response.status(3).build();
        else if(resultat==4)
            return Response.status(4).build();
        else if(resultat == 2)
            return Response.status(2).build();
        else
             return Response.status(1).build();
    }
}
