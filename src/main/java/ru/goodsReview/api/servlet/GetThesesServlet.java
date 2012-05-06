package ru.goodsReview.api.servlet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: artemii
 * Date: 22.04.12
 * Time: 20:35
 * To change this template use File | Settings | File Templates.
 */
@Path("myServlet")
public class GetThesesServlet {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/sayHello/{name}")
    public String hello(@PathParam("name") String name){
        return "hello, " + name;
    }
}
