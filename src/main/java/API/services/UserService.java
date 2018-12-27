package API.services;


import API.implement.*;
import API.interfaces.*;
import io.swagger.annotations.Api;

import javax.ws.rs.Path;

@Api(value = "/user", description = "Endpoint to Service Service")
@Path("/")
public class UserService {

    private IUser iUser;

    public UserService(){
        this.iUser = UserImpl.getInstance();

    }
}
