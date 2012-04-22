package ru.goodsReview.api.servlet;
/*
 *  Date: 09.03.12
 *   Time: 13:15
 *   Author: 
 *      Artemij Chugreev 
 *      artemij.chugreev@gmail.com
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mortbay.util.ajax.JSON;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import java.sql.*;
import java.util.*;

@Path("/product/{id}")
public class ProductResource {


//    TODO it's hardcode
    public static JSONArray thesisByProductId(Integer id) throws SQLException, JSONException {
        java.util.Properties prop = new java.util.Properties();
        prop.put("charSet", "utf-8");
        prop.put("user", "root");
        prop.put("password", "root");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/" + "goodsreview_permanent", prop);
        if (conn == null) {
            System.out.println("Нет соединения с БД!");
            System.exit(0);
        }

        Statement stmt = conn.createStatement();
        String sqlQuery = "SELECT * FROM (thesis JOIN review ON thesis.review_id = review.id) WHERE product_id = "+id;//+id;
        
        ResultSet rs = stmt.executeQuery(sqlQuery);
        JSONArray thesisArray = new JSONArray();
        Integer counter = 0;
        while (rs.next()) { 
            String thesis = rs.getString("content");
            Double importance = rs.getDouble("importance");
//            Map<String, String> thesisProperties = new HashMap<String, String>();
//            thesisProperties.put("content", thesis);
//            thesisProperties.put("importance", importance.toString());
            JSONObject thesisObj = new JSONObject();
            thesisObj.put("content", thesis);
            thesisObj.put("importance", importance.toString());

            thesisArray.put(thesisObj);
            counter++;
        }
        rs.close();
        stmt.close();
        return thesisArray;
    }

    // The Java method will process HTTP GET requests
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getThesisOnProduct(@PathParam("id") Integer id) {
        // Return some cliched textual content
        StringBuilder sb = new StringBuilder();
        JSONObject thesises  = null;
        JSONArray thesisArray = null;
        try {
//           thesises = thesisByProductId(id);
            thesisArray = thesisByProductId(id);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if(thesisArray!=null){
            System.out.println(thesisArray.toString());
            return thesisArray.toString();
        }
        return "";
    }
}
