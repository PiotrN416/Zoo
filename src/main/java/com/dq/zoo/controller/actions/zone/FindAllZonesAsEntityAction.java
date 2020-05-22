package com.dq.zoo.controller.actions.zone;

import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.helpers.ViewHelper;
import com.dq.zoo.model.Entity;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Zone;
import com.dq.zoo.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindAllZonesAsEntityAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        List<Entity> zones = persistence.findAllAsEntity(Zone.class);

        ViewHelper.displaySearchResults("zones", zones, view);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }


    @Override
    public String getName() {
        return "All Zones OBJ";
    }
}
