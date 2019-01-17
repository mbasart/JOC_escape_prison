package API.services;


import API.implement.*;
import API.interfaces.*;
import API.model.Game;
import API.model.Respuesta;
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
    @POST
    @ApiOperation(value = "create a new Game", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Respuesta.class),
            //@ApiResponse(code = 3, message = "Aquest nom pel game ja existeix"),
            @ApiResponse(code = 404, message = "Error")
    })
    @Path("/newGame/{userName}/{gameName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newGame(@PathParam("userName") String userName, @PathParam("gameName") String gameName){
        int value = this.manager.newGame(userName,gameName);
        Respuesta respuesta;
        if(value == 1) {
            respuesta = new Respuesta(1,"Successful");
            return Response.status(201).entity(respuesta).build();
        }
        else if(value == 3) {
            respuesta = new Respuesta(3,"Aquest nom pel game ja existeix");
            return Response.status(201).entity(respuesta).build();
        }
        else {
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "obtain a list of games", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Game.class),
            //@ApiResponse(code = 2, message = "Empty"),
            @ApiResponse(code = 404,message = "Error")
    })
    @Path("/llistaGames")
    @Produces(MediaType.APPLICATION_JSON)
    public Response llistaGames (){

        List<Game> allGames = this.manager.loadAllGames();
        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(allGames) {};
        //String json = new Gson().toJson(allGames);
        try {
            //if (allGames.size() > 0)
                return Response.status(201).entity(entity).build();
            //else
                //return Response.status(2).build();
        }catch (Exception e){
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "obtain a list of games of a specific user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "Succesful",response = Game.class),
            //@ApiResponse(code = 2,message = "No hi ha games"),
            @ApiResponse(code = 404,message = "Error")
    })
    @Path("/gameList/{userName}")
    public Response llistaGamesUser(@PathParam("userName") String userName){
        List<Game> gamesUser = this.manager.loadGamesOfUser(userName);
        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(gamesUser) {};
        //String json = new Gson().toJson(gamesUser);
        try{
            //if(gamesUser.size()>0)
                return Response.status(201).entity(entity).build();
            //else
                //return Response.status(2).build();
        }catch (Exception e) {
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "obtain a game of a specific user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Succesful",response = Game.class),
            @ApiResponse(code = 200, message = "Successful",response = Respuesta.class),
            //@ApiResponse(code = 3, message = "Gamename no existeix")
    })
    @Path("/getGame/{userName}/{nameGame}")
    public Response getGameOfUser(@PathParam("userName") String userName,@PathParam("nameGame") String nameGame){
        Game gameUser = this.manager.getGameOfUser(userName,nameGame);
        Boolean checkUser = this.manager.checkUser(userName);
        Boolean checkGame = this.manager.checkGameOfUser(userName,nameGame);
        Respuesta respuesta;


        if(!checkUser) {
            respuesta = new Respuesta(2,"Username no existeix");
            return Response.status(200).entity(respuesta).build();
        }
        else if(!checkGame) {
            respuesta = new Respuesta(3,"Gamename no existeix");
            return Response.status(200).entity(respuesta).build();
        }
        else {
            return Response.status(201).entity(gameUser).build();
        }
    }

    @PATCH
    @ApiOperation(value = "update a game of a specific user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successful",response = Respuesta.class)
            /*@ApiResponse(code = 1,message ="Successful"),
            @ApiResponse(code = 2, message = "Partida acabada"),
            @ApiResponse(code = 3, message = "Username no existeix"),
            @ApiResponse(code = 4, message = "NameGame no existeix")*/
    })
    @Path("/updateGame/{userName}/{nameGame}/{gameLength}/{healthPoints}")
    public Response updateGame(@PathParam("userName") String userName, @PathParam("nameGame") String nameGame, @PathParam("gameLength") int gameLength, @PathParam("healthPoints") int healthPoints){
        int resultat = this.manager.updateGameOfUser(userName,nameGame,gameLength,healthPoints);
        Respuesta respuesta;
        if(resultat==3) {
            respuesta = new Respuesta(3,"Username no existeix");
            return Response.status(201).entity(respuesta).build();
        }
        else if(resultat==4) {
            respuesta = new Respuesta(4,"NameGame no exiteix");
            return Response.status(201).entity(respuesta).build();
        }
        else if(resultat == 2) {
            respuesta = new Respuesta(2,"Partida acabada");
            return Response.status(201).entity(respuesta).build();
        }
        else {
            respuesta = new Respuesta(1,"Successful");
            return Response.status(201).entity(respuesta).build();
        }
    }
}
