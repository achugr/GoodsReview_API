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

    @Required
    public void setThesisController(ThesisController thesisController) {
        this.thesisController = thesisController;
    }

    public ThesisController getThesisController() {
        return thesisController;
    }
}
