package API.services;


import API.implement.*;
import API.interfaces.*;
import API.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.jaxrs.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/user", description = "Endpoint to Service Service")
@Path("/")
public class UserService {

    //private IUser iUser;
    private Manager manager;

    public UserService(){
        //this.iUser = UserImpl.getInstance();
        this.manager = ManagerImpl.getInstance();

    }

/*
    @GET
    @ApiOperation(value = "Get user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = User.class),
            @ApiResponse(code = 404, message = "Cannot find user")
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
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code= 404, message = "User not found")
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
*/
    //Coses pel user importants!!!!!!!!!!!!!!!!!
    @POST
    @ApiOperation(value = "Login", notes="asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/login/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(@PathParam("userName") String userName, String password){

        try{
            Boolean encontrado = this.manager.login(userName, password);
            if(encontrado == true)
                return Response.status(201).build();
            else
                return Response.status(404).build();
        } catch (Exception e) {
            return Response.status(404).build();
        }

    }

    @PUT
    @ApiOperation(value = "Register", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404,message = "User already exists")
    })
    @Path("/register/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(@PathParam("userName") String userName, String password){
        try{
            Boolean encontrado = this.manager.register(userName,password);
            if(encontrado == true)
                return Response.status(201).build();
            else
                return Response.status(404).build();
        }catch(Exception e){
            return Response.status(404).build();
        }
    }

    @PATCH
    @ApiOperation(value = "Banned",notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "Banned"),
            @ApiResponse(code = 404,message = "Unbanned")
    })
    @Path("/banned/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response banned (@PathParam("userName") String userName){
        try{
            int banned = this.manager.banned(userName);
            if(banned == 1)
                return Response.status(201).build();
            else
                return Response.status(404).build();
        }catch (Exception e){
            return Response.status(404).build();
        }
    }

}
