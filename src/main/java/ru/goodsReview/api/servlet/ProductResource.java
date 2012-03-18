package ru.goodsReview.api.servlet;
/*
 *  Date: 09.03.12
 *   Time: 13:15
 *   Author: 
 *      Artemij Chugreev 
 *      artemij.chugreev@gmail.com
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("/product/{id}")
public class ProductResource {


//    TODO it's harcode
    public static List<String> thesisByProductId(Integer id) throws SQLException {
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
        String sqlQuery = "SELECT * FROM (thesis JOIN review ON thesis.review_id = review.id) WHERE product_id = "+id;
        ResultSet rs = stmt.executeQuery(sqlQuery);


        List<String> thesises = new ArrayList<String>();
        while (rs.next()) { 
            String thesis = rs.getString("content");
            thesises.add(thesis);
        }
        thesises.add("wtf!");
        rs.close();
        stmt.close();
        return thesises;
    }

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    @Produces("text/plain")
    public String getThesisOnProduct(@PathParam("id") Integer id) {
        // Return some cliched textual content
        StringBuilder sb = new StringBuilder();
        List<String> thesises  =null;
        try {
           thesises = thesisByProductId(id);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        for(String thesis: thesises){
            sb.append(thesis + "\n");
        }
        return "thesises on product " + id + sb.toString();
    }
}
