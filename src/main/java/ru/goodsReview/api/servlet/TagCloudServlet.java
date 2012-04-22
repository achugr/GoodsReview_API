package ru.goodsReview.api.servlet;

import org.json.JSONException;
import ru.goodsReview.api.util.FileUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Artemij Chugreev
 * Date: 15.04.12
 * Time: 14:14
 * email: artemij.chugreev@gmail.com
 * skype: achugr
 */
@Path("tagCloud/product")
public class TagCloudServlet {
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String thesisByProductId(){
        String page = "page not found";
        try {
            return FileUtil.readFileAsString("productPage.html");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return page;
    }
}
