package ru.goodsReview.api.servlet;

import org.json.JSONException;
import org.json.JSONObject;
import org.mortbay.util.ajax.JSONObjectConvertor;
import ru.goodsReview.api.util.FileUtil;

import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Artemij Chugreev
 * Date: 07.03.12
 * Time: 2:22
 * email: achugr@yandex-team.ru
 * skype: achugr
 */

@Path("/jsExample")
public class JSExample {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    @Produces(MediaType.TEXT_PLAIN)
    public String jsExample() throws JSONException {
        // Return some cliched textual content
        String page = null;
        try {
            page = FileUtil.readFileAsString("test.js");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return page;
    }


}
