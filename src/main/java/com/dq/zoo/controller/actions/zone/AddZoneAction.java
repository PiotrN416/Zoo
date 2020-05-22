package com.dq.zoo.controller.actions.zone;

import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Zone;
import com.dq.zoo.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddZoneAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        Zone zone = new Zone();

        zone.setName(view.getPropertyCancellable("name"));
        zone.setSpecies(view.getPropertyCancellable("species"));
        zone.setAnimalsCount((int) view.getValidNumberPropertyCancellable("animals count"));
        zone.setFoodType(view.getPropertyCancellable("food type"));

        persistence.create(zone);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Add Zone";
    }
}
