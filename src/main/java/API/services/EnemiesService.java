package API.services;


import API.implement.*;
import API.interfaces.*;

import API.model.Enemies;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ResourceBundle;

@Api(value = "/enemies", description = "Endpoint to Enemies Service")
@Path("/")
public class EnemiesService {

    private IEnemies iEnemies;

    public EnemiesService(){
        this.iEnemies = EnemiesImpl.getInstance();

    }


    @GET
    @ApiOperation(value = "Get enemies", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Enemies.class),
            @ApiResponse(code = 404, message = "Cannot find enemies")
    })
    @Path("/getEnemies/{enemiesId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnemies(@PathParam("enemiesId") int enemiesId) {
        Enemies enemies = iEnemies.getEnemies(enemiesId);
        if (enemies == null) return Response.status(404).build();
        else return Response.status(201).entity(enemies).build();
    }



    @POST
    @ApiOperation(value = "create a new Enemy", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Enemies.class),
    })
    @Path("/addEnemies/{movementType}/{initialPosition}/{healthPoints}/{speed}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEnemies(@PathParam("movementType") String movementType,@PathParam("initialPosition") int initialPosition,@PathParam("healthPoints") int healthPoints,@PathParam("speed") int speed) {
        this.iEnemies.addEnemies(movementType, initialPosition, healthPoints, speed);
        return Response.status(201).build();
    }
}
