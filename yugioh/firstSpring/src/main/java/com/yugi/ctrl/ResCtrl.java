package com.yugi.ctrl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 2016/4/21.
 */
@Path("/res")
public class ResCtrl {


    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String msg) {
        String result = "Restful example : " + msg;
        return Response.status(200).entity(result).build();
    }
}
