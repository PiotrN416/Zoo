package com.dq.zoo.controller.actions.trip;

import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.helpers.ViewHelper;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Trip;
import com.dq.zoo.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindAllTripsAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        List<String> trips = persistence.findAllAsJson(Trip.class);

        ViewHelper.displaySearchResults("trips", trips, view);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "All Trips";
    }
}
