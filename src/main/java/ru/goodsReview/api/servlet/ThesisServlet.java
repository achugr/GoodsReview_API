package ru.goodsReview.api.servlet;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.mortbay.util.ajax.JSON;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.goodsReview.api.util.FileUtil;
import ru.goodsReview.api.util.ThesisSetGenerator;
import ru.goodsReview.beans.ThesisSetForView;
import ru.goodsReview.core.model.Product;
import ru.goodsReview.core.model.Thesis;
import ru.goodsReview.storage.controller.ProductDbController;
import ru.goodsReview.storage.controller.ThesisDbController;

import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Artemij Chugreev
 * Date: 15.04.12
 * Time: 14:39
 * email: artemij.chugreev@gmail.com
 * skype: achugr
 */

/**
 * Servlet for produce ThesisSetForView on product by ID
 */
@Path("thesis")
public class ThesisServlet {
//    some problems with logger now :(
//    TODO read documentation and fix this problem
//    private static final Logger log = Logger.getLogger(ThesisServlet.class);

    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ThesisSetForView getThesisOnProduct(@PathParam("productId") Long id) {
        final FileSystemXmlApplicationContext storageContext = new FileSystemXmlApplicationContext(
                "src/main/resources/database.xml");
        DataSource dataSource = (DataSource) storageContext.getBean("dataSource");
        SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
        ProductDbController productDbController = new ProductDbController(simpleJdbcTemplate);
        ThesisDbController thesisDbController = new ThesisDbController(simpleJdbcTemplate);
        Product product = productDbController.getProductById(id);
        System.out.println("request thesises on product: #" +id +" -> "+ product.getName());
        List<Thesis> thesisList = thesisDbController.getThesesByProductId(id);
        return ThesisSetGenerator.newInstance(product, thesisList);
    }

}
