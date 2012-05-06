package ru.goodsReview.api.servlet;

import ru.goodsReview.api.util.YaSearcher;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: achugr
 * Date: 06.05.12
 * Time: 21:33
 * To change this template use File | Settings | File Templates.
 */
@Path("yaXml")
public class YaXmlHttpHandle {

    @GET
    @Path("/{yaXmlRequest}")
    public int frequency(@PathParam("yaXmlRequest") String request) throws FileNotFoundException {
        YaSearcher yaSearcher = new YaSearcher();
        return Integer.parseInt(yaSearcher.sendRequest(request));
    }
}
