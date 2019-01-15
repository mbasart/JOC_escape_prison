package API.services;


import API.implement.*;
import API.interfaces.*;
import API.model.*;
import com.google.gson.Gson;
import io.swagger.annotations.*;
import io.swagger.jaxrs.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/user", description = "Endpoint to Service Service")
@Path("/user")
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
            @ApiResponse(code = 201, message = "Successful",response = Respuesta.class)
            /*@ApiResponse(code = 0, message = "Password incorrect"),
            @ApiResponse(code = 1, message = "User is banned"),
            @ApiResponse(code = 3, message = "User is admin")*/
    })
    @Path("/login/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(BodyUser bodyUser){
    //public Response login(@PathParam("userName") String userName, String password){
        String userName = bodyUser.getUserName();
        String password = bodyUser.getPassword();
        int encontrado = this.manager.login(userName, password);
        Respuesta respuesta;
        if(encontrado == 1) {
            respuesta = new Respuesta(1,"User is banned");
            return Response.status(201).entity(respuesta).build();
        }
        else if(encontrado == 0) {
            respuesta = new Respuesta(0,"Password incorrect");
            return Response.status(201).entity(respuesta).build();
        }
        else if(encontrado == 3) {
            respuesta = new Respuesta(3,"User is admin");
            return Response.status(201).entity(respuesta).build();
        }
        else {
            respuesta = new Respuesta(2,"Successful");
            return Response.status(201).entity(respuesta).build();
        }

    }

    @PUT
    @ApiOperation(value = "Register", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Respuesta.class),
            //@ApiResponse(code = 2,message = "User already exists"),
            @ApiResponse(code = 404,message = "Error")
    })
    @Path("/register/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@PathParam("userName") String userName,String password){
        try{
            Respuesta respuesta;
            Boolean encontrado = this.manager.register(userName,password);

            if(encontrado == true) {
                int prueba = 1;
                respuesta = new Respuesta(1,"Successful");
                return Response.status(201).entity(respuesta).build();
            }
            else {
                int prueba = 2;
                respuesta = new Respuesta(2,"User already exists");
                return Response.status(201).entity(respuesta).build();
            }
        }catch(Exception e){
            return Response.status(404).build();
        }
    }

    @PATCH
    @ApiOperation(value = "Banned",notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "Successful",response = Respuesta.class),
            @ApiResponse(code = 404,message = "Ni banned ni unbanned")
    })
    @Path("/banned/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response banned (@PathParam("userName") String userName){
        Respuesta respuesta;
        try{
            int banned = this.manager.banned(userName);
            if(banned == 1) {
                respuesta = new Respuesta(1, "Successful, now is banned");
                return Response.status(201).entity(respuesta).build();
            }
            else{
                respuesta = new Respuesta(1, "Successful, now is unbanned");
                return Response.status(201).entity(respuesta).build();
            }
        }catch (Exception e){
            return Response.status(404).build();
        }
    }

    @PATCH
    @ApiOperation(value = "Admin",notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "Successful",response = Respuesta.class),
            @ApiResponse(code = 404,message = "Unsuccessful")
    })
    @Path("/admin/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response admin (@PathParam("userName") String userName){
        int admin = this.manager.admin(userName);
        Respuesta respuesta;
        if(admin == 0){
            respuesta = new Respuesta(0,"Successful, now is not Admin");
            return Response.status(201).entity(respuesta).build();}
        else if(admin == 1){
            respuesta = new Respuesta(1,"Successful, now is Admin");
            return Response.status(201).entity(respuesta).build();}
        else{

            return Response.status(404).build();}
    }

    @GET
    @ApiOperation(value = "obtain a list of users", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = User.class),
            //@ApiResponse(code = 2, message = "Empty"),
            @ApiResponse(code = 404,message = "Unsuccessful")
    })
    @Path("/loadUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadUsers (){

        List<User> allUsers = this.manager.loadAllUsers();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(allUsers) {};
        //String json = new Gson().toJson(allUsers);
        try {
            //if (allUsers.size() > 0)
                return Response.status(201).entity(entity).build();
            //else
                //return Response.status(2).build();
        }catch (Exception e){
            return Response.status(3).build();
        }
    }

}
