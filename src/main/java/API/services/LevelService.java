package API.services;

import API.implement.*;
import API.interfaces.*;

import API.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/level", description = "Endpoint to Level Service")
@Path("/")
public class LevelService {

    private Manager manager;

    public LevelService(){
        this.manager = ManagerImpl.getInstance();

    }
/*
    @GET
    @ApiOperation(value = "Get level", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Level.class),
            @ApiResponse(code = 404, message = "Cannot find level")
    })
    @Path("/getLevel/{levelId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLevel(@PathParam("levelId") int levelId) {
        Level level =  iLevel.getLevel(levelId);
        if (level == null) return Response.status(404).build();
        else return Response.status(201).entity(level).build();
    }



    @POST
    @ApiOperation(value = "create a new Level", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Level.class),
    })
    @Path("/addLevel/{map}/{playerPosition}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLevel(@PathParam("map") String map ,@PathParam("playerPosition") int playerPosition ){
        this.iLevel.addLevel(map,playerPosition);
        return Response.status(201).build();
    }
    */

    //A partir d'aqui coses pel joc
    @GET
    @ApiOperation(value = "Obten un nivell a partir d'un game", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 1,message = "Successful",response = Level.class),
            @ApiResponse(code = 2,message = "Partida acabada"),
            @ApiResponse(code = 3,message = "Username no existeix"),
            @ApiResponse(code = 4,message = "Gamename no existeix")
    })
    @Path("/getLevel/{userName}/{nameGame}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLevel(@PathParam("userName") String userName, @PathParam("nameGame") String nameGame){
        Level level = this.manager.getLevelOfGame(nameGame);
        Boolean checkUser = this.manager.checkUser(userName);
        Boolean checkGame = this.manager.checkGameOfUser(userName,nameGame);
        Boolean checkIsCompleted = this.manager.checkPartidaAcabada(nameGame);

        if(!checkUser)
            return Response.status(3).build();
        else if(!checkGame)
            return Response.status(4).build();
        else if(checkIsCompleted)
            return Response.status(2).build();
        else
            return Response.status(1).entity(level).build();
    }
}
