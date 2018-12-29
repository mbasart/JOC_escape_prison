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

    private ILevel iLevel;

    public LevelService(){
        this.iLevel = LevelImpl.getInstance();

    }

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
}
