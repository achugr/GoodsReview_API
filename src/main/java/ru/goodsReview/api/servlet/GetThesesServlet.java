package ru.goodsReview.api.servlet;

import org.json.JSONException;
import ru.goodsReview.api.util.FileUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;

@Path("getTheses/review")
public class GetThesesServlet {

    @GET
    @Produces("text/html; charset=UTF-8")
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