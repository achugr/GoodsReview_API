package ru.goodsReview.api.util;

import ru.goodsReview.beans.ThesisForView;
import ru.goodsReview.beans.ThesisSetForView;
import ru.goodsReview.core.model.Product;
import ru.goodsReview.core.model.Thesis;

import java.util.LinkedList;
import java.util.List;

/**
 * Artemij Chugreev
 * Date: 15.04.12
 * Time: 14:53
 * email: artemij.chugreev@gmail.com
 * skype: achugr
 */
public class ThesisSetGenerator {
    
    public static ThesisSetForView newInstance(Product product, List<Thesis> thesisList){
        ThesisSetForView thesisSet = new ThesisSetForView();
        thesisSet.setProductName(product.getName());
        List<ThesisForView> thesisForViewList = new LinkedList<ThesisForView>();
        ThesisForView thesisForView;
        for(Thesis thesis : thesisList){
            thesisForView = new ThesisForView();
            thesisForView.setId(thesis.getId());
            thesisForView.setImportance(thesis.getImportance());
            thesisForView.setPositivity(thesis.getPositivity());
            thesisForView.setValue(thesis.getContent());
            thesisForViewList.add(thesisForView);
        }
        thesisSet.getThesises().addAll(thesisForViewList);
        return thesisSet;
    } 
}
