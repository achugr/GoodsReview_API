package ru.goodsReview.api.servlet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.goodsReview.api.util.FileUtil;
import ru.goodsReview.core.model.Product;
import ru.goodsReview.core.model.Thesis;
import ru.goodsReview.storage.controller.ProductDbController;
import ru.goodsReview.storage.controller.ThesisDbController;

import javax.sql.DataSource;
import javax.swing.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Artemij Chugreev
 * Date: 04.04.12
 * Time: 11:06
 * email: artemij.chugreev@gmail.com
 * skype: achugr
 */

/**
 * return script for view tag cloud
 * it's experimental method, we need insert thesis in JSON in this script source
 */
@Path("/tagCloudFunction")
public class ViewTagCloudFunctionServlet {

    @GET
    @Path("/product/{product_id}")
    @Produces(MediaType.TEXT_HTML)
    public String viewScript(@PathParam("product_id") long productId) throws JSONException {
        // Return some cliched textual content
        String page = null;
        List<Thesis> thesisList = thesisesOnProduct(productId);/*new java.util.LinkedList<Thesis>();*/
        String jsonThesises = thesisesIntoJSON(thesisList, "some product");
        try {
            page = FileUtil.readFileAsString("viewTagCloudFunction.js");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "var data="+ jsonThesises +";\n" + page;
    }
    private static String thesisesIntoJSON(List<Thesis> thesisList, String productName){
        JSONObject thesisMain = new JSONObject();
        JSONObject thesisObject;
        JSONArray jsonArray = new JSONArray();
        for(Thesis thesis : thesisList){
            thesisObject = new JSONObject();
            try {
                thesisObject.put("value", thesis.getContent());
                thesisObject.put("positivity", thesis.getPositivity());
                thesisObject.put("importance", thesis.getImportance());
                thesisObject.put("id", thesis.getId());
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            jsonArray.put(thesisObject);
        }
        try {
            thesisMain.put("thesises", jsonArray);
            thesisMain.put("product_name", productName);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return thesisMain.toString();
    }

    private static List<Thesis> thesisesOnProduct(long productId){
        final FileSystemXmlApplicationContext storageContext = new FileSystemXmlApplicationContext(
                "src/main/resources/database.xml");
        DataSource dataSource = (DataSource) storageContext.getBean("dataSource");
        SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
        ProductDbController productDbController = new ProductDbController(simpleJdbcTemplate);
        ThesisDbController thesisDbController = new ThesisDbController(simpleJdbcTemplate);
        Product product = productDbController.getProductById(productId);
        System.out.println("request thesises on product: #" +productId +" -> "+ product.getName());
        return thesisDbController.getThesesByProductId(productId);
    }


}
