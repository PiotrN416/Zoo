package com.dq.zoo.controller.actions.trip;

import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Trip;
import com.dq.zoo.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateTripAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String id = view.getPropertyCancellable("trip identifier");
        String idVal = view.getPropertyCancellable("identifier value");
        String field = view.getPropertyCancellable("update what");
        String fieldVal = view.getPropertyCancellable("update to");

        String result = persistence.update(id, idVal, field, fieldVal, Trip.class);

        view.showMessage(result);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Update trip";
    }
}
