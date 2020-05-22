package com.dq.zoo.helpers;


import com.dq.zoo.model.Entity;
import com.dq.zoo.view.View;

import java.util.List;

public class ViewHelper {

    public static void displaySearchResult(String groupName, Entity result, View view) {
        view.showMessage("Found " + groupName + ": " + result);
        displayDetails(result, view);
    }

    public static void displaySearchResults(String groupName, List results, View view) {
        view.showMessage("Found " + groupName + ": " + results.size());

        for (Object result : results) {
            view.showSubMessage(result.toString());
            displayDetails(result, view);
        }
    }

    private static void displayDetails(Object result, View view) {
        if (!(result instanceof Entity) || ((Entity) result).getDetails().size() == 0) {
            return;
        }
        view.showSubMessage("Details:");
        for (String detail : ((Entity) result).getDetails()) {
            view.showSubMessage(detail);
        }
    }
}
