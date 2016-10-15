package com.yugi.ctrl;


import com.yugi.pojo.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 2016/4/21.
 */
@Path("/ress")
public class RessCtrl {

    final Logger logger = LogManager.getLogger(this.getClass());

    @Context
    protected HttpServletRequest request;


    @GET
    @Path("/{id}-{name}")
    public Response printMessage(@PathParam("id") Integer id, @PathParam("name") String name) {
        ThreadContext.put("hhh", "xxx");
        logger.info("进入");
        Customer customer = new Customer(id, name);
        return Response.ok().entity(customer).build();
//        return Response.status(200).entity(customer).build();
    }
}
