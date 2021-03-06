package ru.goodsReview.api.servlet;

import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.goodsReview.analyzer.AnalyzeThesis;
import ru.goodsReview.analyzer.ExtractThesis;
import ru.goodsReview.analyzer.wordAnalyzer.MystemAnalyzer;
import ru.goodsReview.api.util.ThesisSetGenerator;
import ru.goodsReview.beans.ThesisSetForView;
import ru.goodsReview.core.model.Product;
import ru.goodsReview.core.model.Review;
import ru.goodsReview.core.model.Thesis;
import ru.goodsReview.storage.controller.ProductDbController;
import ru.goodsReview.storage.controller.ThesisDbController;

import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 06.05.12
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */
@Path("review")
public class ReviewServlet {
//    some problems with logger now :(
//    TODO read documentation and fix this problem
//    private static final Logger log = Logger.getLogger(ThesisServlet.class);

    @POST
    @Consumes({"application/x-www-form-urlencoded;charset='UTF8",MediaType.TEXT_PLAIN})
    @Path("/{reviewContent}")
    @Produces(MediaType.APPLICATION_JSON)
    public ThesisSetForView getThesesOnProduct(@PathParam("reviewContent") String reviewContent) {
        try {
            byte [] utfString = reviewContent.getBytes("UTF8");
            reviewContent = new String(utfString, "UTF8");
            System.out.println("utf8: " + reviewContent);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Review review = new Review(0, reviewContent);
        MystemAnalyzer mystemAnalyzer = new MystemAnalyzer();
        List<Thesis> thesisList;
        System.out.println("review: " + review.getContent());
        try {
            thesisList = ExtractThesis.doExtraction(review, mystemAnalyzer);
            for(Thesis thesis : thesisList){
                System.out.println(thesis.getContent());
            }
        } catch (IOException e) {
            e.printStackTrace();
            thesisList = new LinkedList<Thesis>();
        } catch (InterruptedException e) {
            e.printStackTrace();
            thesisList = new LinkedList<Thesis>();
        }

        return ThesisSetGenerator.newInstance(thesisList);
    }
}