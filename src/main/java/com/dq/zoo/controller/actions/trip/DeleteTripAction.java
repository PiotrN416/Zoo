package com.dq.zoo.controller.actions.trip;

import com.dq.zoo.controller.Action;
import com.dq.zoo.helpers.ConstraintsUtil;
import com.dq.zoo.model.PersistenceManager;
import com.dq.zoo.model.entities.Trip;
import com.dq.zoo.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTripAction extends Action {

    private PersistenceManager persistence;
    private View view;

    @Override
    public String execute() {
        String id = view.getPropertyCancellable("trip identifier");
        String val = view.getPropertyCancellable("identifier value");

        String result = persistence.delete(id, val, Trip.class);

        view.showMessage(result);

        return ConstraintsUtil.OPERATION_SUCCESS_MESSAGE;
    }

    @Override
    public String getName() {
        return "Delete trip";
    }
}
