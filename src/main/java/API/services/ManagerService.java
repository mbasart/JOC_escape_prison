/*package API.services;

import API.implement.ManagerImpl;
import API.interfaces.Manager;
import API.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.jaxrs.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.reflections.Reflections.log;

@Api(value="/service",description="Endpoint to Manager Service")
@Path("/")
public class ManagerService {
    private Manager manager;

    public ManagerService(){
        this.manager = ManagerImpl.getInstance();

    }

    @GET
    @ApiOperation(value = "Get user",notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code= 404, message = "User not found")
    })
    @Path("/getUser/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("userId") int userId) {
        User user = this.manager.getUser(userId);
        if (user == null) return Response.status(404).build();
        else return Response.status(201).entity(user).build();
    }

    @POST
    @ApiOperation(value = "create a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class)
    })
    @Path("/addUser/{userName}/{password}/{isAdmin}/{money}/{isBanned}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@PathParam("userName") String userName,@PathParam("password") String password,@PathParam("isAdmin") int isAdmin,@PathParam("money") int money,@PathParam("isBanned") int isBanned){
        this.manager.addUser(userName, password, isAdmin, money, isBanned);
        return Response.status(201).build();
    }

    @PATCH
    @ApiOperation(value = "Update user",notes="asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message="Successful", response = User.class)
    })
    @Path("/update/{userId}/{userName}/{password}/{isAdmin}/{money}/{isBanned}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("userId") int userId, @PathParam("userName") String userName,@PathParam("password") String password,@PathParam("isAdmin") int isAdmin,@PathParam("money") int money,@PathParam("isBanned") int isBanned){
        this.manager.updateUser(userId,userName,password,isAdmin,money,isBanned);
        return Response.status(201).build();
    }

}*/
