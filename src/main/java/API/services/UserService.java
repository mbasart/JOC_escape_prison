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
    @Path("/login/")
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

    @POST
    @ApiOperation(value = "Register", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Respuesta.class),
            //@ApiResponse(code = 2,message = "User already exists"),
            @ApiResponse(code = 404,message = "Error")
    })
    @Path("/register/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(BodyUser bodyUser){
        try{
            Respuesta respuesta;
            String userName = bodyUser.getUserName();
            String password = bodyUser.getPassword();
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
    @Path("/banned/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response banned (String userName){
        Respuesta respuesta;
        //String userName = name;
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
    @Path("/admin/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response admin (String userName){
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

    @POST
    @ApiOperation(value="put username and gamename",notes = "qada")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Respuesta.class),
            //@ApiResponse(code = 2, message = "Empty"),
            @ApiResponse(code = 404,message = "Unsuccessful")
    })
    @Path("/putUserAndGame/{userName}/{gameName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response putUserAndGame (@PathParam("userName") String userName, @PathParam("gameName") String gameName){



        try {
            //if (allUsers.size() > 0)
            //this.gameNameLast.push(gameName);
            //this.nameUserLast.push(userName);
            //if(gameNameLast.size()>=1) {

            this.manager.addUserLast(userName);
            this.manager.addGameLast(gameName);
            //this.userGame.setUser(userName);
            //this.userGame.setGame(gameName);
            if(this.manager.getGameLast().equals(gameName)){
                Respuesta respuesta = new Respuesta(1, "Successful");
                //System.out.print(this.userGame.getGame());
                return Response.status(201).entity(respuesta).build();
            }else{
                Respuesta respuesta = new Respuesta(2, "Error");
                return Response.status(201).entity(respuesta).build();
            }

            //else
            //return Response.status(2).build();
        }catch (Exception e){
            return Response.status(3).build();
        }
    }
    @GET
    @ApiOperation(value="get userName",notes = "qada")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Respuesta.class),
            //@ApiResponse(code = 2, message = "Empty"),
            @ApiResponse(code = 404,message = "Unsuccessful")
    })
    @Path("/getUserNameLast")
    public Response getUserNameLast (){


        try {

            String user = this.manager.getUserLast();
            Respuesta respuesta = new Respuesta(1,user);
            return Response.status(201).entity(respuesta).build();
            //else
            //return Response.status(2).build();
        }catch (Exception e){
            return Response.status(3).build();
        }
    }

    @GET
    @ApiOperation(value="get nameGame",notes = "qada")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Respuesta.class),
            //@ApiResponse(code = 2, message = "Empty"),
            @ApiResponse(code = 404,message = "Unsuccessful")
    })
    @Path("/getGameNameLast")
    public Response getGameNameLast (){


        try {
            String game = this.manager.getGameLast();
            Respuesta respuesta = new Respuesta(1,game);
            System.out.print("Hola:" +this.manager.getGameLast());
            return Response.status(201).entity(respuesta).build();
            //else
            //return Response.status(2).build();
        }catch (Exception e){
            return Response.status(3).build();
        }
    }

}
