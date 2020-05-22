package com.dq.zoo.controller.actions.zone;

import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.helpers.ViewHelper;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Zone;
import com.dq.zoo.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindAllZonesAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        List<String> zones = persistence.findAllAsJson(Zone.class);

        ViewHelper.displaySearchResults("zones", zones, view);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }


    @Override
    public String getName() {
        return "All Zones";
    }
}
