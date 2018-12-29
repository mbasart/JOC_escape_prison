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

@Api(value = "/perk", description = "Endpoint to Perk Service")
@Path("/")
public class PerkService {
    private IPerk iPerk;

    public PerkService(){
        this.iPerk = PerkImpl.getInstance();

    }

    @GET
    @ApiOperation(value = "Get Perk", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Perk.class),
            @ApiResponse(code = 404, message = "Cannot find perk")
    })
    @Path("/getPerk/{perkId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerk(@PathParam("perkId") int perkId) {
        Perk perk =  iPerk.getPerk(perkId);
        if (perk == null) return Response.status(404).build();
        else return Response.status(201).entity(perk).build();
    }



    @POST
    @ApiOperation(value = "create a new Perk", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Perk.class),
    })
    @Path("/addPerk/{perkName}/{usePerk}/{price}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerk(@PathParam("perkName") String perkName,@PathParam("usePerk") String usePerk,@PathParam("price") int price){
        this.iPerk.addPerk(perkName, usePerk, price);
        return Response.status(201).build();
    }
}
