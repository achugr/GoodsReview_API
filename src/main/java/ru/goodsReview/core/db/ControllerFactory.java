package ru.goodsReview.core.db;

/*
 *  Date: 13.11.11
 *  Time: 10:58
 *  Author:
 *     Vanslov Evgeny
 *     vans239@gmail.com
 */

import org.springframework.beans.factory.annotation.Required;
import ru.goodsReview.core.db.controller.*;

public class ControllerFactory {
    private ThesisController thesisController;
    private ProductController productController;
    private ReviewController reviewController;
    private ThesisUniqueController thesisUniqueController;

    @Required
    public void setProductController(ProductController productController) {
        this.productController = productController;
    }

    @Required
    public void setReviewController(ReviewController reviewController) {
        this.reviewController = reviewController;
    }

    @Required
    public void setThesisController(ThesisController thesisController) {
        this.thesisController = thesisController;
    }

    public void setThesisUniqueController(ThesisUniqueController thesisUniqueController) {
        this.thesisUniqueController = thesisUniqueController;
    }

    public ProductController getProductController() {
        return productController;
    }

    public ReviewController getReviewController() {
        return reviewController;
    }

    public ThesisController getThesisController() {
        return thesisController;
    }

    public ThesisUniqueController getThesisUniqueController() {
        return thesisUniqueController;
    }
}
