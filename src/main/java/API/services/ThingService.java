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

@Api(value = "/thing", description = "Endpoint to Thing Service")
@Path("/")
public class ThingService {

    private IThing iThing;

    public ThingService(){
        this.iThing = ThingImpl.getInstance();

    }

    @GET
    @ApiOperation(value = "Get thing", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Thing.class),
            @ApiResponse(code = 404, message = "Cannot find thing")
    })
    @Path("/getThing/{thingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getThing(@PathParam("thingId") int thingId) {
        Thing thing = this.iThing.getThing(thingId);
        if (thing == null) return Response.status(404).build();
        else return Response.status(201).entity(thing).build();
    }



    @POST
    @ApiOperation(value = "create a new Thing", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Thing.class),
    })
    @Path("/addThing/{thingName}/{useThing}/{initialPosition}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEnemies(@PathParam("thingName") String thingName,@PathParam("useThing") String useThing,@PathParam("initialPosition") int initialPosition){
        this.iThing.addThing(thingName,useThing,initialPosition);
        return Response.status(201).build();
    }
}
