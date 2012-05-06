package ru.goodsReview.api.servlet;

import org.json.JSONException;
import ru.goodsReview.api.util.FileUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: artemii
 * Date: 22.04.12
 * Time: 20:35
 * To change this template use File | Settings | File Templates.
 */
@Path("getTheses/review")
public class GetThesesServlet {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getTheses() {
        String page404 = "404 PAGE NOT FOUND";

        try {
            return FileUtil.readFileAsString("thesisPage.html");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return page404;
    }
}